import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class Cell extends JPanel {

    private BufferedImage BG_IMAGE;
    private BufferedImage LINE_IMAGE;
    private String text;
    public void setImage(BufferedImage img){
        BG_IMAGE = img;

    }
    public void setLineImage(BufferedImage img,String text)
    {
        LINE_IMAGE = img;
        this.text = text;
    }

    public Cell(){
        setBorder(new LineBorder(Color.BLACK));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(BG_IMAGE,(this.getWidth() - 64) / 2,(this.getHeight() - 64) / 2,this);
        if(text != null)
            g.drawString(text,this.getWidth() - 15 , this.getHeight() - 15);
    }
}
