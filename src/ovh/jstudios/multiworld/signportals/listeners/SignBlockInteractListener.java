package ovh.jstudios.multiworld.signportals.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import ovh.jstudios.multiworld.signportals.Main;
import ovh.jstudios.multiworld.signportals.PropertyKeys;

import java.util.List;
import java.util.logging.Logger;

public class SignBlockInteractListener implements Listener {

    private final Logger logger;
    private final JavaPlugin plugin;

    public SignBlockInteractListener(Logger logger, JavaPlugin plugin) {

        this.logger = logger;
        this.plugin = plugin;

    }

    @EventHandler
    public void onSignInteract(PlayerInteractEvent event) {

        logger.info("=== Got a PlayerInteractEvent");

        Player player = event.getPlayer();
        logger.info("Player is " + player.getDisplayName());

        boolean hasBlock = event.hasBlock();
        logger.info("Has block: " + hasBlock);

        if (hasBlock) {
            Block block = event.getClickedBlock();
            Material blockMaterial = block.getType();
            logger.info("The block material is " + blockMaterial.toString());

            logger.info("Class of the block is " + block.getClass().getName());
            logger.info("Class of the material is " + blockMaterial.getClass().getName());

            BlockState state = block.getState();
            logger.info("Class of the BlockState is " + state.getClass().getName());

            if (state instanceof Sign) {
                logger.info("This is a sign. Continuing");
                Sign sign = (Sign) state;

                List<MetadataValue> metadata = sign.getMetadata(PropertyKeys.METADATA_SIGN_PORTAL_KEY);
                logger.info("Got ["+ metadata.size() +"] metadata values");

                logger.info("Sign line 3 is " + sign.getLine(3));

                logger.info("Setting a metadata for this sign");
                sign.removeMetadata(PropertyKeys.METADATA_SIGN_PORTAL_KEY, plugin);
                sign.setMetadata(PropertyKeys.METADATA_SIGN_PORTAL_KEY, new FixedMetadataValue(plugin, "true"));

                String[] signText = sign.getLines();
                logger.info("Sign has ["+ signText.length +"] lines");
                sign.setLine(0, "Hello");
            }
        }

    }

}
