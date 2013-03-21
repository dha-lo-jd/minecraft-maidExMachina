package net.minecraft.src;

public class MEMPlanSurface {

	public static final IMEMPlanBlockInfo _VD_ = null;

	public static final int HAIR = 0x241808;
	public static final int SKIN = 0xf5c8aa;
	public static final int BLK_ = 0x000000;
	public static final int WHT_ = 0xffffff;
	public static final int RED_ = 0xed1c24;
	public static final int GRAY = 0xb4b4b4;
	public static final int W_GR = 0xdedede;

	public static final int LEG_ = 0x403010;
	public static final int SHUE = 0x241808;

	public static final int B_BL = 0x404050;
	public static final int D_BL = 0x000020;
	public static final int[] EYE = new int[] {
			0xffffff, 0xdedede, 0xed1c24, 0x404050 };
	// dedede
	// 000020

	public static final int[] _V_ = new int[] {};
	public static final int[] T__ = new int[] { 0 };
	public static final int[] B__ = new int[] { 1 };
	public static final int[] _N_ = new int[] { 2 };
	public static final int[] _S_ = new int[] { 3 };
	public static final int[] __E = new int[] { 4 };
	public static final int[] __W = new int[] { 5 };
	public static final int[] TN_ = new int[] { 0, 2 };
	public static final int[] TS_ = new int[] { 0, 3 };
	public static final int[] T_E = new int[] { 0, 4 };
	public static final int[] T_W = new int[] { 0, 5 };
	public static final int[] BN_ = new int[] { 1, 2 };
	public static final int[] BS_ = new int[] { 1, 3 };
	public static final int[] B_E = new int[] { 1, 4 };
	public static final int[] B_W = new int[] { 1, 5 };
	public static final int[] _NE = new int[] { 2, 4 };
	public static final int[] _NW = new int[] { 2, 5 };
	public static final int[] _SE = new int[] { 3, 4 };
	public static final int[] _SW = new int[] { 3, 5 };
	public static final int[] TNE = new int[] { 0, 2, 4 };
	public static final int[] TNW = new int[] { 0, 2, 5 };
	public static final int[] TSE = new int[] { 0, 3, 4 };
	public static final int[] TSW = new int[] { 0, 3, 5 };
	public static final int[] BNE = new int[] { 1, 2, 4 };
	public static final int[] BNW = new int[] { 1, 2, 5 };
	public static final int[] BSE = new int[] { 1, 3, 4 };
	public static final int[] BSW = new int[] { 1, 3, 5 };

	public static final MEMPlanDefaultFrame defaultFrame = new MEMPlanDefaultFrame(
			W_GR, "/MEMFrame.png");

	private static IMEMPlanBlockInfo I(int color, int[] faces) {
		Integer[] colors = MEMPlanOptionalQuadFrame.getFaceArray(color, null,
				new Integer[] {}, faces);
		String[] textures = MEMPlanOptionalQuadFrame.getFaceArray(null,
				IMEMPlanBlockInfo.EMPTY, new String[] {}, faces);
		return new MEMPlanOptionalQuadFrame(defaultFrame, colors, textures);
	}

	private static IMEMPlanBlockInfo H(int color, int[] faces) {
		Integer[] colors = MEMPlanOptionalQuadFrame.getFaceArray(color, null,
				new Integer[] {}, faces);
		String[] textures = MEMPlanOptionalQuadFrame.getFaceArray(null,
				IMEMPlanBlockInfo.EMPTY, new String[] {}, faces);
		return new MEMPlanOptionalQuadFrame(MEMPlanTransparentFrame.FRAME,
				colors, textures);
	}

	private static IMEMPlanBlockInfo I(Integer... colors) {
		String[] textures = new String[6];
		for (int i = 0; i < colors.length; i++) {
			Integer integer = colors[i];
			if (integer == null) {
				textures[i] = IMEMPlanBlockInfo.EMPTY;
			} else {
				textures[i] = null;
			}
		}
		return new MEMPlanOptionalQuadFrame(defaultFrame, colors, textures);
	}

