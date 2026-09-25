package rip.ysm.compat.curios.neoforge;

import com.elfmcys.yesstevemodel.geckolib3.core.molang.binding.ContextBinding;
import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;
import java.util.List;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import rip.ysm.compat.curios.CuriosCompat;

// Stub NeoForge implementation: the corresponding mod is not (yet) ported to
// NeoForge 1.21.1 in this build, so every hook here is a safe no-op. Replace with
// a real implementation once the mod's 1.21.1 NeoForge jar is added to libs/.
public final class CuriosCompatImpl {

    private CuriosCompatImpl() {
    }

    public static boolean isLoaded() {
        return false;
    }

    public static boolean hasItemInSlot(LivingEntity livingEntity, String str, ReferenceOpenHashSet<Item> set) {
        return false;
    }

    public static boolean hasTaggedItemInSlot(LivingEntity livingEntity, String str, List<TagKey<Item>> list) {
        return false;
    }

    public static boolean hasNoTaggedItemInSlot(LivingEntity entity, String str, List<TagKey<Item>> list) {
        return false;
    }

    public static void registerCuriosItems(ContextBinding binding) {
    }
}
