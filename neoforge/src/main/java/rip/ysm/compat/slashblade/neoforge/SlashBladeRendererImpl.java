package rip.ysm.compat.slashblade.neoforge;

import com.elfmcys.yesstevemodel.geckolib3.geo.animated.AnimatedGeoModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import rip.ysm.compat.slashblade.SlashBladeRenderer;

// Stub NeoForge implementation: the corresponding mod is not (yet) ported to
// NeoForge 1.21.1 in this build, so every hook here is a safe no-op. Replace with
// a real implementation once the mod's 1.21.1 NeoForge jar is added to libs/.
public final class SlashBladeRendererImpl {

    private SlashBladeRendererImpl() {
    }

    public static void renderOnEntity(LivingEntity entity, AnimatedGeoModel model, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, ItemStack stack, float partialTick) {
    }

    public static void renderRightWaist(AnimatedGeoModel model, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, ItemStack stack) {
    }
}
