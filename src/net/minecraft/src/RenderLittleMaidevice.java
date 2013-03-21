package net.minecraft.src;

public class RenderLittleMaidevice extends RenderTyped<EntityLittleMaidevice> {

	@Override
	public void doRenderTyped(EntityLittleMaidevice entity, double par2,
			double par4, double par6, float par8, float par9) {
		if (entity.baseMaid == null) {
			return;
		}
		Render render = renderManager.getEntityRenderObject(entity.baseMaid);

		if (render != null) {
			render.doRender(entity, par2, par4, par6, par8, par9);
		}
	}

}
