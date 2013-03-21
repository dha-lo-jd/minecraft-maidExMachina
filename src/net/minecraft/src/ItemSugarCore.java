package net.minecraft.src;

public class ItemSugarCore extends Item {

	private static final Block SUBST_BLOCK = Block.stone;

	public ItemSugarCore(int par1) {
		super(par1);
		maxStackSize = 1;
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, World par3World, int x, int y,
			int z, int l) {

		if (par1ItemStack.stackSize == 0) {
			return false;
		}

		if (!par2EntityPlayer.canPlayerEdit(x, y, z)) {
			return false;
		}

		if (y == 255) {
			return false;
		}

		int i = par3World.getBlockId(x, y, z);
		if (i == Block.snow.blockID) {
			l = 1;
		} else if (i != Block.vine.blockID && i != Block.tallGrass.blockID
				&& i != Block.deadBush.blockID) {
			if (l == 0) {
				y--;
			}

			if (l == 1) {
				y++;
			}

			if (l == 2) {
				z--;
			}

			if (l == 3) {
				z++;
			}

			if (l == 4) {
				x--;
			}

			if (l == 5) {
				x++;
			}
		}

		if (par3World
				.canBlockBePlacedAt(SUBST_BLOCK.blockID, x, y, z, false, l)) {
			// par3World.playSoundEffect(x, y, z, "random.explode", 4F,
			// (1.0F + (par3World.rand.nextFloat() - par3World.rand
			// .nextFloat()) * 0.2F) * 0.7F);
			// par3World.spawnParticle("hugeexplosion", x, y, z, 0.0D, 0.0D,
			// 0.0D);

			EntityCoreSugarBody coreEntity = buildBody(par1ItemStack,
					par2EntityPlayer, par3World, x, y, z);
			EntityCoreSugarHead coreSugarHead = buildHead(par2EntityPlayer,
					par3World, x, y, z, coreEntity);
			EntityCoreSugarSkirt sugarSkirt = buildSkirt(par2EntityPlayer,
					par3World, x, y, z, coreEntity);

			buildHair(par2EntityPlayer, par3World, x, y, z, coreSugarHead,
					coreEntity);
			buildHair2(par2EntityPlayer, par3World, x, y, z, coreSugarHead,
					coreEntity);

			buildRightLeg(par2EntityPlayer, par3World, x, y, z, sugarSkirt,
					coreEntity);
			buildLeftLeg(par2EntityPlayer, par3World, x, y, z, sugarSkirt,
					coreEntity);

			buildRightArm(par2EntityPlayer, par3World, x, y, z, coreEntity);
			buildLeftArm(par2EntityPlayer, par3World, x, y, z, coreEntity);

			return true;
		} else {
			return false;
		}
	}

	private EntityCoreSugarHead buildHead(EntityPlayer par2EntityPlayer,
			World par3World, int x, int y, int z, EntityCoreSugarBody coreEntity) {
		EntityCoreSugarHead head = new EntityCoreSugarHead(coreEntity,
				par3World, MEMPlanDefault.HEAD_SIZE,
				MEMPlanDefault.HEAD_OFFSET_POS, MEMPlanDefault.HEAD_JOINT_POS);
		head.setPosition(x, y + 17, z);
		head.rotationYaw = par2EntityPlayer.rotationYaw;
		// par3World.spawnEntityInWorld(head);
		head.buildFrame(MEMPlanDefault.HEAD);
		return head;
	}

	private EntityCoreSugarSkirt buildSkirt(EntityPlayer par2EntityPlayer,
			World par3World, int x, int y, int z, EntityCoreSugarBody coreEntity) {
		EntityCoreSugarSkirt head = new EntityCoreSugarSkirt(coreEntity,
				par3World, MEMPlanDefault.SKIRT_SIZE,
				MEMPlanDefault.SKIRT_OFFSET_POS, MEMPlanDefault.SKIRT_JOINT_POS);
		head.setPosition(x, y + 8, z);
		head.rotationYaw = par2EntityPlayer.rotationYaw;
		// par3World.spawnEntityInWorld(head);
		head.buildFrame(MEMPlanDefault.SKIRT);
		return head;
	}

	private void buildHair(EntityPlayer par2EntityPlayer, World par3World,
			int x, int y, int z, EntityCoreSugarHead parent,
			EntityCoreSugarParts coreEntity) {
		EntityCoreSugarHair core = new EntityCoreSugarHair(parent, coreEntity,
				par3World, MEMPlanDefault.HAIR1_SIZE,
				MEMPlanDefault.HAIR_OFFSET_POS, MEMPlanDefault.HAIR_JOINT_POS,
				1);
		core.setPosition(x, y + 8, z);
		core.rotationYaw = par2EntityPlayer.rotationYaw;
		// par3World.spawnEntityInWorld(core);
		core.buildFrame(MEMPlanDefault.HAIR_PARTS);
	}

