package mods.mem.plan;

public class MEMPlanDefaultFrame implements IMEMPlanBlockInfo {

    int color;
    String texture;

    public MEMPlanDefaultFrame(int color, String texture) {
        this.color = color;
        this.texture = texture;
    }

    @Override
    public void setInfo(EntityMEMFrame frame) {
        frame.setColors(new int[]{color, color, color, color, color, color,});
        frame.setTextures(new String[]{texture, texture, texture, texture,
                texture, texture,});
    }
}
