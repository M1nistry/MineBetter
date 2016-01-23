package ministry.minebetter.proxy;

import ministry.minebetter.common.init.ModBlocks;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenders()
    {
        ModBlocks.registerRenders();
    }
}
