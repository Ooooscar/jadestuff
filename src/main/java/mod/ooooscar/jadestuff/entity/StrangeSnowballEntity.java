package mod.ooooscar.jadestuff.entity;

import mod.ooooscar.jadestuff.init.EffectsInit;
import mod.ooooscar.jadestuff.init.EntityInit;
import mod.ooooscar.jadestuff.init.ItemInit;
import mod.ooooscar.jadestuff.init.SoundInit;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.monster.MagmaCubeEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

/**
 * Modified {@link SnowballEntity} which stores a default damage value,
 * deals extra damage to fire based mobs ({@link MagmaCubeEntity} and {@link BlazeEntity}),
 * deals reduced damage to entities in water,
 * extinguishes fire,
 * and has a chance to inflict the Chilled debuff.
 *
 * @author Ooooscar
 */
// TODO: ModSnowballEntity does not render
public class StrangeSnowballEntity extends ProjectileItemEntity {
    private static float default_damage = 0;
    private static float knockbackStrength = 0;
    private static boolean is_crit = false;

    public StrangeSnowballEntity(EntityType<StrangeSnowballEntity> type, World world) {
        super(type, world);
    }

    public StrangeSnowballEntity(LivingEntity entity, World world) {
        super(EntityInit.STRANGE_SNOWBALL.get(), entity, world);
    }

    public StrangeSnowballEntity(double x, double y, double z, World world) {
        super(EntityInit.STRANGE_SNOWBALL.get(), x, y, z, world);
    }

    public void setDefaultDamage(float defaultDamageIn) {
        default_damage = defaultDamageIn;
        is_crit = rand.nextInt((int) (2.5 / default_damage) + 2) == 0;
    }
    public void setKnockbackStrength(float knockbackStrengthIn) {
        knockbackStrength = knockbackStrengthIn;
    }

    /*
     * This gets the default item that the rock will be thrown by.
     * An example would be the snowball throwing the snowball.
     * If you choose to use a block for this, then you will need to
     * add .asItem() onto the end to ensure you are gettin the block item
     */
    @Override
    protected Item getDefaultItem() {
        return ItemInit.STRANGE_SNOWBALL.get().asItem();
    }

    /*
     * This is a method used to spawn the actual entity in the world
     * It uses the NetworkHooks class which is a common class used.
     * It then gets the entity which is this.
     */
    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    // TODO: Prevent spawning snowball particles at the player's head if the speed of snowball is set to a higher value
    @OnlyIn(Dist.CLIENT)
    public IParticleData makeParticle() {
        ItemStack itemstack = this.func_213882_k();
        return (IParticleData) (itemstack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleData(ParticleTypes.ITEM, itemstack));
    }

    /**
     * Handler for {@link World#setEntityState}
     */
    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 3) {
            IParticleData iparticledata = this.makeParticle();
            for(int i = 0; i < 8; ++i) {
                this.world.addParticle(iparticledata, this.getPosX(), this.getPosY(), this.getPosZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    /**
     * Called when the ModSnowball hits an entity
     */
    protected void onEntityHit(EntityRayTraceResult result) {
        float dmg = default_damage;
        float knockback = knockbackStrength;
        Entity entity_hit = result.getEntity();

        // Deal more damage if the hit entity is a blaze or a magma cube, and disables freezing / extinguishing effect
        if (entity_hit instanceof BlazeEntity || entity_hit instanceof MagmaCubeEntity) {
            dmg += 1.5;
            is_crit = false;
        }
        // Deal less damage and knockback if the hit entity is "wet"
        if (entity_hit.isInWaterRainOrBubbleColumn() || entity_hit.isWet()) {
            dmg /= 3;
            knockback /= 3;
        }

        // If it's a critical shot: extinguish burning entity
        if (entity_hit.isBurning() && is_crit) {
            entity_hit.extinguish();
            entity_hit.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH,
                    0.5F, 2.6F + (rand.nextFloat() - rand.nextFloat()) * 0.8F);
            is_crit = false;
        }

        // Cause thrown damage to the entity
        Entity entity_attacker = this.func_234616_v_();
        DamageSource damagesource;
        if (entity_attacker == null) {
            damagesource = DamageSource.causeThrownDamage(this, this);
        } else {
            damagesource = DamageSource.causeThrownDamage(this, entity_attacker);
            if (entity_attacker instanceof LivingEntity) {
                ((LivingEntity)entity_attacker).setLastAttackedEntity(entity_hit);
            }
        }

        if (entity_hit instanceof LivingEntity) {
            LivingEntity living_entity_hit = (LivingEntity)entity_hit;
            living_entity_hit.attackEntityFrom(damagesource, dmg);
            // Apply knockback to the entity
            if (knockback > 0) {
                Vector3d vector3d = this.getMotion().mul(1.0D, 0.0D, 1.0D).normalize().scale(knockback * 0.6D);
                if (vector3d.lengthSquared() > 0.0D) {
                    living_entity_hit.addVelocity(vector3d.x, 0.1D, vector3d.z);
                }
            }
            // If it's a critical shot: apply freezing effect to the entity
            if (is_crit) {
                living_entity_hit.playSound(SoundInit.FREEZE.get(), 0.5F, 2.6F + (rand.nextFloat() - rand.nextFloat()) * 0.8F);
                living_entity_hit.addPotionEffect(new EffectInstance(EffectsInit.CHILLED.get(), (int)(120 * dmg), 5, true, true));
            }
        }

    }

    /**
     * Called when the ModSnowball hits a block
     */
    // Extinguish fire
    protected void func_230299_a_(BlockRayTraceResult result) {
        super.func_230299_a_(result);
        //if (is_crit) {
            BlockPos pos = result.getPos();
            Direction direction = result.getFace();

            BlockPos pos_offset = pos.offset(direction);
            Block block_offset = this.world.getBlockState(pos_offset).getBlock();

            boolean face_can_catch_fire = ((FireBlock)Blocks.FIRE).canCatchFire(this.world, pos, direction);

            if (block_offset instanceof FireBlock && (face_can_catch_fire || (direction.equals(Direction.UP)))) {
                playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, 0.5F, 2.6F + (rand.nextFloat() - rand.nextFloat()) * 0.8F);
                this.world.removeBlock(pos_offset, false);
            }
        //}
    }

    /**
     * Called when the ModSnowball hits a block or an entity
     */
    @Override
    protected void onImpact(RayTraceResult result) {
        super.onImpact(result);
        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte)3);
            this.remove();
        }
    }

}
