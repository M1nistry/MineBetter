package ministry.minebetter.common.block;

import ministry.minebetter.common.meta.block.IMetaBlockName;
import ministry.minebetter.common.util.entity.TileEntityYield;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockDenseIron extends Block implements IMetaBlockName, ITileEntityProvider
{
    private Item drop;

    public BlockDenseIron()
    {
        super (Material.ground);
        this.setUnlocalizedName("dense_iron_ore");
        this.setHardness(3.0f);
        this.setHarvestLevel("pickaxe", 3);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.drop = Item.getItemFromBlock(Blocks.iron_ore);
    }


    @Override
    public Item getItemDropped(IBlockState blockstate, Random random, int fortune)
    {
        return this.drop;
    }

    @Override
    public int quantityDropped(IBlockState blockstate, int fortune, Random random)
    {
        return blockstate.getValue(EXHAUSTED) ? 0 : 1;
    }

    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityYield();
    }

    /* Blockstate code below */

    public static final PropertyBool EXHAUSTED = PropertyBool.create("exhausted");

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[]{ EXHAUSTED });
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {

        return getDefaultState().withProperty(EXHAUSTED, Boolean.valueOf(meta == 1));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(EXHAUSTED).booleanValue() ? 1 : 0;
    }

    @Override
    public String getSpecialName(ItemStack stack) {
        return stack.getItemDamage() > 0 ? "dense" : "exhausted";
    }



}
