import javax.swing.*;
import java.awt.*;

class TableRenderer extends JPanel{

    private static final int TEXT_X = 54;
    private static final int TEXT_Y = 64;
    private static final String SEPARATOR = System.lineSeparator();
    private Element[] elements = PeriodicTable.getElements();

    TableRenderer(){
        FlowLayout f = new FlowLayout();
        f.setVgap(0);
        f.setHgap(0);
        f.setAlignment(FlowLayout.LEFT);
        setLayout(f);
        paintTable();
        paintSeries();
    }

    private void paintElement(int number){
        JEditorPane pane = new JEditorPane();
        Element element = elements[number];
        String data = "";
        pane.setContentType("text/html");
        pane.setSize(TEXT_X - 1, TEXT_Y - 1);
        pane.setPreferredSize(new Dimension(pane.getWidth(), pane.getHeight()));
        Dimension max = pane.getPreferredSize();
        pane.setMaximumSize(max);
        pane.setMinimumSize(max);
        pane.setEditable(false);
        pane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        pane.setBackground(getElementColor(element));
        pane.addMouseListener(new OverlayListener(this));
        data += "<center><font size=2>" + element.getName() + "</font></center>" + SEPARATOR;
        data += "<center><font size=3>" + element.getNumber() + "</font></center>" + SEPARATOR;
        data += "<center><font size=4><strong>" + element.getSymbol() + "</strong></font></center>" + SEPARATOR;
        data += "<center><font size=1>" + element.getMass() + "</font></center>";
        pane.setText(data);
        add(pane);
    }

    private void paintTable(){
        paintElement(0);
        paintBlank(15);
        paintCustomBlank(92);
        paintElement(1);
        paintElement(2);
        paintElement(3);
        paintBlank(9);
        paintCustomBlank(98);
        for(int i = 4; i <= 11; i++){
            paintElement(i);
        }
        paintBlank(9);
        paintCustomBlank(98);
        for(int i = 12; i <= 56; i++){
            paintElement(i);
        }
        for(int i = 71; i <= 88; i++){
            paintElement(i);
        }
        for(int i = 103; i <= 117; i++){
            paintElement(i);
        }
    }

    private void paintSeries(){
        JTextField spacer = new JTextField();
        spacer.setBorder(BorderFactory.createEmptyBorder(10, 465, 10, 456));
        spacer.setEnabled(false);
        spacer.setBackground(getBackground());
        add(spacer);

        paintBlank(2);
        for(int i = 57; i <= 70; i++){
            paintElement(i);
        }
        paintBlank(2);
        for(int i = 89; i <= 102; i++){
            paintElement(i);
        }
    }

    private void paintBlank(int amount){
        JTextArea a = new JTextArea();
        a.setSize(TEXT_X, TEXT_Y);
        a.setBorder(BorderFactory.createEmptyBorder(0, TEXT_X * (amount / 2), 0, TEXT_X * (amount / 2)));
        a.setEnabled(false);
        a.setBackground(getBackground());
        add(a);
    }

    private void paintCustomBlank(int size){
        JTextArea a = new JTextArea();
        a.setSize(TEXT_X, TEXT_Y);
        a.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, size));
        a.setEnabled(false);
        a.setBackground(getBackground());
        add(a);
    }

    private Color getElementColor(Element e){
        Colors c = Colors.getFromElementEnum(e.getNumber());
        assert c != null;

        return c.getColor();
    }
}
