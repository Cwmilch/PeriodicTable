public class Element {

    private String name, symbol, config;
    private int number;
    private int[] charge;
    private float mass;

    public Element(String name, String symbol, int number, String configuration, float mass, int[] charge){
        this.name = name;
        this.symbol = symbol;
        this.number = number;
        this.charge = charge;
        this.config = configuration;
        this.mass = mass;
    }

    public String getName(){
        return name;
    }

    public String getSymbol(){
        return symbol;
    }

    public int getNumber(){
        return number;
    }

    public String getChargeString(){
        String string = "";
        for(int i = 0; i < charge.length; i++){
            string += getChargeString(i) + (charge.length - i > 1 ? "/" : "");
        }

        return string;
    }

    public String getChargeString(int index){
        return Math.abs(charge[index]) + (charge[index] != 0 ? (charge[index] > 0 ? "+" : "-") : "");
    }

    public float getMass(){
        return mass;
    }

    public String getConfiguration(){
        return config;
    }
}

