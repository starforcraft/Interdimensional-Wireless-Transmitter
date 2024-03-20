package com.ultramega.creativewirelesstransmitter.setup;

import com.refinedmods.refinedstorage.api.network.node.INetworkNode;
import com.refinedmods.refinedstorage.apiimpl.API;
import com.refinedmods.refinedstorage.apiimpl.network.node.NetworkNode;
import com.ultramega.creativewirelesstransmitter.node.CreativeWirelessTransmitterNetworkNode;
import com.ultramega.creativewirelesstransmitter.registry.ModCreativeTabs;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

public final class CommonSetup {
    @SubscribeEvent
    public static void onRegister(final RegisterEvent e) {
        e.register(Registries.CREATIVE_MODE_TAB, ModCreativeTabs::register);
    }

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent e) {
        API.instance().getNetworkNodeRegistry().add(CreativeWirelessTransmitterNetworkNode.ID,
                (tag, world, pos) -> readAndReturn(tag, new CreativeWirelessTransmitterNetworkNode(world, pos)));
    }

    private static INetworkNode readAndReturn(CompoundTag tag, NetworkNode node) {
        node.read(tag);

        return node;
    }
}
