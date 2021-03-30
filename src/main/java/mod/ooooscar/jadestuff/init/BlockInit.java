package mod.ooooscar.jadestuff.init;

import mod.ooooscar.jadestuff.Main;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {

    //Registry initialization
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            Main.MOD_ID);

    //Ore
    public static final RegistryObject<Block> ENDER_JADE_ORE = BLOCKS.register("ender_jade_ore", ()
            -> new Block(PropertiesInit.JADEORE_PROPERTIES));

    //Natural
    public static final RegistryObject<Block> JADESTONE = BLOCKS.register("jadestone", ()
            -> new Block(PropertiesInit.JADESTONE_PROPERTIES));
    public static final RegistryObject<Block> JADESTONE_DARK = BLOCKS.register("jadestone_dark", ()
            -> new Block(PropertiesInit.JADESTONEDARK_PROPERTIES));

    //Decor
    public static final RegistryObject<Block> JADESTONE_BRICKS = BLOCKS.register("jadestone_bricks", ()
            -> new Block(PropertiesInit.JADEBRICKS_PROPERTIES));

}
