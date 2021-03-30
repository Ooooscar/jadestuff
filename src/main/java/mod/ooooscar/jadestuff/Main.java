package mod.ooooscar.jadestuff;

import mod.ooooscar.jadestuff.init.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.item.*;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

@SuppressWarnings("deprecation")
@Mod(Main.MOD_ID)
@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Main
{
    public static Main instance;
    public static final String MOD_ID = "jadestuff";
    public static final Logger LOGGER = LogManager.getLogger();

    public Main()
    {
        /*
         * A mod by Ooooscar
         * Registry system credited to Mr Pineapple
         * Code fixed up by LaDestitute
         *
         * This is our Main class. And without this nothing in our mod would work!
         * It's like the bread of a sandwich, you need it otherwise you don't have a sandwich.
         *
         * We call everything here and add it to the EventBus so it can be registered to the game
         * We do lots of things in this class, which you could split of into separate classes and
         * call seperately (better for bigger mods), but for this we'll just call everything here.
         */

        LOGGER.debug("Hello from JadeStuff!");
        instance = this;

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::clientSetup);

        /* Register all of our deferred registries from our list/init classes, which get added to the IEventBus */
        // Recommended registry order list is:
        // Particles, Sound, Items, Blocks, Effects, Potions, Biomes, Paintings, Entities
        // If you are using Deferred Registries, you must do ".get()" after referencing your item/block/biome etc
        // i.e BlockInit.RUBYBLOCK.get().asitem;
        // ".asItem()" as well such as if you want to use a block for a creative-tab icon
        // This will then return the correct thing from the RegistryObject
        // HOWEVER, items must be registered before blocks or you may get issues
        SoundInit.SOUNDS.register(modEventBus);
        ItemInit.ITEMS.register(modEventBus);
        BlockInit.BLOCKS.register(modEventBus);
        EffectsInit.EFFECTS.register(modEventBus);
        EffectsInit.POTIONS.register(modEventBus);
        EntityInit.ENTITIES.register(modEventBus);
    }

    /* In here we feed everything from our BLOCKS deferred register to make BlockItems for us.
     * Instead of using the filter, if we wanted special properties, we can just use the NO_ITEM_BLOCK
     */

    @SubscribeEvent
    public static void createBlockItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

        BlockInit.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
            final Item.Properties properties = new Item.Properties().group(Main.JADESTUFF_BLOCKS);
            final BlockItem blockItem = new BlockItem(block, properties);
            blockItem.setRegistryName(block.getRegistryName());
            registry.register(blockItem);
        });

    }

    /* A registry event to register all biomes into the game */
    @SubscribeEvent
    public static void spawnBiomes(final RegistryEvent.Register<Biome> event) {
        //  BiomeList.registerBiomes();
    }

    /* The FMLCommonSetupEvent (FML - Forge Mod Loader) */
    private void setup(final FMLCommonSetupEvent event)
    {
        /* In the Brewing tutorial I couldn't find this method, so instead I reflected the one that vanilla uses -
        Use this instead */
        //  BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION),
        //  Potions.AWKWARD)), Ingredient.fromItems(ItemList.PEPPERS.get()),
        //  PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION),  PotionList.MORE_HEALTH_POTION.get()));

        /*
         * Here we call the public generate method from our Generation class.
         * You may notice that we don't call it directly, and that is because
         * Minecraft is not thread safe, so we can't add non-thread safe
         * variables anywhere we feel like.
         *
         * Even though this class is deprecated, it is perfectly fine to use
         * deprecated just means that there is a new system in the works, but this
         * still works completely fine!
         */
        //  DeferredWorkQueue.runLater(TutorialGeneration::generate);
    }


    /*
     * ClientSetup, this registers things we want on the client side that the
     * server doesn't really care about, like rendering entities, rendering layers, etc.
     */
    private void clientSetup(final FMLClientSetupEvent event)
    {
        // RenderTypeLookup.setRenderLayer(BlockList.PEPPER_BUSH.get(), RenderType.getCutout());  //getCutout()
        // RenderTypeLookup.setRenderLayer(BlockList.TUTORIAL_DOOR.get(), RenderType.getCutout());
        // RenderTypeLookup.setRenderLayer(BlockList.FROSTBERRY_BUSH.get(), RenderType.getCutout());

        //Just call the method from below, and get the supplier from the event
        registerEntityModels(event.getMinecraftSupplier());
    }


    /*
     * This is a helper method that we are using just to save a little space in clientSetup
     * It takes in the Minecraft Supplier as a parameter, which can be called from the event
     * This is specifically for projectiles like snowball-style items
     */
    private void registerEntityModels(Supplier<Minecraft> minecraft) {
        //Just a variable to set in case I want to add more entities, which will make the code more efficient
        ItemRenderer renderer = minecraft.get().getItemRenderer();

        /*
         * We now need to render the entity on the client using the Rendering Registry
         * We take in the Entity then the RenderType. I use a lambda function here for ease.
         * Most projectiles will use SpriteRenderers for their rendering, using the same texture as the item
         * It takes in the manager from the lambda and the variable above for the item renderer.
         */
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.STRANGE_SNOWBALL.get(), (renderManager) -> new SpriteRenderer<>(renderManager, renderer));
    }

    // Custom ItemGroup tabs
    public static final ItemGroup JADESTUFF_MATERIALS = new ItemGroup(Main.MOD_ID + "_materials") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemInit.ENDER_JADE.get());
        }
    };

    public static final ItemGroup JADESTUFF_BLOCKS = new ItemGroup(Main.MOD_ID + "_blocks") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(BlockInit.JADESTONE_BRICKS.get().asItem());
        }
    };

    public static final ItemGroup JADESTUFF_TOOLS = new ItemGroup(Main.MOD_ID + "_tools") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.IRON_PICKAXE);
        }
    };

    public static final ItemGroup JADESTUFF_WEAPONS = new ItemGroup(Main.MOD_ID + "_weapons") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemInit.STAFF_RED.get());
        }
    };
}

