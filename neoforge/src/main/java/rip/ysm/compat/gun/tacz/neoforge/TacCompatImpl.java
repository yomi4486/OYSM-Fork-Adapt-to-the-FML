package rip.ysm.compat.gun.tacz.neoforge;

import com.elfmcys.yesstevemodel.client.animation.molang.CtrlBinding;
import com.elfmcys.yesstevemodel.client.entity.LivingAnimatable;
import com.elfmcys.yesstevemodel.geckolib3.core.builder.ILoopType;
import com.elfmcys.yesstevemodel.geckolib3.core.enums.PlayState;
import com.elfmcys.yesstevemodel.geckolib3.core.event.predicate.AnimationEvent;
import com.elfmcys.yesstevemodel.geckolib3.geo.animated.AnimatedGeoModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import rip.ysm.compat.gun.tacz.TacCompat;

// Stub NeoForge implementation: the corresponding mod is not (yet) ported to
// NeoForge 1.21.1 in this build, so every hook here is a safe no-op. Replace with
// a real implementation once the mod's 1.21.1 NeoForge jar is added to libs/.
public final class TacCompatImpl {

    private TacCompatImpl() {
    }

    public static boolean isLoaded() {
        return false;
    }

    public static void registerControllerFunctions(CtrlBinding binding) {
    }

    public static void applyItemTransform(ItemStack stack, AnimatedGeoModel model, LivingEntity entity, PoseStack poseStack, int packedLightIn, float partialTicks) {
    }

    public static PlayState handleTaczAnimState(LivingEntity entity, AnimationEvent<? extends LivingAnimatable<?>> event, String animation, ILoopType loopType) {
        return PlayState.STOP;
    }

    public static PlayState handleGunHoldAnimState(ItemStack stack, AnimationEvent<? extends LivingAnimatable<?>> event) {
        return PlayState.STOP;
    }

    public static PlayState handleGunActionAnimState(ItemStack stack, AnimationEvent<? extends LivingAnimatable<?>> event) {
        return PlayState.STOP;
    }

    public static void handleGunSound(LivingEntity entity, ItemStack stack) {
    }

    public static void handleItemSound(ItemStack stack) {
    }

    public static ResourceLocation getGunTexture(ItemStack stack) {
        return null;
    }
}
