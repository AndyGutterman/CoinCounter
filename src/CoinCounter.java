import Currency.Coin;
import UI.Functions.RowFunctions;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class CoinCounter {
    private final JFrame mainWindow;
    private final ArrayList<JTextField> coinTextFields;
    private final ArrayList<JLabel> coinRowValues;
    private final ArrayList<JLabel> coinRowSleeves;
    private final ArrayList<JLabel> coinRowRemainders;
    private final JLabel coins_TotalValueLabel;
    private final JLabel GrandSleeveValue;
    private final JLabel GrandRemainderValue;
    private float grandTotal = 0;
    private int grandQuantity = 0;
    private float grandRemainder;
    private float grandRolledValue;

    public CoinCounter(ArrayList<Coin> CoinList) {
        mainWindow = new JFrame("Coin Counter");
        mainWindow.setIconImage(new ImageIcon(Objects.requireNonNull(CoinCounter.class.getResource("/Icons/coin.png"))).getImage());
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setMinimumSize(new Dimension(1100, 650));

        JPanel coinPanel = new JPanel(new GridBagLayout());

        coinTextFields = new ArrayList<>();
        coinRowValues = new ArrayList<>();
        coinRowSleeves = new ArrayList<>();
        coinRowRemainders = new ArrayList<>();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        coinPanel.setLayout(new GridBagLayout());
        mainWindow.add(coinPanel);
        gbc.gridx = 0;
        gbc.gridy = 1;

        mainWindow.setLayout(new BoxLayout(mainWindow.getContentPane(), BoxLayout.Y_AXIS));

        int row = 1;
        RowFunctions.AddTitleRow(gbc, coinPanel);
        for (Coin coin : CoinList) {
            JLabel coinNameLabel = new JLabel(coin.getName());
            coinNameLabel.setFont(new Font("Arial", Font.BOLD, 18));

            JTextField coinTextField = new JTextField(8);
            coinTextField.setFont(new Font("Arial", Font.PLAIN, 18));

            JLabel coinRowValueLabel = new JLabel(" -.--");
            coinRowValueLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            coinRowValueLabel.setForeground(Color.GREEN.darker());

            JLabel coinRowSleeveQTYLabel = new JLabel(" -- ");
            coinRowSleeveQTYLabel.setFont(new Font("Arial", Font.PLAIN, 18));

            JLabel coinRowRemainderQTYLabel = new JLabel(" --");
            coinRowRemainderQTYLabel.setFont(new Font("Arial", Font.PLAIN, 18));

            gbc.gridx = 0;
            gbc.gridy = row;
            coinPanel.add(coinNameLabel, gbc);

            gbc.gridx = 1;
            coinPanel.add(coinTextField, gbc);
            coinTextFields.add(coinTextField);

            gbc.gridx = 2;
            coinPanel.add(coinRowValueLabel, gbc);
            coinRowValues.add(coinRowValueLabel);

            gbc.gridx = 3;
            coinPanel.add(coinRowSleeveQTYLabel, gbc);
            coinRowSleeves.add(coinRowSleeveQTYLabel);

            gbc.gridx = 4;
            coinPanel.add(coinRowRemainderQTYLabel, gbc);
            coinRowRemainders.add(coinRowRemainderQTYLabel);

            row++;
        }

        JLabel coinTotalQuantityTitleLabel = new JLabel("Grand Totals:");
        coinTotalQuantityTitleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = row;
        coinPanel.add(coinTotalQuantityTitleLabel, gbc);

        JLabel coinTotalQuantity = new JLabel(" -- ");
        coinTotalQuantity.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 1;
        coinPanel.add(coinTotalQuantity, gbc);

        coins_TotalValueLabel = new JLabel(" -- ");
        coins_TotalValueLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 2;
        coinPanel.add(coins_TotalValueLabel, gbc);

        GrandSleeveValue = new JLabel(" -- ");
        GrandSleeveValue.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 3;
        coinPanel.add(GrandSleeveValue, gbc);

        GrandRemainderValue = new JLabel(" -- ");
        GrandRemainderValue.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 4;
        coinPanel.add(GrandRemainderValue, gbc);

        JButton calculateButton = getCalculateButton(CoinList, coinTotalQuantity);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        coinPanel.add(calculateButton, gbc);
    }

    private JButton getCalculateButton(ArrayList<Coin> CoinList, JLabel coinTotalQuantity) {
        JButton calculateButton = new JButton("Calculate");
        calculateButton.setFont(new Font("Arial", Font.BOLD, 20));
        calculateButton.addActionListener(e -> {
            grandTotal = 0;
            grandQuantity = 0;
            grandRolledValue = 0;
            grandRemainder = 0;
            for (int i = 0; i < CoinList.size(); i++) {
                try {
                    int coinQuantity = Integer.parseInt(coinTextFields.get(i).getText());
                    float value = CoinList.get(i).getValue() * coinQuantity;
                    int sleeves = coinQuantity / CoinList.get(i).getRollLimit();
                    int remainderFromSleeves = coinQuantity % CoinList.get(i).getRollLimit();
                    coinRowValues.get(i).setText(String.format("%.2f", value));

                    if (sleeves >= 1) {
                        float rowRolledValue = CoinList.get(i).getRollValue(sleeves);
                        coinRowSleeves.get(i).setText(sleeves + " [$" + rowRolledValue + "]");
                        grandRolledValue += rowRolledValue;
                    } else {
                        coinRowSleeves.get(i).setText(String.valueOf(sleeves));
                    }
                    grandRemainder += remainderFromSleeves * CoinList.get(i).getValue();
                    coinRowRemainders.get(i).setText(String.valueOf(remainderFromSleeves));
                    grandQuantity += coinQuantity;
                    grandTotal += value;
                } catch (NumberFormatException ex) {
                    coinRowValues.get(i).setText("0");
                }
            }
            coinTotalQuantity.setText(String.valueOf(grandQuantity));
            coins_TotalValueLabel.setText(" $" + String.format("%.2f", grandTotal));
            coins_TotalValueLabel.setForeground(Color.GREEN.darker());
            GrandSleeveValue.setText("  [$" + String.format("%.2f", grandRolledValue) + "]");
            GrandRemainderValue.setText("$" + String.format("%.2f", grandRemainder));
        });
        return calculateButton;
    }

    public static void main(String[] args) {
        ArrayList<Coin> DollarCoins = new ArrayList<>();
        Coin Quarter = new Coin("Quarter", 0.25F, 40, DollarCoins);
        Coin Dime = new Coin("Dime", 0.10F, 50, DollarCoins);
        Coin Nickel = new Coin("Nickel", 0.05F, 40, DollarCoins);
        Coin Penny = new Coin("Penny", 0.01F, 50, DollarCoins);
        Penny.updatePlural("Pennies"); // Example of updating plural form for Penny

        SwingUtilities.invokeLater(() -> {
            CoinCounter coinCounter = new CoinCounter(DollarCoins);
            coinCounter.mainWindow.setVisible(true);
        });
    }

}
