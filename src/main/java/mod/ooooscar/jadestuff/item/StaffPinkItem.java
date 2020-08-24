package mod.ooooscar.jadestuff.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class StaffPinkItem extends Item {
    public StaffPinkItem(final Properties properties) {
        super(properties);
    }

    @Override
    public boolean onLeftClickEntity(final ItemStack stack, final PlayerEntity player, final Entity entity) {

        return true;
    }

}
