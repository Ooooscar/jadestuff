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
    public static final SoundEvent EFFECT_K_O = Null();

    private static final SoundEventRegistry[] sound_event_registries = {
        new SoundEventRegistry("effect.freeze"),
        new SoundEventRegistry("effect.k_o"),
    };

    private static final int number_of_registries = sound_event_registries.length;


    private static class SoundEventRegistry {
        private final SoundEvent sound_to_register;

        private SoundEventRegistry(final String name) {
            ResourceLocation resource_location = new ResourceLocation(Main.MODID, name);
            sound_to_register = new SoundEvent(resource_location).setRegistryName(name);
        }
    }

    static SoundEvent[] getAllSoundEvents() {
        final SoundEvent[] sound_events = new SoundEvent[number_of_registries];
        for (int i = 0; i < number_of_registries; i++) {
            sound_events[i] = sound_event_registries[i].sound_to_register;
        }
        return sound_events;
    }
}
