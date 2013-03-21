package net.minecraft.src;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.client.Minecraft;

public class EntityCoreSugarBody extends EntityCoreSugarParts {

	public static Set<Material> ignoreMaterials = new HashSet<Material>();

	public static Set<Material> opaqueMaterials = new HashSet<Material>();

	public static Set<Integer> unbreakableAlwaysMaterials = new HashSet<Integer>();

	public static Set<Material> unbreakableByCollisionMaterials = new HashSet<Material>();

	private static final double HEIGHT_FROM_GROUND = 12.5;

	private static final Set<ChunkCoordinates> hideBlockPos;

	static {
		{
			int[] m = new int[] { Block.bedrock.blockID,
					Block.obsidian.blockID, };
			for (int id : m) {
				unbreakableAlwaysMaterials.add(id);
			}
		}
		{
			Material[] m = new Material[] { Material.dragonEgg, Material.iron,
					Material.rock, };
			for (Material material : m) {
				unbreakableByCollisionMaterials.add(material);
			}
		}
		{
			Material[] m = new Material[] { Material.cactus, Material.cake,
					Material.circuits, Material.clay, Material.cloth,
					Material.craftedSnow, Material.fire, Material.glass,
					Material.ice, Material.leaves, Material.piston,
					Material.plants, Material.pumpkin, Material.redstoneLight,
					Material.sand, Material.snow, Material.sponge,
					Material.vine, Material.web, Material.wood, };
			for (Material material : m) {
				opaqueMaterials.add(material);
			}
		}
		{
			Material[] m = new Material[] { Material.water, Material.lava,
					Material.air, };
			for (Material material : m) {
				ignoreMaterials.add(material);
			}
		}
	}

	static {
		Set<ChunkCoordinates> s = new HashSet<ChunkCoordinates>();
		int z = myWorldMyPosZ - 1;
		for (int i = 0; i < 4; i++) {
			int x = myWorldMyPosX - 1 + i;
			for (int j = 0; j < 4; j++) {
				int y = myWorldMyPosY - 2 + j;
				// s.add(new ChunkCoordinates(x, y, z));
			}

		}
		hideBlockPos = s;
	}

	private double preLoadMotionX;

	private double preLoadMotionY;
	private double preLoadMotionZ;
	protected EntityMEMDeviceZabuton device;

	protected Vec3D devicePos = Vec3D.createVectorHelper(0, 0, 0);

	Set<MEMCollisionObject> collisionSet = new HashSet<MEMCollisionObject>();

	int fallCount;

	Set<ChunkCoordinates> footingAreaCoords;

	Double groundFootHeight;

	Double groundHeight;

	MEMAI memai = new MEMAI(this);

	Set<ChunkCoordinates> prevFootingAreaCoords;

	int speedTick;

	{
		memai.addEntries(MEMAISupport.getMoveJobSet(this));
	}

	public EntityCoreSugarBody(World par1World) {
		super(par1World, "");
	}

	public EntityCoreSugarBody(World par1World, ChunkCoordinates size) {
		super(par1World, size, "");
	}

	@Override
	public void addCollisionBlockPos(EntityMEMBlockWrapper entity, int j4,
			int k4, int l4) {
		collisionSet.add(new MEMCollisionObject(entity, new ChunkCoordinates(
				j4, k4, l4)));
	}

	@Override
	public IMEMDevice getDevice() {
		return device;
	}

	public Set<ChunkCoordinates> getFootAreaCoords() {
		Set<ChunkCoordinates> coordArea2D = CoordsSupport.getCoordArea2D(
				posX - 3, posZ - 3, posX + 4, posZ + 4, posY);
		return coordArea2D;
	}

	public Set<ChunkCoordinates> getFootingAreaCoords() {
		Set<ChunkCoordinates> coordArea2D = CoordsSupport.getCoordArea2D(
				posX - 3, posZ - 3, posX + 4, posZ + 4, posY - groundHeight);
		return coordArea2D;
	}

	@Override
	public MEMAI getMemai() {
		return memai;
	}

