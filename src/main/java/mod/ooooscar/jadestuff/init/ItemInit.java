package mod.ooooscar.jadestuff.init;

import mod.ooooscar.jadestuff.Main;
import mod.ooooscar.jadestuff.item.*;
import net.minecraft.item.Item;
import net.minecraft.item.ShootableItem;
import net.minecraft.item.SnowballItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {

    //Registry initialization
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            Main.MOD_ID);

    //Materials
    public static final RegistryObject<Item> ENDER_JADE = ITEMS.register("ender_jade",
            () -> new Item(new Item.Properties().group(Main.JADESTUFF_MATERIALS)));
    public static final RegistryObject<Item> PURE_JADE = ITEMS.register("pure_jade",
            () -> new Item(new Item.Properties().group(Main.JADESTUFF_MATERIALS)));

    //Weapons
    public static final RegistryObject<Item> STAFF_RED = ITEMS.register("staff_red",
            () -> new StaffRedItem(new Item.Properties().group(Main.JADESTUFF_WEAPONS)));
    public static final RegistryObject<Item> STAFF_YELLOW = ITEMS.register("staff_yellow",
            () -> new StaffYellowItem(new Item.Properties().group(Main.JADESTUFF_WEAPONS)));
    public static final RegistryObject<Item> STAFF_GREEN = ITEMS.register("staff_green",
            () -> new StaffGreenItem(new Item.Properties().group(Main.JADESTUFF_WEAPONS)));
    public static final RegistryObject<ShootableItem> STAFF_BLUE = ITEMS.register("staff_blue",
            () -> new StaffBlueItem(new Item.Properties().group(Main.JADESTUFF_WEAPONS)));
    public static final RegistryObject<Item> STAFF_PINK = ITEMS.register("staff_pink",
            () -> new StaffPinkItem(new Item.Properties().group(Main.JADESTUFF_WEAPONS)));
    public static final RegistryObject<Item> STAFF_WHITE = ITEMS.register("staff_white",
            () -> new StaffWhiteItem(new Item.Properties().group(Main.JADESTUFF_WEAPONS)));
    public static final RegistryObject<SnowballItem> STRANGE_SNOWBALL = ITEMS.register("strange_snowball",
            () -> new StrangeSnowballItem(new Item.Properties().group(Main.JADESTUFF_WEAPONS)));
    public static final RegistryObject<Item> KO = ITEMS.register("ko",
            () -> new EntityKillerItem(new Item.Properties().group(Main.JADESTUFF_WEAPONS)));

}
