package com.elfmcys.yesstevemodel.neoforge.client.animation.predicate;

import com.elfmcys.yesstevemodel.YesSteveModel;
import com.elfmcys.yesstevemodel.client.animation.AnimationState;
import com.elfmcys.yesstevemodel.client.animation.IAnimationPredicate;
import rip.ysm.compat.slashblade.SlashBladeCompat;
import com.elfmcys.yesstevemodel.client.compat.touhoulittlemaid.capability.MaidCapability;
import rip.ysm.compat.gun.tacz.TacCompat;
import com.elfmcys.yesstevemodel.geckolib3.core.builder.ILoopType;
import com.elfmcys.yesstevemodel.geckolib3.core.event.predicate.AnimationEvent;
import com.elfmcys.yesstevemodel.geckolib3.core.enums.PlayState;
import com.elfmcys.yesstevemodel.client.entity.IPreviewAnimatable;
import com.elfmcys.yesstevemodel.molang.runtime.ExpressionEvaluator;
import com.github.tartaricacid.touhoulittlemaid.api.client.render.MaidRenderState;
import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import it.unimi.dsi.fastutil.objects.ObjectListIterator;
import it.unimi.dsi.fastutil.objects.ReferenceArrayList;
import net.minecraft.world.entity.Entity;

import java.util.Objects;

public class TouhouMaidAnimationPredicate implements IAnimationPredicate<MaidCapability> {

    @SuppressWarnings("unchecked")
    private static final ReferenceArrayList<AnimationState<EntityMaid, MaidCapability>>[] priorityHandlers = new ReferenceArrayList[5];

    static {
        for (int i = 0; i < priorityHandlers.length; i++) {
            priorityHandlers[i] = new ReferenceArrayList<>(6);
        }
    }

    public static void registerHandler(AnimationState<EntityMaid, MaidCapability> animationState) {
        priorityHandlers[animationState.getPriority()].add(animationState);
    }

    @Override
    @SuppressWarnings("unchecked")
    public PlayState predicate(AnimationEvent<MaidCapability> event, ExpressionEvaluator<?> evaluator) {
        EntityMaid entity = event.getAnimatable().getEntity();
        if (entity == null || (event.getAnimatable() instanceof IPreviewAnimatable)) {
            return PlayState.STOP;
        }
        if (entity.renderState != MaidRenderState.ENTITY) {
            return PlayState.STOP;
        }
        Entity vehicle = entity.getVehicle();
        if (vehicle != null && vehicle.isAlive()) {
            return PlayState.STOP;
        }
        for (int i = 0; i <= 4; i++) {
            ObjectListIterator<AnimationState<EntityMaid, MaidCapability>> it = priorityHandlers[i].iterator();
            while (it.hasNext()) {
                AnimationState<EntityMaid, MaidCapability> animationState = it.next();
                if (animationState.getPredicate().test(entity, event)) {
                    String str = animationState.getAnimationName();
                    ILoopType loopType = animationState.getLoopType();
                    if (entity.tickCount % 20 == 0) {
                        YesSteveModel.LOGGER.info("[YSM][diag] maid=" + entity.getUUID() + " selectedState=" + str
                                + " priority=" + i + " limbSwingAmount=" + event.getLimbSwingAmount()
                                + " onGround=" + entity.onGround() + " isSprinting=" + entity.isSprinting());
                    }
                    PlayState playState = SlashBladeCompat.handleSlashBladeAnim(entity, event, str, loopType);
                    if (playState != null) {
                        return playState;
                    }
                    return Objects.requireNonNullElseGet(TacCompat.handleTaczAnimState(entity, event, str, loopType), () -> {
                        return IAnimationPredicate.playAnimationWithLoop(event, str, loopType);
                    });
                }
            }
        }
        return PlayState.STOP;
    }
}
