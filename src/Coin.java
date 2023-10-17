public class Coin {
    private String name; // Change from static to non-static
    private float value; // Change from static to non-static
    private int roll_limit;
    public int roll_value; // Also, make this non-static

    public Coin(String name, float val, int roll_limit) {
        this.name = name;
        this.value = val;
        this.roll_limit = roll_limit;
        this.roll_value = (int) (roll_limit * val); // Initialize roll_value here
    }

    public String getName() {
        return name;
    }
}