	public boolean isSetEquals(Set<ChunkCoordinates> par1Set,
			Set<ChunkCoordinates> par2Set) {
		if (par1Set.size() != par2Set.size()) {
			return false;
		}
		for (ChunkCoordinates coordinates : par1Set) {
			if (!par2Set.contains(coordinates)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void onUpdate() {
		Set<MEMCollisionObject> cSet = new HashSet<MEMCollisionObject>(
				collisionSet);
		for (MEMCollisionObject collisionObject : cSet) {
			processCollision(collisionObject);
		}

		super.onUpdate();

		updateDevicePosition();

		memai.updateStates();
		memai.updateAIEntry();

		renderModify = checkRenderModified();
		isRenderTransparent = checkRenderTransparent();

		Set<ChunkCoordinates> prevFootAreaCoords = getFootAreaCoords();
		double prevGroundHeight = MEMSupport.getAreaHeightFromGround(
				prevFootAreaCoords, worldObj, opaqueMaterials)
				+ getDecimalYOffset();

		Minecraft mc = ModLoader.getMinecraftInstance();

		Entity ridingEntity = device.riddenByEntity;

		Set<ChunkCoordinates> footAreaCoords = getFootAreaCoords();
		double groundHeight = MEMSupport.getAreaHeightFromGround(
				footAreaCoords, worldObj, opaqueMaterials)
				+ getDecimalYOffset();

		if (prevGroundHeight - groundHeight >= 3) {
			rotationYaw = prevRotationYaw;
			motionX = 0;
			motionZ = 0;
		}

		moveEntity(motionX, motionY, motionZ);

		footAreaCoords = getFootAreaCoords();
		groundHeight = MEMSupport.getAreaHeightFromGround(footAreaCoords,
				worldObj, opaqueMaterials) + getDecimalYOffset();
		if (prevGroundHeight - groundHeight >= 3) {
			posX = prevPosX;
			posZ = prevPosZ;
			motionX = 0;
			motionZ = 0;
		}

		float f = 0.98F;

		motionX *= f;
		motionY *= 0.98;
		motionZ *= f;
		motionY -= 0.04;

		updateOnFootingArea();

		if (isOnGround()) {
			motionY = 0;
			updateOnFooting();
			if (isFootOnGround()) {
				double up = HEIGHT_FROM_GROUND + 1 - groundHeight;
				up = up > 2 ? 0 : up;
				up = up > 0 ? up : 0;
				posY = posY + up;
			}
		}

		EntityCoordsSupport.updatePositionFromPrev(this);

		super.lazyUpdateParts();
		super.lazyUpdate();

	}

	@Override
	public boolean shouldRenderMEMBlock(EntityMEMBlockWrapper memBlockWrapper) {
		Minecraft mc = ModLoader.getMinecraftInstance();
		EntityPlayerSP player = mc.thePlayer;

		if (isEntityInCore(player) && mc.gameSettings.thirdPersonView == 0) {
			return !hideBlockPos.contains(new ChunkCoordinates(
					memBlockWrapper.innerWorldX, memBlockWrapper.innerWorldY,
					memBlockWrapper.innerWorldZ));
		}

		return super.shouldRenderMEMBlock(memBlockWrapper);
	}

	public void updateOnFooting() {
		int footingSize = 0;
		boolean unbreakable = false;
		float mStrength = 0;
		for (ChunkCoordinates pos : footingAreaCoords) {
			if (!CoordsSupport.isNonMaterial(worldObj, pos, true)) {
				int blockId = worldObj.getBlockId(pos.posX, pos.posY, pos.posZ);
				Material m = Block.blocksList[blockId].blockMaterial;
				if (unbreakableAlwaysMaterials.contains(blockId)) {
					unbreakable = true;
				} else if (!opaqueMaterials.contains(m)) {
					Block b = Block.blocksList[blockId];
					float r = b.blockResistance > 0 ? b.blockResistance : 1;
					float h = b.blockHardness > 0 ? b.blockHardness : 0.1F;
					float strength = (h * 10) * ((r / 10));
					mStrength += strength * 3;
				}
				footingSize++;
			}
		}
		float footingRate = footingSize / (float) footingAreaCoords.size();
		if (unbreakable || footingRate > 0.5) {
			fallCount = 0;
		} else {
			fallCount++;
		}

		if (fallCount > mStrength * footingRate + 10) {
			for (ChunkCoordinates pos : footingAreaCoords) {
				int blockId = worldObj.getBlockId(pos.posX, pos.posY, pos.posZ);
				if (blockId > 0
						&& Block.blocksList[blockId].blockMaterial.isSolid()) {
					worldObj.setBlockWithNotify(pos.posX, pos.posY, pos.posZ, 0);
				}
			}
			fallCount = 0;
		}
	}

	public void updateOnFootingArea() {
		prevFootingAreaCoords = footingAreaCoords;

		Set<ChunkCoordinates> footAreaCoords = getFootAreaCoords();
		groundHeight = MEMSupport.getAreaHeightFromGround(footAreaCoords,
				worldObj, opaqueMaterials) + getDecimalYOffset();

		footingAreaCoords = getFootingAreaCoords();
		groundFootHeight = MEMSupport.getAreaHeightFromGround(
				footingAreaCoords, worldObj, opaqueMaterials)
				+ getDecimalYOffset();

		if (prevFootingAreaCoords != null
				&& !isSetEquals(prevFootingAreaCoords, footingAreaCoords)) {
			fallCount = 0;
		}
	}

	protected void breakCollideBlock(MEMCollisionObject collision) {
		worldObj.setBlockWithNotify(collision.pos.posX, collision.pos.posY,
				collision.pos.posZ, 0);
	}

	protected double getDecimalYOffset() {
		return posY - MathHelper.floor_double(posY);
	}

	@Override
	protected String getPartsName() {
		return "_BODY";
	}

	protected boolean isFootOnGround() {
		return groundFootHeight != null && groundFootHeight < 1;
	}

	@Override
	protected boolean isOnGround() {
		return groundHeight != null && groundHeight < HEIGHT_FROM_GROUND + 1;
	}

	@Override
	protected void lazyUpdate() {
	}

	@Override
	protected void lazyUpdateParts() {
	}

	@Override
	protected void preLoaded() {
		motionX = preLoadMotionX;
		motionY = preLoadMotionY;
		motionZ = preLoadMotionZ;
	}

	protected void processCollision(MEMCollisionObject collision) {
		collisionSet.remove(collision);

		int blockId = worldObj.getBlockId(collision.pos.posX,
				collision.pos.posY, collision.pos.posZ);
		Material m;
		if (blockId == 0) {
			m = Material.air;
		} else {
			m = Block.blocksList[blockId].blockMaterial;
		}
		if (ignoreMaterials.contains(m)) {
			return;
		}
		if (unbreakableByCollisionMaterials.contains(m)
				|| unbreakableAlwaysMaterials.contains(blockId)) {
			pushuOutByMEMBlock(collision);
		} else {
			breakCollideBlock(collision);
		}

	}

	protected void pushuOutByMEMBlock(MEMCollisionObject collision) {
		Vec3D collidVec = Vec3D.createVectorHelper(collision.pos.posX - posX,
				0, collision.pos.posZ - posZ);
		collidVec = collidVec.normalize();

		double r = -0.01;
		double npX = collidVec.xCoord * r;
		// double npY = collidVec.yCoord * r;
		double npZ = collidVec.zCoord * r;

		setPosition(posX + npX, posY, posZ + npZ);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);
		preLoadMotionX = motionX;
		preLoadMotionY = motionY;
		preLoadMotionZ = motionZ;
		motionX = 0.0D;
		motionY = 0.0D;
		motionZ = 0.0D;
		devicePos = TagCompoundSupport
				.readTagVec3D((NBTTagCompound) nbttagcompound
						.getTag("devicePos"));
	}

	protected void updateDevicePosition() {
		Vec3D devicePosCopy = CoordsSupport.copyVectorHelper(devicePos);
		devicePosCopy.rotateAroundY((-rotationYaw * (float) Math.PI) / 180F);
		EntityPosition ep = new EntityPosition(posX + devicePosCopy.xCoord,
				posY + devicePosCopy.yCoord, posZ + devicePosCopy.zCoord,
				rotationYaw, rotationPitch);
		if (device == null) {
			device = new EntityMEMDeviceZabuton(worldObj, new ItemStack(
					mod_zabuton.zabuton), this);
			ep.setPositonAndRotationToEntity(device);
			worldObj.spawnEntityInWorld(device);
		} else {
			ep.setPositonAndRotationToEntity(device);
		}
		// if (device.riddenByEntity != null) {
		// device.updateRiderPosition();
		// }
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
		TagCompoundSupport
				.writeVec3DTag(nbttagcompound, "devicePos", devicePos);
	}

}
