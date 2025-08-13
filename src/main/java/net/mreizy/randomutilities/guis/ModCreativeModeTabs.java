package net.mreizy.randomutilities.guis;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.mreizy.randomutilities.RandomUtilities;
import net.mreizy.randomutilities.blocks.ModBlocks;
import net.mreizy.randomutilities.items.ModItems;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RandomUtilities.MOD_ID);

    public static final Supplier<CreativeModeTab> ASCENSION_ITEMS_TAB = CREATIVE_MODE_TAB.register("raut_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.TABLET_OF_DESTRUCTION_HEAVEN.get()))
                    //.withTabsBefore(ResourceLocation.fromNamespaceAndPath(RandomUtilities.MOD_ID, "raut_blocks_tab"))
                    .title(Component.translatable("creativetab.raut.items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.TABLET_OF_DESTRUCTION_HUMAN);
                        output.accept(ModItems.TABLET_OF_DESTRUCTION_EARTH);
                        output.accept(ModItems.TABLET_OF_DESTRUCTION_HEAVEN);
                    }).build());

/*    public static final Supplier<CreativeModeTab> ASCENSION_BLOCKS_TAB = CREATIVE_MODE_TAB.register("ascensionitb_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.JADE_ORE.get()))
                    .title(Component.translatable("creativetab.ascensionitb.blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.JADE_ORE);
                        output.accept(ModBlocks.JADE_BLOCK);
                        output.accept(ModBlocks.PILL_CAULDRON_HUMAN_LOW);


                    }).build());*/

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
