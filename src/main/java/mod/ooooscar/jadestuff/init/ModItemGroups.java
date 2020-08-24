package mod.ooooscar.jadestuff.init;

import mod.ooooscar.jadestuff.Main;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.function.Supplier;

public final class ModItemGroups {

    public static final ItemGroup JADESTUFF_MATERIALS = new ModItemGroup(Main.MODID + "_materials", () -> new ItemStack(ModItems.ENDER_JADE));
    public static final ItemGroup JADESTUFF_BLOCKS = new ModItemGroup(Main.MODID + "_blocks", () -> new ItemStack(ModBlocks.JADESTONE_BRICKS));
    public static final ItemGroup JADESTUFF_TOOLS = new ModItemGroup(Main.MODID + "_tools", () -> new ItemStack(Items.IRON_PICKAXE));
    public static final ItemGroup JADESTUFF_WEAPONS = new ModItemGroup(Main.MODID + "_weapons", () -> new ItemStack(ModItems.STAFF_RED));

    public static class ModItemGroup extends ItemGroup {

        private final Supplier<ItemStack> iconSupplier;

        public ModItemGroup(final String name, final Supplier<ItemStack> iconSupplier) {
            super(name);
            this.iconSupplier = iconSupplier;
        }

        @Override
        public ItemStack createIcon() {
            return iconSupplier.get();
        }

    }
}
