package net.mreizy.randomutilities.items.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.component.DataComponents;

import java.util.List;

public class MagnetItem extends Item {
    public MagnetItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (player.isShiftKeyDown()) {
            boolean isOn = !isOn(stack);
            setOn(stack, isOn);
            if (!level.isClientSide) {
                level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 0.5F, 1.0F);
                Component status = Component.literal(isOn ? "On" : "Off")
                        .withStyle(isOn ? ChatFormatting.GREEN : ChatFormatting.RED);
                player.sendSystemMessage(Component.literal("Magnet = ").append(status));
            }
            return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
        }
        return super.use(level, player, hand);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return isOn(stack) || super.isFoil(stack);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (!level.isClientSide && entity instanceof Player player && isOn(stack)) {
            double radius = 8.0;
            AABB box = player.getBoundingBox().inflate(radius);
            List<Entity> entities = level.getEntities(player, box, e -> (e instanceof ItemEntity || e instanceof ExperienceOrb) && e.isAlive());
            for (Entity e : entities) {
                double distSq = e.distanceToSqr(player);
                if (distSq > 0 && distSq <= radius * radius) {
                    Vec3 playerPos = player.position().add(0, 0.5, 0); // Slightly above feet for better pulling
                    Vec3 dir = playerPos.subtract(e.position()).normalize().scale(0.2); // Adjust speed as needed
                    e.setDeltaMovement(e.getDeltaMovement().add(dir));
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        boolean isOn = isOn(stack);
        Component status = Component.literal(isOn ? "On" : "Off")
                .withStyle(isOn ? ChatFormatting.GREEN : ChatFormatting.RED);
        tooltipComponents.add(Component.literal("Magnet = ").append(status));
    }

    private boolean isOn(ItemStack stack) {
        CustomData data = stack.get(DataComponents.CUSTOM_DATA);
        return data != null && data.copyTag().getBoolean("on");
    }

    private void setOn(ItemStack stack, boolean on) {
        stack.update(DataComponents.CUSTOM_DATA, CustomData.EMPTY, customData -> {
            CompoundTag tag = customData.copyTag();
            tag.putBoolean("on", on);
            return CustomData.of(tag);
        });
    }
}