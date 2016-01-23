package ministry.minebetter.common.init;

import static ministry.minebetter.api.block.blocks.*;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ministry.minebetter.api.block.IBlock;
import ministry.minebetter.common.block.*;
import ministry.minebetter.common.util.block.BlockStateUtils;

public final class ModBlocks {

    public static void init()
    {
        Block denseIron = new BlockDenseIron();
        GameRegistry.registerBlock(denseIron, "dense_iron_ore");
        //dense_iron = registerBlock();
    }

    public static void createBlocks() {
    }

    public static void registerBlockVariant(Block block, String stateName, int stateMeta)
    {
        Item item = Item.getItemFromBlock(block);
        //BiomesOPlenty.proxy.registerItemVariantModel(item, stateName, stateMeta);

        //BOPCommand.blockCount++;
    }

    public static Block registerBlock(Block block, String blockName)
    {
        // by default, set the creative tab for all blocks added in BOP to CreativeTabBOP.instance
        return registerBlock(block, blockName);
    }

    public static Block registerBlock(Block block, String blockName, CreativeTabs tab)
    {

        block.setUnlocalizedName(blockName);
        block.setCreativeTab(tab);

        if (block instanceof IBlock)
        {
            // if this block supports the IBOPBlock interface then we can determine the item block class, and sub-blocks automatically
            IBlock bopBlock = (IBlock)block;
            GameRegistry.registerBlock(block, bopBlock.getItemClass(), blockName);

            //BiomesOPlenty.proxy.registerNonRenderingProperties(block);

            // check for missing default states
            IBlockState defaultState = block.getDefaultState();
            if (defaultState == null)
            {
                defaultState = block.getBlockState().getBaseState();
                System.out.println("missing default state for " + block.getUnlocalizedName());
            }

            // get the preset blocks variants
            ImmutableSet<IBlockState> presets = BlockStateUtils.getBlockPresets(block);
            if (presets.isEmpty())
            {
                // block has no sub-blocks to register
                registerBlockVariant(block, blockName, 0);
            }
            else
            {
                // register all the sub-blocks
                for (IBlockState state : presets)
                {
                    String stateName = bopBlock.getStateName(state);
                    int stateMeta = block.getMetaFromState(state);
                    registerBlockVariant(block, stateName, stateMeta);
                }
            }
        }
        else
        {
            // for vanilla blocks, just register a single variant with meta=0 and assume ItemBlock for the item class
            GameRegistry.registerBlock(block, ItemBlock.class , blockName);
            registerBlockVariant(block, blockName, 0);
        }

        return block;
    }
}
