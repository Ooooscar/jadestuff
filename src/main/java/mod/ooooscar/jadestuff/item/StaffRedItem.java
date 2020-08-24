package mod.ooooscar.jadestuff.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.dragon.EnderDragonPartEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class StaffRedItem extends Item {
    public StaffRedItem(final Properties properties) {
        super(properties);
    }

    @Override
    public boolean onLeftClickEntity(final ItemStack stack, final PlayerEntity player, final Entity entity) {
        if (!player.world.isRemote) {
            final Entity entityToKill;
            if (entity instanceof EnderDragonPartEntity) { // If it's a part of an Ender Dragon, kill the main Ender Dragon entity
                entityToKill = ((EnderDragonPartEntity) entity).dragon;
            } else {
                entityToKill = entity;
            }

            entityToKill.onKillCommand();
            //player.sendStatusMessage(new TranslationTextComponent("commands.kill.success.single", entityToKill.getDisplayName()));
        }

        return true;
    }

}
