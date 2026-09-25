package rip.ysm.compat.parcool.neoforge;

import com.elfmcys.yesstevemodel.client.animation.molang.CtrlBinding;
import com.elfmcys.yesstevemodel.client.entity.CustomPlayerEntity;
import com.elfmcys.yesstevemodel.geckolib3.core.controller.IAnimationController;
import java.util.Optional;
import java.util.function.BiFunction;
import net.minecraft.world.entity.player.Player;
import org.apache.commons.lang3.tuple.Pair;
import rip.ysm.compat.parcool.ParcoolCompat;

// Stub NeoForge implementation: the corresponding mod is not (yet) ported to
// NeoForge 1.21.1 in this build, so every hook here is a safe no-op. Replace with
// a real implementation once the mod's 1.21.1 NeoForge jar is added to libs/.
public final class ParcoolCompatImpl {

    private ParcoolCompatImpl() {
    }

    public static boolean isLoaded() {
        return false;
    }

    public static Optional<Pair<String, String>> getInCompatibleInfo() {
        return Optional.empty();
    }

    public static Optional<BiFunction<String, CustomPlayerEntity, IAnimationController<CustomPlayerEntity>>> getControllerFactory() {
        return Optional.empty();
    }

    public static boolean isPlayerParcooling(Player player) {
        return false;
    }

    public static String getActionName(Player player) {
        return null;
    }

    public static void registerBindings(CtrlBinding binding) {
    }
}
