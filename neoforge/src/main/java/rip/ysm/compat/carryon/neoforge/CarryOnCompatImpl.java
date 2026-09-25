package rip.ysm.compat.carryon.neoforge;

import com.elfmcys.yesstevemodel.client.animation.molang.CtrlBinding;
import com.elfmcys.yesstevemodel.client.entity.CustomPlayerEntity;
import com.elfmcys.yesstevemodel.geckolib3.core.controller.IAnimationController;
import java.util.Optional;
import java.util.function.BiFunction;
import net.minecraft.world.entity.player.Player;
import rip.ysm.compat.carryon.CarryOnCompat;

// Stub NeoForge implementation: the corresponding mod is not (yet) ported to
// NeoForge 1.21.1 in this build, so every hook here is a safe no-op. Replace with
// a real implementation once the mod's 1.21.1 NeoForge jar is added to libs/.
public final class CarryOnCompatImpl {

    private CarryOnCompatImpl() {
    }

    public static boolean isLoaded() {
        return false;
    }

    public static Optional<BiFunction<String, CustomPlayerEntity, IAnimationController<CustomPlayerEntity>>> getControllerFactory() {
        return Optional.empty();
    }

    public static boolean isPlayerCarrying(Player player) {
        return false;
    }

    public static void registerBindings(CtrlBinding binding) {
    }
}
