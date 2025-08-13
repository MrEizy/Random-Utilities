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



        //Items
        add("item.randomut.todh", "Tablet Of Destruction");
        add("tooltip.raut.todh", "§8[Human]");

        add("item.randomut.tode", "Tablet Of Destruction");
        add("tooltip.raut.tode", "§6[Earth]");

        add("item.randomut.todhe", "Tablet Of Destruction");
        add("tooltip.raut.todhe", "§b[Heaven]");

    }
}
