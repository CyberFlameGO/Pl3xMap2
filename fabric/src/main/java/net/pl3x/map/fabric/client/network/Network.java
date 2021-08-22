package net.pl3x.map.fabric.client.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;
import net.pl3x.map.core.Constants;

public class Network {
    public static final Network INSTANCE = new Network();

    private final Identifier channel = new Identifier(Constants.MODID, Constants.MODID);

    private Network() {
    }

    public void initialize() {
        ClientPlayNetworking.registerGlobalReceiver(this.channel, (client, handler, buf, responseSender) -> {
            // todo
        });
    }
}
