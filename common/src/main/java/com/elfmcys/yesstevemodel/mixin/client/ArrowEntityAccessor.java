package com.elfmcys.yesstevemodel.mixin.client;

import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.alchemy.PotionContents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

// Arrow no longer stores a raw Set<MobEffectInstance> field (1.20.5+ moved tipped
// arrow effects into PotionContents, read via a private getter); invoke it directly.
@Mixin({Arrow.class})
public interface ArrowEntityAccessor {
    @Invoker("getPotionContents")
    PotionContents ysm$getPotionContents();
}
