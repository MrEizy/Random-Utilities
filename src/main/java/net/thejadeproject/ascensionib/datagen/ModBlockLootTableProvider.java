package net.thejadeproject.ascensionib.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.thejadeproject.ascensionib.blocks.ModBlocks;
import net.thejadeproject.ascensionib.items.ModItems;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {

    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.JADE_BLOCK.get());
        dropSelf(ModBlocks.PILL_CAULDRON_HUMAN_LOW.get());
        add(ModBlocks.JADE_ORE.get(),
                block -> createOreDrop(ModBlocks.JADE_ORE.get(), ModItems.RAW_JADE.get()));

    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCK.getEntries().stream().map(Holder::value)::iterator;
    }
}