	public static final IMEMPlanBlockInfo H_1_ = I(HAIR, TNE);
	public static final IMEMPlanBlockInfo H_2_ = I(WHT_, TN_);
	public static final IMEMPlanBlockInfo H_3_ = I(HAIR, TNW);
	public static final IMEMPlanBlockInfo H_4_ = I(HAIR, T_E);
	public static final IMEMPlanBlockInfo H_5_ = I(HAIR, T__);
	public static final IMEMPlanBlockInfo H_6_ = I(HAIR, T_W);
	public static final IMEMPlanBlockInfo H_7_ = I(HAIR, TSE);
	public static final IMEMPlanBlockInfo H_8_ = I(HAIR, TS_);
	public static final IMEMPlanBlockInfo H_9_ = I(HAIR, TSW);

	public static final IMEMPlanBlockInfo H_a_ = I(HAIR, null, WHT_, null,
			null, null);

	public static final IMEMPlanBlockInfo H_b_ = I(HAIR, null, null, null,
			HAIR, HAIR);

	public static final IMEMPlanBlockInfo H_c_ = I(HAIR, null, HAIR, HAIR,
			null, null);

	public static final IMEMPlanBlockInfo H10_ = I(HAIR, _NE);
	public static final IMEMPlanBlockInfo H11_ = I(WHT_, _N_);
	public static final IMEMPlanBlockInfo H11a = I(HAIR, _N_);
	public static final IMEMPlanBlockInfo H12_ = I(HAIR, _NW);
	public static final IMEMPlanBlockInfo H13_ = I(HAIR, __E);
	public static final IMEMPlanBlockInfo H14_ = I(HAIR, __W);
	public static final IMEMPlanBlockInfo H15_ = I(HAIR, _SE);
	public static final IMEMPlanBlockInfo H16_ = I(HAIR, _S_);
	public static final IMEMPlanBlockInfo H17_ = I(HAIR, _SW);

	public static final IMEMPlanBlockInfo H18_ = I(SKIN, _N_);

	public static final IMEMPlanBlockInfo H19a = I(EYE[0], _N_);
	public static final IMEMPlanBlockInfo H19b = I(EYE[1], _N_);
	public static final IMEMPlanBlockInfo H19c = I(EYE[2], _N_);
	public static final IMEMPlanBlockInfo H19d = I(EYE[3], _N_);

	public static final IMEMPlanBlockInfo H20_ = I(SKIN, __E);
	public static final IMEMPlanBlockInfo H21_ = I(SKIN, __W);

	public static final IMEMPlanBlockInfo H30_ = I(SKIN, BNE);
	public static final IMEMPlanBlockInfo H31_ = I(SKIN, BN_);
	public static final IMEMPlanBlockInfo H32_ = I(SKIN, BNW);
	public static final IMEMPlanBlockInfo H33_ = I(SKIN, B_E);
	public static final IMEMPlanBlockInfo H34_ = I(SKIN, B__);
	public static final IMEMPlanBlockInfo H35_ = I(SKIN, B_W);
	public static final IMEMPlanBlockInfo H36_ = I(HAIR, B_E);
	public static final IMEMPlanBlockInfo H37_ = I(HAIR, B__);
	public static final IMEMPlanBlockInfo H38_ = I(HAIR, B_W);
	public static final IMEMPlanBlockInfo H39_ = I(HAIR, BSE);
	public static final IMEMPlanBlockInfo H40_ = I(HAIR, BS_);
	public static final IMEMPlanBlockInfo H41_ = I(HAIR, BSW);

