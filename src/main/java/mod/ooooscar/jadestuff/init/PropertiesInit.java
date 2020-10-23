package mod.ooooscar.jadestuff.init;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class PropertiesInit {
    //A helper class for having most block-properties in one place and calling them from the BlockInit class

    public static final Block.Properties JADEORE_PROPERTIES = Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(3.0F, 9.0F);
    public static final Block.Properties JADESTONE_PROPERTIES = Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(10.0F, 9.0F);
    public static final Block.Properties JADESTONEDARK_PROPERTIES = Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(10.0F, 9.0F);
    public static final Block.Properties JADEBRICKS_PROPERTIES = Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(30.0F, 9.0F);
}
