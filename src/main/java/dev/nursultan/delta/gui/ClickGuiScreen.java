package dev.nursultan.delta.gui;

import dev.nursultan.delta.ClientMod;
import dev.nursultan.delta.module.Module;
import dev.nursultan.delta.module.ModuleManager;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;
import java.io.IOException;
import java.util.List;

public class ClickGuiScreen extends GuiScreen {
    private final ModuleManager manager;
    private int panelX = 30, panelY = 30;
    private final int panelW = 260, rowH = 25;

    public ClickGuiScreen(ModuleManager manager) { this.manager = manager; }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        List<Module> modules = manager.getModules();
        int panelH = 52 + modules.size() * rowH;
        drawRect(panelX, panelY, panelX + panelW, panelY + panelH, 0xEE101321);
        drawRect(panelX, panelY, panelX + panelW, panelY + 3, 0xFF8B5CF6);
        fontRendererObj.drawStringWithShadow("NURSULTAN DELTA", panelX + 12, panelY + 10, 0xFFFFFFFF);
        fontRendererObj.drawString("ЛКМ: переключить модуль", panelX + 12, panelY + 27, 0xFFB9B8D0);
        for (int i = 0; i < modules.size(); i++) {
            Module module = modules.get(i);
            int y = panelY + 45 + i * rowH;
            boolean hover = mouseX >= panelX + 8 && mouseX <= panelX + panelW - 8
                    && mouseY >= y && mouseY < y + rowH;
            drawRect(panelX + 8, y, panelX + panelW - 8, y + rowH - 2,
                    hover ? 0xFF302746 : 0xFF191B2B);
            fontRendererObj.drawString(module.getName(), panelX + 16, y + 8, 0xFFEAEAF4);
            fontRendererObj.drawString(module.isEnabled() ? "§aON" : "§7OFF",
                    panelX + panelW - 44, y + 8, 0xFFFFFFFF);
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if (mouseButton == 0) {
            List<Module> modules = manager.getModules();
            for (int i = 0; i < modules.size(); i++) {
                int y = panelY + 45 + i * rowH;
                if (mouseX >= panelX + 8 && mouseX <= panelX + panelW - 8
                        && mouseY >= y && mouseY < y + rowH) {
                    modules.get(i).toggle();
                    ClientMod.CONFIG.save(manager);
                    break;
                }
            }
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == Keyboard.KEY_ESCAPE || keyCode == Keyboard.KEY_RSHIFT) {
            ClientMod.CONFIG.save(manager);
            mc.displayGuiScreen(null);
            return;
        }
        super.keyTyped(typedChar, keyCode);
    }

    @Override public boolean doesGuiPauseGame() { return false; }
}
