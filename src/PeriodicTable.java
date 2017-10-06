import javax.swing.*;
import javax.swing.plaf.LayerUI;
import java.util.Scanner;

public class PeriodicTable{

    private static Element[] elements;
    private static LayerUI<JComponent> layerUI;

    public static void main(String[] args){
        elements = new Element[118];
        Scanner sc = null;
        try {
            sc = new Scanner(PeriodicTable.class.getResourceAsStream("Values.txt"));
        } catch (Exception e) {
            System.out.println("Values.txt file not found! Exiting...");
            System.exit(0);
        }

        //Iterate through each line in Values.txt, use the values given to create a new Element object
        for(int i = 0; i < elements.length; i++){
            String data = sc.next();
            String[] values = data.split(";");

            float mass = Float.parseFloat(values[3]);
            int[] charge = charge(values[4]);

            elements[i] = new Element(values[0], values[1], i + 1, values[2].replaceAll("\\.", " "), mass, charge);
        }
        sc.close();

        layerUI = new ElementInfoLayer();

        JFrame table = new JFrame("Periodic Table");
        table.setSize(960, 675);
        JLayer<JComponent> layer = new JLayer<>(new TableRenderer(), layerUI);
        table.setContentPane(layer);
        table.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        table.setResizable(false);
        table.setVisible(true);
    }

    /**
     * Parse the atomic charge of an element specified in Values.txt in case there's multiple charge possibilities
     * @param input the input string
     * @return input if the element only has one charge, otherwise an array of the possible charges
     */
    private static int[] charge(String input){
        int[] charge;
        if(input.contains("/")){
            String[] charges = input.split("/");
            charge = new int[charges.length];
            for(int i = 0; i < charge.length; i++){
                charge[i] = Integer.parseInt(charges[i]);
            }
        }else{
            charge = new int[]{Integer.parseInt(input)};
        }
        return charge;
    }

    /**
     * Get an Element at the given index from {@link PeriodicTable#elements}
     * @param number The number of the element
     * @return elements[number]
     */
    static Element getElement(String number){
        return elements[Integer.parseInt(number) - 1];
    }

    /**
     * @return {@link PeriodicTable#elements}
     */
    static Element[] getElements(){
        return elements;
    }

    static LayerUI<JComponent> getLayer(){
        return layerUI;
    }
}