	public static final ChunkCoordinates HEAD_SIZE = new ChunkCoordinates(8, 8,
			8);
	public static final IMEMPlanBlockInfo[][][] HEAD = new IMEMPlanBlockInfo[][][] {//
			{
					//
					{ H_1_, H_a_, H_2_, H_2_, H_2_, H_2_, H_a_, H_3_, },
					{ H_4_, H_5_, H_5_, H_5_, H_5_, H_5_, H_5_, H_6_, },
					{ H_4_, H_5_, H_5_, H_5_, H_5_, H_5_, H_5_, H_6_, },
					{ H_4_, H_5_, H_5_, H_5_, H_5_, H_5_, H_5_, H_6_, },
					{ H_4_, H_5_, H_5_, H_5_, H_5_, H_5_, H_5_, H_6_, },
					{ H_4_, H_8_, H_5_, H_5_, H_5_, H_5_, H_5_, H_6_, },
					{ H_b_, _VD_, H_4_, H_5_, H_5_, H_5_, H_5_, H_6_, },
					{ H_7_, H_c_, H_8_, H_8_, H_8_, H_8_, H_8_, H_9_, }, //
			}, {//
				//
					{ H10_, H11_, H11a, H11a, H11a, H11a, H11_, H12_, }, //
					{ H13_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14_, }, //
					{ H13_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14_, }, //
					{ H13_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14_, }, //
					{ H13_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14_, }, //
					{ H13_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14_, }, //
					{ H13_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14_, }, //
					{ H15_, H16_, H16_, H16_, H16_, H16_, H16_, H17_, },//
			}, {//
				//
					{ H10_, H11a, H11a, H18_, H18_, H11a, H11a, H12_, }, //
					{ H13_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14_, }, //
					{ H13_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14_, }, //
					{ H13_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14_, }, //
					{ H13_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14_, }, //
					{ H13_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14_, }, //
					{ H13_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14_, }, //
					{ H15_, H16_, H16_, H16_, H16_, H16_, H16_, H17_, },//
			}, {//
				//
					{ H10_, H11a, H18_, H18_, H18_, H18_, H11a, H12_, }, //
					{ H13_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14_, }, //
					{ H13_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14_, }, //
					{ H13_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14_, }, //
					{ H13_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14_, }, //
					{ H13_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14_, }, //
					{ H13_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14_, }, //
					{ H15_, H16_, H16_, H40_, H40_, H16_, H16_, H17_, },//
			}, {//
				//
					{ H10_, H19b, H19d, H18_, H18_, H19d, H19b, H12_, }, //
					{ H20_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H21_, }, //
					{ H20_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H21_, }, //
					{ H13_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14_, }, //
					{ H13_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14_, }, //
					{ H13_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14_, }, //
					{ H13_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14_, }, //
					{ H15_, H16_, H17_, _VD_, _VD_, H15_, H16_, H17_, },//
			}, {//
				//
					{ H10_, H19a, H19c, H18_, H18_, H19c, H19a, H12_, }, //
					{ H20_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H21_, }, //
					{ H20_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H21_, }, //
					{ H13_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14_, }, //
					{ H13_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14_, }, //
					{ H13_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14_, }, //
					{ H13_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14_, }, //
					{ H15_, H16_, H17_, _VD_, _VD_, H15_, H16_, H17_, },//
			}, {//
				//
					{ H10_, H19a, H19b, H18_, H18_, H19b, H19a, H12_, }, //
					{ H20_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H21_, }, //
					{ H20_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H21_, }, //
					{ H20_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H21_, }, //
					{ H13_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14_, }, //
					{ H13_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14_, }, //
					{ H13_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14_, }, //
					{ H15_, H16_, H17_, _VD_, _VD_, H15_, H16_, H17_, },//
			}, {//
					//
					{ H30_, H31_, H31_, H31_, H31_, H31_, H31_, H32_, },
					{ H33_, H34_, H34_, H34_, H34_, H34_, H34_, H35_, },
					{ H33_, H34_, H34_, H34_, H34_, H34_, H34_, H35_, },
					{ H33_, H34_, H34_, H34_, H34_, _VD_, H34_, H35_, },
					{ H36_, H37_, H37_, H37_, H37_, H37_, H37_, H38_, },
					{ H36_, H37_, H37_, H37_, H37_, H37_, H37_, H38_, },
					{ H36_, H37_, H37_, H37_, H37_, H37_, H37_, H38_, },
					{ H39_, H40_, H40_, H40_, H40_, H40_, H40_, H41_, },//
			}, };

	public static final Vec3D HEAD_OFFSET_POS = Vec3D.createVectorHelper(0.5,
			-3, 0.5);
	public static final Vec3D HEAD_JOINT_POS = Vec3D.createVectorHelper(0.5, 3,
			0.5);

	public static final IMEMPlanBlockInfo HA1_ = H(HAIR, _SE);
	public static final IMEMPlanBlockInfo HA2_ = H(HAIR, _S_);
	public static final IMEMPlanBlockInfo HA3_ = H(HAIR, _SW);
	public static final IMEMPlanBlockInfo HA4_ = H(HAIR, __E);
	public static final IMEMPlanBlockInfo HA6_ = H(HAIR, __W);

	public static final IMEMPlanBlockInfo HA1a = H(RED_, _SE);
	public static final IMEMPlanBlockInfo HA2a = H(RED_, _S_);
	public static final IMEMPlanBlockInfo HA3a = H(RED_, _SW);
	public static final IMEMPlanBlockInfo HA4a = H(RED_, __E);
	public static final IMEMPlanBlockInfo HA6a = H(RED_, __W);

