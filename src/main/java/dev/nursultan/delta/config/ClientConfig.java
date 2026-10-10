package dev.nursultan.delta.config;

import dev.nursultan.delta.module.Module;
import dev.nursultan.delta.module.ModuleManager;
import net.minecraft.client.Minecraft;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class ClientConfig {
    private File getFile() {
        File dir = new File(Minecraft.getMinecraft().mcDataDir, "config");
        if (!dir.exists()) dir.mkdirs();
        return new File(dir, "nursultan-delta.properties");
    }

    public void load(ModuleManager manager) {
        File file = getFile();
        if (!file.isFile()) return;
        Properties properties = new Properties();
        try (FileInputStream in = new FileInputStream(file)) {
            properties.load(in);
            for (Module module : manager.getModules()) {
                String value = properties.getProperty("module." + module.getName());
                if (value != null) module.setEnabled(Boolean.parseBoolean(value));
            }
        } catch (Exception e) {
            System.err.println("[NursultanDelta] Config load failed: " + e.getMessage());
        }
    }

    public void save(ModuleManager manager) {
        Properties properties = new Properties();
        for (Module module : manager.getModules())
            properties.setProperty("module." + module.getName(), Boolean.toString(module.isEnabled()));
        try (FileOutputStream out = new FileOutputStream(getFile())) {
            properties.store(out, "Nursultan Delta settings");
        } catch (Exception e) {
            System.err.println("[NursultanDelta] Config save failed: " + e.getMessage());
        }
    }
}
