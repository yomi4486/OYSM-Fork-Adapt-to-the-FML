package rip.ysm.compat.slashblade.neoforge;

import com.elfmcys.yesstevemodel.client.animation.molang.CtrlBinding;
import com.elfmcys.yesstevemodel.client.entity.LivingAnimatable;
import com.elfmcys.yesstevemodel.geckolib3.core.builder.ILoopType;
import com.elfmcys.yesstevemodel.geckolib3.core.enums.PlayState;
import com.elfmcys.yesstevemodel.geckolib3.core.event.predicate.AnimationEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import rip.ysm.compat.slashblade.SlashBladeCompat;

// Stub NeoForge implementation: the corresponding mod is not (yet) ported to
// NeoForge 1.21.1 in this build, so every hook here is a safe no-op. Replace with
// a real implementation once the mod's 1.21.1 NeoForge jar is added to libs/.
public final class SlashBladeCompatImpl {

    private SlashBladeCompatImpl() {
    }

    public static boolean isLoaded() {
        return false;
    }

    public static boolean isSlashBladeItem(ItemStack itemStack) {
        return false;
    }

    public static String getComboAnimName(AnimationEvent<? extends LivingAnimatable<?>> event) {
        return null;
    }

    public static PlayState handleSlashBladeAnim(LivingEntity livingEntity, AnimationEvent<? extends LivingAnimatable<?>> event, String str, ILoopType loopType) {
        return PlayState.STOP;
    }

    public static void registerControllerFunctions(CtrlBinding ctrlBinding) {
    }

    public static boolean hasNewApi() {
        return false;
    }
}
