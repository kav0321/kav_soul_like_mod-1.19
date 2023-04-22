package net.kav.kav_soul_like.client.gui;

import io.github.cottonmc.cotton.gui.GuiDescription;
import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import io.github.cottonmc.cotton.gui.widget.WTabPanel;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.util.math.MatrixStack;

public class LevelUpScreen extends CottonClientScreen {
    public LevelUpScreen(GuiDescription description) {
        super(description);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float partialTicks) {
        super.render(matrices, mouseX, mouseY, partialTicks);
        assert this.client != null;
        if (this.client.player != null) {
            int scaledWidth = this.client.getWindow().getScaledWidth();
            int scaledHeight = this.client.getWindow().getScaledHeight();
            InventoryScreen.drawEntity(scaledWidth / 2 +75, scaledHeight / 2 -60, 30, -mouseX/20+28, -mouseY/4, this.client.player);
        }
    }

}
