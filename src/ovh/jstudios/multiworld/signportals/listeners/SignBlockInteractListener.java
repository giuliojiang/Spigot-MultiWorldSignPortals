package ovh.jstudios.multiworld.signportals.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.block.data.BlockData;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import ovh.jstudios.multiworld.signportals.EnabledPlayers;
import ovh.jstudios.multiworld.signportals.StringConstants;
import ovh.jstudios.multiworld.signportals.sign.SignUtilities;

import java.util.List;
import java.util.logging.Logger;

public class SignBlockInteractListener implements Listener {

    private final JavaPlugin plugin;
    private final EnabledPlayers chargedPlayers;

    public SignBlockInteractListener(Logger logger, JavaPlugin plugin, EnabledPlayers chargedPlayers) {

        this.plugin = plugin;
        this.chargedPlayers = chargedPlayers;

    }

    @EventHandler
    public void onSignInteract(PlayerInteractEvent event) {

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        Player player = event.getPlayer();

        boolean hasBlock = event.hasBlock();

        if (hasBlock) {
            Block block = event.getClickedBlock();

            Sign sign = SignUtilities.getSignState(block);
            if (sign == null) {
                return;
            }

            if (chargedPlayers.consumeCharge(player.getUniqueId())) {
                // Case when player has a charge ------------------------------
                boolean enabled = SignUtilities.enableSignAsPortal(sign);
                if (enabled) {
                    player.sendMessage("This sign is now enabled as a portal. Right-click it to use it");
                } else {
                    player.sendMessage("Could not enable this portal. Make sure the first line is [mw] and the second line is the name of the destination world");
                }
            } else {
                // Case when player isn't charged -----------------------------
                String portalDestination = SignUtilities.getPortalDestination(sign);
                if (portalDestination == null) {
                    return;
                }

                player.sendMessage("Teleporting you to world ["+ portalDestination +"]");
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                String command = "mw move " + player.getDisplayName() + " " + portalDestination;
                Bukkit.dispatchCommand(console, command);
            }

        }

    }

}
