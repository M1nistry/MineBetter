package ministry.minebetter;

import ministry.minebetter.common.block.BlockDenseIron;
import ministry.minebetter.common.handler.BlockEventHandler;
import ministry.minebetter.common.init.BlockTileEntities;
import ministry.minebetter.common.init.ModBlocks;
import ministry.minebetter.common.init.ModItems;
import ministry.minebetter.common.util.block.BlockManager;
import ministry.minebetter.common.util.entity.TileEntityYield;
import ministry.minebetter.crafting.ModCrafting;
import ministry.minebetter.proxy.CommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class MineBetter {

    @SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent e)
    {
        ModBlocks.init();
        ModItems.init();
        ModCrafting.init();
        BlockTileEntities.register();
        GameRegistry.registerWorldGenerator(new BlockManager(), 3);
    }
    @EventHandler
    public void Init (FMLInitializationEvent e)
    {
        proxy.registerRenders();
        MinecraftForge.EVENT_BUS.register(new BlockEventHandler());
    }
    @EventHandler
    public void postInit(FMLPostInitializationEvent e)
    {

    }

}
