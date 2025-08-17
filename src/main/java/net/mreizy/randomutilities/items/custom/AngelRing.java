package net.mreizy.randomutilities.items.custom;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class AngelRing extends Item implements ICurioItem {

    public AngelRing(Properties properties) {
        super(properties);
    }

    @Override
    public void onEquipFromUse(SlotContext slotContext, ItemStack stack) {
        slotContext.entity().playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
        ICurioItem.super.onEquipFromUse(slotContext, stack);
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        LivingEntity entity = slotContext.entity();
        if (entity instanceof Player player) {
            stopFlight(player);
        }
        ICurioItem.super.onUnequip(slotContext, newStack, stack);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity entity = slotContext.entity();
        if (!(entity instanceof Player player)) {
            return;
        }

        if (player.isCreative() || player.isSpectator()) return;

        startFlight(player);
    }

    private void startFlight(Player player) {
        player.getAbilities().mayfly = true;
        player.onUpdateAbilities();
    }

    private void stopFlight(Player player) {
        player.getAbilities().mayfly = false;
        player.getAbilities().flying = false;
        player.onUpdateAbilities();
    }
}