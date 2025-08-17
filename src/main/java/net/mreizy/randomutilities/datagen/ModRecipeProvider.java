package net.mreizy.randomutilities.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.mreizy.randomutilities.RandomUtilities;
import net.mreizy.randomutilities.blocks.ModBlocks;
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


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.WOOD_SPIKE.get())
                .pattern(" W ")
                .pattern("WSW")
                .pattern("CCC")
                .define('W', ItemTags.PLANKS)
                .define('S', Items.WOODEN_SWORD)
                .define('C', Blocks.COBBLESTONE)
                .unlockedBy("has_sword", has(Items.WOODEN_SWORD)).save(recipeOutput, "raut:shaped/wood_spike");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COPPER_SPIKE.get())
                .pattern(" W ")
                .pattern("WSW")
                .pattern("CCC")
                .define('W', Items.COPPER_INGOT)
                .define('S', ModBlocks.WOOD_SPIKE)
                .define('C', Blocks.COPPER_BLOCK)
                .unlockedBy("has_wood_spike", has(ModBlocks.WOOD_SPIKE)).save(recipeOutput, "raut:shaped/copper_spike");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.IRON_SPIKE.get())
                .pattern(" W ")
                .pattern("WSW")
                .pattern("CCC")
                .define('W', Items.IRON_INGOT)
                .define('S', ModBlocks.COPPER_SPIKE)
                .define('C', Blocks.IRON_BLOCK)
                .unlockedBy("has_copper_spike", has(ModBlocks.COPPER_SPIKE)).save(recipeOutput, "raut:shaped/iron_spike");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GOLD_SPIKE.get())
                .pattern(" W ")
                .pattern("WSW")
                .pattern("CCC")
                .define('W', Items.GOLD_INGOT)
                .define('S', ModBlocks.IRON_SPIKE)
                .define('C', Blocks.GOLD_BLOCK)
                .unlockedBy("has_iron_spike", has(ModBlocks.IRON_SPIKE)).save(recipeOutput, "raut:shaped/gold_spike");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DIAMOND_SPIKE.get())
                .pattern(" W ")
                .pattern("WSW")
                .pattern("CCC")
                .define('W', Items.DIAMOND)
                .define('S', ModBlocks.GOLD_SPIKE)
                .define('C', Blocks.DIAMOND_BLOCK)
                .unlockedBy("has_gold_spike", has(ModBlocks.GOLD_SPIKE)).save(recipeOutput, "raut:shaped/diamond_spike");

        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                Ingredient.of(ModBlocks.DIAMOND_SPIKE),
                Ingredient.of(Items.NETHERITE_INGOT),
                RecipeCategory.COMBAT, ModBlocks.NETHERITE_SPIKE.get().asItem()
        ).unlocks("has_netherite_upgrade", has(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE)).save(recipeOutput, "raut:smithing/netherite_spike");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INVENTORY_MAGNET.get())
                .pattern("LLL")
                .pattern("LER")
                .pattern("RRR")
                .define('L', Items.LAPIS_LAZULI)
                .define('R', Items.REDSTONE)
                .define('E', Items.ENDER_PEARL)
                .unlockedBy("has_ender_pearl", has(Items.ENDER_PEARL)).save(recipeOutput, "raut:shaped/inventory_magnet");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GOLD_RING.get())
                .pattern("GGG")
                .pattern("G G")
                .pattern("GGG")
                .define('G', Items.GOLD_INGOT)
                .unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT)).save(recipeOutput, "raut:shaped/gold_ring");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.HORN_OF_HOLDING.get())
                .pattern("GSG")
                .pattern("SES")
                .pattern("GSG")
                .define('G', Items.GOLD_INGOT)
                .define('S', Items.STRING)
                .define('E', Items.ENDER_PEARL)
                .unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT)).save(recipeOutput, "raut:shaped/horn_of_holding");

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

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TABLET_OF_DESTRUCTION_HEAVEN.get())
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
