package com.simibubi.create.content.contraptions.components.flywheel.engine;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.simibubi.create.AllBlockPartials;
import com.simibubi.create.foundation.tileEntity.renderer.SafeTileEntityRenderer;
import com.simibubi.create.foundation.utility.AngleHelper;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.Direction;

public class EngineRenderer<T extends EngineTileEntity> extends SafeTileEntityRenderer<T> {

	public EngineRenderer(TileEntityRendererDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	protected void renderSafe(T te, float partialTicks, MatrixStack ms, IRenderTypeBuffer buffer, int light,
		int overlay) {
		Block block = te.getBlockState()
			.getBlock();
		if (block instanceof EngineBlock) {
			EngineBlock engineBlock = (EngineBlock) block;
			AllBlockPartials frame = engineBlock.getFrameModel();
			if (frame != null) {
				Direction facing = te.getBlockState()
					.get(EngineBlock.HORIZONTAL_FACING);
				float angle = AngleHelper.rad(AngleHelper.horizontalAngle(facing));
				frame.renderOn(te.getBlockState())
					.rotateCentered(Direction.UP, angle)
					.translate(0, 0, -1)
					.light(WorldRenderer.getLightmapCoordinates(te.getWorld(), te.getBlockState(), te.getPos()))
					.renderInto(ms, buffer.getBuffer(RenderType.getSolid()));
			}
		}
	}

}
