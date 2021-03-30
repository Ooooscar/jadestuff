package mod.ooooscar.jadestuff.entity;

import mod.ooooscar.jadestuff.init.EntityInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.EnumSet;

public class EndGuardEntity extends MonsterEntity {
    private float heightOffset = 0.5F;
    private int heightOffsetUpdateTime;
    private static final DataParameter<Byte> ON_FIRE = EntityDataManager.createKey(EndGuardEntity.class, DataSerializers.BYTE);

    //TODO: Change
    public EndGuardEntity(EntityType<? extends EndGuardEntity> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
        this.setPathPriority(PathNodeType.WATER, 0.0F);
        this.setPathPriority(PathNodeType.LAVA, 8.0F);
        this.setPathPriority(PathNodeType.DANGER_FIRE, 0.0F);
        this.setPathPriority(PathNodeType.DAMAGE_FIRE, 0.0F);
        this.experienceValue = 12;
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(4, new EndGuardEntity.FireballAttackGoal(this));
        this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    // TODO: CHANGE
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_VEX_AMBIENT;
    }

    // TODO: CHANGE
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_SHULKER_BULLET_HIT;
    }

    // TODO: CHANGE
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SQUID_DEATH;
    }

    @Override
    public EntityType<?> getType() {
        return EntityInit.END_GUARD.get();
    }

    public float getBrightness() {
        return 0.5F;
    }

    public void livingTick() {
        if (!this.onGround && this.getMotion().y < 0.0D) {
            this.setMotion(this.getMotion().mul(1.0D, 0.6D, 1.0D));
        }

        if (this.world.isRemote) {
            // TODO: CHANGE
            if (this.rand.nextInt(24) == 0 && !this.isSilent()) {
                this.world.playSound(this.getPosX() + 0.5D, this.getPosY() + 0.5D, this.getPosZ() + 0.5D, SoundEvents.ENTITY_VEX_AMBIENT, this.getSoundCategory(), 1.0F + this.rand.nextFloat(), this.rand.nextFloat() * 0.7F + 0.3F, false);
            }

            for(int i = 0; i < 2; ++i) {
                this.world.addParticle(ParticleTypes.SNEEZE, this.getPosXRandom(0.5D), this.getPosYRandom(), this.getPosZRandom(0.5D), 0.0D, 0.0D, 0.0D);
            }
        }

        super.livingTick();
    }

    public boolean func_230270_dK_() {
        return true;
    }

    protected void updateAITasks() {
        --this.heightOffsetUpdateTime;
        if (this.heightOffsetUpdateTime <= 0) {
            this.heightOffsetUpdateTime = 100;
            this.heightOffset = 0.5F + (float)this.rand.nextGaussian() * 3.0F;
        }

        LivingEntity livingentity = this.getAttackTarget();
        if (livingentity != null && livingentity.getPosYEye() > this.getPosYEye() + (double)this.heightOffset && this.canAttack(livingentity)) {
            Vector3d vector3d = this.getMotion();
            this.setMotion(this.getMotion().add(0.0D, ((double)0.3F - vector3d.y) * (double)0.3F, 0.0D));
            this.isAirBorne = true;
        }

        super.updateAITasks();
    }

    public boolean onLivingFall(float distance, float damageMultiplier) {
        return false;
    }

    /*
    public boolean isBurning() {
        return this.isCharged();
    }

    private boolean isCharged() {
        return (this.dataManager.get(ON_FIRE) & 1) != 0;
    }
     */

    private void setOnFire(boolean onFire) {
        byte b0 = this.dataManager.get(ON_FIRE);
        if (onFire) {
            b0 = (byte)(b0 | 1);
        } else {
            b0 = (byte)(b0 & -2);
        }

        this.dataManager.set(ON_FIRE, b0);
    }

    static class FireballAttackGoal extends Goal {
        private final EndGuardEntity end_guard;
        private int attackStep;
        private int attackTime;
        private int field_223527_d;

        public FireballAttackGoal(EndGuardEntity endGuardIn) {
            this.end_guard = endGuardIn;
            this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            LivingEntity livingentity = this.end_guard.getAttackTarget();
            return livingentity != null && livingentity.isAlive() && this.end_guard.canAttack(livingentity);
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            this.attackStep = 0;
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask() {
            this.end_guard.setOnFire(false);
            this.field_223527_d = 0;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            --this.attackTime;
            LivingEntity livingentity = this.end_guard.getAttackTarget();
            if (livingentity != null) {
                boolean flag = this.end_guard.getEntitySenses().canSee(livingentity);
                if (flag) {
                    this.field_223527_d = 0;
                } else {
                    ++this.field_223527_d;
                }

                double d0 = this.end_guard.getDistanceSq(livingentity);
                if (d0 < 4.0D) {
                    if (!flag) {
                        return;
                    }

                    if (this.attackTime <= 0) {
                        this.attackTime = 20;
                        this.end_guard.attackEntityAsMob(livingentity);
                    }

                    this.end_guard.getMoveHelper().setMoveTo(livingentity.getPosX(), livingentity.getPosY(), livingentity.getPosZ(), 1.0D);
                } else if (d0 < this.getFollowDistance() * this.getFollowDistance() && flag) {
                    double d1 = livingentity.getPosX() - this.end_guard.getPosX();
                    double d2 = livingentity.getPosYHeight(0.5D) - this.end_guard.getPosYHeight(0.5D);
                    double d3 = livingentity.getPosZ() - this.end_guard.getPosZ();
                    if (this.attackTime <= 0) {
                        ++this.attackStep;
                        if (this.attackStep == 1) {
                            this.attackTime = 60;
                            this.end_guard.setOnFire(true);
                        } else if (this.attackStep <= 4) {
                            this.attackTime = 6;
                        } else {
                            this.attackTime = 100;
                            this.attackStep = 0;
                            this.end_guard.setOnFire(false);
                        }

                        if (this.attackStep > 1) {
                            float f = MathHelper.sqrt(MathHelper.sqrt(d0)) * 0.5F;
                            if (!this.end_guard.isSilent()) {
                                this.end_guard.world.playEvent((PlayerEntity)null, 1018, this.end_guard.func_233580_cy_(), 0);
                            }

                            for(int i = 0; i < 1; ++i) {
                                // TODO: CHANGE
                                SmallFireballEntity smallfireballentity = new SmallFireballEntity(this.end_guard.world, this.end_guard, d1 + this.end_guard.getRNG().nextGaussian() * (double)f, d2, d3 + this.end_guard.getRNG().nextGaussian() * (double)f);
                                smallfireballentity.setPosition(smallfireballentity.getPosX(), this.end_guard.getPosYHeight(0.5D) + 0.5D, smallfireballentity.getPosZ());
                                this.end_guard.world.addEntity(smallfireballentity);
                            }
                        }
                    }

                    this.end_guard.getLookController().setLookPositionWithEntity(livingentity, 10.0F, 10.0F);
                } else if (this.field_223527_d < 5) {
                    this.end_guard.getMoveHelper().setMoveTo(livingentity.getPosX(), livingentity.getPosY(), livingentity.getPosZ(), 1.0D);
                }

                super.tick();
            }
        }

        private double getFollowDistance() {
            return this.end_guard.func_233637_b_(Attributes.field_233819_b_);
        }
    }

}

