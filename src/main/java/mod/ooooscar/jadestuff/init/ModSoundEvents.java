package mod.ooooscar.jadestuff.init;

import mod.ooooscar.jadestuff.Main;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.ObjectHolder;

import static mod.ooooscar.jadestuff.util.InjectionUtil.Null;

@ObjectHolder(Main.MODID)
public final class ModSoundEvents {

    @ObjectHolder("effect.freeze")
    public static final SoundEvent EFFECT_FREEZE = Null();
    @ObjectHolder("effect.k_o")
    public static final SoundEvent EFFECT_K_O = Null();

    private static final SoundEvent[] sound_events = {
        add("effect.freeze"),
        add("effect.k_o"),
    };

    private static SoundEvent add(final String name) {
        ResourceLocation resource_location = new ResourceLocation(Main.MODID, name);
        return new SoundEvent(resource_location).setRegistryName(name);
    }

    static SoundEvent[] getAllSoundEvents() {
        return sound_events;
    }
}
