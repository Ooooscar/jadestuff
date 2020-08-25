package mod.ooooscar.jadestuff.init;

import mod.ooooscar.jadestuff.Main;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.potion.Effect;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD)
public final class ModEventSubscriber {

    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(ModItems.getAllItems());
        event.getRegistry().registerAll(ModBlocks.getAllBlockItems());
    }

    @SubscribeEvent
    public static void onRegisterBlocks(final RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(ModBlocks.getAllBlocks());
    }

    @SubscribeEvent
    public static void onRegisterEffects(final RegistryEvent.Register<Effect> event) {
        event.getRegistry().registerAll(ModEffects.getAllEffects());
    }

    @SubscribeEvent
    public static void onRegisterSoundEvents(final RegistryEvent.Register<SoundEvent> event) {
        event.getRegistry().registerAll(ModSoundEvents.getAllSoundEvents());
    }

    @SubscribeEvent
    public static void onRegisterEntityTypes(final RegistryEvent.Register<EntityType<?>> event) {
        event.getRegistry().registerAll(ModEntities.getAllEntityTypes());
    }

    @SubscribeEvent
    public static void onRegisterEntityModels(final ModelRegistryEvent event) {
        ModEntities.registerAllEntityModels();
        for (int i = 0; i < 10; i++) {
            System.out.println("ALL ENTITY MODELS REGISTERED");
        }
    }

    @SubscribeEvent
    public static void onRegisterEntityRenderers(final FMLClientSetupEvent event) {
        ModEntities.registerAllEntityRenderers();
    }

}
