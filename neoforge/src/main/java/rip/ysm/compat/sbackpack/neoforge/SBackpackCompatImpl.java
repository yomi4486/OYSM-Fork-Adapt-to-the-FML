package rip.ysm.compat.sbackpack.neoforge;

import com.elfmcys.yesstevemodel.client.animation.molang.CtrlBinding;
import java.util.Optional;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.tuple.Pair;
import rip.ysm.compat.sbackpack.SBackpackCompat;

// Stub NeoForge implementation: the corresponding mod is not (yet) ported to
// NeoForge 1.21.1 in this build, so every hook here is a safe no-op. Replace with
// a real implementation once the mod's 1.21.1 NeoForge jar is added to libs/.
public final class SBackpackCompatImpl {

    private SBackpackCompatImpl() {
    }

    public static boolean isLoaded() {
        return false;
    }

    public static void setupRenderLayers() {
    }

    public static Optional<Pair<String, String>> getInCompatibleInfo() {
        return Optional.empty();
    }

    public static void registerControllerFunctions(CtrlBinding binding) {
    }

    public static ItemStack getBackpackItem(LivingEntity livingEntity) {
        return ItemStack.EMPTY;
    }
}
