package mod.ooooscar.jadestuff.item;

import mod.ooooscar.jadestuff.init.ModSoundEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.dragon.EnderDragonPartEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TranslationTextComponent;

/**
 * An item that kills an entity when you left click on it.
 * <p>
 * Test for this thread:
 * http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/modification-development/2760814-getting-entitys-string-id
 *
 * @author Choonster
 */
public class EntityKillerItem extends Item {
    public EntityKillerItem(final Item.Properties properties) {
        super(properties.maxStackSize(1));
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

            // TODO: Does not play sound at all
            entity.world.playSound(entity.getPosX(), entity.getPosY(), entity.getPosZ(), ModSoundEvents.EFFECT_K_O, SoundCategory.NEUTRAL, 1.0F, 2.6F + (entity.world.rand.nextFloat() - entity.world.rand.nextFloat()) * 0.8F, false);
            entityToKill.onKillCommand();
            player.sendStatusMessage(new TranslationTextComponent("commands.kill.success.single", entityToKill.getDisplayName()), true);
        }

        return true;
    }
}
