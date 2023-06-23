package net.kav.kav_soul_like.statusEffect;

import net.kav.kav_soul_like.Kav_soul_like;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;

public class ModStatusEffects {
    public static StatusEffect staminaboost;
    public static StatusEffect registerStatusEffect(String name)
    {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(Kav_soul_like.MOD_ID, name),
                new staminaboost());
    }
    public static void registerEffects() {
        staminaboost = registerStatusEffect("stamina_boost");
    }
}
