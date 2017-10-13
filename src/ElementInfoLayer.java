import javax.swing.*;
import javax.swing.plaf.LayerUI;
import java.awt.*;
import java.awt.image.BufferedImage;

class ElementInfoLayer extends LayerUI<JComponent> {

    private static BufferedImage[] images = new BufferedImage[PeriodicTable.getElements().length];


    //Use multiple threads to speed up downloading of images for each element.
    ElementInfoLayer(){
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

    /**
     * Display the info associated with a given {@link Element} in the center of the periodic table
     * @param input The text in the Element's JEditorPane
     * @param g The graphics of the {@link TableRenderer}
     * @param c The color associated with the Element.
     */
    private void paintInfo(String input, Graphics2D g, Color c){
        g.setColor(new Color(185, 195, 199));
        g.fillRect(106, 63, 530, 126);
        g.setColor(c);

        //Get text within the JEditorPane, remove HTML tags
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

    /**
     * Return the image associated with a given {@link Element}.
     * @param element The atomic number of the Element
     * @return {@link ElementInfoLayer#images}[@param element} - 1]
     */
    private BufferedImage getImage(int element){
        return images[element - 1];
    }

    /**
     * Parse the text displayed in an Element's JEditorPane to get the Element's name and atomic number.
     * @param input The string to iterate through
     * @return A two value string array, with the name at index 0 and the number at index 1.
     */
    private String[] data(String input){
        String[] data = new String[2];
        int start = 0;
        for(int i = 0; i < data.length; i++){
            int end = findSubstring(input, start, i != 1);
            data[i] = input.substring(start, end);
            start = end;
        }
        return data;
    }

    /**
     * Iterate through a string until an integer is reached if untilInteger is true, or a non-integer is reached if
     * untilInteger is false.
     * @param s The string to iterate through
     * @param beginIndex The index of the string to start at
     * @param untilInteger Whether the method runs until a character or an integer is reached
     * @return The amount of characters between beginIndex and the first integer/non-integer
     */
    private int findSubstring(String s, int beginIndex, boolean untilInteger){
        int end = 0;
        char[] chars = s.substring(beginIndex).toCharArray();
        for(char c : chars){
            if(untilInteger){
                if(!isIntegerChar(c)){
                    end++;
                }else{
                    break;
                }
            }else{
                if(isIntegerChar(c)){
                    end++;
                }else{
                    break;
                }
            }
        }
        return beginIndex + end;
    }

    /**
     * Add a BufferedImage to the {@link ElementInfoLayer#images} array.
     * @param image The BufferedImage to add
     * @param i The index of the array to add the image to.
     */
    static void setPicture(BufferedImage image, int i){
        images[i] = image;
    }

    /**
     * Check if a character is an integer.
     * @param c The character to check
     * @return True if {@link Integer#parseInt(String)} doesn't throw an exception
     */
    private boolean isIntegerChar(char c){
        String s = Character.toString(c);
        try{
            Integer.parseInt(s);
        }catch (NumberFormatException e){
            return c == '.';
        }
        return true;
    }
}
