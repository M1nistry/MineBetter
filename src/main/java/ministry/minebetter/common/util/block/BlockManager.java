package ministry.minebetter.common.util.block;

import java.util.Random;

import ministry.minebetter.api.block.blocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class BlockManager implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        switch(world.provider.getDimensionId())
        {
            case 1: generateEnd(world, random, new BlockPos(chunkX * 16, 64, chunkZ * 16)); break;
            case 0: generateOverworld(world, random, new BlockPos(chunkX * 16, 64, chunkZ * 16)); break;
            case -1: generateNether(world, random, new BlockPos(chunkX * 16, 64, chunkZ * 16)); break;
        }

    }

    private void addStoneOre(Block block, World world, Random random, BlockPos pos, int maxX, int maxZ, int maxVeinSize, int chanceToSpawn, int minY, int maxY, Block generateIn)
    {
        WorldGenMinable mine = new WorldGenMinable(block.getDefaultState(), maxVeinSize);
        for (int i = 0; i < chanceToSpawn; i++)
        {
            int x = pos.getX() + random.nextInt(maxX);
            int y = minY + random.nextInt(maxY - minY);
            int z = pos.getZ() + random.nextInt(maxZ);
            mine.generate(world, random, new BlockPos(x, y, z));
        }
    }

    private void generateNether(World world, Random random, BlockPos pos)
    {

    }


    private void generateOverworld(World world, Random random, BlockPos pos)
    {
        addStoneOre(blocks.dense_iron, world, random, pos, 16, 16, 4 + random.nextInt(5), 80, 0, 60, Blocks.stone);
    }

    private void generateEnd(World world, Random random, BlockPos pos)
    {

    }
}
