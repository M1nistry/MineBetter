package ministry.minebetter.proxy;

import ministry.minebetter.common.init.ModBlocks;
import ministry.minebetter.common.init.ModItems;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenders()
    {
        ModBlocks.registerRenders();
        ModItems.registerRenders();
    }
}
