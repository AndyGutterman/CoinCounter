package Currency;

import java.util.ArrayList;

public class Coin {
    private final String name;
    private final float value;
    private final int roll_limit;
    public float roll_value;
    private String plural;

    public Coin(String name, float val, int roll_limit, ArrayList<Coin> CoinList) {
        this.name = name;
        this.value = val;
        this.roll_limit = roll_limit;
        this.roll_value = (val * roll_limit);
        CoinList.add(this);
    }

    public String getName() {
        if (plural!= null){
            return plural + " [" + roll_limit + " per roll]:";
        }
        return name +"s" + " [" + roll_limit + " per roll]:";
    }

    public void updatePlural(String plural) {
        this.plural = plural;
    }
    public float getValue() {
        return value;
    }

    public int getRollLimit() {
        return roll_limit;
    }

    public float getRollValue(int qty) {
        return roll_value * qty;
    }

}