package net.kav.kav_soul_like.client.gui.buttons;

import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.icon.Icon;
import io.github.cottonmc.cotton.gui.widget.icon.TextureIcon;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
@Environment(EnvType.CLIENT)
public class WButtonLeft extends WButton {



    @Override
    public void paint(MatrixStack matrices, int x, int y, int mouseX, int mouseY) {
        Icon icon;
        if (this.isEnabled()) {
            boolean hovered = (mouseX >= 0 && mouseY >= 0 && mouseX < getWidth() && mouseY < getHeight());
            icon = new TextureIcon(new Identifier("kav_soul_like:textures/gui/buttonleft.png"));
            if (hovered) {
                icon = new TextureIcon(new Identifier("kav_soul_like:textures/gui/buttonleftselected.png"));
            }
        } else {
            icon = new TextureIcon(new Identifier("kav_soul_like:textures/gui/buttonleftdisable.png"));
        }
        icon.paint(matrices, x , y, 5);
    }

    @Override
    public void setSize(int x, int y) {
        this.width = 4;
        this.height = 7;
    }

}
