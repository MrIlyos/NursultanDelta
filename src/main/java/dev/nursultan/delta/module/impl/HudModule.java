package dev.nursultan.delta.module.impl;

import dev.nursultan.delta.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HudModule extends Module {
    public HudModule() {
        super("HUD", "Название клиента и FPS", true);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onOverlay(RenderGameOverlayEvent.Text event) {
        if (!isEnabled()) return;
        Minecraft mc = Minecraft.getMinecraft();
        mc.fontRendererObj.drawStringWithShadow("§5Nursultan §dDelta §7| §fFPS: "
                + Minecraft.getDebugFPS(), 6, 6, 0xFFFFFF);
    }
}
