import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ImageThread implements Runnable {

    private int start, end;

    public ImageThread(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for(int i = start; i <= end; i++){
            String name = PeriodicTable.getElements()[i].getName().toLowerCase();
            Image img = null;
            try{
                URL url = new URL("http://images-of-elements.com/s/" + name + ".jpg");
                img = ImageIO.read(url);
            } catch (IOException e) {
                try {
                    name = i < 104 ? (i == 13 ? "aluminium" : "caesium") : "transactinoid";
                    URL url = new URL("http://images-of-elements.com/s/" + name + (!name.equals("transactinoid") ? ".jpg" : ".png"));
                    img = ImageIO.read(url);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            ElementInfoLayer.setPicture((BufferedImage)img, i);
        }
    }

}
