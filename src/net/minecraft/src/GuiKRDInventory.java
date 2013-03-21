package net.minecraft.src;

public class GuiKRDInventory extends GuiLittleMaidInventory {

	private static final int TAB_X = 0;
	private static final int TAB_Y = -16;

	private static final int TAB_W = 24;
	private static final int TAB_H = 16;

	private final KRDGuiJobDelegate jobDelegate;

	public GuiKRDInventory(EntityLittleMaidevice entitylittlemaid1,
			IInventory iinventory, InventoryLittleMaid inventorylittlemaid,
			KRDGuiJobDelegate jobDelegate) {
		super(entitylittlemaid1, iinventory, inventorylittlemaid);
		this.jobDelegate = jobDelegate;
	}

	@Override
	protected void drawGuiContainerForegroundLayer() {
		super.drawGuiContainerForegroundLayer();
		fontRenderer.drawString(StatCollector.translateToLocal("AP"), 86, 32,
				0x404040);
	}

	/**
	 * Returns if the passed mouse position is over the specified slot.
	 */
	private boolean isMouseOverSlot(Slot par1Slot, int par2, int par3) {
		int i = guiLeft;
		int j = guiTop;
		par2 -= i;
		par3 -= j;
		return par2 >= par1Slot.xDisplayPosition - 1
				&& par2 < par1Slot.xDisplayPosition + 16 + 1
				&& par3 >= par1Slot.yDisplayPosition - 1
				&& par3 < par1Slot.yDisplayPosition + 16 + 1;
	}

}
