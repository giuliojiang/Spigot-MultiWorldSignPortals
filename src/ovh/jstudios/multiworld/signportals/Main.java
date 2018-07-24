package ovh.jstudios.multiworld.signportals;

import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import ovh.jstudios.multiworld.signportals.listeners.SignBlockInteractListener;

import java.util.logging.Logger;

public class Main extends JavaPlugin {

    private Logger logger;
    private Server server;

    // Interface methods ======================================================

    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {

        this.logger = getLogger();
        this.server = getServer();
        initListeners();
        logger.info("Loaded.");

    }

    // Private methods ========================================================

    private void initListeners() {

        PluginManager pluginManager = server.getPluginManager();
        pluginManager.registerEvents(new SignBlockInteractListener(logger, this), this);

    }

}
