package mod.ooooscar.jadestuff.init;

import mod.ooooscar.jadestuff.Main;
import mod.ooooscar.jadestuff.entity.projectile.ModSnowballEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ObjectHolder;

import static mod.ooooscar.jadestuff.util.InjectionUtil.Null;

@ObjectHolder(Main.MODID)
public class ModEntityTypes {
    public static final EntityType<ModSnowballEntity> STRANGE_SNOWBALL = Null();

    private static final EntityType[] entity_types = {
        build("strange_snowball",
            EntityType.Builder.<ModSnowballEntity>create((ModSnowballEntity::new), EntityClassification.MISC)
            .size(EntityType.SNOWBALL.getWidth(), EntityType.SNOWBALL.getHeight())),
    };

    /**
     * Build an {@link EntityType} from a {@link EntityType.Builder} using the specified name.
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

    static EntityType[] getAllEntityTypes() {
        return entity_types;
    }

}
