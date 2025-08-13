package net.thejadeproject.ascensionib.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.thejadeproject.ascensionib.AscensionIB;
import net.thejadeproject.ascensionib.items.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, AscensionIB.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.JADE.get());
        basicItem(ModItems.RAW_JADE.get());
        basicItem(ModItems.REGENERATION_PILL.get());
    }
}
