package mod.ooooscar.jadestuff.item;

import mod.ooooscar.jadestuff.entity.projectile.ModSnowballEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SnowballItem;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

// TODO: Check unlocking conditions of recipes
public class ModSnowballItem extends SnowballItem {
    public ModSnowballItem(Item.Properties builder) {
        super(builder.maxStackSize(32));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        // TODO: Change thrown sound
        worldIn.playSound((PlayerEntity)null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        if (!worldIn.isRemote) {
            ModSnowballEntity mod_snowball_entity = new ModSnowballEntity(worldIn, playerIn);
            mod_snowball_entity.setDefaultDamage(0.5F);
            mod_snowball_entity.setKnockbackStrength(0.0F);
            mod_snowball_entity.setItem(itemstack);
            mod_snowball_entity.func_234612_a_(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.addEntity(mod_snowball_entity);
        }

        playerIn.addStat(Stats.ITEM_USED.get(this));
        if (!playerIn.abilities.isCreativeMode) {
            itemstack.shrink(1);
        }

        return ActionResult.func_233538_a_(itemstack, worldIn.isRemote());
    }

}
