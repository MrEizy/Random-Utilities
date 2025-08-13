package net.thejadeproject.ascensionib.blocks.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.thejadeproject.ascensionib.AscensionIB;
import net.thejadeproject.ascensionib.blocks.ModBlocks;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, AscensionIB.MOD_ID);



    public static final Supplier<BlockEntityType<PillCauldronLowHumanEntity>> PILL_CAULDRON_LOW_HUMAN =
            BLOCK_ENTITIES.register("pill_cauldron_low_human", () -> BlockEntityType.Builder.of(
                    PillCauldronLowHumanEntity::new, ModBlocks.PILL_CAULDRON_HUMAN_LOW.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
