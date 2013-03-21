package net.minecraft.src;

import java.util.Map;
import java.util.logging.Level;

public class mod_MaidExMachina extends BaseMod {

	@MLProp(min = 1, max = 32000)
	public static int sugarCore_ItemID = 22326;

	@MLProp(min = 32, max = 255)
	public static int BIOME_VOID_ID = 201;

	public static Item itemSugarCore;

	public static MEMBuilder MEM_BUILDER = new MEMBuilder();

	public static KeyBinding KEY_DRILL = new KeyBinding("Robo.Drill", 19);

	public mod_MaidExMachina() {
	}

	@Override
	public String getVersion() {
		return "1.2.5-1";
	}

	@Override
	public void load() {
		itemSugarCore = (new ItemSugarCore(sugarCore_ItemID)).setIconCoord(14,
				0).setItemName("sugarCore");

		ModLoader.addName(itemSugarCore, "Sugar Core");
		ModLoader.addName(itemSugarCore, "ja_JP", "砂糖コア");

		ModLoader.addRecipe(new ItemStack(itemSugarCore, 1), new Object[] {
				"CCC", "CRC", "CCC", Character.valueOf('C'), Item.cake,
				Character.valueOf('R'), Item.redstone, });

		MEM_BUILDER.registFactory(EntityCoreSugarHead.FACTORY);
		MEM_BUILDER.registFactory(EntityCoreSugarSkirt.FACTORY);
		MEM_BUILDER.registFactory(EntityCoreSugarArm.FACTORY);
		MEM_BUILDER.registFactory(EntityCoreSugarRightArm.FACTORY);
		MEM_BUILDER.registFactory(EntityCoreSugarLeg.FACTORY);
		MEM_BUILDER.registFactory(EntityCoreSugarHair.FACTORY);

		ModLoader.registerEntityID(EntityCoreSugarBody.class, "MEMBody",
				ModLoader.getUniqueEntityId());

		ModLoader.registerEntityID(EntityLittleMaidevice.class,
				"LittleMaidevice", ModLoader.getUniqueEntityId());

		ModLoader.registerKey(this, KEY_DRILL, false);
	}

	@Override
	public void addRenderer(Map map) {
		RenderCoreSugar.entityRenderMap.put(
				net.minecraft.src.EntityMEMBlockWrapper.class,
				new RenderMEMBlockWrapper());
		RenderCoreSugar.entityRenderMap.put(
				net.minecraft.src.EntityMEMFrame.class, new RenderMEMFrame());
		map.put(net.minecraft.src.EntityCoreSugarParts.class,
				new RenderCoreSugarParts());
		map.put(net.minecraft.src.EntityMEMFrame.class, new RenderMEMFrame());

		map.put(net.minecraft.src.EntityLittleMaidevice.class,
				new RenderLittleMaidevice());
	}

	private void loggingExeption(Throwable thrown) {
		ModLoader.getLogger().log(Level.INFO, thrown.getMessage(), thrown);
	}

	@Override
	public void modsLoaded() {

		MaidExChangeSupport.registMaidExChangeSupportType(
				EntityLittleMaidevice.class,
				EntityLittleMaidevice.CHANGE_SUPPORT_DEVICE);

	}
}
