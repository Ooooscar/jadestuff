package mod.ooooscar.jadestuff.client.renderer.entity;

import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.util.ResourceLocation;

public class ModSpriteRenderer extends SpriteRenderer {
    //private final ResourceLocation entityTexture;

    public ModSpriteRenderer(final EntityRendererManager renderManagerIn, final ItemRenderer itemRendererIn, float scaleIn, boolean ifAlwaysBrightIn) {
        super(renderManagerIn, itemRendererIn, scaleIn, ifAlwaysBrightIn);
    }

    /*
    @Override
    protected ResourceLocation getEntityTexture(final ArrowEntity entity) {
        return entityTexture;
    }

     */
}
