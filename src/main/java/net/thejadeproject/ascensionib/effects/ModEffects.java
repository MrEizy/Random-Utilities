package net.thejadeproject.ascensionib.effects;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.thejadeproject.ascensionib.AscensionIB;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECT =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, AscensionIB.MOD_ID);

    public static final Holder<MobEffect> QI_ENHANCED_REGENERATION = MOB_EFFECT.register("qi_enhanced_regeneration",
            () -> new QiEnhancedRegeneration(MobEffectCategory.BENEFICIAL, 0x34e5eb)
    );





    public static void register(IEventBus eventBus) {
        MOB_EFFECT.register(eventBus);
    }
}
