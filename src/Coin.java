public class Coin {
    private static String name;
    private static float value;
    private int roll_limit;

    public int roll_value = (int) (roll_limit * value);

    public Coin(String name, float val, int roll_limit) {
        this.name = name;
        this.value = val;
        this.roll_limit = roll_limit;
    }

    public String getName() {
        return name;
    }
}
