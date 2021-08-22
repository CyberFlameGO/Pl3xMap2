package net.pl3x.map.fabric.server.command.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.kyori.adventure.text.minimessage.Template;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.pl3x.map.api.Pl3xMap;
import net.pl3x.map.api.command.Sender;
import net.pl3x.map.api.player.Player;
import net.pl3x.map.core.configuration.Lang;
import net.pl3x.map.fabric.server.command.FabricSender;

import java.util.Collection;
import java.util.Collections;
import static me.lucko.fabric.api.permissions.v0.Permissions.require;
import static net.minecraft.command.argument.EntityArgumentType.getPlayers;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class ShowCommand {
    public static LiteralArgumentBuilder<ServerCommandSource> register() {
        return literal("show")
                .requires(require("pl3xmap.command.show", 2))
                .then(argument("player", EntityArgumentType.players())
                        .requires(require("pl3xmap.command.show.other", 2))
                        .executes(ctx -> show(ctx, getPlayers(ctx, "player")))
                )
                .executes(ctx -> show(ctx, Collections.singleton(ctx.getSource().getPlayer())));
    }

    private static int show(CommandContext<ServerCommandSource> ctx, Collection<ServerPlayerEntity> players) {
        int i = 0;
        Sender sender = new FabricSender(ctx.getSource());
        for (ServerPlayerEntity serverPlayer : players) {
            Player player = Pl3xMap.api().getPlayerManager().getPlayer(serverPlayer.getUuid());
            if (!player.isHidden()) {
                sender.sendMessage(Lang.PLAYER_NOT_HIDDEN, Template.of("player", player.getName()));
                continue;
            }
            player.setHidden(false, true);
            sender.sendMessage(Lang.SHOW_PLAYER_SUCCESS, Template.of("player", player.getName()));
            i++;
        }
        return i;
    }
}