	public static final ChunkCoordinates HAIR1_SIZE = new ChunkCoordinates(8,
			4, 3);
	public static final IMEMPlanBlockInfo[][][] HAIR_PARTS = new IMEMPlanBlockInfo[][][] {//
			{
					//
					{ HA4_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, HA6_, },
					{ HA4_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, HA6_, },
					{ HA1_, HA2_, HA2_, _VD_, _VD_, HA2_, HA2_, HA3_, }, //
			}, {
					//
					{ _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, }, //
					{ HA4a, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, HA6a, }, //
					{ HA1a, HA2a, _VD_, _VD_, _VD_, _VD_, HA2a, HA3a, }, //
			}, {
					//
					{ _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, }, //
					{ HA4_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, HA6_, }, //
					{ HA1_, HA2_, _VD_, _VD_, _VD_, _VD_, HA2_, HA3_, }, //
			}, {
					//
					{ _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, }, //
					{ HA4_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, HA6_, }, //
					{ HA1_, HA2_, _VD_, _VD_, _VD_, _VD_, HA2_, HA3_, }, //
			}, };
	public static final Vec3D HAIR_OFFSET_POS = Vec3D.createVectorHelper(0.5,
			3, -2.5);
	public static final Vec3D HAIR_JOINT_POS = Vec3D.createVectorHelper(0.5,
			-3, 0.5);

	public static final ChunkCoordinates BODY_SIZE = new ChunkCoordinates(6, 5,
			4);

	public static final IMEMPlanBlockInfo B_1_ = I(WHT_, TNE);
	public static final IMEMPlanBlockInfo B_2_ = I(W_GR, TN_);
	public static final IMEMPlanBlockInfo B_2a = I(RED_, TN_);
	public static final IMEMPlanBlockInfo B_3_ = I(WHT_, TNW);
	public static final IMEMPlanBlockInfo B_4_ = I(B_BL, T_E);
	public static final IMEMPlanBlockInfo B_5_ = I(W_GR, T__);
	public static final IMEMPlanBlockInfo B_5a = I(SKIN, T__);
	public static final IMEMPlanBlockInfo B_6_ = I(B_BL, T_W);
	public static final IMEMPlanBlockInfo B_7_ = I(B_BL, TSE);
	public static final IMEMPlanBlockInfo B_8_ = I(B_BL, TS_);
	public static final IMEMPlanBlockInfo B_9_ = I(B_BL, TSW);

	public static final IMEMPlanBlockInfo B11_ = I(WHT_, _NE);
	public static final IMEMPlanBlockInfo B12_ = I(W_GR, _N_);
	public static final IMEMPlanBlockInfo B12a = I(RED_, _N_);
	public static final IMEMPlanBlockInfo B13_ = I(WHT_, _NW);
	public static final IMEMPlanBlockInfo B14_ = I(B_BL, __E);
	public static final IMEMPlanBlockInfo B16_ = I(B_BL, __W);
	public static final IMEMPlanBlockInfo B17_ = I(B_BL, _SE);
	public static final IMEMPlanBlockInfo B18_ = I(B_BL, _S_);
	public static final IMEMPlanBlockInfo B19_ = I(B_BL, _SW);

	public static final IMEMPlanBlockInfo B18b = I(B_BL, BS_);

	public static final IMEMPlanBlockInfo B11b = I(D_BL, _NE);
	public static final IMEMPlanBlockInfo B12b = I(B_BL, _N_);
	public static final IMEMPlanBlockInfo B13b = I(D_BL, _NW);
	public static final IMEMPlanBlockInfo B14b = I(GRAY, __E);
	public static final IMEMPlanBlockInfo B16b = I(GRAY, __W);

	public static final IMEMPlanBlockInfo B11c = I(GRAY, _NE);
	public static final IMEMPlanBlockInfo B12c = I(GRAY, _N_);
	public static final IMEMPlanBlockInfo B13c = I(GRAY, _NW);
	public static final IMEMPlanBlockInfo B14c = I(GRAY, __E);
	public static final IMEMPlanBlockInfo B16c = I(GRAY, __W);
	public static final IMEMPlanBlockInfo B17c = I(GRAY, _SE);
	public static final IMEMPlanBlockInfo B19c = I(GRAY, _SW);

