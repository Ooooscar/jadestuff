package mod.ooooscar.jadestuff.client.renderer.entity;

import mod.ooooscar.jadestuff.Main;
import mod.ooooscar.jadestuff.client.renderer.entity.model.EndGuardModel;
import mod.ooooscar.jadestuff.entity.EndGuardEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.BlazeModel;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EndGuardRenderer extends MobRenderer<EndGuardEntity, EndGuardModel>
{
    protected static final ResourceLocation TEXTURE = new ResourceLocation(Main.MOD_ID,
            "textures/entity/jade_guard.png");

    public EndGuardRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new EndGuardModel(), 0.5f);
    }

    @Override
    protected int getBlockLight(EndGuardEntity entityIn, BlockPos partialTicks) {
        return 15;
    }

    @Override
    public ResourceLocation getEntityTexture(EndGuardEntity entity) {
        return TEXTURE;
    }


}
