package net.mreizy.randomutilities.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.mreizy.randomutilities.RandomUtilities;
import net.mreizy.randomutilities.items.ModItems;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.Objects;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, RandomUtilities.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //basicItem(ModItems.JADE.get());

        //Tablet Of Destructions
        tablet(ModItems.TABLET_OF_DESTRUCTION_HUMAN.get());
        tablet(ModItems.TABLET_OF_DESTRUCTION_EARTH.get());
        tablet(ModItems.TABLET_OF_DESTRUCTION_HEAVEN.get());
    }




    public ItemModelBuilder tablet(Item item){
        return basicItemWithSharedTexture(item, ResourceLocation.fromNamespaceAndPath(
                RandomUtilities.MOD_ID,
                "tablet_of_destruction"
        ));
    }
    public ItemModelBuilder basicItemWithSharedTexture(Item item,ResourceLocation texture){
        return basicItemWithSharedTexture(
                Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item)),
                texture
        );
    }
    public ItemModelBuilder basicItemWithSharedTexture(ResourceLocation item,ResourceLocation texture) {
        return (this.getBuilder(item.toString())).parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(texture.getNamespace(), "item/" + texture.getPath()));
    }
}