	public static final IMEMPlanBlockInfo B11d = I(B_BL, _NE);
	public static final IMEMPlanBlockInfo B13d = I(B_BL, _NW);

	public static final IMEMPlanBlockInfo B_ad = I(null, null, WHT_, null,
			B_BL, null);
	public static final IMEMPlanBlockInfo B_bd = I(null, null, WHT_, null,
			null, B_BL);

	public static final IMEMPlanBlockInfo B17d = I(WHT_, _SE);
	public static final IMEMPlanBlockInfo B19d = I(WHT_, _SW);

	public static final IMEMPlanBlockInfo[][][] BODY = new IMEMPlanBlockInfo[][][] {//
	{
			//
			{ B_1_, B_2_, B_2a, B_2a, B_2_, B_3_, },//
			{ B_4_, B_5_, B_5a, B_5a, B_5_, B_6_, },//
			{ B_4_, B_5_, B_5a, B_5a, B_5_, B_6_, },//
			{ B_7_, B_8_, B_8_, B_8_, B_8_, B_9_, },//
	}, {
			//
			{ B_ad, B12_, B12a, B12a, B12_, B_bd, },//
			{ B14_, _VD_, _VD_, _VD_, _VD_, B16_, },//
			{ B14_, _VD_, _VD_, _VD_, _VD_, B16_, },//
			{ B17_, B18_, B18_, B18_, B18_, B19_, },//
	}, {
			//
			{ B11d, B12_, B12a, B12a, B12_, B13d, },//
			{ B14_, _VD_, _VD_, _VD_, _VD_, B16_, },//
			{ B14_, _VD_, _VD_, _VD_, _VD_, B16_, },//
			{ B17_, B18_, B18b, B18b, B18_, B19_, },//
	}, {
			//
			{ B11b, B12b, B12b, B12b, B12b, B13b, },//
			{ B14b, _VD_, _VD_, _VD_, _VD_, B16b, },//
			{ B14_, _VD_, _VD_, _VD_, _VD_, B16_, },//
			{ B17_, B19_, _VD_, _VD_, B17_, B19_, },//
	}, {
			//
			{ B11c, B12c, B12c, B12c, B12c, B13c, },//
			{ B14c, _VD_, _VD_, _VD_, _VD_, B16c, },//
			{ B14c, _VD_, _VD_, _VD_, _VD_, B16c, },//
			{ B17c, B19d, _VD_, _VD_, B17d, B19c, },//
	}, };

	public static final ChunkCoordinates SKIRT_SIZE = new ChunkCoordinates(8,
			9, 8);

	public static final IMEMPlanBlockInfo S_1_ = I(GRAY, TNE);
	public static final IMEMPlanBlockInfo S_2_ = I(WHT_, TN_);
	public static final IMEMPlanBlockInfo S_2a = I(W_GR, TN_);
	public static final IMEMPlanBlockInfo S_3_ = I(GRAY, TNW);
	public static final IMEMPlanBlockInfo S_4_ = I(WHT_, T_E);
	public static final IMEMPlanBlockInfo S_5_ = I(WHT_, T__);
	public static final IMEMPlanBlockInfo S_6_ = I(WHT_, T_W);
	public static final IMEMPlanBlockInfo S_4a = I(W_GR, T_E);
	public static final IMEMPlanBlockInfo S_5a = I(GRAY, T__);
	public static final IMEMPlanBlockInfo S_6a = I(W_GR, T_W);
	public static final IMEMPlanBlockInfo S_4b = I(B_BL, T_E);
	public static final IMEMPlanBlockInfo S_5b = I(B_BL, T__);
	public static final IMEMPlanBlockInfo S_6b = I(B_BL, T_W);
	public static final IMEMPlanBlockInfo S_7_ = I(B_BL, TSE);
	public static final IMEMPlanBlockInfo S_8_ = I(GRAY, TS_);
	public static final IMEMPlanBlockInfo S_8a = I(B_BL, TS_);
	public static final IMEMPlanBlockInfo S_9_ = I(B_BL, TSW);

	public static final IMEMPlanBlockInfo S_a_ = I(B_BL, null, null, GRAY,
			null, null);

	public static final IMEMPlanBlockInfo S_5c = defaultFrame;

