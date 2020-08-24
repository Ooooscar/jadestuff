package mod.ooooscar.jadestuff.init;

import mod.ooooscar.jadestuff.Main;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraftforge.registries.ObjectHolder;

import java.util.Objects;

import static mod.ooooscar.jadestuff.util.InjectionUtil.Null;

@ObjectHolder(Main.MODID)
public final class ModBlocks {

    public static final BlockItem ENDER_JADE_ORE = Null();
    public static final BlockItem JADESTONE = Null();
    public static final BlockItem JADESTONE_DARK = Null();
    public static final BlockItem JADESTONE_BRICKS = Null();

    private static final BlockRegistry[] block_registries = {
        new BlockRegistry(
            new Block(Block.Properties.create(Material.ROCK)
                .sound(SoundType.STONE).hardnessAndResistance(3.0F, 9.0F))
                .setRegistryName("ender_jade_ore"),
            modBlocks()
        ),
        new BlockRegistry(
            new Block(Block.Properties.create(Material.ROCK)
                .sound(SoundType.STONE).hardnessAndResistance(10.0F, 9.0F))
                .setRegistryName("jadestone"),
            modBlocks()
        ),
        new BlockRegistry(
            new Block(Block.Properties.create(Material.ROCK)
                .sound(SoundType.STONE).hardnessAndResistance(10.0F, 9.0F))
                .setRegistryName("jadestone_dark"),
            modBlocks()
        ),
        new BlockRegistry(
            new Block(Block.Properties.create(Material.ROCK)
                .sound(SoundType.STONE).hardnessAndResistance(30.0F, 9.0F))
                .setRegistryName("jadestone_bricks"),
            modBlocks()
        ),
    };

    private static final int number_of_registries = block_registries.length;


    private static BlockItem.Properties modBlocks() {
        return new BlockItem.Properties().group(ModItemGroups.JADESTUFF_BLOCKS);
    }

    private static class BlockRegistry {
        private final Block block_to_register;
        private final BlockItem block_item_to_register;

        private BlockRegistry(final Block block, final BlockItem.Properties block_item_properties) {
            block_to_register = block;
            block_item_to_register = new BlockItem(block, block_item_properties);
            block_item_to_register.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
        }
    }

    static Block[] getAllBlocks() {
        final Block[] blocks = new Block[number_of_registries];
        for (int i = 0; i < number_of_registries; i++) {
            blocks[i] = block_registries[i].block_to_register;
        }
        return blocks;
    }

    static BlockItem[] getAllBlockItems() {
        final BlockItem[] block_items = new BlockItem[number_of_registries];
        for (int i = 0; i < number_of_registries; i++) {
            block_items[i] = block_registries[i].block_item_to_register;
        }
        return block_items;
    }

}
