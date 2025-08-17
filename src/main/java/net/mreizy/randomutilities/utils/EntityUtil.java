package net.mreizy.randomutilities.utils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

import java.util.Optional;
import java.util.UUID;

public class EntityUtil {

    //region Static Methods

    /**
     * Gets the player by given uuid Call on server only!
     *
     * @param uuid the uuid of the player
     * @return Optional containing the player.
     */
    public static Optional<ServerPlayer> getPlayerByUuiDGlobal(UUID uuid) {
        for (ServerLevel level : ServerLifecycleHooks.getCurrentServer().getAllLevels()) {
            ServerPlayer player = (ServerPlayer) level.getPlayerByUUID(uuid);
            if (player != null)
                return Optional.of(player);
        }
        return Optional.empty();
    }

    /**
     * Gets the entity by given uuid. Call on server only!
     *
     * @param uuid the uuid of the entity
     * @return Optional containing the entity.
     */
    public static Optional<? extends Entity> getEntityByUuiDGlobal(UUID uuid) {
        return getEntityByUuiDGlobal(ServerLifecycleHooks.getCurrentServer(), uuid);
    }

    /**
     * Gets the entity by given uuid. Call on server only!
     *
     * @param uuid the uuid of the entity
     * @return Optional containing the entity.
     */
    public static Optional<? extends Entity> getEntityByUuiDGlobal(MinecraftServer server, UUID uuid) {
        if (uuid != null && server != null) {
            for (ServerLevel level : server.getAllLevels()) {
                Entity entity = level.getEntity(uuid);
                if (entity != null)
                    return Optional.of(entity);
            }
        }
        return Optional.empty();
    }

    public static EntityType<?> entityTypeFromNbt(CompoundTag nbtTagCompound) {
        ResourceLocation typeId = ResourceLocation.parse(nbtTagCompound.getString("id"));
        return BuiltInRegistries.ENTITY_TYPE.get(typeId);
    }
    //endregion Static Methods

}