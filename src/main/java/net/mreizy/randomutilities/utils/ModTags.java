package net.mreizy.randomutilities.utils;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.mreizy.randomutilities.RandomUtilities;
import top.theillusivec4.curios.api.CuriosTags;

public class ModTags {

    public static class Blocks {
        public static final TagKey<Block> DESTRUCTIBLE_BLOCKS = createTag("blocks_destruction");



        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(RandomUtilities.MOD_ID, name));
        }
    }

    public static class Item {





        private static TagKey<net.minecraft.world.item.Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(RandomUtilities.MOD_ID, name));
        }
    }



    public static class Entities {

        public static final TagKey<EntityType<?>> HOH_DENY_LIST = makeEntityTypeTag(ResourceLocation.fromNamespaceAndPath(RandomUtilities.MOD_ID, "horn_of_holding_deny_list"));



        public static TagKey<EntityType<?>> makeEntityTypeTag(String id) {
            return makeEntityTypeTag(ResourceLocation.parse(id));
        }

        public static TagKey<EntityType<?>> makeEntityTypeTag(ResourceLocation id) {
            return TagKey.create(Registries.ENTITY_TYPE, id);
        }
    }
}
