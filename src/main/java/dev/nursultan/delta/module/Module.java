package dev.nursultan.delta.module;

public abstract class Module {
    private final String name;
    private final String description;
    private boolean enabled;

    protected Module(String name, String description, boolean enabledByDefault) {
        this.name = name;
        this.description = description;
        this.enabled = enabledByDefault;
    }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) {
        if (this.enabled == enabled) return;
        this.enabled = enabled;
        if (enabled) onEnable(); else onDisable();
    }
    public void toggle() { setEnabled(!enabled); }
    public void onEnable() {}
    public void onDisable() {}
    public void onUpdate() {}
}
