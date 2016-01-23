package ministry.minebetter;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies = "required-before:biomesoplenty")
public class MineBetter {

    @EventHandler
    public void preInit(FMLPreInitializationEvent e)
    {

    }
    @EventHandler
    public void Init (FMLInitializationEvent e)
    {

    }
    @EventHandler
    public void postInit(FMLPostInitializationEvent e)
    {
        Block newBlock = new Block(Material.iron);
    }
}