	public static final IMEMPlanBlockInfo S11_ = I(WHT_, _NE);
	public static final IMEMPlanBlockInfo S12_ = I(WHT_, _N_);
	public static final IMEMPlanBlockInfo S12a = I(W_GR, _N_);
	public static final IMEMPlanBlockInfo S12b = I(GRAY, _N_);
	public static final IMEMPlanBlockInfo S12c = I(B_BL, _N_);
	public static final IMEMPlanBlockInfo S13_ = I(WHT_, _NW);

	public static final IMEMPlanBlockInfo S11a = I(GRAY, _NE);
	public static final IMEMPlanBlockInfo S13a = I(GRAY, _NW);
	public static final IMEMPlanBlockInfo S11b = I(B_BL, _NE);
	public static final IMEMPlanBlockInfo S13b = I(B_BL, _NW);
	public static final IMEMPlanBlockInfo S11c = I(D_BL, _NE);
	public static final IMEMPlanBlockInfo S13c = I(D_BL, _NW);

	public static final IMEMPlanBlockInfo S14a = I(W_GR, __E);
	public static final IMEMPlanBlockInfo S16a = I(W_GR, __W);
	public static final IMEMPlanBlockInfo S14b = I(WHT_, __E);
	public static final IMEMPlanBlockInfo S16b = I(WHT_, __W);
	public static final IMEMPlanBlockInfo S14c = I(GRAY, __E);
	public static final IMEMPlanBlockInfo S16c = I(GRAY, __W);
	public static final IMEMPlanBlockInfo S14d = I(D_BL, __E);
	public static final IMEMPlanBlockInfo S16d = I(D_BL, __W);
	public static final IMEMPlanBlockInfo S14_ = I(B_BL, __E);
	public static final IMEMPlanBlockInfo S16_ = I(B_BL, __W);

	public static final IMEMPlanBlockInfo S17_ = I(B_BL, _SE);
	public static final IMEMPlanBlockInfo S18_ = I(B_BL, _S_);
	public static final IMEMPlanBlockInfo S18a = I(WHT_, _S_);
	public static final IMEMPlanBlockInfo S18b = I(GRAY, _S_);
	public static final IMEMPlanBlockInfo S19_ = I(B_BL, _SW);

	public static final IMEMPlanBlockInfo S17c = I(D_BL, _SE);
	public static final IMEMPlanBlockInfo S18c = I(D_BL, _S_);
	public static final IMEMPlanBlockInfo S19c = I(D_BL, _SW);

	public static final IMEMPlanBlockInfo S18d = I(B_BL, BS_);

	public static final IMEMPlanBlockInfo S21_ = I(WHT_, BNE);
	public static final IMEMPlanBlockInfo S22_ = I(null, WHT_, WHT_, WHT_,
			null, null);
	public static final IMEMPlanBlockInfo S23_ = I(WHT_, BNW);

	public static final IMEMPlanBlockInfo S24_ = I(null, WHT_, null, null,
			WHT_, WHT_);
	public static final IMEMPlanBlockInfo S25_ = I(B_BL, B__);

	public static final IMEMPlanBlockInfo S27_ = I(WHT_, BSE);
	public static final IMEMPlanBlockInfo S29_ = I(WHT_, BSW);

