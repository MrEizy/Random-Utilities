/*
 * MIT License
 *
 * Copyright 2025 MrEizy
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT
 * OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
package net.mreizy.randomutilities.items.custom.tools;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.mreizy.randomutilities.items.ModItems;
import net.mreizy.randomutilities.utils.EntityUtil;
import net.mreizy.randomutilities.utils.ModTags;

import java.util.List;

public class HornOfHolding extends Item {
    private static final MapCodec<EntityType<?>> ENTITY_TYPE_FIELD_CODEC = BuiltInRegistries.ENTITY_TYPE.byNameCodec().fieldOf("id");

    public HornOfHolding(Properties properties) {
        super(properties.durability(1)); // Set durability to 1 to enable the durability bar
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        // Always show the durability bar
        return true;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        // Show full bar (13) if entity is stored, empty (0) if not
        return stack.has(DataComponents.ENTITY_DATA) ? 13 : 0;
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        // Add glint effect when holding an entity
        return stack.has(DataComponents.ENTITY_DATA);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getHand() != InteractionHand.MAIN_HAND) {
            return InteractionResult.PASS;
        }

        Player player = context.getPlayer();
        ItemStack itemStack = context.getItemInHand();
        Level level = context.getLevel();
        Direction facing = context.getClickedFace();
        BlockPos pos = context.getClickedPos();
        if (itemStack.has(DataComponents.ENTITY_DATA)) {
            //whenever we have an entity stored we can do nothing but release it
            if (!level.isClientSide) {
                CompoundTag entityData = itemStack.get(DataComponents.ENTITY_DATA).getUnsafe();
                itemStack.remove(DataComponents.ENTITY_DATA); //delete entity from item right away to avoid duplicate in case of unexpected error

                EntityType type = EntityUtil.entityTypeFromNbt(entityData);

                facing = facing == null ? Direction.UP : facing;

                BlockPos spawnPos = pos.immutable();
                if (!level.getBlockState(spawnPos).getCollisionShape(level, spawnPos).isEmpty()) {
                    spawnPos = spawnPos.relative(facing);
                }

                //remove position from tag to allow the entity to spawn where it should be
                entityData.remove("Pos");

                //type.spawn uses the sub-tag EntityTag
                CompoundTag wrapper = new CompoundTag();
                wrapper.put("EntityTag", entityData);

                Entity entity = type.create(level);
                entity.load(entityData);
                entity.absMoveTo(spawnPos.getX() + 0.5, spawnPos.getY(), spawnPos.getZ() + 0.5, 0, 0);
                level.addFreshEntity(entity);
                player.swing(context.getHand());

                // Play sound but don't remove the horn
                level.playSound(null, pos, SoundEvents.GLASS_BREAK, SoundSource.PLAYERS, 1f,
                        1 + 0.5f * player.getRandom().nextFloat());

                player.inventoryMenu.broadcastChanges();
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target,
                                                  InteractionHand hand) {

        if (hand != InteractionHand.MAIN_HAND)
            return InteractionResult.PASS;

        if (!target.isAlive())
            return InteractionResult.PASS;

        //This is called from PlayerEventHandler#onPlayerRightClickEntity, because we need to bypass sitting entities processInteraction
        if (target.level().isClientSide)
            return InteractionResult.PASS;

        //Do not allow players.
        if (target instanceof Player)
            return InteractionResult.FAIL;

        //Already got an entity in there.
        if (stack.has(DataComponents.ENTITY_DATA))
            return InteractionResult.FAIL;

        //do not capture entities on deny lists
        if (target.getType().is(ModTags.Entities.HOH_DENY_LIST) && stack.getItem().equals(ModItems.HORN_OF_HOLDING.get())) {
            player.sendSystemMessage(
                    Component.translatable(this.getDescriptionId() + ".message.entity_type_denied"));
            return InteractionResult.FAIL;
        }

        var entityData = new CompoundTag();
        var id = target.getEncodeId();
        if(id != null)
            entityData.putString("id", id);
        entityData = target.saveWithoutId(entityData);

        //serialize entity
        stack.set(DataComponents.ENTITY_DATA, CustomData.of(entityData));
        //show player swing anim
        player.swing(hand);
        //play ender pearl sound
        player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
        player.setItemInHand(hand, stack); //need to write the item back to hand, otherwise we only modify a copy
        target.remove(Entity.RemovalReason.DISCARDED);
        player.inventoryMenu.broadcastChanges();
        return InteractionResult.SUCCESS;
    }

    @Override
    public String getDescriptionId(ItemStack stack) {
        return stack.has(DataComponents.ENTITY_DATA) ? this.getDescriptionId() :
                this.getDescriptionId() + "_empty";
    }

    protected EntityType<?> getType(ItemStack pStack) {
        CustomData customdata = pStack.getOrDefault(DataComponents.ENTITY_DATA, CustomData.EMPTY);
        return customdata.read(ENTITY_TYPE_FIELD_CODEC).getOrThrow();
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);

        if (pStack.has(DataComponents.ENTITY_DATA)) {
            EntityType<?> type = this.getType(pStack);
            // Use the entity type's translated name in the tooltip
            pTooltipComponents.add(Component.translatable(this.getDescriptionId() + ".tooltip_filled", type.getDescription().getString()));
        } else {
            pTooltipComponents.add(Component.translatable(this.getDescriptionId() + ".tooltip_empty"));
        }
    }
}