package net.pl3x.map.fabric.server.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.pl3x.map.api.command.Sender;
import net.pl3x.map.fabric.server.command.commands.HideCommand;
import net.pl3x.map.fabric.server.command.commands.ShowCommand;

import static me.lucko.fabric.api.permissions.v0.Permissions.require;
import static net.minecraft.server.command.CommandManager.literal;

public class Pl3xMapCommand {
    public Pl3xMapCommand(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("map")
                .requires(require("pl3xmap.command.map", 2))
                .then(HideCommand.register())
                .then(ShowCommand.register())
                .executes(this::showHelp)
        );
    }

    private int showHelp(CommandContext<ServerCommandSource> ctx) {
        Sender sender = new FabricSender(ctx.getSource());
        // todo
        sender.sendMessage("[Pl3xMap] help");
        return 1;
    }
}
