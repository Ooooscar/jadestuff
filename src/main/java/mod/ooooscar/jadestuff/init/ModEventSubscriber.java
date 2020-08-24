package mod.ooooscar.jadestuff.init;

import mod.ooooscar.jadestuff.Main;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.potion.Effect;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD)
public final class ModEventSubscriber {

    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(ModItems.getAllItems());
        event.getRegistry().registerAll(ModBlocks.getAllBlockItems());
    }

    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(ModBlocks.getAllBlocks());
    }

    @SubscribeEvent
    public static void onRegisterEffects(RegistryEvent.Register<Effect> event) {
        event.getRegistry().registerAll(ModEffects.getAllEffects());
    }

    @SubscribeEvent
    public static void onRegisterSoundEvents(RegistryEvent.Register<SoundEvent> event) {
        event.getRegistry().registerAll(ModSoundEvents.getAllSoundEvents());
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onRegisterEntityTypes(RegistryEvent.Register<EntityType<?>> event) {
        event.getRegistry().registerAll(ModEntityTypes.getAllEntityTypes());
    }

}
