package rip.ysm.compat.simpleplanes.neoforge;

import com.elfmcys.yesstevemodel.client.entity.GeckoVehicleEntity;
import com.elfmcys.yesstevemodel.geckolib3.core.event.predicate.AnimationEvent;
import java.util.Optional;
import org.joml.Vector3f;
import rip.ysm.compat.simpleplanes.SimplePlanesCompat;

// Stub NeoForge implementation: the corresponding mod is not (yet) ported to
// NeoForge 1.21.1 in this build, so every hook here is a safe no-op. Replace with
// a real implementation once the mod's 1.21.1 NeoForge jar is added to libs/.
public final class SimplePlanesCompatImpl {

    private SimplePlanesCompatImpl() {
    }

    public static boolean isLoaded() {
        return false;
    }

    public static Optional<Vector3f> getSimplePlanesRotation(AnimationEvent<GeckoVehicleEntity> event) {
        return Optional.empty();
    }
}
