package mod.ooooscar.jadestuff.item;

import mod.ooooscar.jadestuff.entity.ModSnowballEntity;
import mod.ooooscar.jadestuff.init.ModEffects;
import mod.ooooscar.jadestuff.init.ModItems;
import mod.ooooscar.jadestuff.init.ModSoundEvents;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.IVanishable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShootableItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class StaffBlueItem extends ShootableItem implements IVanishable {

    /**
     * The cooldown of the launcher (in ticks)
     */
    private static final int COOLDOWN = 2;
    
    public StaffBlueItem(final Item.Properties properties) {
        super(properties.maxStackSize(1).maxDamage(100));
    }

    /**
     * Get the cooldown of the launcher (in ticks).
     *
     * @param launcher The launcher
     * @return The cooldown of the launcher (in ticks), or 0 if there is none
     */
    protected int getCooldown(final ItemStack launcher) {
        return COOLDOWN;
    }

    /**
     * Does the player need ammunition to fire the launcher?
     *
     * @param stack  The launcher ItemStack
     * @param player The player to check
     * @return True if the player is not in creative mode and the launcher doesn't have the Infinity enchantment
     */
    private boolean isAmmoRequired(final ItemStack stack, final PlayerEntity player) {
        return !player.abilities.isCreativeMode && EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) == 0;
    }

    @Override
    public Predicate<ItemStack> getInventoryAmmoPredicate() {
        return stack -> stack.getItem() == ModItems.STRANGE_SNOWBALL;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(final World worldIn, final PlayerEntity playerIn, final Hand handIn) {
        final ItemStack heldItem = playerIn.getHeldItem(handIn);

        final boolean ammoRequired = isAmmoRequired(heldItem, playerIn);
        final ItemStack ammo = playerIn.findAmmo(heldItem);
        final boolean hasAmmo = !ammo.isEmpty();

        if (!ammoRequired || hasAmmo) {
            final int cooldown = getCooldown(heldItem);
            if (cooldown > 0) {
                playerIn.getCooldownTracker().setCooldown(this, cooldown);
            }

            // TODO: Change thrown sound
            worldIn.playSound(null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5f, 0.4f / (random.nextFloat() * 0.4f + 0.8f));

            if (!worldIn.isRemote) {
                final ModSnowballEntity mod_snowball_entity = new ModSnowballEntity(worldIn, playerIn);
                mod_snowball_entity.setDefaultDamage(1.0F);
                mod_snowball_entity.setKnockbackStrength(1.5F);
                mod_snowball_entity.func_234612_a_(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 2.0F, 0.5F);
                worldIn.addEntity(mod_snowball_entity);
            }

            if (ammoRequired) {
                if (ammo.isEmpty()) {
                    playerIn.inventory.deleteStack(ammo);
                }
                ammo.shrink(1);
            }

            // TODO: Not working
            if (!playerIn.abilities.isCreativeMode) {
                heldItem.damageItem(1, playerIn, (onBrokenIn) -> {
                    onBrokenIn.sendBreakAnimation(EquipmentSlotType.MAINHAND);
                });
            }

            playerIn.setActiveHand(handIn);
            return ActionResult.resultConsume(heldItem);
        }

        return ActionResult.resultFail(heldItem);
    }

    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.playSound(ModSoundEvents.EFFECT_FREEZE, 0.5F, 2.6F + (target.world.rand.nextFloat() - target.world.rand.nextFloat()) * 0.8F);
        target.addPotionEffect(new EffectInstance(ModEffects.CHILLED, 180, 5, true, true));
        // TODO: Not Working
        if (attacker instanceof PlayerEntity && !((PlayerEntity) attacker).abilities.isCreativeMode) {
            stack.damageItem(1, attacker, (onBrokenIn) -> {
                onBrokenIn.sendBreakAnimation(EquipmentSlotType.MAINHAND);
            });
        }
        return true;
    }

    @Override
    public int func_230305_d_() {
        return 0;
    }
}

