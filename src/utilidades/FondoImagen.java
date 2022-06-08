package utilidades;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.border.Border;

/**
 *
 * @author PABLO
 */
//Clase para cargar un fondo en el JDesktopPanel
public class FondoImagen implements Border {

    private final Image img;

    public FondoImagen() {
        img = new ImageIcon(this.getClass().getResource("/iconos/logo.jpg")).getImage();
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

        g.drawImage(img, 0, 0, c.getWidth(), c.getHeight(), null);

    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(0, 0, 0, 0);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }

}
