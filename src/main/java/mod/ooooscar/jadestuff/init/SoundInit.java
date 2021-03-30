package mod.ooooscar.jadestuff.init;

import mod.ooooscar.jadestuff.Main;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundInit {
    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Main.MOD_ID);

    //Projectiles
    public static final RegistryObject<SoundEvent> FREEZE =
            SOUNDS.register("effect.freeze", () ->
                    new SoundEvent(new ResourceLocation(Main.MOD_ID, "effect.freeze")));

    //Kill sounds
    public static final RegistryObject<SoundEvent> KO =
            SOUNDS.register("effect.k_o", () ->
                    new SoundEvent(new ResourceLocation(Main.MOD_ID, "effect.k_o")));


}
