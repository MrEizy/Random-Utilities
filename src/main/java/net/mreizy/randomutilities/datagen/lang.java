package net.mreizy.randomutilities.datagen;

import net.minecraft.data.PackOutput;
import net.mreizy.randomutilities.RandomUtilities;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class lang extends LanguageProvider {
    public lang(PackOutput output, String locale) {
        super(output, RandomUtilities.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {

        //Tabs
        add("creativetab.raut.items", "Random Utilities");




        //Blocks
        add("block.randomut.wood_spike", "Wooden Spikes");
        add("block.randomut.copper_spike", "Copper Spikes");
        add("block.randomut.iron_spike", "Iron Spikes");
        add("block.randomut.gold_spike", "Gold Spikes");
        add("block.randomut.diamond_spike", "Diamond Spikes");
        add("block.randomut.netherite_spike", "Netherite Spikes");



        //Items
        add("item.randomut.todh", "Tablet Of Destruction");
        add("tooltip.raut.todh", "§8[Human]");

        add("item.randomut.tode", "Tablet Of Destruction");
        add("tooltip.raut.tode", "§6[Earth]");

        add("item.randomut.todhe", "Tablet Of Destruction");
        add("tooltip.raut.todhe", "§b[Heaven]");

        add("item.randomut.horn_of_holding", "Horn Of Holding");
        add("item.randomut.horn_of_holding_empty", "Horn Of Holding");
        add("item.randomut.horn_of_holding.tooltip_empty", "No Genetical Makeup Inside");
        add("item.randomut.horn_of_holding.tooltip_filled", "Contains the Genetical Makeup of: %s");
        add("item.randomut.horn_of_holding.message.entity_type_denied", "This type of Genetical Makeup is not Allowed inside!");


        add("item.randomut.inventory_magnet", "Magnet");
        add("item.randomut.gold_ring", "Gold Ring");
        add("item.randomut.angel_ring", "Angel Ring");



        //Curios
        add("curios.identifier.angel_ring", "Angel Ring");

    }
}
