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

    /**
     * @return The Element's name
     */
    String getName(){
        return name;
    }

    /**
     * @return The Element's atomic symbol.
     */
    String getSymbol(){
        return symbol;
    }

    /**
     * @return The Element's atomic number.
     */
    int getNumber(){
        return number;
    }

    /**
     * Gets the charge of the Element, separates multiple values with '/' if applicable.
     * @return The Element's charges as a string.
     */
    String getChargeString(){
        StringBuilder string = new StringBuilder();
        for(int i = 0; i < charge.length; i++){
            string.append(getChargeString(i)).append(charge.length - i > 1 ? "/" : "");
        }

        return string.toString();
    }

    /**
     * @param index The index of the Element's charge in {@link Element#charge}
     * @return The charge of the Element as a string, appending a + or - sign to the end if applicable.
     */
    private String getChargeString(int index){
        int val = charge[index];
        String sign = "";
        if (charge[index] != 0){
            sign = charge[index]  > 0 ? "+" : "-";
        }

        return Math.abs(val) + sign;
    }

    float getMass(){
        return mass;
    }

    String getConfiguration(){
        return config;
    }
}

