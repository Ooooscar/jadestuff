package mod.ooooscar.jadestuff.init;

import mod.ooooscar.jadestuff.Main;
import mod.ooooscar.jadestuff.client.renderer.entity.model.EndGuardModel;
import mod.ooooscar.jadestuff.entity.EndGuardEntity;
import mod.ooooscar.jadestuff.entity.StrangeSnowballEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityInit {

    /* Create a new Deferred Registry for all our entities to register to
     * This is called in our Main class and added to the EventBus, which saves us making each class one
     * Alternatively you could use registry events. Though it doesn't make a massive difference.
     * Deferred Registries are a new and more efficient way of registering lots of things.
     *
     * For updating to 1.16 you'll need to create method from the Deferred Register class, instead of calling on the
     * constructor. Use "DeferredRegister.create()" as below instead of a new DeferredRegister<>()).
     */
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Main.MOD_ID);

    /*
     * This will look a little different to other things you might have registered as it takes in some
     * generic values as well using the EntityType class.
     *
     * The EntityType takes in a generic value of your entity to make sure it has the correct one, as there are many
     * different types of entities.
     *
     * Just like other registries the register takes in the name for the entity then a supplier for the entity.
     * The name would be the same as others.
     * However, the supplier is a little different.
     * It uses the EntityType Builder class.
     * This initially takes in the entity, and the classification.
     * It takes the entity as a supplier, and then the EntityClassification which I have set to MISC
     * We can then use the methods in this class to improve our entity. here I have used .size to set
     * the bounding box for our entity.
     *
     * At the end of this method we need to build the entity, and this takes in the registry name
     * which is the same as the first parameter of the register method.
     *
     * PS, we don't register renderers for projectiles/snowball-like items, that's in Main
     */

    //Projectiles
    public static final RegistryObject<EntityType<StrangeSnowballEntity>> STRANGE_SNOWBALL = ENTITIES.register("strange_snowball",
            () -> EntityType.Builder.<StrangeSnowballEntity>create(StrangeSnowballEntity::new,
                    EntityClassification.MISC).size(EntityType.SNOWBALL.getWidth(), EntityType.SNOWBALL.getHeight()).build("strange_snowball"));

    //Mobs
    public static final RegistryObject<EntityType<EndGuardEntity>> END_GUARD = ENTITIES.register("end_guard",
            () -> EntityType.Builder.<EndGuardEntity>create(EndGuardEntity::new,
                    EntityClassification.MONSTER).size(EntityType.BLAZE.getWidth(), EntityType.BLAZE.getHeight()).build("end_guard"));
}
