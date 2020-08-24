package mod.ooooscar.jadestuff.init;

import mod.ooooscar.jadestuff.Main;
import mod.ooooscar.jadestuff.potion.JadestuffEffects;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.registries.ObjectHolder;

import static mod.ooooscar.jadestuff.util.InjectionUtil.Null;

@ObjectHolder(Main.MODID)
public class ModEffects {
    public static final JadestuffEffects CHILLED = Null();

    private static final Effect[] effects = {
        new JadestuffEffects(EffectType.HARMFUL, 173, 208, 255).setRegistryName("chilled")
                .addAttributesModifier(Attributes.field_233821_d_, "7107DE5E-7CE8-4030-940E-514C1F160890", (double)-0.20F, AttributeModifier.Operation.MULTIPLY_TOTAL),
    };

    static Effect[] getAllEffects() {
        return effects;
    }

}
