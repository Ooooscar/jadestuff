package mod.ooooscar.jadestuff.init;

import mod.ooooscar.jadestuff.Main;
import mod.ooooscar.jadestuff.item.*;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

import static mod.ooooscar.jadestuff.util.InjectionUtil.Null;

@ObjectHolder(Main.MODID)
public final class ModItems {
    public static final Item ENDER_JADE = Null();
    public static final Item PURE_JADE = Null();
    public static final StaffRedItem STAFF_RED = Null();
    public static final StaffYellowItem STAFF_YELLOW = Null();
    public static final StaffGreenItem STAFF_GREEN = Null();
    public static final StaffBlueItem STAFF_BLUE = Null();
    public static final StaffPinkItem STAFF_PINK = Null();
    public static final StaffWhiteItem STAFF_WHITE = Null();
    public static final ModSnowballItem STRANGE_SNOWBALL = Null();
    public static final EntityKillerItem KO = Null();

    private static final Item[] items = {
        new Item(modMaterials()).setRegistryName("ender_jade"),
        new Item(modMaterials()).setRegistryName("pure_jade"),
        new StaffRedItem(modWeapons()).setRegistryName("staff_red"),
        new StaffYellowItem(modWeapons()).setRegistryName("staff_yellow"),
        new StaffGreenItem(modWeapons()).setRegistryName("staff_green"),
        new StaffBlueItem(modWeapons()).setRegistryName("staff_blue"),
        new StaffPinkItem(modWeapons()).setRegistryName("staff_pink"),
        new StaffWhiteItem(modWeapons()).setRegistryName("staff_white"),
        new ModSnowballItem(modWeapons()).setRegistryName("strange_snowball"),
        new EntityKillerItem(modWeapons()).setRegistryName("ko"),
    };


    private static Item.Properties modMaterials() {
        return new Item.Properties().group(ModItemGroups.JADESTUFF_MATERIALS);
    }
    private static Item.Properties modWeapons() {
        return new Item.Properties().group(ModItemGroups.JADESTUFF_WEAPONS);
    }

    static Item[] getAllItems() {
        return items;
    }

}
