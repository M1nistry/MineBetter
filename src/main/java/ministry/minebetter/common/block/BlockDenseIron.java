package ministry.minebetter.common.block;

import ministry.minebetter.Reference;
import ministry.minebetter.api.block.IBlock;
import ministry.minebetter.api.block.blocks;
import ministry.minebetter.common.item.ItemMineBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;

public class BlockDenseIron extends Block //implements IBlock
{

//    public static final PropertyInteger YIELD = PropertyInteger.create("yield", 1, 740);
//    @Override
//    protected BlockState createBlockState() {
//        return new BlockState(this, new IProperty[] { YIELD });
//    }

    public BlockDenseIron()
    {
        super (Material.ground);
        this.setUnlocalizedName("dense_iron_ore");
       // this.setTickRandomly(true);
        this.setBlockUnbreakable();
        //this.setHarvestLevel("pickaxe", 3);

        this.setCreativeTab(CreativeTabs.tabBlock);
    }

//    @Override
//    public Class<? extends ItemBlock> getItemClass() { return ItemMineBlock.class; }
//    @Override
//    public int getItemRenderColor(IBlockState state, int tintIndex) { return this.getRenderColor(state); }
//    @Override
//    public IProperty[] getPresetProperties() { return null; }
//    @Override
//    public IProperty[] getNonRenderingProperties() { return null; }
//    @Override
//    public String getStateName(IBlockState state)
//    {
//        return "dense_iron_ore";
//    }


}
