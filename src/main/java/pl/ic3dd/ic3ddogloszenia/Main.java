package pl.ic3dd.ic3ddogloszenia;

import org.bukkit.plugin.java.JavaPlugin;
import pl.ic3dd.ic3ddogloszenia.commands.AnnouncementCommand;
import pl.ic3dd.ic3ddogloszenia.commands.CreateAnnouncementCommand;
import pl.ic3dd.ic3ddogloszenia.commands.ReloadConfigCommand;
import pl.ic3dd.ic3ddogloszenia.events.PlayerJoinListener;

import java.util.Objects;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        CreateAnnouncementCommand createAnnouncementCommand = new CreateAnnouncementCommand();
        AnnouncementCommand announcementCommand = new AnnouncementCommand(this, createAnnouncementCommand);

        Objects.requireNonNull(getCommand("ogloszenie")).setExecutor(announcementCommand);
        Objects.requireNonNull(getCommand("stworzogloszenie")).setExecutor(new CreateAnnouncementCommand());
        Objects.requireNonNull(getCommand("ogloszeniareload")).setExecutor(new ReloadConfigCommand(this));
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(announcementCommand, this);
    }
}
