package net.minecraft.src;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import net.minecraft.client.Minecraft;

public class EntityCoreSugarHead extends EntityCoreSugarChildParts {

	public static final MEMBuilder.IMEMCoreChildPartsFactory FACTORY = new MEMBuilder.IMEMCoreChildPartsFactory() {
		public static final String NAME = "HEAD";

		@Override
		public EntityCoreSugarChildParts createChildParts(
				EntityCoreSugarParts parent, EntityCoreSugarParts core,
				World par1World, NBTTagCompound tag) {
			return new EntityCoreSugarHead(parent, par1World, tag);
		}

		@Override
		public String getName() {
			return NAME;
		}
	};

	private static final Set<ChunkCoordinates> hideBlockPos;
	static {
		Set<ChunkCoordinates> s = new HashSet<ChunkCoordinates>();
		int z = myWorldMyPosZ - 3;
		for (int j = 0; j < 3; j++) {
			int y = myWorldMyPosY - 2 + j;
			for (int i = 0; i < 2; i++) {
				int x = myWorldMyPosX - 2 + i;
				s.add(new ChunkCoordinates(x, y, z));
			}
			for (int i = 0; i < 2; i++) {
				int x = myWorldMyPosX + 2 + i;
				s.add(new ChunkCoordinates(x, y, z));
			}

		}
		hideBlockPos = s;
	}

	public EntityCoreSugarHead(EntityCoreSugarParts parent, World par1World,
			ChunkCoordinates size, Vec3D offset, Vec3D jointPos) {
		super(parent, par1World, size, offset, jointPos, "");
	}

	private EntityCoreSugarHead(EntityCoreSugarParts parent, World par1World,
			NBTTagCompound tag) {
		super(parent, par1World, tag);
	}

	private static AxisAlignedBB getInitBounds() {
		return AxisAlignedBB.getBoundingBox(0, 0, 0, 0, 0, 0);
	}

	private static MEMCollisionBox getInitBounds(AxisAlignedBB src,
			double offsetX, double offsetY, double offsetZ) {
		return new MEMCollisionBox(setBB(getInitBounds(), src),
				Vec3D.createVectorHelper(offsetX, offsetY, offsetZ));
	}

	private static AxisAlignedBB setBB(AxisAlignedBB dist, AxisAlignedBB src) {
		dist.setBB(src);
		return dist;
	}

	{
		getMemai().addEntries(MEMAISupport.getLookingJobSet(this));

		AxisAlignedBB aabb = AxisAlignedBB.getBoundingBoxFromPool(0, 0, 0, 0,
				0, 0);
		double off = 0.5;
		double exp = 3.5;
		double sub = -3;
		double add = 4;
		collisionBoxSet = new HashSet<MEMCollisionBox>(
				Arrays.asList(new MEMCollisionBox[] {
						getInitBounds(aabb.expand(exp, off, exp), off, sub, off),// top
						getInitBounds(aabb.expand(exp, off, exp), off, add, off),// bottom
						getInitBounds(aabb.expand(exp, exp, off), off, off, sub),// front
						getInitBounds(aabb.expand(off, exp, exp), sub, off, off),// west
						getInitBounds(aabb.expand(off, exp, exp), add, off, off),// east

						// back
						getInitBounds(aabb.expand(exp, exp, off), off, off, add),
						getInitBounds(aabb.expand(exp, exp, off), off, off, add),
						getInitBounds(aabb.expand(exp, exp, off), off, off, add),
						getInitBounds(aabb.expand(exp, exp, off), off, off, add),

				}));
	}

	/**
	 * Changes pitch and yaw so that the entity calling the function is facing
	 * the entity provided as an argument.
	 */
	@Override
	public void faceEntity(Entity par1Entity, float par2, float par3) {
		double d = par1Entity.posX - parent.posX;
		double d2 = par1Entity.posZ - parent.posZ;
		double d1;

		if (par1Entity instanceof EntityLiving) {
			EntityLiving entityliving = (EntityLiving) par1Entity;
			d1 = (parent.posY + (double) getEyeHeight())
					- (entityliving.posY + (double) entityliving.getEyeHeight());
		} else {
			d1 = (par1Entity.boundingBox.minY + par1Entity.boundingBox.maxY)
					/ 2D - (parent.posY + (double) getEyeHeight());
		}

		double d3 = MathHelper.sqrt_double(d * d + d2 * d2);
		float f = (float) ((Math.atan2(d2, d) * 180D) / Math.PI) + 90F;
		float f1 = (float) (-((Math.atan2(d1, d3) * 180D) / Math.PI));
		float pitch = f1 - parent.rotationPitch;
		float yaw = f - parent.rotationYaw;

		boolean rFlag = true;
		while (pitch > 180) {
			pitch -= 360;
		}
		while (pitch < -180) {
			pitch += 360;
		}
		if (-50 > pitch) {
			rFlag = false;
		}
		if (pitch > 40) {
			rFlag = false;
		}
		while (yaw > 180) {
			yaw -= 360;
		}
		while (yaw < -180) {
			yaw += 360;
		}
		if (-50 > yaw) {
			rFlag = false;
		}
		if (yaw > 50) {
			rFlag = false;
		}
		if (rFlag) {
			rotationPitchOffset = -updateRotation(rotationPitch
					- parent.rotationPitch, pitch, par3);
			rotationYawOffset = -updateRotation(rotationYaw
					- parent.rotationYaw, yaw, par2);
		} else {
			rotationPitchOffset = updateRotation(rotationPitchOffset, 0, 10F);
			rotationYawOffset = updateRotation(rotationYawOffset, 0, 10F);
		}
	}

	@Override
	public float getEyeHeight() {
		return 6;
	}

	@Override
	public String getPartsNameInTag() {
		return FACTORY.getName();
	}

	@Override
	public boolean isEntityInCore(Entity entity) {
		return !(entity.posX < posX - myWorldMyPosX - 1 + minX
				|| posX - myWorldMyPosX + 1 + maxX < entity.posX
				|| entity.posY < posY - myWorldMyPosY + minY
				|| posY - myWorldMyPosY + maxY < entity.posY
				|| entity.posZ < posZ - myWorldMyPosZ - 1 + minZ || posZ
				- myWorldMyPosZ + 1 + maxZ < entity.posZ);
	}

	@Override
	public boolean shouldRenderMEMBlock(EntityMEMBlockWrapper memBlockWrapper) {
		Minecraft mc = ModLoader.getMinecraftInstance();
		EntityPlayerSP player = mc.thePlayer;

		if (isEntityInCore(player)) {
			return !hideBlockPos.contains(new ChunkCoordinates(
					memBlockWrapper.innerWorldX, memBlockWrapper.innerWorldY,
					memBlockWrapper.innerWorldZ));
		}

		return super.shouldRenderMEMBlock(memBlockWrapper);
	}

	@Override
	protected String getPartsName() {
		return "_HEAD";
	}

}
