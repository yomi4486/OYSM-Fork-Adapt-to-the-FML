package rip.ysm.api.client.neoforge;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.client.event.RenderLivingEvent;
import net.neoforged.neoforge.common.NeoForge;

public final class RenderLivingBridgeImpl {

    private RenderLivingBridgeImpl() {
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static boolean firePre(LivingEntity entity, LivingEntityRenderer<?, ?> renderer, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        return NeoForge.EVENT_BUS.post(new RenderLivingEvent.Pre<>(entity, (LivingEntityRenderer) renderer, partialTick, poseStack, bufferSource, packedLight)).isCanceled();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void firePost(LivingEntity entity, LivingEntityRenderer<?, ?> renderer, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        NeoForge.EVENT_BUS.post(new RenderLivingEvent.Post<>(entity, (LivingEntityRenderer) renderer, partialTick, poseStack, bufferSource, packedLight));
    }
}
