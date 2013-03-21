package net.minecraft.src;

public class KRDGuiJobDelegate extends MaidJobDelegate<EntityLittleMaidevice>
		implements IMaidJobMaidInventory<EntityLittleMaidevice> {

	boolean isAccounter;
	boolean prevMode;

	public KRDGuiJobDelegate(EntityLittleMaidevice maid) {
		super(maid);
	}

	@Override
	public GuiLittleMaidInventory getSwitchInventory(
			EntityLittleMaidevice entitylittlemaid1, IInventory iinventory,
			InventoryLittleMaid inventorylittlemaid) {
		if (!isAccounter) {
			return null;
		}
		return new GuiKRDInventory(entitylittlemaid1, iinventory,
				inventorylittlemaid, this);
	}

	@Override
	protected void doUpdateJob(EntityLittleMaidevice maid) {
	}

}
