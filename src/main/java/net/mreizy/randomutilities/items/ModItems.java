package net.mreizy.randomutilities.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.mreizy.randomutilities.RandomUtilities;
import net.mreizy.randomutilities.items.custom.TabletOfDestructionEarth;
import net.mreizy.randomutilities.items.custom.TabletOfDestructionHeaven;
import net.mreizy.randomutilities.items.custom.TabletOfDestructionHuman;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(RandomUtilities.MOD_ID);


    //public static final DeferredItem<Item> JADE = ITEMS.register("jade",
    //        () -> new Item(new Item.Properties()));
    //public static final DeferredItem<Item> REGENERATION_PILL = ITEMS.register("regeneration_pill",
    //        () -> new Item(new Item.Properties().food(ModFoodProperties.REGENERATION_PILL)));


    public static final DeferredItem<Item> TABLET_OF_DESTRUCTION_HUMAN = ITEMS.register("todh",
            () -> new TabletOfDestructionHuman(new Item.Properties()){
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {{
                    tooltipComponents.add(Component.translatable("tooltip.raut.todh"));}
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });
    public static final DeferredItem<Item> TABLET_OF_DESTRUCTION_EARTH = ITEMS.register("tode",
            () -> new TabletOfDestructionEarth(new Item.Properties()){
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {{
                    tooltipComponents.add(Component.translatable("tooltip.raut.tode"));}
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });
    public static final DeferredItem<Item> TABLET_OF_DESTRUCTION_HEAVEN = ITEMS.register("todhe",
            () -> new TabletOfDestructionHeaven(new Item.Properties()){
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {{
                    tooltipComponents.add(Component.translatable("tooltip.raut.todhe"));}
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
