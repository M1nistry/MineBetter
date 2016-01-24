package ministry.minebetter.common.init;

import static ministry.minebetter.api.block.blocks.*;

import ministry.minebetter.common.meta.block.BlockMeta;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

import ministry.minebetter.Reference;
import ministry.minebetter.common.block.*;

public final class ModBlocks {

    public static void init()
    {
        dense_iron = registerBlock(new BlockDenseIron(), "dense_iron_ore", CreativeTabs.tabBlock);
    }

    public static Block registerBlock(Block block, String blockName, CreativeTabs tab)
    {

        block.setUnlocalizedName(blockName);
        block.setCreativeTab(tab);

        GameRegistry.registerBlock(block, BlockMeta.class, block.getUnlocalizedName().substring(5));

        return block;
    }

    public static void registerRenders()
    {
        registerRender(dense_iron);
    }

    public static void registerRender(Block block)
    {
        Item item = Item.getItemFromBlock(block);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
                .register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }
}
