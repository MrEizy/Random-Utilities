package net.mreizy.randomutilities.blocks.custom.spikes;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;


public class CopperSpikeBlock extends Block {
    public static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 7, 16);
    public CopperSpikeBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (!level.isClientSide() && entity instanceof LivingEntity) {
            entity.hurt(level.damageSources().generic(), 1F);  // Deals 1 damage every ~0.5 seconds (1.5 per second total)
        }
        super.stepOn(level, pos, state, entity);
    }
}