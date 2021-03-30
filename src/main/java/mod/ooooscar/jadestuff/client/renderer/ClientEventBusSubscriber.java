package mod.ooooscar.jadestuff.client.renderer;

import mod.ooooscar.jadestuff.Main;
import mod.ooooscar.jadestuff.client.renderer.entity.EndGuardRenderer;
import mod.ooooscar.jadestuff.init.EntityInit;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void clientSetupEvent(FMLClientSetupEvent event){
      //  RenderingRegistry.registerEntityRenderingHandler(EntityInit.END_GUARD.get(), EndGuardRenderer::new);
    }
}