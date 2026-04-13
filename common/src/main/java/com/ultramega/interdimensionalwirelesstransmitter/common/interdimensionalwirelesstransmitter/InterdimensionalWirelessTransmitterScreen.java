package com.ultramega.interdimensionalwirelesstransmitter.common.interdimensionalwirelesstransmitter;

import com.refinedmods.refinedstorage.common.support.AbstractBaseScreen;
import com.refinedmods.refinedstorage.common.support.containermenu.PropertyTypes;
import com.refinedmods.refinedstorage.common.support.widget.RedstoneModeSideButtonWidget;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

import static com.refinedmods.refinedstorage.common.util.IdentifierUtil.createIdentifier;
import static com.refinedmods.refinedstorage.common.util.IdentifierUtil.createTranslation;
import static com.ultramega.interdimensionalwirelesstransmitter.common.InterdimensionalIdentifierUtil.createInterdimensionalTranslation;

public class InterdimensionalWirelessTransmitterScreen extends AbstractBaseScreen<InterdimensionalWirelessTransmitterContainerMenu> {
    private static final MutableComponent INACTIVE = createTranslation("gui", "wireless_transmitter.inactive");
    private static final Identifier TEXTURE = createIdentifier("textures/gui/wireless_transmitter.png");

    private final TransmittingIcon icon;

    public InterdimensionalWirelessTransmitterScreen(final InterdimensionalWirelessTransmitterContainerMenu containerMenu,
                                                     final Inventory inventory,
                                                     final Component title) {
        super(containerMenu, inventory, title, 176, 137);
        this.inventoryLabelY = 43;
        this.icon = new TransmittingIcon(this.getMenu().isActive());
    }

    @Override
    protected void init() {
        super.init();
        this.addSideButton(new RedstoneModeSideButtonWidget(this.getMenu().getProperty(PropertyTypes.REDSTONE_MODE)));
    }

    @Override
    protected void containerTick() {
        super.containerTick();
        this.icon.tick(this.getMenu().isActive());
    }

    @Override
    protected Identifier getTexture() {
        return TEXTURE;
    }

    @Override
    public void extractBackground(final GuiGraphicsExtractor graphics, final int mouseX, final int mouseY, final float partialTicks) {
        super.extractBackground(graphics, mouseX, mouseY, partialTicks);

        this.icon.render(graphics, this.leftPos + 7, this.topPos + 22);
    }

    @Override
    protected void extractLabels(final GuiGraphicsExtractor graphics, final int mouseX, final int mouseY) {
        super.extractLabels(graphics, mouseX, mouseY);
        if (!this.getMenu().isActive()) {
            graphics.text(this.font, INACTIVE, 7 + this.icon.getWidth() + 4, 25, -12566464, false);
            return;
        }
        graphics.text(
            this.font,
            createInterdimensionalTranslation("gui", "interdimensional_wireless_transmitter.distance"),
            7 + this.icon.getWidth() + 4,
            25,
            -12566464,
            false
        );
    }
}
