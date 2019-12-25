package Constants;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Helper {

    private Helper(){

    }
    private static final String pre_File = "./src/";
    public static BufferedImage BLACK_CIRCLE(){
        try{
            File file = new File(pre_File+"masyu_assets/beyaz.png");
            return ImageIO.read(file);
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return null;
    }
    public static BufferedImage WHITE_CIRCLE(){
        try{
            File file = new File(pre_File+"masyu_assets/siyah.png");
            return ImageIO.read(file);
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return null;
    }
    public static BufferedImage HORIZONTAL_LINE(){
        try{
            File file = new File(pre_File+"masyu_assets/2.png");
            return ImageIO.read(file);
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return null;
    }
    public static BufferedImage VERTICAL_LINE(){
        try{
            File file = new File(pre_File+"masyu_assets/8.png");
            return ImageIO.read(file);
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return null;
    }
    public static BufferedImage BOTTOM_RIGHT(){
        try{
            File file = new File(pre_File+"masyu_assets/7.png");
            return ImageIO.read(file);
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return null;
    }
    public static BufferedImage BOTTOM_LEFT(){
        try{
            File file = new File(pre_File+"masyu_assets/5.png");
            return ImageIO.read(file);
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static BufferedImage UP_RIGHT(){
        try{
            File file = new File(pre_File+"masyu_assets/1.png");
            return ImageIO.read(file);
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return null;
    }
    public static BufferedImage UP_LEFT(){
        try{
            File file = new File(pre_File+"masyu_assets/3.png");
            return ImageIO.read(file);
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
