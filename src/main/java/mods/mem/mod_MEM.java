package mods.mem;

/**
 * Created by dha_lo_jd on 2016/12/04.
 */

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

@Mod(modid = "MaidExMachina")
public class mod_MEM {
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

        GameRegistry.addRecipe(new ItemStack(Items.diamond),
                "#",
                "#",
                "#",
                '#', Blocks.dirt
        );

        GameRegistry.addRecipe(new ItemStack(Items.diamond),
                "###",
                '#', Blocks.dirt
        );

        GameRegistry.addRecipe(new ItemStack(Items.diamond),
                "# #",
                '#', Blocks.dirt
        );

        GameRegistry.addRecipe(new ItemStack(Items.ender_pearl),
                "FWF",
                "W W",
                "FWF",
                'F', Items.feather,
                'W', Blocks.end_stone
        );

        GameRegistry.addRecipe(new ItemStack(Blocks.wool, 5),
                "LWL",
                'L', Blocks.leaves,
                'W', Blocks.web
        );

        GameRegistry.addRecipe(new ItemStack(Blocks.wool, 5, 10),
                "LLL",
                "LWL",
                "LLL",
                'L', Blocks.leaves,
                'W', Blocks.web
        );

        GameRegistry.addRecipe(new ItemStack(Items.diamond_pickaxe),
                "HD",
                'H', new ItemStack(Items.diamond_hoe, 1, OreDictionary.WILDCARD_VALUE),
                'D', Items.diamond
        );

        GameRegistry.addShapelessRecipe(new ItemStack(Items.diamond),
                new ItemStack(Blocks.wool, 1, 5),
                Items.feather,
                Blocks.web
        );

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }
}
