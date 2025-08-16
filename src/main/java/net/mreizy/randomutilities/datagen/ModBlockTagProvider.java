package net.mreizy.randomutilities.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.mreizy.randomutilities.RandomUtilities;
import net.mreizy.randomutilities.blocks.ModBlocks;
import net.mreizy.randomutilities.utils.ModTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, RandomUtilities.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {


        tag(ModTags.Blocks.DESTRUCTIBLE_BLOCKS)
                .add(Blocks.STONE)
                .add(Blocks.GRANITE)
                .add(Blocks.ANDESITE)
                .add(Blocks.DIORITE)
                .add(Blocks.DEEPSLATE)
                .add(Blocks.GRAVEL)
                .add(Blocks.SAND)
                .add(Blocks.TUFF)
                .add(Blocks.DIRT);


        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.WOOD_SPIKE.get());

    }
}
