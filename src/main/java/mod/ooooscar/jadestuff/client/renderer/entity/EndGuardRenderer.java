package mod.ooooscar.jadestuff.client.renderer.entity;

import mod.ooooscar.jadestuff.entity.EndGuardEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.BlazeModel;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EndGuardRenderer extends MobRenderer<EndGuardEntity, BlazeModel<EndGuardEntity>> {
    private final ResourceLocation textureLocation;

    public EndGuardRenderer(final EntityRendererManager renderManagerIn, final ResourceLocation textureLocationIn) {
        super(renderManagerIn, new BlazeModel<>(), 0.5F);
        this.textureLocation = textureLocationIn;
    }

    @Override
    protected int getBlockLight(EndGuardEntity entityIn, BlockPos partialTicks) {
        return 15;
    }

    /**
     * Returns the location of an entity's texture.
     */
    @Override
    public ResourceLocation getEntityTexture(final EndGuardEntity entity) {
        return textureLocation;
    }
}
