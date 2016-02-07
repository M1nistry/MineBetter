package ministry.minebetter.common.init;

import ministry.minebetter.common.util.entity.TileEntityCoalDrill;
import ministry.minebetter.common.util.entity.TileEntityYield;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockTileEntities {

    public static void register()
    {

        GameRegistry.registerTileEntity(TileEntityYield.class, "mbYield");
        GameRegistry.registerTileEntity(TileEntityCoalDrill.class, "mbCoalDrill");
    }
}
