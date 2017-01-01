package mods.mem.plan;

import net.minecraft.util.ChunkCoordinates;

/**
 * Created by dhaAsusAdmin on 2017/01/01.
 */
public class MEMPlanDefault {
    public static final IMEMPlanBlockInfo _VD_ = null;

    public static final int HAIR = 0xf6f6f6;
    public static final int LBN_ = 0xff8cdc;
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
            0xffffff, 0xdedede, 0x004e93, 0x404050 };
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

    private static IMEMPlanBlockInfo H(int color, int[] faces) {
        Integer[] colors = MEMPlanOptionalQuadFrame.getFaceArray(color, null,
                new Integer[] {}, faces);
        String[] textures = MEMPlanOptionalQuadFrame.getFaceArray(null,
                IMEMPlanBlockInfo.EMPTY, new String[] {}, faces);
        return new MEMPlanOptionalQuadFrame(MEMPlanTransparentFrame.FRAME,
                colors, textures);
    }

    private static IMEMPlanBlockInfo H(Integer... colors) {
        String[] textures = new String[6];
        for (int i = 0; i < colors.length; i++) {
            Integer integer = colors[i];
            if (integer == null) {
                textures[i] = IMEMPlanBlockInfo.EMPTY;
            } else {
                textures[i] = null;
            }
        }
        return new MEMPlanOptionalQuadFrame(MEMPlanTransparentFrame.FRAME,
                colors, textures);
    }

    private static IMEMPlanBlockInfo P(int color, int[] faces) {
        Integer[] colors = MEMPlanOptionalQuadFrame.getFaceArray(color, null,
                new Integer[] {}, faces);
        String[] textures = MEMPlanOptionalQuadFrame.getFaceArray(null,
                IMEMPlanBlockInfo.EMPTY, new String[] {}, faces);
        MEMPlanOptionalQuadFrame quadFrame = new MEMPlanOptionalQuadFrame(
                MEMPlanTransparentFrame.FRAME, colors, textures);
        quadFrame.setFaces(MEMPlanOptionalQuadFrame
                .getFaceArray(RenderFace.BOTH, RenderFace.NOTHING,
                        new RenderFace[] {}, faces));
        return quadFrame;
    }

    private static IMEMPlanBlockInfo P(Integer... colors) {
        String[] textures = new String[6];
        RenderFace[] faces = new RenderFace[6];
        for (int i = 0; i < colors.length; i++) {
            Integer integer = colors[i];
            if (integer == null) {
                textures[i] = IMEMPlanBlockInfo.EMPTY;
                faces[i] = null;
            } else {
                textures[i] = null;
                faces[i] = RenderFace.BOTH;
            }
        }
        MEMPlanOptionalQuadFrame quadFrame = new MEMPlanOptionalQuadFrame(
                MEMPlanTransparentFrame.FRAME, colors, textures);
        quadFrame.setFaces(faces);
        return quadFrame;
    }

    public static final IMEMPlanBlockInfo H_1_ = I(RED_, TNE);
    public static final IMEMPlanBlockInfo H_2_ = I(RED_, TN_);
    public static final IMEMPlanBlockInfo H_3_ = I(RED_, TNW);
    public static final IMEMPlanBlockInfo H_4_ = I(RED_, T_E);
    public static final IMEMPlanBlockInfo H_5_ = I(RED_, T__);
    public static final IMEMPlanBlockInfo H_6_ = I(RED_, T_W);
    public static final IMEMPlanBlockInfo H_7_ = I(RED_, TSE);
    public static final IMEMPlanBlockInfo H_8_ = I(RED_, TS_);
    public static final IMEMPlanBlockInfo H_9_ = I(RED_, TSW);

    public static final IMEMPlanBlockInfo H_a_ = I(RED_, null, WHT_, null,
            null, null);

    public static final IMEMPlanBlockInfo H_b_ = I(RED_, null, null, null,
            HAIR, HAIR);

    public static final IMEMPlanBlockInfo H_c_ = I(RED_, null, HAIR, HAIR,
            null, null);

    public static final IMEMPlanBlockInfo H10_ = I(HAIR, _NE);
    public static final IMEMPlanBlockInfo H10a = I(RED_, _NE);
    public static final IMEMPlanBlockInfo H10b = I(WHT_, _NE);
    public static final IMEMPlanBlockInfo H11_ = I(RED_, _N_);
    public static final IMEMPlanBlockInfo H11a = I(HAIR, _N_);
    public static final IMEMPlanBlockInfo H11b = I(WHT_, _N_);
    public static final IMEMPlanBlockInfo H12_ = I(HAIR, _NW);
    public static final IMEMPlanBlockInfo H12a = I(RED_, _NW);
    public static final IMEMPlanBlockInfo H12b = I(WHT_, _NW);
    public static final IMEMPlanBlockInfo H13_ = I(HAIR, __E);
    public static final IMEMPlanBlockInfo H13a = I(RED_, __E);
    public static final IMEMPlanBlockInfo H13b = I(WHT_, __E);
    public static final IMEMPlanBlockInfo H14_ = I(HAIR, __W);
    public static final IMEMPlanBlockInfo H14a = I(RED_, __W);
    public static final IMEMPlanBlockInfo H14b = I(WHT_, __W);
    public static final IMEMPlanBlockInfo H15_ = I(HAIR, _SE);
    public static final IMEMPlanBlockInfo H15a = I(RED_, _SE);
    public static final IMEMPlanBlockInfo H15b = I(WHT_, _SE);
    public static final IMEMPlanBlockInfo H16_ = I(HAIR, _S_);
    public static final IMEMPlanBlockInfo H16a = I(RED_, _S_);
    public static final IMEMPlanBlockInfo H16b = I(WHT_, _S_);
    public static final IMEMPlanBlockInfo H17_ = I(HAIR, _SW);
    public static final IMEMPlanBlockInfo H17a = I(RED_, _SW);
    public static final IMEMPlanBlockInfo H17b = I(WHT_, _SW);

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
    public static final IMEMPlanBlockInfo H40b = I(WHT_, BS_);
    public static final IMEMPlanBlockInfo H41_ = I(HAIR, BSW);

    public static final ChunkCoordinates HEAD_SIZE = new ChunkCoordinates(8, 8,
            8);
    public static final IMEMPlanBlockInfo[][][] HEAD = new IMEMPlanBlockInfo[][][] {//
            {
                    //
                    { H_1_, H_2_, H_2_, H_2_, H_2_, H_2_, H_2_, H_3_, },
                    { H_4_, H_5_, H_5_, H_5_, H_5_, H_5_, H_5_, H_6_, },
                    { H_4_, H_5_, H_5_, H_5_, H_5_, H_5_, H_5_, H_6_, },
                    { H_4_, H_5_, H_5_, H_5_, H_5_, H_5_, H_5_, H_6_, },
                    { H_4_, H_5_, H_5_, H_5_, H_5_, H_5_, H_5_, H_6_, },
                    { H_4_, H_5_, H_5_, H_5_, H_5_, H_5_, H_5_, H_6_, },
                    { H_4_, H_5_, H_5_, H_5_, H_5_, H_5_, H_5_, H_6_, },
                    { H_7_, H_8_, H_8_, H_8_, H_8_, H_8_, H_8_, H_9_, }, //
            }, {//
            //
            { H10a, H11_, H11b, H11b, H11b, H11b, H11_, H12a, }, //
            { H13a, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14a, }, //
            { H13a, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14a, }, //
            { H13a, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14a, }, //
            { H13a, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14a, }, //
            { H13a, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14a, }, //
            { H13a, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14a, }, //
            { H15a, H16a, H16a, H16a, H16a, H16a, H16a, H17a, },//
    }, {//
            //
            { H10b, H11b, H11a, H18_, H18_, H11a, H11b, H12b, }, //
            { H13b, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14b, }, //
            { H13b, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14b, }, //
            { H13b, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14b, }, //
            { H13a, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14a, }, //
            { H13a, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14a, }, //
            { H13a, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14a, }, //
            { H15a, H16a, H16a, H16a, H16a, H16a, H16a, H17a, },//
    }, {//
            //
            { H10_, H11a, H18_, H18_, H18_, H18_, H11a, H12_, }, //
            { H13_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14_, }, //
            { H13_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14_, }, //
            { H13_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14_, }, //
            { H13b, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14b, }, //
            { H13b, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14b, }, //
            { H13b, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, H14b, }, //
            { H15b, H16b, H16b, H40b, H40b, H16b, H16b, H17b, },//
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

    public static final IMEMPlanBlockInfo HA1_ = P(HAIR, _SE);
    public static final IMEMPlanBlockInfo HA2_ = P(HAIR, _S_);
    public static final IMEMPlanBlockInfo HA3_ = P(HAIR, _SW);
    public static final IMEMPlanBlockInfo HA4_ = P(HAIR, __E);
    public static final IMEMPlanBlockInfo HA6_ = P(HAIR, __W);

    public static final IMEMPlanBlockInfo HAal = H(W_GR, TNE);
    public static final IMEMPlanBlockInfo HAbl = H(WHT_, T_E);
    public static final IMEMPlanBlockInfo HAcl = H(W_GR, TSE);
    public static final IMEMPlanBlockInfo HAdl = H(WHT_, _NE);
    public static final IMEMPlanBlockInfo HAel = H(WHT_, __E);
    public static final IMEMPlanBlockInfo HAfl = H(WHT_, _SE);
    public static final IMEMPlanBlockInfo HAgl = H(W_GR, BNE);
    public static final IMEMPlanBlockInfo HAhl = H(WHT_, B_E);
    public static final IMEMPlanBlockInfo HAil = H(W_GR, BSE);

    public static final IMEMPlanBlockInfo HAar = H(W_GR, TNW);
    public static final IMEMPlanBlockInfo HAbr = H(WHT_, T_W);
    public static final IMEMPlanBlockInfo HAcr = H(W_GR, TSW);
    public static final IMEMPlanBlockInfo HAdr = H(WHT_, _NW);
    public static final IMEMPlanBlockInfo HAer = H(WHT_, __W);
    public static final IMEMPlanBlockInfo HAfr = H(WHT_, _SW);
    public static final IMEMPlanBlockInfo HAgr = H(W_GR, BNW);
    public static final IMEMPlanBlockInfo HAhr = H(WHT_, B_W);
    public static final IMEMPlanBlockInfo HAir = H(W_GR, BSW);

    public static final IMEMPlanBlockInfo HAas = H(GRAY, T_E);
    public static final IMEMPlanBlockInfo HAbs = H(W_GR, T__);
    public static final IMEMPlanBlockInfo HAcs = H(GRAY, T_W);
    public static final IMEMPlanBlockInfo HAds = H(W_GR, __E);
    public static final IMEMPlanBlockInfo HAfs = H(W_GR, __W);
    public static final IMEMPlanBlockInfo HAgs = H(GRAY, B_E);
    public static final IMEMPlanBlockInfo HAhs = H(W_GR, B__);
    public static final IMEMPlanBlockInfo HAis = H(GRAY, B_W);

    public static final IMEMPlanBlockInfo HAat = H(WHT_, null, null, W_GR,
            WHT_, null);
    public static final IMEMPlanBlockInfo HAbt = H(WHT_, TS_);
    public static final IMEMPlanBlockInfo HAct = H(WHT_, null, null, W_GR,
            null, WHT_);
    public static final IMEMPlanBlockInfo HAdt = H(WHT_, _SE);
    public static final IMEMPlanBlockInfo HAet = H(WHT_, _S_);
    public static final IMEMPlanBlockInfo HAft = H(WHT_, _SW);
    public static final IMEMPlanBlockInfo HAgt = H(null, WHT_, null, W_GR,
            WHT_, null);
    public static final IMEMPlanBlockInfo HAht = H(WHT_, BS_);
    public static final IMEMPlanBlockInfo HAit = H(null, WHT_, null, W_GR,
            null, WHT_);

    public static final IMEMPlanBlockInfo HAww = H(WHT_, WHT_, WHT_, WHT_,
            WHT_, WHT_);

    public static final ChunkCoordinates HAIR1_SIZE = new ChunkCoordinates(10,
            8, 6);

    public static final IMEMPlanBlockInfo[][] HAIR_PARTS_Y0 = new IMEMPlanBlockInfo[][] {
            { HAal, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, HAar, },
            { HAbl, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, HAbr, },
            { HAcl, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, HAcr, },
            { _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },
            { _VD_, _VD_, _VD_, _VD_, HAww, HAww, _VD_, _VD_, _VD_, _VD_, },
            { _VD_, _VD_, _VD_, _VD_, HAww, HAww, _VD_, _VD_, _VD_, _VD_, },//
    };
    public static final IMEMPlanBlockInfo[][] HAIR_PARTS_Y1 = new IMEMPlanBlockInfo[][] {
            { HAdl, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, HAdr, },
            { HAel, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, HAer, },
            { HAfl, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, HAfr, },
            { _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },
            { _VD_, _VD_, _VD_, _VD_, HAww, HAww, _VD_, _VD_, _VD_, _VD_, },
            { _VD_, _VD_, _VD_, _VD_, HAww, HAww, _VD_, _VD_, _VD_, _VD_, },//
    };
    public static final IMEMPlanBlockInfo[][] HAIR_PARTS_Y2 = new IMEMPlanBlockInfo[][] {
            { HAgl, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, HAgr, },
            { HAhl, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, HAhr, },
            { HAil, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, HAir, },
            { _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },
            { _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },
            { _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },//
    };
    public static final IMEMPlanBlockInfo[][] HAIR_PARTS_Y3 = new IMEMPlanBlockInfo[][] {
            { _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },
            { _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },
            { _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },
            { _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },
            { _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },
            { _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },//
    };
    public static final IMEMPlanBlockInfo[][] HAIR_PARTS_Y4 = new IMEMPlanBlockInfo[][] {
            { _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },
            { _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },
            { _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },
            { _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },
            { _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },
            { _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },//
    };
    public static final IMEMPlanBlockInfo[][] HAIR_PARTS_Y5 = new IMEMPlanBlockInfo[][] {
            { _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },
            { _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },
            { _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },
            { _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },
            { _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },
            { _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },//
    };
    public static final IMEMPlanBlockInfo[][] HAIR_PARTS_Y6 = new IMEMPlanBlockInfo[][] {
            { _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },
            { _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },
            { _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },
            { _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },
            { _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },
            { _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },//
    };
    public static final IMEMPlanBlockInfo[][] HAIR_PARTS_Y7 = new IMEMPlanBlockInfo[][] {
            { _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },
            { _VD_, HA4_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, HA6_, _VD_, },
            { _VD_, HA4_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, HA6_, _VD_, },
            { _VD_, HA1_, HA2_, HA2_, _VD_, _VD_, HA2_, HA2_, HA3_, _VD_, },
            { _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },
            { _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },//
    };
    public static final IMEMPlanBlockInfo[][][] HAIR_PARTS = new IMEMPlanBlockInfo[][][] {
            HAIR_PARTS_Y0,//
            HAIR_PARTS_Y1,//
            HAIR_PARTS_Y2,//
            HAIR_PARTS_Y3,//
            HAIR_PARTS_Y4,//
            HAIR_PARTS_Y5,//
            HAIR_PARTS_Y6,//
            HAIR_PARTS_Y7,//
    };
    public static final Vec3D HAIR_OFFSET_POS = Vec3D.createVectorHelper(0.5,
            0, -1.5);
    public static final Vec3D HAIR_JOINT_POS = Vec3D.createVectorHelper(0.5,
            -1, 1.5);

    public static final IMEMPlanBlockInfo HB1_ = new MEMPlanDefaultFrame(HAIR,
            null);
    public static final IMEMPlanBlockInfo HB2_ = new MEMPlanDefaultFrame(LBN_,
            null);
    public static final ChunkCoordinates HAIR2_SIZE = new ChunkCoordinates(11,
            9, 2);
    public static final IMEMPlanBlockInfo[][] HAIR2_PARTS_Y0 = new IMEMPlanBlockInfo[][] {
            { HB1_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, HB1_, },
            { HB1_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, HB1_, },//
    };
    public static final IMEMPlanBlockInfo[][] HAIR2_PARTS_Y1 = new IMEMPlanBlockInfo[][] {
            { HB1_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, HB1_, },
            { HB1_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, HB1_, },//
    };
    public static final IMEMPlanBlockInfo[][] HAIR2_PARTS_Y2 = new IMEMPlanBlockInfo[][] {
            { HB2_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, HB2_, },
            { HB2_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, HB2_, },//
    };
    public static final IMEMPlanBlockInfo[][] HAIR2_PARTS_Y3 = new IMEMPlanBlockInfo[][] {
            { HB1_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, HB1_, },
            { HB1_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, HB1_, },//
    };
    public static final IMEMPlanBlockInfo[][] HAIR2_PARTS_Y4 = new IMEMPlanBlockInfo[][] {
            { HB1_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, HB1_, },
            { HB1_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, HB1_, },//
    };
    public static final IMEMPlanBlockInfo[][] HAIR2_PARTS_Y5 = new IMEMPlanBlockInfo[][] {
            { HB1_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, HB1_, },
            { _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },//
    };
    public static final IMEMPlanBlockInfo[][] HAIR2_PARTS_Y6 = new IMEMPlanBlockInfo[][] {
            { HB1_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, HB1_, },
            { _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },//
    };
    public static final IMEMPlanBlockInfo[][] HAIR2_PARTS_Y7 = new IMEMPlanBlockInfo[][] {
            { HB1_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, HB1_, },
            { _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },//
    };
    public static final IMEMPlanBlockInfo[][] HAIR2_PARTS_Y8 = new IMEMPlanBlockInfo[][] {
            { HB1_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, HB1_, },
            { _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, },//
    };
    public static final IMEMPlanBlockInfo[][][] HAIR2_PARTS = new IMEMPlanBlockInfo[][][] {
            HAIR2_PARTS_Y0,//
            HAIR2_PARTS_Y1,//
            HAIR2_PARTS_Y2,//
            HAIR2_PARTS_Y3,//
            HAIR2_PARTS_Y4,//
            HAIR2_PARTS_Y5,//
            HAIR2_PARTS_Y6,//
            HAIR2_PARTS_Y7,//
            HAIR2_PARTS_Y8,//
    };
    public static final Vec3D HAIR2_OFFSET_POS = Vec3D.createVectorHelper(0,
            3.5, 0.5);
    public static final Vec3D HAIR2_JOINT_POS = Vec3D.createVectorHelper(0.5,
            2, 2);

    public static final ChunkCoordinates BODY_SIZE = new ChunkCoordinates(6, 5,
            4);
    public static final Vec3D DEVICE_POS = Vec3D.createVectorHelper(0.5, -2.5,
            0.5);

    public static final IMEMPlanBlockInfo B_1_ = I(RED_, TNE);
    public static final IMEMPlanBlockInfo B_2_ = I(WHT_, TN_);
    public static final IMEMPlanBlockInfo B_2a = I(WHT_, TN_);
    public static final IMEMPlanBlockInfo B_3_ = I(RED_, TNW);
    public static final IMEMPlanBlockInfo B_4_ = I(RED_, T_E);
    public static final IMEMPlanBlockInfo B_5_ = I(RED_, T__);
    public static final IMEMPlanBlockInfo B_5a = I(RED_, T__);
    public static final IMEMPlanBlockInfo B_6_ = I(RED_, T_W);
    public static final IMEMPlanBlockInfo B_7_ = I(RED_, TSE);
    public static final IMEMPlanBlockInfo B_8_ = I(WHT_, TS_);
    public static final IMEMPlanBlockInfo B_9_ = I(RED_, TSW);

    public static final IMEMPlanBlockInfo B11_ = I(RED_, _NE);
    public static final IMEMPlanBlockInfo B12_ = I(RED_, _N_);
    public static final IMEMPlanBlockInfo B12a = I(RED_, _N_);
    public static final IMEMPlanBlockInfo B13_ = I(RED_, _NW);
    public static final IMEMPlanBlockInfo B14_ = I(RED_, __E);
    public static final IMEMPlanBlockInfo B16_ = I(RED_, __W);
    public static final IMEMPlanBlockInfo B17_ = I(RED_, _SE);
    public static final IMEMPlanBlockInfo B18_ = I(RED_, _S_);
    public static final IMEMPlanBlockInfo B19_ = I(RED_, _SW);

    public static final IMEMPlanBlockInfo B12d = I(WHT_, _N_);

    public static final IMEMPlanBlockInfo B18b = I(RED_, BS_);

    public static final IMEMPlanBlockInfo B11b = I(RED_, _NE);
    public static final IMEMPlanBlockInfo B12b = I(RED_, _N_);
    public static final IMEMPlanBlockInfo B13b = I(RED_, _NW);
    public static final IMEMPlanBlockInfo B14b = I(RED_, __E);
    public static final IMEMPlanBlockInfo B16b = I(RED_, __W);

    public static final IMEMPlanBlockInfo B11c = I(RED_, _NE);
    public static final IMEMPlanBlockInfo B12c = I(RED_, _N_);
    public static final IMEMPlanBlockInfo B13c = I(RED_, _NW);
    public static final IMEMPlanBlockInfo B14c = I(RED_, __E);
    public static final IMEMPlanBlockInfo B16c = I(RED_, __W);
    public static final IMEMPlanBlockInfo B17c = I(RED_, _SE);
    public static final IMEMPlanBlockInfo B19c = I(RED_, _SW);

    public static final IMEMPlanBlockInfo B11d = I(RED_, _NE);
    public static final IMEMPlanBlockInfo B13d = I(RED_, _NW);

    public static final IMEMPlanBlockInfo B_ad = I(RED_, _NW);
    public static final IMEMPlanBlockInfo B_bd = I(RED_, _NE);

    public static final IMEMPlanBlockInfo B17d = I(RED_, _SE);
    public static final IMEMPlanBlockInfo B19d = I(RED_, _SW);

    public static final IMEMPlanBlockInfo[][][] BODY = new IMEMPlanBlockInfo[][][] {//
            {
                    //
                    { B_1_, B_2_, B_2a, B_2a, B_2_, B_3_, },//
                    { B_4_, B_5_, B_5a, B_5a, B_5_, B_6_, },//
                    { B_4_, B_5_, B_5a, B_5a, B_5_, B_6_, },//
                    { B_7_, B_8_, B_8_, B_8_, B_8_, B_9_, },//
            }, {
            //
            { B_ad, B12_, B12d, B12d, B12_, B_bd, },//
            { B14_, _VD_, _VD_, _VD_, _VD_, B16_, },//
            { B14_, _VD_, _VD_, _VD_, _VD_, B16_, },//
            { B17_, B18_, B18_, B18_, B18_, B19_, },//
    }, {
            //
            { B11d, B12_, B12d, B12d, B12_, B13d, },//
            { B14_, _VD_, _VD_, _VD_, _VD_, B16_, },//
            { B14_, _VD_, _VD_, _VD_, _VD_, B16_, },//
            { B17_, B18_, B18b, B18b, B18_, B19_, },//
    }, {
            //
            { B11b, B12b, B12d, B12d, B12b, B13b, },//
            { B14b, _VD_, _VD_, _VD_, _VD_, B16b, },//
            { B14_, _VD_, _VD_, _VD_, _VD_, B16_, },//
            { B17_, B19_, _VD_, _VD_, B17_, B19_, },//
    }, {
            //
            { B11c, B12c, B12d, B12d, B12c, B13c, },//
            { B14c, _VD_, _VD_, _VD_, _VD_, B16c, },//
            { B14c, _VD_, _VD_, _VD_, _VD_, B16c, },//
            { B17c, B19d, _VD_, _VD_, B17d, B19c, },//
    }, };

    public static final ChunkCoordinates SKIRT_SIZE = new ChunkCoordinates(8,
            9, 8);

    public static final IMEMPlanBlockInfo S_1_ = P(WHT_, TNE);
    public static final IMEMPlanBlockInfo S_2_ = P(WHT_, TN_);
    public static final IMEMPlanBlockInfo S_2a = P(WHT_, TN_);
    public static final IMEMPlanBlockInfo S_3_ = P(WHT_, TNW);
    public static final IMEMPlanBlockInfo S_4_ = P(WHT_, T_E);
    public static final IMEMPlanBlockInfo S_5_ = P(WHT_, T__);
    public static final IMEMPlanBlockInfo S_6_ = P(WHT_, T_W);
    public static final IMEMPlanBlockInfo S_4a = P(WHT_, T_E);
    public static final IMEMPlanBlockInfo S_5a = P(WHT_, T__);
    public static final IMEMPlanBlockInfo S_6a = P(WHT_, T_W);
    public static final IMEMPlanBlockInfo S_4b = P(WHT_, T_E);
    public static final IMEMPlanBlockInfo S_5b = P(WHT_, T__);
    public static final IMEMPlanBlockInfo S_6b = P(WHT_, T_W);
    public static final IMEMPlanBlockInfo S_7_ = P(WHT_, TSE);
    public static final IMEMPlanBlockInfo S_8_ = P(WHT_, TS_);
    public static final IMEMPlanBlockInfo S_8a = P(WHT_, TS_);
    public static final IMEMPlanBlockInfo S_9_ = P(WHT_, TSW);

    public static final IMEMPlanBlockInfo S_a_ = P(RED_, T__);

    public static final IMEMPlanBlockInfo S_5c = defaultFrame;

    public static final IMEMPlanBlockInfo S11_ = P(RED_, _NE);
    public static final IMEMPlanBlockInfo S12_ = P(RED_, _N_);
    public static final IMEMPlanBlockInfo S12a = P(RED_, _N_);
    public static final IMEMPlanBlockInfo S12b = P(RED_, _N_);
    public static final IMEMPlanBlockInfo S12c = P(RED_, _N_);
    public static final IMEMPlanBlockInfo S13_ = P(RED_, _NW);

    public static final IMEMPlanBlockInfo S11a = P(RED_, _NE);
    public static final IMEMPlanBlockInfo S13a = P(RED_, _NW);
    public static final IMEMPlanBlockInfo S11b = P(RED_, _NE);
    public static final IMEMPlanBlockInfo S13b = P(RED_, _NW);
    public static final IMEMPlanBlockInfo S11c = P(RED_, _NE);
    public static final IMEMPlanBlockInfo S13c = P(RED_, _NW);

    public static final IMEMPlanBlockInfo S14a = P(RED_, __E);
    public static final IMEMPlanBlockInfo S16a = P(RED_, __W);
    public static final IMEMPlanBlockInfo S14b = P(RED_, __E);
    public static final IMEMPlanBlockInfo S16b = P(RED_, __W);
    public static final IMEMPlanBlockInfo S14c = P(RED_, __E);
    public static final IMEMPlanBlockInfo S16c = P(RED_, __W);
    public static final IMEMPlanBlockInfo S14d = P(RED_, __E);
    public static final IMEMPlanBlockInfo S16d = P(RED_, __W);
    public static final IMEMPlanBlockInfo S14_ = P(RED_, __E);
    public static final IMEMPlanBlockInfo S16_ = P(RED_, __W);

    public static final IMEMPlanBlockInfo S17_ = P(RED_, _SE);
    public static final IMEMPlanBlockInfo S18_ = P(RED_, _S_);
    public static final IMEMPlanBlockInfo S18a = P(RED_, _S_);
    public static final IMEMPlanBlockInfo S18b = P(RED_, _S_);
    public static final IMEMPlanBlockInfo S19_ = P(RED_, _SW);

    public static final IMEMPlanBlockInfo S17c = P(RED_, _SE);
    public static final IMEMPlanBlockInfo S18c = P(RED_, _S_);
    public static final IMEMPlanBlockInfo S19c = P(RED_, _SW);

    public static final IMEMPlanBlockInfo S18d = P(RED_, BS_);

    public static final IMEMPlanBlockInfo S21_ = P(WHT_, _NE);
    public static final IMEMPlanBlockInfo S22_ = P(WHT_, _N_);
    public static final IMEMPlanBlockInfo S23_ = P(WHT_, _NW);

    public static final IMEMPlanBlockInfo S24_ = P(WHT_, __E);
    public static final IMEMPlanBlockInfo S25_ = _VD_;
    public static final IMEMPlanBlockInfo S26_ = P(WHT_, __W);

    public static final IMEMPlanBlockInfo S27_ = P(WHT_, _SE);
    public static final IMEMPlanBlockInfo S28_ = P(WHT_, _S_);
    public static final IMEMPlanBlockInfo S29_ = P(WHT_, _SW);

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
                    { S_4b, S_5b, S_5b, S_5b, S_5b, S_5b, S_5b, S_6b, },
                    { S_7_, S_8a, S_8a, S_8a, S_8a, S_8a, S_8a, S_9_, }, //
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
            { S17_, S18_, S18_, S18_, S18_, S18_, S18_, S19_, }, //
    }, {
            //
            { S11a, S12_, S12_, S12_, S12_, S12_, S12_, S13a, },//
            { S14c, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16c, }, //
            { S14_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16_, }, //
            { S14_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16_, }, //
            { S14_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16_, }, //
            { S14_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16_, }, //
            { S14_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16_, }, //
            { S17_, S18_, S18_, S18_, S18_, S18_, S18_, S19_, }, //
    }, {//
            //
            { S11b, S12b, S12b, S12b, S12b, S12b, S12b, S13b, },//
            { S14_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16_, }, //
            { S14_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16_, }, //
            { S14_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16_, }, //
            { S14_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16_, }, //
            { S14_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16_, }, //
            { S14_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S16_, }, //
            { S17_, S18_, S18_, S18_, S18_, S18_, S18_, S19_, }, //
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
            { S24_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S26_, }, //
            { S24_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S26_, }, //
            { S24_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S26_, }, //
            { S24_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S26_, }, //
            { S24_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S26_, }, //
            { S24_, _VD_, _VD_, _VD_, _VD_, _VD_, _VD_, S26_, }, //
            { S27_, S28_, S28_, S28_, S28_, S28_, S28_, S29_, }, //
    }, };
    public static final Vec3D SKIRT_OFFSET_POS = Vec3D.createVectorHelper(0.5,
            3, 0.5);
    public static final Vec3D SKIRT_JOINT_POS = Vec3D.createVectorHelper(0.5,
            -3, 0.5);

    public static final ChunkCoordinates LEG_SIZE = new ChunkCoordinates(3, 10,
            4);

    public static final IMEMPlanBlockInfo L_1_ = new MEMPlanDefaultFrame(LEG_,
            null);
    public static final IMEMPlanBlockInfo L_2_ = new MEMPlanDefaultFrame(SHUE,
            null);
    public static final IMEMPlanBlockInfo L_3_ = new MEMPlanDefaultFrame(WHT_,
            null);
    public static final IMEMPlanBlockInfo L_4_ = new MEMPlanDefaultFrame(SKIN,
            null);

    public static final IMEMPlanBlockInfo SP1_ = new MEMPlanDefaultFrame(BLK_,
            null);
    public static final IMEMPlanBlockInfo[][][] LEG = new IMEMPlanBlockInfo[][][] { //
            {//
                    //
                    { SP1_, SP1_, SP1_, },//
                    { SP1_, _VD_, SP1_, },//
                    { SP1_, _VD_, SP1_, },//
                    { SP1_, SP1_, SP1_, },//
            }, {//
            //
            { SP1_, SP1_, SP1_, },//
            { SP1_, _VD_, SP1_, },//
            { SP1_, _VD_, SP1_, },//
            { SP1_, SP1_, SP1_, },//
    }, {//
            //
            { SP1_, SP1_, SP1_, },//
            { SP1_, _VD_, SP1_, },//
            { SP1_, _VD_, SP1_, },//
            { SP1_, SP1_, SP1_, },//
    }, {//
            //
            { SP1_, SP1_, SP1_, },//
            { SP1_, _VD_, SP1_, },//
            { SP1_, _VD_, SP1_, },//
            { SP1_, SP1_, SP1_, },//
    }, {//
            //
            { L_4_, L_4_, L_4_, },//
            { L_4_, _VD_, L_4_, },//
            { L_4_, _VD_, L_4_, },//
            { L_4_, L_4_, L_4_, },//
    }, {//
            //
            { L_3_, L_3_, L_3_, },//
            { L_3_, _VD_, L_3_, },//
            { L_3_, _VD_, L_3_, },//
            { L_3_, L_3_, L_3_, },//
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
            1.99, 0.5);
    public static final Vec3D LEG_RIGHT_OFFSET_POS = Vec3D.createVectorHelper(
            0, 4, 0.5);
    public static final Vec3D LEG_RIGHT_JOINT_POS = Vec3D.createVectorHelper(2,
            1.99, 0.5);

    public static final ChunkCoordinates ARM_SIZE = new ChunkCoordinates(2, 8,
            2);
    public static final IMEMPlanBlockInfo A_1_ = new MEMPlanDefaultFrame(RED_,
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
