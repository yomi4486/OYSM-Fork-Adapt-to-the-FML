package com.elfmcys.yesstevemodel.neoforge.client.gui.button;

import com.elfmcys.yesstevemodel.client.gui.button.ModelButton;

import com.elfmcys.yesstevemodel.neoforge.capability.TlmAttachments;
import com.elfmcys.yesstevemodel.client.entity.PlayerPreviewEntity;
import com.elfmcys.yesstevemodel.client.model.ModelAssembly;
import com.elfmcys.yesstevemodel.util.ComponentUtil;
import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import com.github.tartaricacid.touhoulittlemaid.network.message.YsmMaidModelPackage;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.PacketDistributor;

public class TouhouMaidModelButton extends ModelButton {

    private final EntityMaid maid;

    public TouhouMaidModelButton(int x, int y, boolean isAuthLocked, PlayerPreviewEntity previewEntity, ModelAssembly modelAssembly, EntityMaid entityMaid) {
        super(x, y, isAuthLocked, previewEntity, modelAssembly);
        this.maid = entityMaid;
    }

    @Override
    public void onPress() {
        if (this.isStarred) {
            return;
        }
        Component component = ComponentUtil.getDisplayName(this.renderContext, this.modelIdHolder.getModelId());
        this.maid.getData(TlmAttachments.MAID).setYsmModel(this.modelIdHolder.getModelId(), this.modelIdHolder.getCurrentTextureName());
        PacketDistributor.sendToServer(new YsmMaidModelPackage(this.maid.getId(), this.modelIdHolder.getModelId(), this.modelIdHolder.getCurrentTextureName(), component));
    }
}
