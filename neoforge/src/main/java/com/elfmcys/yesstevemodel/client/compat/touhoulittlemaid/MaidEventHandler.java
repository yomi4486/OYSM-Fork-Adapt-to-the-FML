package com.elfmcys.yesstevemodel.client.compat.touhoulittlemaid;

import com.elfmcys.yesstevemodel.client.compat.touhoulittlemaid.event.MaidScreenEvent;
import com.elfmcys.yesstevemodel.client.compat.touhoulittlemaid.event.MaidClientTickEvent;
import com.elfmcys.yesstevemodel.neoforge.capability.TlmAttachments;
import com.elfmcys.yesstevemodel.geckolib3.core.molang.util.StringPool;
import com.github.tartaricacid.touhoulittlemaid.client.renderer.entity.EntityMaidRenderer;
import com.github.tartaricacid.touhoulittlemaid.entity.item.EntityChair;
import com.github.tartaricacid.touhoulittlemaid.entity.item.EntitySit;
import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import com.github.tartaricacid.touhoulittlemaid.geckolib3.geo.IGeoEntityRenderer;
import com.github.tartaricacid.touhoulittlemaid.item.ItemHakureiGohei;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;

import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.EntityTickEvent;


public class MaidEventHandler {

    private static MaidEntityRenderer maidRenderer;

    public static void init() {
        NeoForge.EVENT_BUS.register(new MaidScreenEvent());
        NeoForge.EVENT_BUS.register(new MaidClientTickEvent());
        NeoForge.EVENT_BUS.addListener(MaidEventHandler::onEntityTick);
    }

    private static void onEntityTick(EntityTickEvent.Post event) {
        // Relying on the render path alone to flip noCulling is a chicken-and-egg
        // problem: it only runs once vanilla's small-hitbox frustum check already
        // let the entity through, so a maid that is never caught by that first
        // check (e.g. its feet-sized hitbox sits outside the frustum while the
        // much larger custom model would still be on screen) never gets the flag
        // set and keeps popping in/out as the camera turns. Ticking is not
        // frustum-gated, so setting it here every tick closes that gap.
        if (event.getEntity() instanceof EntityMaid maid && maid.isYsmModel()) {
            maid.noCulling = true;
        }
    }

    public static void registerMaidRenderer() {
        EntityMaidRenderer.YSM_ENTITY_MAID_RENDERER = context -> {
            maidRenderer = new MaidEntityRenderer(context);
            return (IGeoEntityRenderer) maidRenderer;
        };
    }

    public static boolean isMaid(Entity entity) {
        return entity instanceof EntityMaid;
    }

    public static boolean isYsmModelMaid(Entity entity) {
        if (!(entity instanceof EntityMaid entityMaid)) {
            return false;
        }
        return entityMaid.isYsmModel();
    }

    public static boolean isChair(Entity entity) {
        return entity instanceof EntityChair;
    }

    public static boolean isSit(Entity entity) {
        return entity instanceof EntitySit;
    }

    public static String getChairModelId(Entity entity) {
        if (entity instanceof EntityChair) {
            return ((EntityChair) entity).getModelId();
        }
        return StringPool.EMPTY;
    }

    public static boolean isMaidFishing(LivingEntity livingEntity) {
        return (livingEntity instanceof EntityMaid) && ((EntityMaid) livingEntity).fishing != null;
    }

    public static void setExtraRenderFlag(LivingEntity livingEntity) {
        if (livingEntity instanceof EntityMaid maid) {
            maid.getData(TlmAttachments.MAID).setExtraRenderFlag(true);
        }
    }

    public static boolean isGohei(Item item) {
        return item instanceof ItemHakureiGohei;
    }

    public static MaidEntityRenderer getMaidRenderer() {
        return maidRenderer;
    }
}
