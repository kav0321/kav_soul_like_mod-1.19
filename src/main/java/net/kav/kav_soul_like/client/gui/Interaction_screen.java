package net.kav.kav_soul_like.client.gui;

import io.github.cottonmc.cotton.gui.GuiDescription;
import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import net.kav.kav_soul_like.entity.ModEntities;
import net.kav.kav_soul_like.entity.custom.melina_entity;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.text.Text;

import static io.github.cottonmc.cotton.gui.client.BackgroundPainter.createLightDarkVariants;
import static io.github.cottonmc.cotton.gui.client.BackgroundPainter.createNinePatch;

public class Interaction_screen extends CottonClientScreen {
    public Interaction_screen(GuiDescription description) {
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
            melina_entity melina_entity = new melina_entity(ModEntities.DOROTHY_ENTITY_ENTITY_TYPE,this.client.world);
            InventoryScreen.drawEntity(scaledWidth / 2 -185, scaledHeight / 2 +60, 30, -mouseX/4+4, -mouseY/4, melina_entity);
        }
    }

}
