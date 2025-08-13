package net.thejadeproject.ascensionib.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.thejadeproject.ascensionib.effects.ModEffects;

public class ModFoodProperties {
    public static final FoodProperties REGENERATION_PILL = new FoodProperties.Builder().fast().alwaysEdible()
            .effect(() -> new MobEffectInstance(ModEffects.QI_ENHANCED_REGENERATION, 200), 1f).build();
}