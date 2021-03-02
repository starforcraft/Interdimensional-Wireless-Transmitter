package com.YTrollman.CreativeWirelessTransmitter.block;

import javax.annotation.Nullable;

import com.YTrollman.CreativeWirelessTransmitter.container.CreativeWirelessTransmitterContainer;
import com.YTrollman.CreativeWirelessTransmitter.registry.ModBlocks;
import com.YTrollman.CreativeWirelessTransmitter.tileentity.CreativeWirelessTransmitterTileEntity;
import com.refinedmods.refinedstorage.block.BlockDirection;
import com.refinedmods.refinedstorage.block.ColoredNetworkBlock;
import com.refinedmods.refinedstorage.container.factory.PositionalTileContainerProvider;
import com.refinedmods.refinedstorage.util.BlockUtils;
import com.refinedmods.refinedstorage.util.NetworkUtils;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class CreativeWirelessTransmitterBlock extends ColoredNetworkBlock {
    private static final VoxelShape SHAPE_DOWN = makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 11.0D, 10.0D);
    private static final VoxelShape SHAPE_UP = makeCuboidShape(6.0D, 5.0D, 6.0D, 10.0D, 16.0D, 10.0D);
    private static final VoxelShape SHAPE_EAST = makeCuboidShape(5.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D);
    private static final VoxelShape SHAPE_WEST = makeCuboidShape(0.0D, 6.0D, 6.0D, 11.0D, 10.0D, 10.0D);
    private static final VoxelShape SHAPE_NORTH = makeCuboidShape(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 11.0D);
    private static final VoxelShape SHAPE_SOUTH = makeCuboidShape(6.0D, 6.0D, 5.0D, 10.0D, 10.0D, 16.0D);
    
    public CreativeWirelessTransmitterBlock() {
        super(BlockUtils.DEFAULT_ROCK_PROPERTIES);
    }

    @Override
    public BlockDirection getDirection() {
        return BlockDirection.ANY;
    }

    @Override
    public boolean hasConnectedState() {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new CreativeWirelessTransmitterTileEntity();
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        switch (state.get(getDirection().getProperty())) {
            case DOWN:
                return SHAPE_DOWN;
            case UP:
                return SHAPE_UP;
            case NORTH:
                return SHAPE_NORTH;
            case SOUTH:
                return SHAPE_SOUTH;
            case WEST:
                return SHAPE_WEST;
            case EAST:
                return SHAPE_EAST;
            default:
                return VoxelShapes.empty();
        }
    }
	
    
    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        ActionResultType result = ModBlocks.CREATIVE_WIRELESS_TRANSMITTER.changeBlockColor(state, player.getHeldItem(hand), world, pos, player);
        if (result != ActionResultType.PASS) {
            return result;
        }

        if (!world.isRemote) {
            return NetworkUtils.attemptModify(world, pos, player, () -> NetworkHooks.openGui(
                (ServerPlayerEntity) player,
                new PositionalTileContainerProvider<CreativeWirelessTransmitterTileEntity>(
                    new TranslationTextComponent("gui.refinedstorage.wireless_transmitter"),
                    (tile, windowId, inventory, p) -> new CreativeWirelessTransmitterContainer(tile, player, windowId),
                    pos
                ),
                pos
            ));
        }

        return ActionResultType.SUCCESS;
    }
}
