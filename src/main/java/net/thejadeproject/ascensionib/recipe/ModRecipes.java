package net.thejadeproject.ascensionib.recipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.thejadeproject.ascensionib.AscensionIB;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, AscensionIB.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, AscensionIB.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<PillCauldronLowHumanRecipe>> CAULDRON_LOW_HUMAN_SERIALIZER =
            SERIALIZERS.register("pill_cauldron_low_human", PillCauldronLowHumanRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<PillCauldronLowHumanRecipe>> CAULDRON_LOW_HUMAN_TYPE =
            TYPES.register("pill_cauldron_low_human", () -> new RecipeType<PillCauldronLowHumanRecipe>() {
                @Override
                public String toString() {
                    return "pill_cauldron_low_human";
                }
            });





    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}
