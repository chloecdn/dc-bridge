package de.chloedev.dcbridge.proxy.io;

import de.chloedev.dcbridge.proxy.Main;
import org.jetbrains.annotations.Nullable;
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

    @SuppressWarnings("unchecked")
    public <T> T get(Class<T> type, String key, @Nullable T def) {
        return (T) this.file.get(key, def);
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
        } catch (IOException e) {
            Main.getInstance().getLogger().severe("Couldn't Reload Config: ");
            e.printStackTrace();
        }
    }
}