	public static final IMEMPlanBlockInfo[][][] SKIRT = new IMEMPlanBlockInfo[][][] {//
			{
					// y0
					{ _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },//
					{ _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },//
					{ _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },//
					{ _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },//
					{ _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },//
					{ _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },//
					{ _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },//
					{ _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },//
			},
			{
					// y1
					{ S_1_, S_2a, S_2_, S_2_, S_2_, S_2_, S_2_, S_3_, },
					{ S_4_, S_5_, S_5_, S_5_, S_5_, S_5_, S_5_, S_6_, },
					{ S_4a, S_5_, S_5a, S_5a, S_5a, S_5a, S_5_, S_6a, },
					{ S_4_, S_5_, S_5c, S_5c, S_5c, S_5c, S_5_, S_6_, },
					{ S_4_, S_5_, S_5c, S_5c, S_5c, S_5c, S_5_, S_6_, },
					{ S_4a, S_5_, S_5a, S_5a, S_5a, S_5a, S_5_, S_6a, },
					{ S_4b, S_5b, S_5b, S_5b, S_5b, S_5b, _VD_, S_6b, },
					{ S_7_, S_a_, S_a_, S_8a, S_8a, S_a_, S_a_, S_9_, }, //
			}, {
					// y2
					{ S11_, S12_, S12a, S12a, S12_, S12_, S12a, S13_, },//
					{ S14a, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16a, }, //
					{ S14b, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16b, }, //
					{ S14c, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16c, }, //
					{ S14_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16_, }, //
					{ S14_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16_, }, //
					{ S14_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16_, }, //
					{ S17_, S18a, S18a, S18_, S18_, S18a, S18a, S19_, }, //
			}, {
					// y3
					{ S11_, S12_, S12_, S12_, S12_, S12a, S12_, S13_, },//
					{ S14b, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16b, }, //
					{ S14b, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16b, }, //
					{ S14_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16_, }, //
					{ S14_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16_, }, //
					{ S14_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16_, }, //
					{ S14_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16_, }, //
					{ S17_, S18b, S18b, S18d, S18d, S18b, S18b, S19_, }, //
			}, {
					// y4
					{ S11_, S12_, S12_, S12a, S12a, S12_, S12_, S13_, },//
					{ S14b, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16b, }, //
					{ S14c, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16c, }, //
					{ S14_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16_, }, //
					{ S14_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16_, }, //
					{ S14_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16_, }, //
					{ S14_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16_, }, //
					{ S17_, S18_, S19_, _VD_, _VD_, S17_, S18_, S19_, }, //
			}, {
					//
					{ S11a, S12_, S12_, S12_, S12_, S12_, S12_, S13a, },//
					{ S14c, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16c, }, //
					{ S14_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16_, }, //
					{ S14_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16_, }, //
					{ S14_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16_, }, //
					{ S14_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16_, }, //
					{ S14_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16_, }, //
					{ S17_, S18_, S19_, _VD_, _VD_, S17_, S18_, S19_, }, //
			}, {//
				//
					{ S11b, S12b, S12b, S12b, S12b, S12b, S12b, S13b, },//
					{ S14_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16_, }, //
					{ S14_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16_, }, //
					{ S14_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16_, }, //
					{ S14_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16_, }, //
					{ S14_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16_, }, //
					{ S14_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16_, }, //
					{ S17_, S18_, S19_, _VD_, _VD_, S17_, S18_, S19_, }, //
			}, {//
				//
					{ S11c, S12c, S12c, S12c, S12c, S12c, S12c, S13c, },//
					{ S14d, S25_, S25_, S25_, S25_, S25_, S25_, S16d, },//
					{ S14d, S25_, S25_, S25_, S25_, S25_, S25_, S16d, },//
					{ S14d, S25_, S25_, S25_, S25_, S25_, S25_, S16d, },//
					{ S14d, S25_, S25_, S25_, S25_, S25_, S25_, S16d, },//
					{ S14d, S25_, S25_, S25_, S25_, S25_, S25_, S16d, },//
					{ S14d, S25_, S25_, S25_, S25_, S25_, S25_, S16d, },//
					{ S17c, S18c, S18c, S18c, S18c, S18c, S18c, S19c, }, //
			}, {//
				//
					{ S21_, S22_, S22_, S22_, S22_, S22_, S22_, S23_, },//
					{ S24_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S24_, }, //
					{ S24_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S24_, }, //
					{ S24_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S24_, }, //
					{ S24_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S24_, }, //
					{ S24_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S24_, }, //
					{ S24_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S24_, }, //
					{ S27_, S22_, S22_, S22_, S22_, S22_, S22_, S29_, }, //
			}, };
	public static final Vec3D SKIRT_OFFSET_POS = Vec3D.createVectorHelper(0.5,
			3, 0.5);
	public static final Vec3D SKIRT_JOINT_POS = Vec3D.createVectorHelper(0.5,
			-3, 0.5);

	public static final ChunkCoordinates LEG_SIZE = new ChunkCoordinates(3, 8,
			4);

