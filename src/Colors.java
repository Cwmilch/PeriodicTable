import java.awt.*;

enum Colors {

    ALKALI(new Color(211, 84, 0), new int[]{3, 11, 19, 37, 55, 87}),
    ALKALINE(new Color(230, 126, 34), new int[]{4, 12, 20, 38, 56, 88}),
    HALOGEN(new Color(241, 156, 15), new int[]{9, 17, 35, 53, 85, 117}),
    NOBLE(new Color(155, 89, 182), new int[]{2, 10, 18, 36, 54, 86, 118}),
    METALLOID(new Color(26, 188, 156), new int[]{5, 14, 32, 33, 51, 52}),
    TRANSITION(new Color(52, 73, 94), new int[]{21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48,
            57, 72, 73, 74, 75, 76, 77, 78, 79, 80, 89, 104, 105, 106, 107, 108, 109, 110, 111, 112}),
    LANTHANIDE(new Color(192, 57, 43), new int[]{58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71}),
    ACTINIDE(new Color(231, 76, 60), new int[]{90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103}),
    NONE(new Color(145, 165, 166), new int[]{1, 5, 6, 7, 8, 13, 14, 15, 16, 31, 32, 33, 34, 49, 50, 51, 52, 81, 82, 83, 84, 113, 114, 115, 116});

    private Color c;
    private int[] elements;

    Colors(Color c, int[] numbers){
        this.c = c;
        this.elements = numbers;
    }

    public Color getColor(){
        return c;
    }

    /**
     * Return the classification a given Element falls into in order to get its color.
     * @param elementNum The atomic number of the element
     * @return The enum value associated with the Element's number
     */
    public static Colors getFromElementEnum(int elementNum){
        for(Colors c : Colors.values()){
            int[] elements = c.elements;
            for(int i : elements){
                if(i == elementNum){
                    return c;
                }
            }
        }
        return null;
    }
}
