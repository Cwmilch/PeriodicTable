class Element {

    private String name, symbol, config;
    private int number;
    private int[] charge;
    private float mass;

    Element(String name, String symbol, int number, String configuration, float mass, int[] charge){
        this.name = name;
        this.symbol = symbol;
        this.number = number;
        this.charge = charge;
        this.config = configuration;
        this.mass = mass;
    }

    String getName(){
        return name;
    }

    String getSymbol(){
        return symbol;
    }

    int getNumber(){
        return number;
    }

    String getChargeString(){
        String string = "";
        for(int i = 0; i < charge.length; i++){
            string += getChargeString(i) + (charge.length - i > 1 ? "/" : "");
        }

        return string;
    }

    private String getChargeString(int index){
        return Math.abs(charge[index]) + (charge[index] != 0 ? (charge[index] > 0 ? "+" : "-") : "");
    }

    float getMass(){
        return mass;
    }

    String getConfiguration(){
        return config;
    }
}

