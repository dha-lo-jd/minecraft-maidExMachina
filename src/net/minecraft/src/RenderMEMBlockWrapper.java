package net.minecraft.src;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.src.obb.IntersectRect;
import net.minecraft.src.obb.OBBCube;

import org.lwjgl.opengl.GL11;

public class RenderMEMBlockWrapper extends RenderTyped<EntityMEMBlockWrapper> {
	private RenderBlocks renderBlocks;

	public RenderMEMBlockWrapper() {
		renderBlocks = new RenderBlocks();
		shadowSize = 0.5F;
	}

	@Override
	public void doRenderTyped(EntityMEMBlockWrapper entity, double par2,
			double par4, double par6, float par8, float par9) {

		WorldOneChunk world = entity.getInnerWorld();

		Block block = Block.blocksList[entity.getBlockID()];
		if (block == null) {
			return;
		}

		world.renderChunkCoordinates = new ChunkCoordinates(entity.innerWorldX,
				entity.innerWorldY, entity.innerWorldZ);

		renderBlocks.blockAccess = world;
		if (entity.shouldRenderCheckByCore()) {
			TileEntity tileEntity = entity.getBlockTileEntity();
			renderTileEntity(entity, par2, par4, par6, par8);
			renderNormalBlock(entity, par2, par4, par6, world);
		}
		// Minecraft mc = ModLoader.getMinecraftInstance();
		// if (mc.objectMouseOver != null
		// && mc.objectMouseOver.entityHit == entity) {
		// drawSide(entity, par2, par4, par6);
		// }

		world.renderChunkCoordinates = null;

	}

	public static void drawSide(EntityMEMBlockWrapper entity, double par2,
			double par4, double par6) {
		Minecraft mc = ModLoader.getMinecraftInstance();
		int sideHit = EntityCoordsSupport.getEntityBlockSideHit(entity,
				mc.thePlayer);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) entity.innerWorldX
				- entity.coreEntity.myWorldMyPosX, (float) entity.innerWorldY
				- entity.coreEntity.myWorldMyPosY, (float) entity.innerWorldZ
				- entity.coreEntity.myWorldMyPosZ);
		GL11.glLineWidth(2.0F);
		AxisAlignedBB box = entity.getSelectedBoundingBoxFromPool();
		RenderBoxVertexHelper vertexHelper = new RenderBoxSpecificQuadVertexHelper(
				RenderLineDrawHelper.HELPER, new RenderPaintColorHelper(
						0x000000), box, entity.posX, entity.posY, entity.posZ);
		if (sideHit == 0) {
			sideHit = 1;
		} else if (sideHit == 1) {
			sideHit = 0;
		}
		vertexHelper.noRenderSides.add(0);
		vertexHelper.noRenderSides.add(1);
		vertexHelper.noRenderSides.add(2);
		vertexHelper.noRenderSides.add(3);
		vertexHelper.noRenderSides.add(4);
		vertexHelper.noRenderSides.add(5);
		vertexHelper.noRenderSides.remove(sideHit);
		vertexHelper.drawVertex();
		GL11.glPopMatrix();
	}

	private void renderTileEntity(EntityMEMBlockWrapper entity, double par2,
			double par4, double par6, float par8) {
		WorldOneChunk world = entity.getInnerWorld();
		ChunkCoordinates prevCp = world.renderChunkCoordinates;
		world.renderChunkCoordinates = null;
		boolean prevForceInner = world.isForceInner();
		GL11.glPushMatrix();
		GL11.glTranslatef((float) entity.innerWorldX
				- entity.coreEntity.myWorldMyPosX, (float) entity.innerWorldY
				- entity.coreEntity.myWorldMyPosY, (float) entity.innerWorldZ
				- entity.coreEntity.myWorldMyPosZ);

		TileEntity tileEntity = entity.getBlockTileEntity();

		if (tileEntity != null) {
			int oldTileX = tileEntity.xCoord;
			int oldTileY = tileEntity.yCoord;
			int oldTileZ = tileEntity.zCoord;

			tileEntity.xCoord = entity.innerWorldX;
			tileEntity.yCoord = entity.innerWorldY;
			tileEntity.zCoord = entity.innerWorldZ;
			world.setForceInner(true);
			TileEntityRenderer.instance.renderTileEntityAt(tileEntity, -0.5,
					-0.5, -0.5, par8);
			tileEntity.xCoord = oldTileX;
			tileEntity.yCoord = oldTileY;
			tileEntity.zCoord = oldTileZ;
		}
		GL11.glPopMatrix();
		world.renderChunkCoordinates = prevCp;
		world.setForceInner(prevForceInner);
	}

	private void renderNormalBlock(EntityMEMBlockWrapper entity, double par2,
			double par4, double par6, World world) {

		Block block = Block.blocksList[entity.getBlockID()];

		GL11.glPushMatrix();
		loadTexture("/terrain.png");

		GL11.glTranslatef((float) entity.innerWorldX
				- entity.coreEntity.myWorldMyPosX, (float) entity.innerWorldY
				- entity.coreEntity.myWorldMyPosY, (float) entity.innerWorldZ
				- entity.coreEntity.myWorldMyPosZ);
		if (block == Block.dragonEgg) {
			Tessellator tessellator = Tessellator.instance;
			tessellator.startDrawingQuads();
			tessellator.setTranslation(
					(float) (-MathHelper.floor_double(entity.posX)) - 0.5F,
					(float) (-MathHelper.floor_double(entity.posY)) - 0.5F,
					(float) (-MathHelper.floor_double(entity.posZ)) - 0.5F);
			renderBlocks.renderBlockByRenderType(block,
					MathHelper.floor_double(entity.posX),
					MathHelper.floor_double(entity.posY),
					MathHelper.floor_double(entity.posZ));
			tessellator.setTranslation(0.0D, 0.0D, 0.0D);
			tessellator.draw();
		} else {
			block.setBlockBoundsBasedOnState(renderBlocks.blockAccess,
					MathHelper.floor_double(entity.posX),
					MathHelper.floor_double(entity.posY),
					MathHelper.floor_double(entity.posZ));
			// renderBlocks.renderBlockFallingSand(block, world,
			// MathHelper.floor_double(entity.posX),
			// MathHelper.floor_double(entity.posY),
			// MathHelper.floor_double(entity.posZ));
			// renderBlocks.renderBlockFallingSand(block, world, 0, 0, 0);
			Tessellator tessellator = Tessellator.instance;
			tessellator.startDrawingQuads();
			tessellator.setTranslation(-0.5, -0.5, -0.5);
			renderBlocks.renderBlockAllFaces(block, 0, 0, 0);
			tessellator.setTranslation(0.0D, 0.0D, 0.0D);
			tessellator.draw();
		}

		GL11.glPopMatrix();
	}

}
