package dev.nursultan.delta;

import dev.nursultan.delta.config.ClientConfig;
import dev.nursultan.delta.gui.ClickGuiScreen;
import dev.nursultan.delta.module.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

@Mod(modid = ClientMod.MOD_ID, name = ClientMod.NAME, version = ClientMod.VERSION, clientSideOnly = true)
public class ClientMod {
    public static final String MOD_ID = "nursultandelta";
    public static final String NAME = "Nursultan Delta";
    public static final String VERSION = "0.1.0";
    public static final Minecraft MC = Minecraft.getMinecraft();
    public static final ModuleManager MODULES = new ModuleManager();
    public static final ClientConfig CONFIG = new ClientConfig();
    private KeyBinding openGuiKey;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MODULES.registerDefaults();
        CONFIG.load(MODULES);
        openGuiKey = new KeyBinding("key.nursultandelta.clickgui", Keyboard.KEY_RSHIFT,
                "key.categories.nursultandelta");
        ClientRegistry.registerKeyBinding(openGuiKey);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(MODULES);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (openGuiKey != null && openGuiKey.isPressed()) {
            if (MC.currentScreen == null) MC.displayGuiScreen(new ClickGuiScreen(MODULES));
            else if (MC.currentScreen instanceof ClickGuiScreen) MC.displayGuiScreen(null);
        }
    }
}
