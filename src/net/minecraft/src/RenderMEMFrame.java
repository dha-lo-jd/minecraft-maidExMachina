package net.minecraft.src;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.src.RenderQuadVertexHelper.RenderFace;
import net.minecraft.src.obb.IntersectRect;
import net.minecraft.src.obb.OBBCube;

import org.lwjgl.opengl.GL11;

public class RenderMEMFrame extends RenderTyped<EntityMEMFrame> {

	private RenderBlocks renderBlocks;

	public RenderMEMFrame() {
		renderBlocks = new RenderBlocks();
		shadowSize = 0.5F;
	}

	@Override
	public void doRenderTyped(EntityMEMFrame entity, double par2, double par4,
			double par6, float par8, float par9) {

		WorldOneChunk world = entity.getInnerWorld();

		// Block block = Block.blocksList[entity.getBlockID()];
		// if (block == null) {
		// return;
		// }

		world.renderChunkCoordinates = new ChunkCoordinates(entity.innerWorldX,
				entity.innerWorldY, entity.innerWorldZ);

		renderBlocks.blockAccess = world;
		IRenderDrawModeHelper helper = RenderQuadDrawHelper.HELPER;
		if (entity.shouldRenderCheckByCore()) {
			GL11.glPushMatrix();
			// helper.startDrawing();
			drawBox(entity, par2, par4, par6, par9);
			// helper.finishDraw();
			GL11.glPopMatrix();
		} else {
			// GL11.glPushMatrix();
			// GL11.glDisable(GL11.GL_LIGHTING);
			// GL11.glEnable(GL11.GL_BLEND);
			// GL11.glBlendFunc(GL11.GL_ALPHA, GL11.GL_ONE);
			// GL11.glColor4f(1.0F, 1.0F, 0.2F, 0.2F);
			// drawBox(entity, par2, par4, par6, par9);
			// GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			// GL11.glDisable(GL11.GL_BLEND);
			// GL11.glEnable(GL11.GL_TEXTURE_2D);
			// GL11.glEnable(GL11.GL_LIGHTING);
			// GL11.glPopMatrix();

		}

		world.renderChunkCoordinates = null;

	}

	private IRenderDrawModeHelper[] getDrawModeHelpers() {
		IRenderDrawModeHelper helper = RenderQuadDrawHelper.HELPER;
		return new IRenderDrawModeHelper[] { helper, helper, helper, helper,
				helper, helper, };
	}

	private IRenderDrawColorHelper[] getDrawColorHelpers(EntityMEMFrame entity,
			String[] textures) {
		IRenderDrawColorHelper[] helpers = new IRenderDrawColorHelper[6];
		for (int i = 0; i < 6; i++) {
			int[] colors = entity.getColors();
			if (textures[i] != null) {
				helpers[i] = new RenderTextureColorHelper(colors[i], this,
						textures[i]);
			} else {
				helpers[i] = new RenderPaintColorHelper(colors[i]);
			}
		}
		return helpers;
	}

	private String[] getTextures(EntityMEMFrame entity) {
		return entity.getTextures();
	}

	private RenderFace[] getFaces(EntityMEMFrame entity) {
		return entity.getFaces();
	}

	private Map<EntityMEMFrame, RenderBoxVertexHelper> map = new HashMap<EntityMEMFrame, RenderBoxVertexHelper>();

	private RenderBoxVertexHelper getRenderBoxVertexHelper(EntityMEMFrame entity) {
		if (map.containsKey(entity)) {
			return map.get(entity);
		}

		String[] textures = getTextures(entity);
		RenderQuadVertexHelper[] quadVertexHelpers = RenderBoxOptionalQuadVertexHelper
				.createVertexHelpers(getDrawModeHelpers(),
						getDrawColorHelpers(entity, textures), textures);
		RenderFace[] rendering = getFaces(entity);
		for (int i = 0; i < rendering.length; i++) {
			RenderFace b = rendering[i];
			quadVertexHelpers[i].setFace(b);
		}
		RenderBoxVertexHelper vertexHelper = new RenderBoxOptionalQuadVertexHelper(
				quadVertexHelpers, entity.getSelectedBoundingBoxFromPool(),
				entity.posX, entity.posY, entity.posZ);

		map.put(entity, vertexHelper);
		return vertexHelper;
	}

	protected void drawBox(EntityMEMFrame entity, double par2, double par4,
			double par6, float par9) {

		GL11.glPushMatrix();
		// Tessellator tessellator = Tessellator.instance;
		// tessellator.setTranslation((float) entity.innerWorldX
		// - entity.coreEntity.myWorldMyPosX, (float) entity.innerWorldY
		// - entity.coreEntity.myWorldMyPosY, (float) entity.innerWorldZ
		// - entity.coreEntity.myWorldMyPosZ);
		GL11.glTranslatef((float) entity.innerWorldX
				- entity.coreEntity.myWorldMyPosX, (float) entity.innerWorldY
				- entity.coreEntity.myWorldMyPosY, (float) entity.innerWorldZ
				- entity.coreEntity.myWorldMyPosZ);
		// GL11.glTranslatef((float) par2, (float) par4, (float) par6);

		// float f1 = entity.childLastTickRotationYaw
		// + (entity.rotationYaw - entity.childLastTickRotationYaw) * par9;
		// // float f1 = entity.rotationYaw;
		// float f2 = entity.childLastTickRotationPitch
		// + (entity.rotationPitch - entity.childLastTickRotationPitch)
		// * par9;
		// float f3 = entity.childLastTickRotationZ
		// + (entity.rotationZ - entity.childLastTickRotationZ) * par9;
		// GL11.glRotatef(-f1, 0, 1, 0);
		// GL11.glRotatef(-f3, 0, 0, 1);
		// GL11.glRotatef(f2, 1, 0, 0);
		RenderBoxVertexHelper vertexHelper = getRenderBoxVertexHelper(entity);
		vertexHelper.drawVertex();

		// tessellator.setTranslation(0, 0, 0);
		GL11.glPopMatrix();
	}

}
