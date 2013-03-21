package net.minecraft.src;

import net.minecraft.src.RenderQuadVertexHelper.RenderFace;

public class EntityMEMFrame extends EntityMEMBlockWrapper {

	protected int[] colors = new int[6];
	protected String[] textures = new String[6];
	protected RenderFace[] faces = new RenderFace[] { RenderFace.NORMAL,
			RenderFace.NORMAL, RenderFace.NORMAL, RenderFace.NORMAL,
			RenderFace.NORMAL, RenderFace.NORMAL, };

	public EntityMEMFrame(World par1World, int blockID, int innerWorldX,
			int innerWorldY, int innerWorldZ, EntityCoreSugar coreEntity,
			TileEntity myTileEntity) {
		super(par1World, blockID, innerWorldX, innerWorldY, innerWorldZ,
				coreEntity, myTileEntity);
	}

	public int[] getColors() {
		return colors;
	}

	public String[] getTextures() {
		return textures;
	}

	public void setColor(int color, int face) {
		this.colors[face] = color;
	}

	public void setColors(int[] colors) {
		this.colors = colors;
	}

	public void setTexture(String texture, int face) {
		this.textures[face] = texture;
	}

	public void setTextures(String[] textures) {
		this.textures = textures;
	}

	@Override
	protected boolean isUnBreakable() {
		return true;
	}

	@Override
	protected void setDeadMEM() {
	}

	public RenderFace[] getFaces() {
		return faces;
	}

	public void setFace(RenderFace rendering, int face) {
		this.faces[face] = rendering;
	}

	public void setFaces(RenderFace[] faces) {
		this.faces = faces;
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);
		for (int i = 0; i < 6; i++) {
			colors[i] = nbttagcompound.getInteger("color" + i);
		}
		for (int i = 0; i < 6; i++) {
			textures[i] = nbttagcompound.getString("texture" + i);
			textures[i] = textures[i].length() > 0 ? textures[i] : null;
		}
		for (int i = 0; i < 6; i++) {
			RenderFace face = RenderFace.valueOf(nbttagcompound
					.getString("face" + i));
			if (face == null) {
				face = RenderFace.NORMAL;
			}
			faces[i] = face;
		}
	}

	public void updateRenderFace() {
		EntityMEMBlockWrapper block;
		block = coreEntity.getMyWorldBlockWrapper(innerWorldX, innerWorldY + 1,
				innerWorldZ);
		if (block != null && block instanceof EntityMEMFrame) {
			faces[0] = RenderFace.NOTHING;
		}
		block = coreEntity.getMyWorldBlockWrapper(innerWorldX, innerWorldY - 1,
				innerWorldZ);
		if (block != null && block instanceof EntityMEMFrame) {
			faces[1] = RenderFace.NOTHING;
		}
		block = coreEntity.getMyWorldBlockWrapper(innerWorldX, innerWorldY,
				innerWorldZ - 1);
		if (block != null && block instanceof EntityMEMFrame) {
			faces[2] = RenderFace.NOTHING;
		}
		block = coreEntity.getMyWorldBlockWrapper(innerWorldX, innerWorldY,
				innerWorldZ + 1);
		if (block != null && block instanceof EntityMEMFrame) {
			faces[3] = RenderFace.NOTHING;
		}
		block = coreEntity.getMyWorldBlockWrapper(innerWorldX - 1, innerWorldY,
				innerWorldZ);
		if (block != null && block instanceof EntityMEMFrame) {
			faces[4] = RenderFace.NOTHING;
		}
		block = coreEntity.getMyWorldBlockWrapper(innerWorldX + 1, innerWorldY,
				innerWorldZ);
		if (block != null && block instanceof EntityMEMFrame) {
			faces[5] = RenderFace.NOTHING;
		}
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
		for (int i = 0; i < 6; i++) {
			nbttagcompound.setInteger("color" + i, colors[i]);
		}
		for (int i = 0; i < 6; i++) {
			if (textures[i] != null) {
				nbttagcompound.setString("texture" + i, textures[i]);
			}
		}
		for (int i = 0; i < 6; i++) {
			nbttagcompound.setString("face" + i, faces[i].name());
		}
	}

}
