package net.mreizy.randomutilities.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.mreizy.randomutilities.RandomUtilities;
import net.mreizy.randomutilities.items.ModItems;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }


    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TABLET_OF_DESTRUCTION_HUMAN.get())
                .pattern("DND")
                .pattern("DTD")
                .pattern("DDD")
                .define('D', Items.IRON_INGOT)
                .define('N', Items.DIAMOND)
                .define('T', Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE)
                .unlockedBy("has_diamond", has(Items.DIAMOND)).save(recipeOutput, "raut:shaped/human_tablet_of_destruction");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TABLET_OF_DESTRUCTION_EARTH.get())
                .pattern("DND")
                .pattern("DTD")
                .pattern("DDD")
                .define('D', Items.GOLD_INGOT)
                .define('N', Items.EMERALD)
                .define('T', ModItems.TABLET_OF_DESTRUCTION_HUMAN)
                .unlockedBy("has_emerald", has(Items.EMERALD)).save(recipeOutput, "raut:shaped/earth_tablet_of_destruction");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TABLET_OF_DESTRUCTION_HUMAN.get())
                .pattern("DND")
                .pattern("DTD")
                .pattern("DDD")
                .define('D', Items.DIAMOND)
                .define('N', Items.NETHERITE_INGOT)
                .define('T', ModItems.TABLET_OF_DESTRUCTION_EARTH)
                .unlockedBy("has_netherite", has(Items.NETHERITE_INGOT)).save(recipeOutput, "raut:shaped/heaven_tablet_of_destruction");



    }


    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, RandomUtilities.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
