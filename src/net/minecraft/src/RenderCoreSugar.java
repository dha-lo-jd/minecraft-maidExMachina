package net.minecraft.src;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;

import org.lwjgl.opengl.GL11;

public class RenderCoreSugar extends RenderTyped<EntityCoreSugar> {

	public static final Map<Class, Render> entityRenderMap = new HashMap<Class, Render>();

	public RenderCoreSugar() {
		shadowSize = 0F;
	}

	boolean compiled = false;
	private int compiledId;

	@Override
	public void doRenderTyped(EntityCoreSugar entity, double par2, double par4,
			double par6, float par8, float par9) {
		Minecraft mc = ModLoader.getMinecraftInstance();
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glTranslatef((float) par2, (float) par4, (float) par6);

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
		if (!compiled) {
			compiled = true;
			compiledId = GL11.glGenLists(1);
			GL11.glNewList(compiledId, GL11.GL_COMPILE);
			for (EntityMEMBlockWrapper e : entity.renderChild) {
				if (e.isInRangeToRenderVec3DByCore(mc.renderViewEntity
						.getPosition(1))) {
					renderEntity(entity, e, par9);

				}
			}
			GL11.glEndList();
		}

		GL11.glCallList(compiledId);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();

	}

	/**
	 * Will render the specified entity at the specified partial tick time.
	 * Args: entity, partialTickTime
	 */
	public void renderEntity(EntityCoreSugar entity,
			EntityMEMBlockWrapper par1Entity, float par2) {
		Minecraft mc = ModLoader.getMinecraftInstance();
		// double d = par1Entity.posX - mc.renderViewEntity.posX;
		// double d1 = par1Entity.posY - mc.renderViewEntity.posY;
		// double d2 = par1Entity.posZ - mc.renderViewEntity.posZ;
		// float f = par1Entity.rotationYaw;
		Entity viewEntity = mc.renderViewEntity;
		renderEntityLastTick(entity, par1Entity, par2);
		// if (entity.getRegistedInnerEntity(viewEntity) == null) {
		// renderEntityLastTick(entity, par1Entity, par2);
		// } else {
		// renderEntityLatest(entity, par1Entity, par2);
		// }
	}

	protected void renderEntityLatest(EntityCoreSugar entity,
			EntityMEMBlockWrapper par1Entity, float par2) {
		Minecraft mc = ModLoader.getMinecraftInstance();
		// double d = par1Entity.childLastTickPosX
		// + (par1Entity.posX - par1Entity.childLastTickPosX)
		// * (double) par2;
		// double d1 = par1Entity.childLastTickPosY
		// + (par1Entity.posY - par1Entity.childLastTickPosY)
		// * (double) par2;
		// double d2 = par1Entity.childLastTickPosZ
		// + (par1Entity.posZ - par1Entity.childLastTickPosZ)
		// * (double) par2;
		double d = par1Entity.posX;
		double d1 = par1Entity.posY;
		double d2 = par1Entity.posZ;
		Entity viewEntity = mc.renderViewEntity;
		double offX = (viewEntity.posX - viewEntity.lastTickPosX)
				* (double) par2;
		double offY = (viewEntity.posY - viewEntity.lastTickPosY)
				* (double) par2;
		double offZ = (viewEntity.posZ - viewEntity.lastTickPosZ)
				* (double) par2;
		double renderViewX = viewEntity.lastTickPosX + offX;
		double renderViewY;
		if (viewEntity.ridingEntity != null) {
			renderViewY = viewEntity.posY;
		} else {
			renderViewY = viewEntity.lastTickPosY + offY;
		}
		double renderViewZ = viewEntity.lastTickPosZ + offZ;
		// double renderViewX = viewEntity.posX;
		// double renderViewY = viewEntity.posY;
		// double renderViewZ = viewEntity.posZ;
		float f = par1Entity.rotationYaw;
		d = d - renderViewX;
		d1 = d1 - renderViewY;
		d2 = d2 - renderViewZ;
		renderEntityWithPosYaw(par1Entity, d, d1, d2, f, par2);
	}

	protected void renderEntityLastTick(EntityCoreSugar entity,
			EntityMEMBlockWrapper par1Entity, float par2) {
		Minecraft mc = ModLoader.getMinecraftInstance();
		double d = par1Entity.childLastTickPosX
				+ (par1Entity.posX - par1Entity.childLastTickPosX)
				* (double) par2;
		double d1 = par1Entity.childLastTickPosY
				+ (par1Entity.posY - par1Entity.childLastTickPosY)
				* (double) par2;
		double d2 = par1Entity.childLastTickPosZ
				+ (par1Entity.posZ - par1Entity.childLastTickPosZ)
				* (double) par2;
		// float f = par1Entity.prevRotationYaw
		// + (par1Entity.rotationYaw - par1Entity.prevRotationYaw) * par2;
		// double d = par1Entity.posX;
		// double d1 = par1Entity.posY;
		// double d2 = par1Entity.posZ;
		float f = par1Entity.rotationYaw;
		Entity viewEntity = mc.renderViewEntity;
		double offX = (viewEntity.posX - viewEntity.lastTickPosX)
				* (double) par2;
		double offY = (viewEntity.posY - viewEntity.lastTickPosY)
				* (double) par2;
		double offZ = (viewEntity.posZ - viewEntity.lastTickPosZ)
				* (double) par2;
		double renderViewX = viewEntity.lastTickPosX + offX;
		double renderViewY = viewEntity.lastTickPosY + offY;
		double renderViewZ = viewEntity.lastTickPosZ + offZ;
		// double renderViewX = viewEntity.posX;
		// double renderViewY = viewEntity.posY;
		// double renderViewZ = viewEntity.posZ;
		d = d - renderViewX;
		d1 = d1 - renderViewY;
		d2 = d2 - renderViewZ;
		renderEntityWithPosYaw(par1Entity, d, d1, d2, f, par2);
	}

	/**
	 * Renders the specified entity with the passed in position, yaw, and
	 * partialTickTime. Args: entity, x, y, z, yaw, partialTickTime
	 */
	public void renderEntityWithPosYaw(Entity par1Entity, double par2,
			double par4, double par6, float par8, float par9) {
		Render render = getEntityRenderObject(par1Entity);

		if (render != null) {
			render.renderManager = renderManager;
			render.doRender(par1Entity, par2, par4, par6, par8, par9);
		}
	}

	public Render getEntityClassRenderObject(Class par1Class) {
		Render render = entityRenderMap.get(par1Class);

		if (render == null && par1Class != (net.minecraft.src.Entity.class)) {
			render = getEntityClassRenderObject(par1Class.getSuperclass());
			entityRenderMap.put(par1Class, render);
		}

		return render;
	}

	public Render getEntityRenderObject(Entity par1Entity) {
		return getEntityClassRenderObject(par1Entity.getClass());
	}
}
