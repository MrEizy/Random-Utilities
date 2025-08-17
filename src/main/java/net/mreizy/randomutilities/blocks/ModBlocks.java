package net.mreizy.randomutilities.blocks;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.mreizy.randomutilities.RandomUtilities;
import net.mreizy.randomutilities.blocks.custom.spikes.*;
import net.mreizy.randomutilities.items.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCK =
            DeferredRegister.createBlocks(RandomUtilities.MOD_ID);


    public static final DeferredBlock<Block> WOOD_SPIKE = registerBlock("wood_spike",
            () -> new WoodSpikeBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .strength(2.0F)
                    .sound(SoundType.WOOD)
                    .requiresCorrectToolForDrops()
            ));

    public static final DeferredBlock<Block> COPPER_SPIKE = registerBlock("copper_spike",
            () -> new CopperSpikeBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .strength(3.0F)
                    .sound(SoundType.COPPER)
                    .requiresCorrectToolForDrops()
            ));

    public static final DeferredBlock<Block> IRON_SPIKE = registerBlock("iron_spike",
            () -> new IronSpikeBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.RAW_IRON)
                    .strength(3.5F)
                    .sound(SoundType.METAL)
                    .requiresCorrectToolForDrops()
            ));

    public static final DeferredBlock<Block> GOLD_SPIKE = registerBlock("gold_spike",
            () -> new GoldSpikeBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.GOLD)
                    .strength(4.0F)
                    .sound(SoundType.METAL)
                    .requiresCorrectToolForDrops()
            ));

    public static final DeferredBlock<Block> DIAMOND_SPIKE = registerBlock("diamond_spike",
            () -> new DiamondSpikeBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.DIAMOND)
                    .strength(4.5F)
                    .sound(SoundType.METAL)
                    .requiresCorrectToolForDrops()
            ));

    public static final DeferredBlock<Block> NETHERITE_SPIKE = registerBlock("netherite_spike",
            () -> new NetheriteSpikeBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.NETHER)
                    .strength(5.0F)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .requiresCorrectToolForDrops()
            ));

    //public static final DeferredBlock<Block> JADE_ORE = registerBlock("jade_ore",
    //        () -> new DropExperienceBlock(UniformInt.of(2, 4),
    //                BlockBehaviour.Properties.of()
    //                        .strength(4.5f, 3.5f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCK.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCK.register(eventBus);
    }
}
