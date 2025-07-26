package cn.walleo.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class LCZDoor extends DoorBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final EnumProperty<DoorHingeSide> HINGE = BlockStateProperties.DOOR_HINGE;
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;

    public LCZDoor(BlockSetType type, Properties properties) {
        super(type, properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(OPEN, false)
                .setValue(HINGE, DoorHingeSide.LEFT)
                .setValue(POWERED, false)
                .setValue(HALF, DoubleBlockHalf.LOWER));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HALF, FACING, OPEN, HINGE, POWERED);
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        // 根据门的开启状态和方向返回中心对称的碰撞箱
        boolean isOpen = state.getValue(OPEN);
        Direction direction = state.getValue(FACING);

        if (isOpen) {
            // 门打开时的碰撞箱（位于中央）
            return Block.box(6, 0, 6, 10, 16, 10);
        } else {
            // 门关闭时的碰撞箱（根据朝向调整）
            return switch (direction) {
                case NORTH, SOUTH -> Block.box(6, 0, 0, 10, 16, 16);
                default -> Block.box(0, 0, 6, 16, 16, 10);
            };
        }
    }

    @Override
    public void neighborChanged(BlockState state, Level world, @NotNull BlockPos pos, @NotNull Block block, @NotNull BlockPos fromPos, boolean isMoving) {
        boolean isPowered = world.hasNeighborSignal(pos) || world.hasNeighborSignal(pos.relative(
                state.getValue(HALF) == DoubleBlockHalf.LOWER ? Direction.UP : Direction.DOWN));

        if (isPowered != state.getValue(POWERED)) {
            world.setBlock(pos, state.setValue(POWERED, isPowered), 2);

            // 触发动画效果
            if (isPowered && !state.getValue(OPEN)) {
                world.setBlock(pos, state.setValue(OPEN, true), 2);
                world.levelEvent(null, 1001, pos, 0); // 播放开门声音
            } else if (!isPowered && state.getValue(OPEN)) {
                world.setBlock(pos, state.setValue(OPEN, false), 2);
                world.levelEvent(null, 1002, pos, 0); // 播放关门声音
            }
        }
    }
}
