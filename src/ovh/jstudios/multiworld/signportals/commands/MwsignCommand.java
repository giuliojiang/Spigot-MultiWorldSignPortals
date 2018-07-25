package ovh.jstudios.multiworld.signportals.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ovh.jstudios.multiworld.signportals.EnabledPlayers;

import java.util.UUID;

public class MwsignCommand implements CommandExecutor {

    private final EnabledPlayers chargedPlayers;

    public MwsignCommand(EnabledPlayers chargedPlayers) {

        this.chargedPlayers = chargedPlayers;

    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            UUID uuid = player.getUniqueId();
            chargedPlayers.chargePlayer(uuid);
            commandSender.sendMessage("You now have a charge to enable a sign as a mw portal. Right-click on a valid sign to enable it");
        } else {
            commandSender.sendMessage("Only a player can use this command");
        }

        return true;

    }

}
