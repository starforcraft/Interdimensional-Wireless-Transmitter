package com.ultramega.creativewirelesstransmitter.gui;

import com.refinedmods.refinedstorage.blockentity.NetworkNodeBlockEntity;
import com.refinedmods.refinedstorage.screen.BaseScreen;
import com.refinedmods.refinedstorage.screen.widget.sidebutton.RedstoneModeSideButton;
import com.ultramega.creativewirelesstransmitter.CreativeWirelessTransmitter;
import com.ultramega.creativewirelesstransmitter.container.CreativeWirelessTransmitterContainerMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CreativeWirelessTransmitterScreen extends BaseScreen<CreativeWirelessTransmitterContainerMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(CreativeWirelessTransmitter.MOD_ID, "textures/gui/creative_wireless_transmitter.png");

    public CreativeWirelessTransmitterScreen(CreativeWirelessTransmitterContainerMenu container, Inventory inventory, Component title) {
        super(container, 211, 137, inventory, title);
    }

    @Override
    public void onPostInit(int x, int y) {
        addSideButton(new RedstoneModeSideButton(this, NetworkNodeBlockEntity.REDSTONE_MODE));
    }

    @Override
    public void tick(int x, int y) {

    }

    @Override
    public void renderBackground(GuiGraphics graphics, int x, int y, int mouseX, int mouseY) {
        graphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
    }

    @Override
    public void renderForeground(GuiGraphics graphics, int mouseX, int mouseY) {
        renderString(graphics, 7, 7, title.getString());
        renderString(graphics, 28, 25, I18n.get("gui.creativewirelesstransmitter.creative_wireless_transmitter.distance"));
        renderString(graphics, 7, 43, I18n.get("container.inventory"));
    }
}
