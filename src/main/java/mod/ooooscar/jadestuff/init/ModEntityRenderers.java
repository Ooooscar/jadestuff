package mod.ooooscar.jadestuff.init;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import mod.ooooscar.jadestuff.entity.EndGuardEntity;
import mod.ooooscar.jadestuff.entity.ModSnowballEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;

import static mod.ooooscar.jadestuff.util.InjectionUtil.Null;

public class ModEntityRenderers {
    //public static final EntityRenderer<EndGuardEntity> END_GUARD_ENTITY_MODEL = Null();





    /*
    public static class EndGuardModel extends EntityModel<Entity> {
        private final ModelRenderer head;
        private final ModelRenderer sticks_top;
        private final ModelRenderer sticks_middle;
        private final ModelRenderer sticks_bottom;
        public EndGuardModel() {
            textureWidth = 64;
            textureHeight = 32;
            head = new ModelRenderer(this);
            head.setRotationPoint(0.0F, 0.0F, 0.0F);
            head.setTextureOffset(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
            sticks_top = new ModelRenderer(this);
            sticks_top.setRotationPoint(0.0F, 24.0F, 0.0F);
            sticks_top.setTextureOffset(0, 16).addBox(-8.0F, -26.0F, -8.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
            sticks_top.setTextureOffset(0, 16).addBox(6.0F, -26.0F, -8.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
            sticks_top.setTextureOffset(0, 16).addBox(6.0F, -26.0F, 6.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
            sticks_top.setTextureOffset(0, 16).addBox(-8.0F, -26.0F, 6.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
            sticks_middle = new ModelRenderer(this);
            sticks_middle.setRotationPoint(5.0F, 2.0F, -5.0F);
            sticks_middle.setTextureOffset(0, 16).addBox(-11.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
            sticks_middle.setTextureOffset(0, 16).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
            sticks_middle.setTextureOffset(0, 16).addBox(-1.0F, 0.0F, 9.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
            sticks_middle.setTextureOffset(0, 16).addBox(-11.0F, 0.0F, 9.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
            sticks_bottom = new ModelRenderer(this);
            sticks_bottom.setRotationPoint(3.0F, 10.0F, -3.0F);
            sticks_bottom.setTextureOffset(0, 16).addBox(-7.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
            sticks_bottom.setTextureOffset(0, 16).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
            sticks_bottom.setTextureOffset(0, 16).addBox(-1.0F, 0.0F, 5.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
            sticks_bottom.setTextureOffset(0, 16).addBox(-7.0F, 0.0F, 5.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
        }

        @Override
        public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
                           float alpha) {
            head.render(matrixStack, buffer, packedLight, packedOverlay);
            sticks_top.render(matrixStack, buffer, packedLight, packedOverlay);
            sticks_middle.render(matrixStack, buffer, packedLight, packedOverlay);
            sticks_bottom.render(matrixStack, buffer, packedLight, packedOverlay);
        }

        public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
            modelRenderer.rotateAngleX = x;
            modelRenderer.rotateAngleY = y;
            modelRenderer.rotateAngleZ = z;
        }

        public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
            this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
            this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
            this.sticks_top.rotateAngleY = f2 / 20.f;
            this.sticks_middle.rotateAngleY = f2;
            this.sticks_bottom.rotateAngleY = f2 / 20.f;
        }
    }*/
}
