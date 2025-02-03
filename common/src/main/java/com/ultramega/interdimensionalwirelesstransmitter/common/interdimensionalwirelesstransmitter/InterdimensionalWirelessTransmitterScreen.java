package com.ultramega.interdimensionalwirelesstransmitter.common.interdimensionalwirelesstransmitter;

import com.refinedmods.refinedstorage.common.support.AbstractBaseScreen;
import com.refinedmods.refinedstorage.common.support.containermenu.PropertyTypes;
import com.refinedmods.refinedstorage.common.support.widget.RedstoneModeSideButtonWidget;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import static com.refinedmods.refinedstorage.common.util.IdentifierUtil.createIdentifier;
import static com.refinedmods.refinedstorage.common.util.IdentifierUtil.createTranslation;
import static com.ultramega.interdimensionalwirelesstransmitter.common.InterdimensionalIdentifierUtil.createInterdimensionalTranslation;

public class InterdimensionalWirelessTransmitterScreen extends AbstractBaseScreen<InterdimensionalWirelessTransmitterContainerMenu> {
    private static final MutableComponent INACTIVE = createTranslation("gui", "wireless_transmitter.inactive");
    private static final ResourceLocation TEXTURE = createIdentifier("textures/gui/wireless_transmitter.png");

    private final TransmittingIcon icon;

    public InterdimensionalWirelessTransmitterScreen(final InterdimensionalWirelessTransmitterContainerMenu containerMenu,
                                                     final Inventory inventory,
                                                     final Component title) {
        super(containerMenu, inventory, title);
        this.inventoryLabelY = 43;
        this.imageWidth = 176;
        this.imageHeight = 137;
        this.icon = new TransmittingIcon(getMenu().isActive());
    }

    @Override
    protected void init() {
        super.init();
        addSideButton(new RedstoneModeSideButtonWidget(getMenu().getProperty(PropertyTypes.REDSTONE_MODE)));
    }

    @Override
    protected void containerTick() {
        super.containerTick();
        icon.tick(getMenu().isActive());
    }

    @Override
    protected ResourceLocation getTexture() {
        return TEXTURE;
    }

    @Override
    protected void renderBg(final GuiGraphics graphics, final float delta, final int mouseX, final int mouseY) {
        super.renderBg(graphics, delta, mouseX, mouseY);

        icon.render(graphics, leftPos + 7, topPos + 22);
    }

    @Override
    protected void renderLabels(final GuiGraphics graphics, final int mouseX, final int mouseY) {
        super.renderLabels(graphics, mouseX, mouseY);
        if (!getMenu().isActive()) {
            graphics.drawString(font, INACTIVE, 7 + icon.getWidth() + 4, 25, 4210752, false);
            return;
        }
        graphics.drawString(
            font,
            createInterdimensionalTranslation("gui", "interdimensional_wireless_transmitter.distance"),
            7 + icon.getWidth() + 4,
            25,
            4210752,
            false
        );
    }
}