	public static final IMEMPlanBlockInfo L_1_ = new MEMPlanDefaultFrame(LEG_,
			null);
	public static final IMEMPlanBlockInfo L_2_ = new MEMPlanDefaultFrame(SHUE,
			null);
	public static final IMEMPlanBlockInfo[][][] LEG = new IMEMPlanBlockInfo[][][] { //
	{//
		//
					{ _VD_, _VD_, _VD_, },//
					{ _VD_, _VD_, _VD_, },//
					{ _VD_, _VD_, _VD_, },//
					{ _VD_, _VD_, _VD_, },//
			}, {//
				//
					{ _VD_, _VD_, _VD_, },//
					{ _VD_, _VD_, _VD_, },//
					{ _VD_, _VD_, _VD_, },//
					{ _VD_, _VD_, _VD_, },//
			}, {//
				//
					{ _VD_, _VD_, _VD_, },//
					{ _VD_, _VD_, _VD_, },//
					{ _VD_, _VD_, _VD_, },//
					{ _VD_, _VD_, _VD_, },//
			}, {//
				//
					{ _VD_, _VD_, _VD_, },//
					{ _VD_, _VD_, _VD_, },//
					{ _VD_, _VD_, _VD_, },//
					{ _VD_, _VD_, _VD_, },//
			}, {//
				//
					{ _VD_, _VD_, _VD_, },//
					{ _VD_, _VD_, _VD_, },//
					{ _VD_, _VD_, _VD_, },//
					{ _VD_, _VD_, _VD_, },//
			}, {//
				//
					{ L_1_, L_1_, L_1_, },//
					{ L_1_, _VD_, L_1_, },//
					{ L_1_, _VD_, L_1_, },//
					{ L_1_, L_1_, L_1_, },//
			}, {//
				//
					{ L_1_, L_1_, L_1_, },//
					{ L_1_, _VD_, L_1_, },//
					{ L_1_, _VD_, L_1_, },//
					{ L_1_, L_1_, L_1_, },//
			}, {//
				//
					{ L_2_, L_2_, L_2_, },//
					{ L_2_, L_2_, L_2_, },//
					{ L_2_, L_2_, L_2_, },//
					{ L_2_, L_2_, L_2_, },//
			}, };
	public static final Vec3D LEG_LEFT_OFFSET_POS = Vec3D.createVectorHelper(0,
			4, 0.5);
	public static final Vec3D LEG_LEFT_JOINT_POS = Vec3D.createVectorHelper(-1,
			1, 0.5);
	public static final Vec3D LEG_RIGHT_OFFSET_POS = Vec3D.createVectorHelper(
			0, 4, 0.5);
	public static final Vec3D LEG_RIGHT_JOINT_POS = Vec3D.createVectorHelper(2,
			1, 0.5);

	public static final ChunkCoordinates ARM_SIZE = new ChunkCoordinates(2, 8,
			2);
	public static final IMEMPlanBlockInfo A_1_ = new MEMPlanDefaultFrame(B_BL,
			null);
	public static final IMEMPlanBlockInfo A_2_ = new MEMPlanDefaultFrame(WHT_,
			null);
	public static final IMEMPlanBlockInfo A_3_ = new MEMPlanDefaultFrame(SKIN,
			null);
	public static final IMEMPlanBlockInfo[][][] ARM = new IMEMPlanBlockInfo[][][] { //
	{//
		//
					{ A_1_, A_1_, },//
					{ A_1_, A_1_, },//
			}, {//
				//
					{ A_1_, A_1_, },//
					{ A_1_, A_1_, },//
			}, {//
				//
					{ A_2_, A_2_, },//
					{ A_2_, A_2_, },//
			}, {//
				//
					{ A_3_, A_3_, },//
					{ A_3_, A_3_, },//
			}, {//
				//
					{ A_3_, A_3_, },//
					{ A_3_, A_3_, },//
			}, {//
				//
					{ A_3_, A_3_, },//
					{ A_3_, A_3_, },//
			}, {//
				//
					{ A_3_, A_3_, },//
					{ A_3_, A_3_, },//
			}, {//
				//
					{ A_3_, A_3_, },//
					{ A_3_, A_3_, },//
			}, };
	public static final Vec3D ARM_LEFT_OFFSET_POS = Vec3D.createVectorHelper(
			1.5, 3, 0.5);
	public static final Vec3D ARM_LEFT_JOINT_POS = Vec3D.createVectorHelper(
			-2.5, 1, 0.5);
	public static final Vec3D ARM_RIGHT_OFFSET_POS = Vec3D.createVectorHelper(
			-0.5, 3, 0.5);
	public static final Vec3D ARM_RIGHT_JOINT_POS = Vec3D.createVectorHelper(
			3.5, 1, 0.5);
}
