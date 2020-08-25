package mod.ooooscar.jadestuff.init;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import mod.ooooscar.jadestuff.Main;
import mod.ooooscar.jadestuff.client.renderer.entity.*;
import mod.ooooscar.jadestuff.client.renderer.entity.model.*;
import mod.ooooscar.jadestuff.entity.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.registries.ObjectHolder;

import java.util.ArrayList;

import static mod.ooooscar.jadestuff.util.InjectionUtil.Null;

@ObjectHolder(Main.MODID)
public class ModEntities {
    private final static ArrayList<EntityRegistry> entityRegistries = new ArrayList<>();
    private static int number_of_registries = 0;

    public static final EntityType<ModSnowballEntity> STRANGE_SNOWBALL = Null();
    public static final EntityType<EndGuardEntity> END_GUARD = Null();

    /**
     * Registry Method for all {@link EntityRenderer}s,
     * used in {@link ModEventSubscriber}
     */
    static void registerAllEntityRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(STRANGE_SNOWBALL, renderManager ->
                new SpriteRenderer<>(renderManager, Minecraft.getInstance().getItemRenderer(), 1.0F, true)
        );
        RenderingRegistry.registerEntityRenderingHandler(END_GUARD, renderManager ->
                new EndGuardRenderer(renderManager, new ResourceLocation(Main.MODID,"textures/entity/end_guard/end_guard.png"))
        );
    }


    private static final Initializer init = new Initializer();
    private static final class Initializer {
        /**
         * Initialize all {@link EntityRegistry}s
         */
        private Initializer() {
            entityRegistries.add(new EntityRegistry("strange_snowball",
                    EntityType.Builder.<ModSnowballEntity>create((ModSnowballEntity::new), EntityClassification.MISC)
                            .size(EntityType.SNOWBALL.getWidth(), EntityType.SNOWBALL.getHeight())
            ));
            entityRegistries.add(new EntityRegistry("end_guard",
                    EntityType.Builder.<EndGuardEntity>create((EndGuardEntity::new), EntityClassification.MONSTER)
                            .size(EntityType.BLAZE.getWidth(), EntityType.BLAZE.getHeight()),
                    new EndGuardModel<>(), 0.5f,  "end_guard"
            ));

            number_of_registries = entityRegistries.size();
        }
    }


    /**
     * Util subclass used for organizing {@link EntityType}s and their {@link EntityModel}s
     */
    private static final class EntityRegistry {
        private final String name;
        private final String glowingLayerName;

        private final EntityType<? extends Entity> entityType;
        private final EntityModel<? extends Entity> entityModel;

        private float shadowSize = 0.5f;

        private EntityRegistry(String nameIn, EntityType.Builder<? extends Entity> entityTypeBuilderIn) {
            name = nameIn;
            entityType = build(name, entityTypeBuilderIn);
            entityModel = null;
            glowingLayerName = null;
        }
        private EntityRegistry(String nameIn, EntityType.Builder<? extends Entity> entityTypeBuilderIn, EntityModel<? extends Entity> entityModelIn, float shadowSizeIn) {
            name = nameIn;
            entityType = build(name, entityTypeBuilderIn);
            entityModel = entityModelIn;
            glowingLayerName = null;
            shadowSize = shadowSizeIn;
        }
        private EntityRegistry(String nameIn, EntityType.Builder<? extends Entity> entityTypeBuilderIn, EntityModel<? extends Entity> entityModelIn, float shadowSizeIn, String glowingLayerNameIn) {
            name = nameIn;
            entityType = build(name, entityTypeBuilderIn);
            entityModel = entityModelIn;
            glowingLayerName = glowingLayerNameIn;
            shadowSize = shadowSizeIn;
        }

        /**
         * Build an {@link EntityType} from a {@link EntityType.Builder} using the specified name
         *
         * @param name    The entity type name
         * @param builder The entity type builder to build
         * @return The built entity type
         *
         * @author Choonster
         */
        private static <T extends Entity> EntityType<T> build(final String name, final EntityType.Builder<T> builder) {
            final ResourceLocation registryName = new ResourceLocation(Main.MODID, name);
            final EntityType<T> entityType = builder.build(registryName.toString());
            entityType.setRegistryName(registryName);
            return entityType;
        }
    }

    /**
     * Return all {@link EntityType}s,
     * used in {@link ModEventSubscriber}
     */
    static EntityType<? extends Entity>[] getAllEntityTypes() {
        EntityType[] all_entity_types = new EntityType[number_of_registries];
        for (int i = 0; i < number_of_registries; i++) {
            all_entity_types[i] = (EntityType<Entity>) entityRegistries.get(i).entityType;
        }
        return all_entity_types;
    }

    /**
     * Registry Method for all {@link EntityModel}s,
     * used in {@link ModEventSubscriber}
     */
    static void registerAllEntityModels() {
        for (EntityRegistry registry : entityRegistries) {
            if (registry.entityModel != null) {

                ResourceLocation textureLocation = new ResourceLocation(Main.MODID, "textures/entity/" + registry.name + "/" + registry.glowingLayerName + ".png");

                if (registry.glowingLayerName == null) {

                    RenderingRegistry.registerEntityRenderingHandler(registry.entityType, renderManager -> {
                        return new MobRenderer(renderManager, registry.entityModel, registry.shadowSize) {
                            @Override
                            public ResourceLocation getEntityTexture(Entity entity) {
                                return textureLocation;
                            }
                        };
                    });

                } else {

                    RenderingRegistry.registerEntityRenderingHandler(registry.entityType, renderManager -> {
                        return new MobRenderer(renderManager, registry.entityModel, registry.shadowSize) {
                            {
                                this.addLayer(new GlowingLayer<>(this, registry.name, registry.glowingLayerName));
                            }
                            @Override
                            public ResourceLocation getEntityTexture(Entity entity) {
                                return textureLocation;
                            }
                        };
                    });

                }

            }
        }
    }

    /**
     * A "glowing layer" ({@link LayerRenderer}) for a given {@link Entity}
     *
     * @param <T> The entity
     * @param <M> The entity model name
     *
     * @author MCreator
     */
    @OnlyIn(Dist.CLIENT)
    private static class GlowingLayer<T extends Entity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
        private static String name;
        private static String glowingLayerName;
        public GlowingLayer(IEntityRenderer<T, M> er, String nameIn, String glowingLayerNameIn) {
            super(er);
            name = nameIn;
            glowingLayerName = glowingLayerNameIn;
        }
        public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entityLivingBaseIn, float limbSwing,
                           float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEyes(
                    new ResourceLocation(Main.MODID, "textures/entity/" + name + "/" + glowingLayerName + ".png"))
            );
            this.getEntityModel().render(matrixStackIn, ivertexbuilder, 15728640, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
        }
    }

}