	private void buildHair2(EntityPlayer par2EntityPlayer, World par3World,
			int x, int y, int z, EntityCoreSugarHead parent,
			EntityCoreSugarParts coreEntity) {
		EntityCoreSugarHair core = new EntityCoreSugarHair(parent, coreEntity,
				par3World, MEMPlanDefault.HAIR2_SIZE,
				MEMPlanDefault.HAIR2_OFFSET_POS,
				MEMPlanDefault.HAIR2_JOINT_POS, 2);
		core.setPosition(x, y + 8, z);
		core.rotationYaw = par2EntityPlayer.rotationYaw;
		// par3World.spawnEntityInWorld(core);
		core.buildFrame(MEMPlanDefault.HAIR2_PARTS);
	}

	private void buildRightArm(EntityPlayer par2EntityPlayer, World par3World,
			int x, int y, int z, EntityCoreSugarBody coreEntity) {
		EntityCoreSugarArm head = new EntityCoreSugarRightArm(coreEntity,
				par3World, MEMPlanDefault.ARM_SIZE,
				MEMPlanDefault.ARM_RIGHT_OFFSET_POS,
				MEMPlanDefault.ARM_RIGHT_JOINT_POS, 1, "_R");
		head.setPosition(x, y + 8, z);
		head.rotationYaw = par2EntityPlayer.rotationYaw;
		// par3World.spawnEntityInWorld(head);
		head.buildFrame(MEMPlanDefault.ARM);
	}

	private void buildLeftArm(EntityPlayer par2EntityPlayer, World par3World,
			int x, int y, int z, EntityCoreSugarBody coreEntity) {
		buildArm(par2EntityPlayer, par3World, x, y, z, coreEntity,
				MEMPlanDefault.ARM_LEFT_OFFSET_POS,
				MEMPlanDefault.ARM_LEFT_JOINT_POS, -1, "_L");
	}

	private void buildArm(EntityPlayer par2EntityPlayer, World par3World,
			int x, int y, int z, EntityCoreSugarBody coreEntity, Vec3D offset,
			Vec3D jointPos, int i, String suffix) {
		EntityCoreSugarArm head = new EntityCoreSugarArm(coreEntity, par3World,
				MEMPlanDefault.ARM_SIZE, offset, jointPos, i, suffix);
		head.setPosition(x, y + 8, z);
		head.rotationYaw = par2EntityPlayer.rotationYaw;
		// par3World.spawnEntityInWorld(head);
		head.buildFrame(MEMPlanDefault.ARM);
	}

	private void buildRightLeg(EntityPlayer par2EntityPlayer, World par3World,
			int x, int y, int z, EntityCoreSugarSkirt parent,
			EntityCoreSugarParts coreEntity) {
		buildLeg(par2EntityPlayer, par3World, x, y, z, parent, coreEntity,
				MEMPlanDefault.LEG_RIGHT_OFFSET_POS,
				MEMPlanDefault.LEG_RIGHT_JOINT_POS, 1, "_R");
	}

	private void buildLeftLeg(EntityPlayer par2EntityPlayer, World par3World,
			int x, int y, int z, EntityCoreSugarSkirt parent,
			EntityCoreSugarParts coreEntity) {
		buildLeg(par2EntityPlayer, par3World, x, y, z, parent, coreEntity,
				MEMPlanDefault.LEG_LEFT_OFFSET_POS,
				MEMPlanDefault.LEG_LEFT_JOINT_POS, -1, "_L");
	}

	private void buildLeg(EntityPlayer par2EntityPlayer, World par3World,
			int x, int y, int z, EntityCoreSugarSkirt parent,
			EntityCoreSugarParts coreEntity, Vec3D offset, Vec3D jointPos,
			int i, String suffix) {
		EntityCoreSugarLeg head = new EntityCoreSugarLeg(parent, coreEntity,
				par3World, MEMPlanDefault.LEG_SIZE, offset, jointPos, i, suffix);
		head.setPosition(x, y + 8, z);
		head.rotationYaw = par2EntityPlayer.rotationYaw;
		// par3World.spawnEntityInWorld(head);
		head.buildFrame(MEMPlanDefault.LEG, i == 1);
	}

	private EntityCoreSugarBody buildBody(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z) {
		EntityCoreSugarBody coreEntity = new EntityCoreSugarBody(par3World,
				MEMPlanDefault.BODY_SIZE);
		coreEntity.devicePos = CoordsSupport
				.copyVectorHelper(MEMPlanDefault.DEVICE_POS);
		Vec3D v3d = Vec3D.createVectorHelper(0, 0, 8);
		v3d.rotateAroundY((-par2EntityPlayer.rotationYaw * (float) Math.PI) / 180F);

		coreEntity.setPosition(x + v3d.xCoord, y + 14, z + v3d.zCoord);
		coreEntity.rotationYaw = -par2EntityPlayer.rotationYaw;
		if (par3World.spawnEntityInWorld(coreEntity)) {
			par3World.playSoundEffect((float) x + 0.5F, (float) y + 0.5F,
					(float) z + 0.5F, SUBST_BLOCK.stepSound.getStepSound(),
					(SUBST_BLOCK.stepSound.getVolume() + 1.0F) / 2.0F,
					SUBST_BLOCK.stepSound.getPitch() * 0.8F);
			par1ItemStack.stackSize--;
		}
		coreEntity.buildFrame(MEMPlanDefault.BODY);
		return coreEntity;
	}

}
