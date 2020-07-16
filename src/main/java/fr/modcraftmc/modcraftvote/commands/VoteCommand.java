package fr.modcraftmc.modcraftvote.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fr.modcraftmc.modcraftvote.ModcraftVote;
import fr.modcraftmc.modcraftvote.vote.VoteTracker;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;

public class VoteCommand {


    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {

        commandDispatcher.register(
                Commands.literal("vote").executes(VoteCommand::vote)
        );

        commandDispatcher.register(
                Commands.literal("startvotecheck").executes(VoteCommand::startvotecheck));

    }

    public static int vote(CommandContext<CommandSource> cmd) throws CommandSyntaxException {

        ServerPlayerEntity player = cmd.getSource().asPlayer();

        player.sendMessage(new StringTextComponent(""));
        player.sendMessage(new StringTextComponent("§8----------------------§aModcraftMC§8--------------------"));
        player.sendMessage(new StringTextComponent(""));
        player.sendMessage(new StringTextComponent("§aEnvie de souvenir le serveur sans payer le moindre §eeuro §a?"));
        player.sendMessage(new StringTextComponent("§aVote pour le serveur et obtient une récompense !"));
        player.sendMessage(new StringTextComponent("§6Clique ici !").applyTextStyle((textstyle)-> {
            textstyle.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new StringTextComponent("§aClique ici !")));
            textstyle.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/startvotecheck"));

        }));
        player.sendMessage(new StringTextComponent("§8L'équipe de ModcraftMC."));
        player.sendMessage(new StringTextComponent(""));

        return 0;
    }

    public static int startvotecheck(CommandContext<CommandSource> cmd) throws CommandSyntaxException {

        ServerPlayerEntity player = cmd.getSource().asPlayer();



        player.sendMessage(new StringTextComponent(""));
        player.sendMessage(new StringTextComponent("§8----------------------§aModcraftMC§8--------------------"));
        player.sendMessage(new StringTextComponent(""));
        player.sendMessage(new StringTextComponent("§6Clique ici").applyTextStyle((style -> style.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://serveur-prive.net/minecraft/modcraftmc-1273/vote"))))
        .appendText(" §apour obtenir le lien du vote"));
        player.sendMessage(new StringTextComponent("§aExpiration du vote dans 5 minutes"));
        player.sendMessage(new StringTextComponent(""));
        player.sendMessage(new StringTextComponent("§8Ton vote sera vérifié dans quelques instants."));
        player.sendMessage(new StringTextComponent(""));

        ModcraftVote.tracker.addVoteTracker(player);




        return 0;
    }
}
