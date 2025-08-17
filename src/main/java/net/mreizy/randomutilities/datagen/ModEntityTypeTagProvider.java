package net.mreizy.randomutilities.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.mreizy.randomutilities.RandomUtilities;
import net.mreizy.randomutilities.utils.ModTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ModEntityTypeTagProvider extends EntityTypeTagsProvider {
    public ModEntityTypeTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(packOutput, lookupProvider, RandomUtilities.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.addCommonTags();
    }

    private void addCommonTags() {
        this.tag(ModTags.Entities.HOH_DENY_LIST).addOptionalTag(Tags.EntityTypes.CAPTURING_NOT_SUPPORTED).addTag(Tags.EntityTypes.BOSSES);

    }
}
