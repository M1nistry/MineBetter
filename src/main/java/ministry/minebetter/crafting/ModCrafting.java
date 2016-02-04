package ministry.minebetter.crafting;

import ministry.minebetter.api.item.items;
import ministry.minebetter.common.item.ItemProspectLense;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by M1nistry on 3/02/2016.
 */
public class ModCrafting {
    public static void init()
    {
        GameRegistry.addRecipe(new ItemStack(items.prospect_lense), new Object[] { "GPG", "GRG", "G#G", 'G', Items.gold_ingot, 'P', Blocks.glass_pane, 'R', Items.redstone, '#', Blocks.glass});
    }
}
