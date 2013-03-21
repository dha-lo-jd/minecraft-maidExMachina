package net.minecraft.src;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.src.obb.IntersectRect;
import net.minecraft.src.obb.IntersectVector;
import net.minecraft.src.obb.OBBCube;
import net.minecraft.src.obb.Point;

import org.lwjgl.opengl.GL11;

public class RenderCoreSugarParts extends RenderTyped<EntityCoreSugarParts> {
	protected static final RenderBoxVertexHelper RENDER_PUNCH_TARGET;

	protected static final RenderBoxVertexHelper RENDER_PUNCH_TARGET_CHARGED_X;

	protected static final RenderBoxVertexHelper RENDER_PUNCH_TARGET_CHARGED_Y;
	protected static final RenderBoxVertexHelper RENDER_PUNCH_TARGET_CHARGED_Z;
	protected static final RenderBoxVertexHelper RENDER_PUNCH_TARGET_CHARGING;
	static {
		{
			AxisAlignedBB box = AxisAlignedBB.getBoundingBox(-0.01, -0.01,
					-0.01, 1.01, 1.01, 1.01);
			RenderBoxVertexHelper vertexHelper = new RenderBoxSpecificQuadVertexHelper(
					RenderLineDrawHelper.HELPER, new RenderPaintColorHelper(
							0x000000), box, 0, 0, 0);
			RENDER_PUNCH_TARGET = vertexHelper;
		}
		{
			AxisAlignedBB box = AxisAlignedBB.getBoundingBox(-0.01, -0.01,
					-0.01, 1.01, 1.01, 1.01);
			RenderBoxVertexHelper vertexHelper = new RenderBoxSpecificQuadVertexHelper(
					RenderQuadDrawHelper.HELPER, new RenderPaintColorHelper(
							0xFF0000), box, 0, 0, 0);
			RENDER_PUNCH_TARGET_CHARGING = vertexHelper;
		}
		{
			AxisAlignedBB box = AxisAlignedBB.getBoundingBox(-0.01, 0.249,
					0.249, 1.01, 0.76, 0.76);
			RenderBoxVertexHelper vertexHelper = new RenderBoxSpecificQuadVertexHelper(
					RenderLineDrawHelper.HELPER, new RenderPaintColorHelper(
							0x000000), box, 0, 0, 0);
			RENDER_PUNCH_TARGET_CHARGED_X = vertexHelper;
		}
		{
			AxisAlignedBB box = AxisAlignedBB.getBoundingBox(0.249, -0.01,
					0.249, 0.76, 1.01, 0.76);
			RenderBoxVertexHelper vertexHelper = new RenderBoxSpecificQuadVertexHelper(
					RenderLineDrawHelper.HELPER, new RenderPaintColorHelper(
							0x000000), box, 0, 0, 0);
			RENDER_PUNCH_TARGET_CHARGED_Y = vertexHelper;
		}
		{
			AxisAlignedBB box = AxisAlignedBB.getBoundingBox(0.249, 0.249,
					-0.01, 0.76, 0.76, 1.01);
			RenderBoxVertexHelper vertexHelper = new RenderBoxSpecificQuadVertexHelper(
					RenderLineDrawHelper.HELPER, new RenderPaintColorHelper(
							0x000000), box, 0, 0, 0);
			RENDER_PUNCH_TARGET_CHARGED_Z = vertexHelper;
		}
	}
	private RenderCoreSugar renderCoreSugar = new RenderCoreSugar();
	Map<EntityCoreSugarParts, Integer> map = new HashMap<EntityCoreSugarParts, Integer>();

	int r = 0;

	public RenderCoreSugarParts() {
		shadowSize = 0F;
	}

