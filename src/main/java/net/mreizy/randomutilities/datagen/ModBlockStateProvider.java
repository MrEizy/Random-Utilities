package net.mreizy.randomutilities.datagen;

import net.minecraft.data.PackOutput;
import net.mreizy.randomutilities.RandomUtilities;
import net.mreizy.randomutilities.blocks.ModBlocks;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, RandomUtilities.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        //simpleBlockWithItem(ModBlocks.JADE_ORE);



    }

    private void simpleBlockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
