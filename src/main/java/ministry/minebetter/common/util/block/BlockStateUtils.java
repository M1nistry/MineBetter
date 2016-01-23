package ministry.minebetter.common.util.block;

import java.util.*;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;

import ministry.minebetter.api.block.IBlock;
import com.google.common.collect.ImmutableSet;

public class BlockStateUtils {

    // utility function for dumping block state info to a string
    public static String getStateInfoAsString(IBlockState state)
    {
        String desc = state.getBlock().getClass().getName() + "[";
        Iterator it = state.getProperties().entrySet().iterator();
        boolean first = true;
        while (it.hasNext())
        {
            if (!first) {desc = desc + ",";}
            Map.Entry entry = (Map.Entry)it.next();
            IProperty iproperty = (IProperty)entry.getKey();
            Comparable comparable = (Comparable)entry.getValue();
            desc = desc + iproperty.getName() + "=" + iproperty.getName(comparable);
            first = false;
        }
        desc = desc + "]";
        return desc;
    }

    // returns a set of states, one for every possible combination of values from the provided properties
    public static ImmutableSet<IBlockState> getStatesSet(IBlockState baseState, IProperty... properties)
    {
        Stack<IProperty> propStack = new Stack<IProperty>();
        List<IBlockState> states = new ArrayList<IBlockState>();
        for (IProperty prop : properties) {propStack.push(prop);}
        if (!propStack.isEmpty())
        {
            addStatesToList(baseState, states, propStack);
        }
        ImmutableSet<IBlockState> ret = ImmutableSet.copyOf(states);
        return ret;
    }

    // recursively add state values to a list
    private static void addStatesToList(IBlockState state, List<IBlockState> list, Stack<IProperty> stack)
    {
        if (stack.empty())
        {
            list.add(state);
            return;
        }
        else
        {
            IProperty prop = stack.pop();
            for (Object value : prop.getAllowedValues())
            {
                addStatesToList(state.withProperty(prop, (Comparable)value), list, stack);
            }
            stack.push(prop);
        }
    }

    // return all of the different 'preset' variants of a block
    // works by looping through all the different values of the properties specified in block.getPresetProperties()
    // only works on blocks supporting IBOPBlock - returns an empty set for vanilla blocks
    public static ImmutableSet<IBlockState> getBlockPresets(Block block)
    {
        if (!(block instanceof IBlock)) {return ImmutableSet.<IBlockState>of();}
        IBlockState defaultState = block.getDefaultState();
        if (defaultState == null) {defaultState = block.getBlockState().getBaseState();}
        return getStatesSet(defaultState, ((IBlock)block).getPresetProperties());
    }

    /**Discards additional block information to retrieve a state equivalent to those in the inventory**/
    public static IBlockState getPresetState(IBlockState state)
    {
        IBlockState outState = state.getBlock().getDefaultState();

        if (state.getBlock() instanceof IBlock)
        {
            IBlock bopBlock = (IBlock)state.getBlock();

            for (IProperty property : bopBlock.getPresetProperties())
            {
                outState = outState.withProperty(property, state.getValue(property));
            }
        }

        return outState;
    }
}
