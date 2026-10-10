package dev.nursultan.delta.module;

import dev.nursultan.delta.module.impl.HudModule;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<Module>();
    public void register(Module module) { modules.add(module); }
    public List<Module> getModules() { return Collections.unmodifiableList(modules); }

    public void registerDefaults() { register(new HudModule()); }

    public Module getByName(String name) {
        for (Module module : modules) if (module.getName().equalsIgnoreCase(name)) return module;
        return null;
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        for (Module module : modules) if (module.isEnabled()) module.onUpdate();
    }
}
