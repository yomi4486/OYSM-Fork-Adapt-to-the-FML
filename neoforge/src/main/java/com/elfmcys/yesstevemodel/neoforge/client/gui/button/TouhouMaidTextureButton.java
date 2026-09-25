package com.elfmcys.yesstevemodel.neoforge.client.gui.button;

import com.elfmcys.yesstevemodel.client.gui.button.TextureButton;

import com.elfmcys.yesstevemodel.neoforge.capability.TlmAttachments;
import com.elfmcys.yesstevemodel.client.entity.PlayerPreviewEntity;
import com.elfmcys.yesstevemodel.client.model.ModelAssembly;
import com.elfmcys.yesstevemodel.util.ComponentUtil;
import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import com.github.tartaricacid.touhoulittlemaid.network.message.YsmMaidModelPackage;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.PacketDistributor;

public class TouhouMaidTextureButton extends TextureButton {

    private final EntityMaid maid;

    private final int index;

    private String textureId;

    private String textureName;

    private Component displayComponent;

    public TouhouMaidTextureButton(int x, int y, PlayerPreviewEntity previewEntity, EntityMaid entityMaid, int textureIndex, ModelAssembly modelAssembly) {
        super(x, y, previewEntity, modelAssembly);
        this.maid = new EntityMaid(entityMaid.level());
        this.maid.setIsYsmModel(true);
        this.maid.setOnGround(true);
        this.index = entityMaid.getId();
        var cap = entityMaid.getData(TlmAttachments.MAID);
        this.textureId = cap.getModelId();
        ModelAssembly modelAssembly2 = cap.getModelAssembly();
        this.displayComponent = ComponentUtil.getDisplayName(modelAssembly2, this.textureId);
        this.textureName = modelAssembly2.getAnimationBundle().getTextures().getKeyAt(textureIndex);
        this.maid.setYsmModel(this.textureId, this.textureName, this.displayComponent);
        previewEntity.initModelWithTexture(this.textureId, this.textureName);
    }

    @Override
    public void onPress() {
        this.maid.setYsmModel(this.textureId, this.textureName, this.displayComponent);
        PacketDistributor.sendToServer(new YsmMaidModelPackage(this.index, this.textureId, this.textureName, this.displayComponent));
    }
}
