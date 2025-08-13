package net.mreizy.randomutilities.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.mreizy.randomutilities.blocks.ModBlocks;
import net.mreizy.randomutilities.items.ModItems;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {

    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        //dropSelf(ModBlocks.JADE_BLOCK.get());

    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCK.getEntries().stream().map(Holder::value)::iterator;
    }
}
