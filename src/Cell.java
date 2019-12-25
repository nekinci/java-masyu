import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class Cell extends JPanel {

    private BufferedImage BG_IMAGE;
    private BufferedImage LINE_IMAGE;
    public void setImage(BufferedImage img){
        BG_IMAGE = img;
    }
    public void setLineImage(BufferedImage img) { LINE_IMAGE = img; }

    public Cell(){
        setBorder(new LineBorder(Color.BLACK));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(BG_IMAGE,(this.getWidth() - 64) / 2,(this.getHeight() - 64) / 2,this);
        g.drawImage(LINE_IMAGE,(this.getWidth() - 64) / 2,(this.getHeight() - 64) / 2,this);

    }
}
