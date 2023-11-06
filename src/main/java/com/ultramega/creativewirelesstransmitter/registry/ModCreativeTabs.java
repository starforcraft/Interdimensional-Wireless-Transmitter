package com.ultramega.creativewirelesstransmitter.registry;

import com.ultramega.creativewirelesstransmitter.CreativeWirelessTransmitter;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CreativeWirelessTransmitter.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TAB_CREATIVEWIRELESSTRANSMITTER = TABS.register(CreativeWirelessTransmitter.MOD_ID, () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.creativewirelesstransmitter")).icon(() -> new ItemStack(ModItems.CREATIVE_WIRELESS_TRANSMITTER.get(DyeColor.BLUE).get())).displayItems((featureFlags, output) -> {
        for (DyeColor color : DyeColor.values()) {
            output.accept(new ItemStack(ModItems.CREATIVE_WIRELESS_TRANSMITTER.get(color).get()));
        }
    }).build());
}
