import javax.swing.*;
import javax.swing.plaf.LayerUI;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PeriodicTable{

    private static Element[] elements;
    private static LayerUI<JComponent> layerUI;

    public static void main(String[] args){
        elements = new Element[118];
        Scanner sc = null;
        try {
            sc = new Scanner(new FileInputStream("Values.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
        table.setSize(960, 650);
        JLayer<JComponent> layer = new JLayer<JComponent>(new TableRenderer(), layerUI);
        table.setContentPane(layer);
        table.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        table.setResizable(false);
        table.setVisible(true);
    }

    public static int[] charge(String input){
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

    public static Element getElement(String number){
        return elements[Integer.parseInt(number) - 1];
    }

    public static Element[] getElements(){
        return elements;
    }

    public static LayerUI<JComponent> getLayer(){
        return layerUI;
    }
}