	@Override
	public void doRenderTyped(EntityCoreSugarParts entity, double par2,
			double par4, double par6, float par8, float par9) {
		renderCoreSugar.renderManager = renderManager;
		Minecraft mc = ModLoader.getMinecraftInstance();

		boolean blend = GL11.glIsEnabled(GL11.GL_BLEND);
		for (EntityCoreSugarChildParts childParts : entity.parts) {
			renderChild(par9, childParts);
		}
		GL11.glPushMatrix();
		setViewerTransration(entity, par2, par4, par6, par9);

		EntityRotateSupport.fixRotation(entity);
		updateRotation(entity, par9);

		if (!map.containsKey(entity)) {
			int compiledId = GL11.glGenLists(1);
			map.put(entity, compiledId);
		}
		Integer compiledId = map.get(entity);

		updateRenderer(entity, par9, compiledId);
		GL11.glCallList(compiledId);

		renderFocusBlock(entity, par2, par4, par6, mc);
		GL11.glPopMatrix();

		MEMAIPunchStateStatus status = MEMAIPunchStateStatus.getStatus(entity
				.getStateMap());
		if (entity.isCore()) {
			if (status.punchTarget != null
					&& status.punchTarget.typeOfHit == EnumMovingObjectType.TILE) {

				float t = (float) status.charge
						/ MEMAIPunchStateStatus.CHARGE_MAX;

				GL11.glPushMatrix();
				GL11.glDisable(GL11.GL_LIGHTING);

				GL11.glTranslated(status.punchTarget.blockX
						- renderManager.renderPosX, status.punchTarget.blockY
						- renderManager.renderPosY, status.punchTarget.blockZ
						- renderManager.renderPosZ);

				RENDER_PUNCH_TARGET.drawVertex();

				if (status.charge == MEMAIPunchStateStatus.CHARGE_MAX) {
					GL11.glDisable(GL11.GL_BLEND);
					RENDER_PUNCH_TARGET_CHARGED_X.drawVertex();
					RENDER_PUNCH_TARGET_CHARGED_Y.drawVertex();
					RENDER_PUNCH_TARGET_CHARGED_Z.drawVertex();
				}

				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				RenderSupport.GLOBAL_ALPHA = t * 0.25F;
				RENDER_PUNCH_TARGET_CHARGING.drawVertex();

				GL11.glDisable(GL11.GL_BLEND);

				GL11.glEnable(GL11.GL_TEXTURE_2D);
				GL11.glEnable(GL11.GL_LIGHTING);
				GL11.glPopMatrix();
				RenderSupport.GLOBAL_ALPHA = 1;
			}
			if (status.drillTarget != null
					&& status.drillTarget.typeOfHit == EnumMovingObjectType.TILE) {
				GL11.glPushMatrix();
				GL11.glDisable(GL11.GL_LIGHTING);

				GL11.glTranslated(status.drillTarget.blockX
						- renderManager.renderPosX, status.drillTarget.blockY
						- renderManager.renderPosY, status.drillTarget.blockZ
						- renderManager.renderPosZ);

				RENDER_PUNCH_TARGET.drawVertex();

				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				RenderSupport.GLOBAL_ALPHA = 0.25F;
				RENDER_PUNCH_TARGET_CHARGING.drawVertex();

				GL11.glDisable(GL11.GL_BLEND);

				GL11.glEnable(GL11.GL_TEXTURE_2D);
				GL11.glEnable(GL11.GL_LIGHTING);
				GL11.glPopMatrix();
				RenderSupport.GLOBAL_ALPHA = 1;

			}
		}

		test(entity, par9);

		if (blend) {
			GL11.glEnable(GL11.GL_BLEND);
		} else {
			GL11.glDisable(GL11.GL_BLEND);
		}
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_LIGHTING);
	}

	private void test(EntityCoreSugarParts entity, float par9) {
		List l = entity.worldObj.getEntitiesWithinAABB(EntityPlayerSP.class,
				entity.boundingBox.expand(8, 1, 8));

		if (l != null) {
			for (Object object : l) {
				Entity entity1 = (Entity) object;
				if (!(entity1 instanceof EntityMEMBlockWrapper)
						&& !(entity1 instanceof EntityCoreSugar)) {

					GL11.glPushMatrix();
					GL11.glDisable(GL11.GL_LIGHTING);
					GL11.glDisable(GL11.GL_TEXTURE_2D);
					GL11.glDisable(GL11.GL_BLEND);
					GL11.glRotatef(entity1.prevRotationYaw
							+ (entity1.rotationYaw - entity1.prevRotationYaw)
							* par9 + 180F, 0.0F, -1.0F, 0.0F);
					GL11.glRotatef(
							entity1.prevRotationPitch
									+ (entity1.rotationPitch - entity1.prevRotationPitch)
									* par9, -1.0F, 0.0F, 0.0F);
					GL11.glLineWidth(1F);

					float scale = (1 / 1600f) * 32 * 2;
					GL11.glScalef(scale, scale, 1);
					testplbox(par9, entity1, scale);
					testplmove(entity1, scale);

					boolean collide = false;
					EntityMEMBlockWrapper nearE = null;
					double lqN = 0;
					for (EntityMEMBlockWrapper e : entity.renderChild) {
						if (!(entity1 instanceof EntityMEMBlockWrapper)
								&& !(entity1 instanceof EntityCoreSugar)) {

							OBBCube obbA = OBBCube.createOBBCube(e);
							Vec3D pos = OBBCube
									.getPosFromAABB(
											entity1.boundingBox,
											OBBCube.getRadiusFromAABB(entity1.boundingBox));
							if (obbA != null
									&& obbA.isCollisionCircle(0.6, pos)) {
								if (nearE == null) {
									nearE = e;
									lqN = entity.getLength(entity1, e);
								} else {
									double lqE = entity.getLength(entity1, e);
									if (lqE < lqN) {
										nearE = e;
										lqN = lqE;
									}
								}
							}
						}
					}
					if (nearE != null) {
						IntersectRect rect = new IntersectRect(
								nearE.boundingBox);
						// rect.slideToEntity(entity1, 0.15);
						testrectr(entity1, rect);

						Vec3D v3d = Vec3D.createVectorHelper(0, 0, -0.4);
						v3d.rotateAroundY(RotateSupport
								.toRadF(entity1.rotationYaw));

						Point prevPosP = new Point(entity1.prevPosX
								- rect.pos.x, entity1.prevPosZ - rect.pos.y);
						Point posP = new Point(prevPosP.x + v3d.xCoord,
								prevPosP.y + v3d.zCoord);
						{
							GL11.glPushMatrix();
							GL11.glTranslatef((float) -entity1.posX,
									(float) -entity1.posZ, 0);
							GL11.glTranslatef((float) rect.pos.x,
									(float) rect.pos.y, 0);
							testPos(prevPosP);
							testPos(posP);
							GL11.glPushMatrix();
							GL11.glLineWidth(1F);
							Tessellator tessellator = Tessellator.instance;
							tessellator.startDrawing(GL11.GL_LINE_LOOP);
							tessellator.setBrightness(15728640);
							tessellator.setColorRGBA_F(1, 1, 0, 1);
							tessellator.addVertex(prevPosP.x, prevPosP.y, 0);
							tessellator.addVertex(posP.x, posP.y, 0);
							tessellator.draw();
							GL11.glPopMatrix();
							GL11.glPopMatrix();
						}
						prevPosP = rect.getZeroAxisPoint(prevPosP,
								RotateSupport.toRadF(nearE.rotationYaw));
						posP = rect.getZeroAxisPoint(posP,
								RotateSupport.toRadF(nearE.rotationYaw));

						IntersectVector vector = new IntersectVector(prevPosP,
								posP);

						IntersectVector collideVec = null;
						Point collideP = null;
						if (collideP == null && vector.from.y <= 0
								&& vector.to.y > rect.topVec.from.y) {
							collideVec = rect.topVec;
							collideP = rect.getCollisionPoint(vector,
									collideVec);
						}
						if (collideP == null && vector.from.x <= 0
								&& vector.to.x > rect.leftVec.from.x) {
							collideVec = rect.leftVec;
							collideP = rect.getCollisionPoint(vector,
									collideVec);
						}
						if (collideP == null && vector.from.y >= 0
								&& vector.to.y < rect.bottomVec.from.y) {
							collideVec = rect.bottomVec;
							collideP = rect.getCollisionPoint(vector,
									collideVec);
						}
						if (collideP == null && vector.from.x >= 0
								&& vector.to.x < rect.rightVec.from.x) {
							collideVec = rect.rightVec;
							collideP = rect.getCollisionPoint(vector,
									collideVec);
						}
						if (collideP != null && collideVec != null) {
							GL11.glPushMatrix();
							GL11.glTranslatef((float) -entity1.posX,
									(float) -entity1.posZ, 0);
							GL11.glTranslatef((float) rect.pos.x,
									(float) rect.pos.y, 0);
							testPos(collideP);
							GL11.glPopMatrix();
							GL11.glPushMatrix();
							GL11.glLineWidth(2F);
							GL11.glTranslatef((float) -entity1.posX,
									(float) -entity1.posZ, 0);
							GL11.glTranslatef((float) rect.pos.x,
									(float) rect.pos.y, 0);
							Tessellator tessellator = Tessellator.instance;
							tessellator.startDrawing(GL11.GL_LINE_LOOP);
							tessellator.setBrightness(15728640);
							tessellator.setColorRGBA_F(1, 0, 0, 1);
							tessellator.addVertex(collideVec.from.x,
									collideVec.from.y, 0);
							tessellator.addVertex(collideVec.to.x,
									collideVec.to.y, 0);
							tessellator.draw();
							GL11.glPopMatrix();
						}
						Vec3D v3dSlide = rect.getSlidePoint(vector);
						if (v3dSlide != null) {
							v3dSlide.rotateAroundY(RotateSupport
									.toRadF(nearE.rotationYaw));

							v3dSlide = Vec3D
									.createVectorHelper(v3dSlide.xCoord
											+ rect.pos.x - entity1.posX, 0,
											v3dSlide.zCoord + rect.pos.y
													- entity1.posZ);

							GL11.glPushMatrix();
							GL11.glLineWidth(1F);
							Tessellator tessellator = Tessellator.instance;
							tessellator.startDrawing(GL11.GL_LINE_LOOP);
							tessellator.setBrightness(15728640);
							tessellator.setColorRGBA_F(1, 0, 0, 1);
							tessellator.addVertex(0, 0, 0);
							tessellator.addVertex((v3dSlide.xCoord),
									(v3dSlide.zCoord), 0);
							tessellator.draw();
							GL11.glPopMatrix();
						}
					}
					GL11.glPopMatrix();
				}
			}
		}
	}

	private void testrectr(Entity entity1, IntersectRect rect) {
		{
			GL11.glPushMatrix();
			GL11.glTranslatef((float) -entity1.posX, (float) -entity1.posZ, 0);
			GL11.glTranslatef((float) rect.pos.x, (float) rect.pos.y, 0);
			GL11.glLineWidth(1F);
			Tessellator tessellator = Tessellator.instance;
			tessellator.startDrawing(GL11.GL_LINE_LOOP);
			tessellator.setBrightness(15728640);
			tessellator.setColorRGBA_F(1, 0, 0, 1);
			tessellator.addVertex(rect.topVec.from.x, rect.topVec.from.y, 0);
			tessellator.addVertex(rect.topVec.to.x, rect.topVec.to.y, 0);
			tessellator.addVertex(rect.rightVec.to.x, rect.rightVec.to.y, 0);
			tessellator.addVertex(rect.bottomVec.to.x, rect.bottomVec.to.y, 0);
			tessellator.draw();
			GL11.glPopMatrix();
		}
	}

	private void testeslide(float scale, EntityMEMBlockWrapper e,
			IntersectRect rect, Vec3D v3d, IntersectVector vector) {
		Vec3D v3dSlide = rect.getSlidePoint(vector);
		if (v3dSlide != null) {
			// v3dSlide.rotateAroundY(RotateSupport.toRadF(e.rotationYaw));

			GL11.glPushMatrix();
			Tessellator tessellator = Tessellator.instance;
			tessellator.startDrawing(GL11.GL_LINE_LOOP);
			tessellator.setBrightness(15728640);
			tessellator.setColorRGBA_F(1, 0, 0, 1);
			tessellator.addVertex(0, 0, 0);
			tessellator.addVertex((v3dSlide.xCoord), (v3dSlide.zCoord), 0);
			tessellator.draw();
			GL11.glPopMatrix();
		} else {
			GL11.glPushMatrix();
			Tessellator tessellator = Tessellator.instance;
			tessellator.startDrawing(GL11.GL_LINE_LOOP);
			tessellator.setBrightness(15728640);
			tessellator.setColorRGBA_F(1, 1, 0, 1);
			tessellator.addVertex(0, 0, 0);
			tessellator.addVertex(v3d.xCoord, v3d.zCoord, 0);
			tessellator.draw();
			GL11.glPopMatrix();
		}
	}

	private void testPos(Point p) {
		{
			GL11.glPushMatrix();
			GL11.glPointSize(3F);
			Tessellator tessellator = Tessellator.instance;
			tessellator.startDrawing(GL11.GL_POINTS);
			tessellator.setBrightness(15728640);
			tessellator.setColorRGBA_F(1, 0, 0, 1);
			tessellator.addVertex(p.x, p.y, 0);
			tessellator.draw();
			GL11.glPopMatrix();
		}
	}

	private void testebox(float par9, Entity entity1, float scale,
			EntityMEMBlockWrapper e, boolean collide) {
		{
			GL11.glPushMatrix();
			Tessellator tessellator = Tessellator.instance;
			tessellator.startDrawing(GL11.GL_LINE_LOOP);
			tessellator.setBrightness(15728640);
			tessellator.setColorRGBA_F(1, 1, 1, 0.2f);
			double mx = e.boundingBox.minX - entity1.posX;
			double my = e.boundingBox.minZ - entity1.posZ;
			double tx = e.boundingBox.maxX - entity1.posX;
			double ty = e.boundingBox.maxZ - entity1.posZ;
			tessellator.addVertex(mx, -my, 0);
			tessellator.addVertex(tx, -my, 0);
			tessellator.addVertex(tx, -ty, 0);
			tessellator.addVertex(mx, -ty, 0);
			tessellator.draw();
			GL11.glPopMatrix();
		}
	}

	private void testplmove(Entity entity1, float scale) {
		{
			GL11.glPushMatrix();
			Tessellator tessellator = Tessellator.instance;
			tessellator.startDrawing(GL11.GL_LINE_LOOP);
			tessellator.setBrightness(15728640);
			tessellator.setColorRGBA_F(0, 0, 1, 1);
			tessellator.addVertex(0, 0, 0);
			tessellator.addVertex((entity1.prevPosX - entity1.posX),
					-(entity1.prevPosZ - entity1.posZ), 0);
			tessellator.draw();
			GL11.glPopMatrix();
		}
	}

	private void testplbox(float par9, Entity entity1, float scale) {
		{
			GL11.glPushMatrix();
			GL11.glRotatef(entity1.prevRotationYaw
					+ (entity1.rotationYaw - entity1.prevRotationYaw) * par9,
					0.0F, 0.0F, -1.0F);
			Tessellator tessellator = Tessellator.instance;
			tessellator.startDrawing(GL11.GL_LINE_LOOP);
			tessellator.setBrightness(15728640);
			tessellator.setColorRGBA_F(0, 1, 1, 1);
			double mx = entity1.boundingBox.minX - entity1.posX;
			double my = entity1.boundingBox.minZ - entity1.posZ;
			double tx = entity1.boundingBox.maxX - entity1.posX;
			double ty = entity1.boundingBox.maxZ - entity1.posZ;
			tessellator.addVertex(mx, my, 0);
			tessellator.addVertex(tx, my, 0);
			tessellator.addVertex(tx, ty, 0);
			tessellator.addVertex(mx, ty, 0);
			tessellator.draw();
			GL11.glPopMatrix();
		}
	}

	protected void renderChild(float par9, EntityCoreSugarChildParts childParts) {
		double d = childParts.lastTickPosX
				+ (childParts.posX - childParts.lastTickPosX) * (double) par9;
		double d1 = childParts.lastTickPosY
				+ (childParts.posY - childParts.lastTickPosY) * (double) par9;
		double d2 = childParts.lastTickPosZ
				+ (childParts.posZ - childParts.lastTickPosZ) * (double) par9;
		float f = childParts.prevRotationYaw
				+ (childParts.rotationYaw - childParts.prevRotationYaw) * par9;

		doRenderTyped(childParts, d - renderManager.renderPosX, d1
				- renderManager.renderPosY, d2 - renderManager.renderPosZ, f,
				par9);
	}

	protected void renderFocusBlock(EntityCoreSugarParts entity, double par2,
			double par4, double par6, Minecraft mc) {
		if (!entity.isRenderTransparent) {
			if (mc.objectMouseOver != null
					&& mc.objectMouseOver.entityHit != null
					&& mc.objectMouseOver.entityHit instanceof EntityMEMBlockWrapper) {
				EntityMEMBlockWrapper e = (EntityMEMBlockWrapper) mc.objectMouseOver.entityHit;
				if (entity.renderChild.contains(e)
						&& e.isInRangeToRenderVec3DByCore(mc.renderViewEntity
								.getPosition(1))) {
					RenderMEMBlockWrapper.drawSide(e, par2, par4, par6);
				}
			}
		}
	}

	protected void setViewerTransration(EntityCoreSugarParts entity,
			double par2, double par4, double par6, float par9) {
		Minecraft mc = ModLoader.getMinecraftInstance();
		Entity viewEntity = mc.renderViewEntity;

		Vec3D innerViewEntityLastTick = entity.getCore().innerViewEntityLastTick;
		if (innerViewEntityLastTick != null) {
			double tickX = entity.lastTickPosX
					+ (entity.posX - entity.lastTickPosX) * (double) par9;
			double tickY = entity.lastTickPosY
					+ (entity.posY - entity.lastTickPosY) * (double) par9;
			double tickZ = entity.lastTickPosZ
					+ (entity.posZ - entity.lastTickPosZ) * (double) par9;
			double lX = viewEntity.lastTickPosX
					- innerViewEntityLastTick.xCoord;
			double lY = viewEntity.lastTickPosY
					- innerViewEntityLastTick.yCoord;
			double lZ = viewEntity.lastTickPosZ
					- innerViewEntityLastTick.zCoord;
			// double lX = viewEntity.lastTickPosX;
			// double lY = viewEntity.lastTickPosY;
			// double lZ = viewEntity.lastTickPosZ;
			double offX = (viewEntity.posX - lX) * (double) par9;
			double offY = (viewEntity.posY - lY) * (double) par9;
			double offZ = (viewEntity.posZ - lZ) * (double) par9;
			double renderViewX = lX + offX;
			double renderViewY;
			if (viewEntity.ridingEntity != null) {
				renderViewY = viewEntity.posY;
			} else {
				renderViewY = lY + offY;
			}
			double renderViewZ = lZ + offZ;
			// double renderViewX = viewEntity.posX;
			// double renderViewY = viewEntity.posY;
			// double renderViewZ = viewEntity.posZ;
			tickX = tickX - renderViewX;
			tickY = tickY - renderViewY;
			tickZ = tickZ - renderViewZ;
			GL11.glTranslatef((float) tickX, (float) tickY, (float) tickZ);
		} else {
			GL11.glTranslatef((float) par2, (float) par4, (float) par6);
		}
	}

	protected void updateRenderer(EntityCoreSugarParts entity, float par9,
			Integer compiledId) {
		if (entity.renderModify) {
			Minecraft mc = ModLoader.getMinecraftInstance();
			GL11.glNewList(compiledId, GL11.GL_COMPILE);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			if (entity.isRenderTransparent) {
				// GL11.glDisable(GL11.GL_DEPTH_TEST);
				GL11.glEnable(GL11.GL_DEPTH_TEST);
				GL11.glEnable(GL11.GL_BLEND);
				// GL11.glBlendFunc(GL11.GL_SRC_ALPHA,
				// GL11.GL_ONE_MINUS_SRC_ALPHA);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
				RenderSupport.GLOBAL_ALPHA = 0.25F;
			} else {
				GL11.glDisable(GL11.GL_BLEND);
				RenderSupport.GLOBAL_ALPHA = 1F;
			}

			for (EntityMEMBlockWrapper e : entity.renderChild) {
				if (e.isInRangeToRenderVec3DByCore(mc.renderViewEntity
						.getPosition(1))) {
					renderCoreSugar.renderEntity(entity, e, par9);
				}
			}

			if (entity.isRenderTransparent) {

				AxisAlignedBB box = entity.getRenderFrameBox();
				GL11.glEnable(GL11.GL_DEPTH_TEST);
				if (box != null) {
					box = box.expand(0.5, 0.5, 0.5);
					GL11.glPushMatrix();
					GL11.glBlendFunc(GL11.GL_SRC_ALPHA,
							GL11.GL_ONE_MINUS_SRC_ALPHA);
					RenderSupport.GLOBAL_ALPHA = 0.25F;

					GL11.glTranslatef(0, 0, 0);
					GL11.glLineWidth(0.5F);
					RenderBoxVertexHelper vertexHelper = new RenderBoxSpecificQuadVertexHelper(
							RenderLineDrawHelper.HELPER,
							new RenderPaintColorHelper(0x000000), box,
							entity.myWorldMyPosX, entity.myWorldMyPosY,
							entity.myWorldMyPosZ);
					vertexHelper.drawVertex();
					GL11.glPopMatrix();
				}
				GL11.glDisable(GL11.GL_BLEND);

			}
			RenderSupport.GLOBAL_ALPHA = 1F;
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEndList();
			entity.renderModify = false;
		}
	}

	protected void updateRotation(EntityCoreSugarParts entity, float par9) {
		float f1 = entity.prevRotationYaw
				+ (entity.rotationYaw - entity.prevRotationYaw) * par9;
		// float f1 = entity.rotationYaw;
		float f2 = entity.prevRotationPitch
				+ (entity.rotationPitch - entity.prevRotationPitch) * par9;
		float f3 = entity.prevRotationZ
				+ (entity.rotationZ - entity.prevRotationZ) * par9;
		GL11.glRotatef(-f1, 0, 1, 0);
		GL11.glRotatef(-f3, 0, 0, 1);
		GL11.glRotatef(f2, 1, 0, 0);

		MEMAIPunchStateStatus status = MEMAIPunchStateStatus.getStatus(entity
				.getStateMap());
		if (status.rollingRotationAngle != 0
				&& entity instanceof EntityCoreSugarRightArm) {
			GL11.glTranslatef(0.5F, 0, 0.5F);
			GL11.glRotatef(status.rollingRotationAngle, 0, 1, 0);
			GL11.glTranslatef(-0.5F, 0, -0.5F);
			// GL11.glTranslatef(-0.5F, 0, -0.5F);
		}
	}

}
