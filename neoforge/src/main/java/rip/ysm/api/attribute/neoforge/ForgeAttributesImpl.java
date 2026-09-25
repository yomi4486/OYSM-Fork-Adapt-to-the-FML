package rip.ysm.api.attribute.neoforge;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.common.NeoForgeMod;

// MC 1.20.5+ absorbed most of Forge's old custom reach/gravity/step-height
// attributes into vanilla; only swim speed and nametag distance remain
// NeoForge-specific (NeoForgeMod), the rest now live on vanilla Attributes.
public final class ForgeAttributesImpl {

    private ForgeAttributesImpl() {
    }

    public static Attribute blockReach() {
        return Attributes.BLOCK_INTERACTION_RANGE.value();
    }

    public static Attribute entityReach() {
        return Attributes.ENTITY_INTERACTION_RANGE.value();
    }

    public static Attribute swimSpeed() {
        return NeoForgeMod.SWIM_SPEED.value();
    }

    public static Attribute entityGravity() {
        return Attributes.GRAVITY.value();
    }

    public static Attribute stepHeightAddition() {
        return Attributes.STEP_HEIGHT.value();
    }

    public static Attribute nametagDistance() {
        return NeoForgeMod.NAMETAG_DISTANCE.value();
    }
}
