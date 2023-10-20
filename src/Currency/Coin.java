package Currency;

import java.util.ArrayList;

public class Coin {
    private final String name; // Change from static to non-static
    private final float value; // Change from static to non-static
    private final int roll_limit;
    public float roll_value; // Also, make this non-static
    private String plural;

    public Coin(String name, float val, int roll_limit, ArrayList<Coin> CoinList) {
        this.name = name;
        this.value = val;
        this.roll_limit = roll_limit;
        this.roll_value = (float)(val * roll_limit);
        CoinList.add(this);
    }

    public String getName() {
        if (plural!= null){
            return plural + " [" + roll_limit + " per roll]:";
        }
        return name +"s" + " [" + roll_limit + " per roll]:";
    }

    public float getValue() {
        return value;
    }

    public int getRollLimit() {
        return roll_limit;
    }

    public void updatePlural(String plural) {
        this.plural = plural;
    }

    public float getRollValue(int qty) {
        return roll_value * qty;
    }
}