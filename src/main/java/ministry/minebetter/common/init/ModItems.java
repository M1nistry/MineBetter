package ministry.minebetter.common.init;

import ministry.minebetter.Reference;
import ministry.minebetter.common.item.ItemProspectLense;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static ministry.minebetter.api.block.blocks.dense_iron;
import static ministry.minebetter.api.item.items.*;

/**
 * Created by M1nistry on 3/02/2016.
 */
public class ModItems {

    public static void init()
    {
        prospect_lense = registerItem(new ItemProspectLense(), "prospect_lense", CreativeTabs.tabTools);
    }

    public static Item registerItem(Item item, String itemName, CreativeTabs tab)
    {
        item.setUnlocalizedName(itemName);
        item.setCreativeTab(tab);

        GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
        return item;
    }

    public static void registerRenders()
    {
        registerRender(prospect_lense);
    }

    public static void registerRender(Item item)
    {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
                .register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }
}
