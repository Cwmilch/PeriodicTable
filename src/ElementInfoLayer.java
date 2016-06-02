import javax.swing.*;
import javax.swing.plaf.LayerUI;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ElementInfoLayer extends LayerUI<JComponent> {

    private static BufferedImage[] images = new BufferedImage[PeriodicTable.getElements().length];


    public ElementInfoLayer(){
        Thread[] getters = new Thread[5];
        getters[0] = new Thread(new ImageThread(0, 21));
        getters[1] = new Thread(new ImageThread(22, 40));
        getters[2] = new Thread(new ImageThread(41, 60));
        getters[3] = new Thread(new ImageThread(61, 80));
        getters[4] = new Thread(new ImageThread(81, 117));
        for(Thread t : getters){
            t.start();
        }
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        if(!(c instanceof JEditorPane)) {
            super.paint(g, c);
        } else {
            Graphics2D g2 = (Graphics2D) g;
            paintInfo(((JEditorPane) c).getText(), g2, c.getBackground().darker());
        }
    }

    public void paintInfo(String input, Graphics2D g, Color c){
        g.setColor(new Color(185, 195, 199));
        g.fillRect(106, 63, 530, 126);
        g.setColor(c);
        String[] data = data(input.replaceAll("<[^>]*>", ""));
        Element e = PeriodicTable.getElement(data[1]);
        String name = "Name: " + e.getName();
        String num = "Atomic Number: " + Integer.toString(e.getNumber());
        String sym = "Symbol: " + e.getSymbol();
        String mass = "Atomic Mass: " + Float.toString(e.getMass());
        String charge = "Ionic Charge: " + e.getChargeString();
        String config = "Electron Configuration: " + e.getConfiguration();
        String[] values = {name, num, sym, mass, charge, config};
        int y = 84;

        for(String s : values){
            g.drawString(s, 120, y);
            y += 20;
        }
        BufferedImage image = getImage(e.getNumber());
        g.drawImage(image, 500, 64, 136, 125, null);
    }

    public BufferedImage getImage(int element){
        return images[element - 1];
    }

    public String[] data(String input){
        String[] data = new String[2];
        int start = 0;
        for(int i = 0; i < data.length; i++){
            int end = findSubstring(input, start, (i + 1) % 2 == 0);
            data[i] = input.substring(start, end);
            start = end;
        }
        return data;
    }

    public int findSubstring(String s, int beginIndex, boolean integer){
        int end = 0;
        char[] chars = s.substring(beginIndex).toCharArray();
        for(char c : chars){
            if(integer){
                if(isIntegerChar(c)){
                    end++;
                }else{
                    break;
                }
            }else{
                if(!isIntegerChar(c)){
                    end++;
                }else{
                    break;
                }
            }
        }
        return beginIndex + end;
    }

    public static void setPicture(BufferedImage image, int i){
        images[i] = image;
    }

    public boolean isIntegerChar(char c){
        String s = Character.toString(c);
        try{
            Integer.parseInt(s);
        }catch (NumberFormatException e){
            return c == '.';
        }
        return true;
    }
}
