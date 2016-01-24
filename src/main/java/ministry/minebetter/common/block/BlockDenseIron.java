package ministry.minebetter.common.block;

import com.google.common.collect.ImmutableMap;
import ministry.minebetter.common.meta.block.IMetaBlockName;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Collection;
import java.util.Random;

public class BlockDenseIron extends Block implements IMetaBlockName
{
    private Item drop;

    public BlockDenseIron()
    {
        super (Material.ground);
        this.setUnlocalizedName("dense_iron_ore");
        this.setHardness(3.0f);
        this.setHarvestLevel("pickaxe", 2);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.drop = Item.getItemFromBlock(Blocks.iron_ore);
        this.setDefaultState(this.blockState.getBaseState().withProperty(YIELD, 1));
    }


    @Override
    public Item getItemDropped(IBlockState blockstate, Random random, int fortune)
    {
        return this.drop;
    }

    @Override
    public int quantityDropped(IBlockState blockstate, int fortune, Random random)
    {
        return 1;
    }

    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!((Boolean)state.getValue(EXHAUSTED)))
        {
            int i = new Random().nextInt(100);
            System.out.println("RANDOM: " + i);
            if (i < 10)
            {
                worldIn.setBlockState(pos, state.withProperty(EXHAUSTED, true));
                return;
            }
            worldIn.setBlockState(pos, state.withProperty(EXHAUSTED, false));
            if (worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 1.0).canHarvestBlock(this)) {
                worldIn.spawnEntityInWorld(new EntityItem(worldIn, pos.getX(), pos.getY() + 1.0f, pos.getZ(), new ItemStack(Item.getItemFromBlock(Blocks.iron_ore), 1)));
            }
        }
        super.breakBlock(worldIn, pos, state);
    }

    /* Blockstate code below */

    public static final PropertyInteger YIELD = PropertyInteger.create("yield", 0, 1);
    public static final PropertyBool EXHAUSTED = PropertyBool.create("exhausted");

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[]{ YIELD, EXHAUSTED });
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {

        return getDefaultState().withProperty(EXHAUSTED, Boolean.valueOf(meta == 1));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((Boolean)state.getValue(EXHAUSTED)).booleanValue() ? 1 : 0;
    }

    @Override
    public String getSpecialName(ItemStack stack) {
        return stack.getItemDamage() > 0 ? "dense" : "exhausted";
    }

}
