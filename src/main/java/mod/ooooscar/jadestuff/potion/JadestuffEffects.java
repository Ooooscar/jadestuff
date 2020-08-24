package mod.ooooscar.jadestuff.potion;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import java.awt.*;

/**
 * A base class for this mod's potions.
 *
 * @author Choonster
 */
public class JadestuffEffects extends Effect {
    protected JadestuffEffects(final EffectType effectType, final int liquidColor) {
        super(effectType, liquidColor);
    }

    public JadestuffEffects(final EffectType effectType, final int liquidR, final int liquidG, final int liquidB) {
        this(effectType, new Color(liquidR, liquidG, liquidB).getRGB());
    }
}
