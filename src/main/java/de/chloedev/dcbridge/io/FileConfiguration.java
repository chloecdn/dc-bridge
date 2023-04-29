package de.chloedev.dcbridge.io;

import de.chloedev.dcbridge.Main;
import org.simpleyaml.configuration.file.YamlFile;

import java.io.IOException;

public class FileConfiguration {

    private YamlFile file;

    public FileConfiguration(String name) {
        try {
            this.file = new YamlFile("./" + name + ".yml");
            this.file.createOrLoad();
        } catch (IOException e) {
            Main.getInstance().getLogger().severe("Couldn't Load Config: ");
            e.printStackTrace();
            //Bungee can't disable a plugin, woopeedoo, enjoy the errors my lads ._.
        }
    }

    public YamlFile getFile() {
        return file;
    }

    public void set(String key, Object value) {
        this.file.set(key, value);
        try {
            this.file.save();
        } catch (IOException e) {
            Main.getInstance().getLogger().severe("Couldn't Save Config: ");
            e.printStackTrace();
        }
    }

    public void reload() {
        try {
            this.file.createOrLoad();
            Main.getInstance().getLogger().info("Reloaded Config.");
        } catch (IOException e) {
            Main.getInstance().getLogger().severe("Couldn't Reload Config: ");
            e.printStackTrace();
        }
    }
}
