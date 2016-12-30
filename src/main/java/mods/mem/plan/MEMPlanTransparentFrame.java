package mods.mem.plan;

public class MEMPlanTransparentFrame extends MEMPlanDefaultFrame {

    public static final MEMPlanTransparentFrame FRAME = new MEMPlanTransparentFrame();

    protected MEMPlanTransparentFrame() {
        super(0, null);
    }

    @Override
    public void setInfo(EntityMEMFrame frame) {
        super.setInfo(frame);
        frame.setFaces(new RenderFace[]{RenderFace.NOTHING,
                RenderFace.NOTHING, RenderFace.NOTHING, RenderFace.NOTHING,
                RenderFace.NOTHING, RenderFace.NOTHING,});
    }
}
