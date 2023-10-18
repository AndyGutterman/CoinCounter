import Currency.*;
import UI.Functions.RowFunctions;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private double grandRolledValue;

    public CoinCounter() {
        mainWindow = new JFrame("  Coin Counter");
        mainWindow.setIconImage(new ImageIcon(Objects.requireNonNull(CoinCounter.class.getResource("/Icons/coin.png"))).getImage());
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(500, 325);

        ArrayList<Coin> CoinList = new ArrayList<>();
        Coin Quarter = new Coin("Quarter", 0.25F, 40);
        Coin Dime = new Coin("Dime", 0.10F, 50);
        Coin Nickel = new Coin("Nickel", 0.05F, 40);
        Coin Penny = new Coin("Penny", .01F, 50);
        Penny.updatePlural("Pennies");

        CoinList.add(Quarter);
        CoinList.add(Dime);
        CoinList.add(Nickel);
        CoinList.add(Penny);

        JPanel coinPanel = new JPanel(new GridBagLayout()); // Panel containing all per-row values and labels

        coinTextFields = new ArrayList<>();
        coinRowValues = new ArrayList<>();
        coinRowSleeves = new ArrayList<>();
        coinRowRemainders = new ArrayList<>();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        coinPanel.setLayout(new GridBagLayout());
        mainWindow.add(coinPanel);
        gbc.gridx = 0; // Set the column position
        gbc.gridy = 1; // Set the row position to place the spacerPanel below the coinPanel

        mainWindow.setLayout(new BoxLayout(mainWindow.getContentPane(), BoxLayout.Y_AXIS));

        int row = 1;
        RowFunctions.AddTitleRow(gbc, coinPanel);
        for (Coin coin : CoinList) {
            JLabel coinNameLabel = new JLabel(coin.getName());
            JTextField coinTextField = new JTextField(5);
            JLabel coinRowValueLabel = new JLabel(" -.--" );
            JLabel coinRowSleeveQTYLabel = new JLabel(" -- ");
            JLabel coinRowRemainderQTYLabel = new JLabel(" --");

            coinNameLabel.setName(coin.getName());
            coinTextField.setName(coin.getName());
            coinRowValueLabel.setName(coin.getName());
            coinRowValueLabel.setForeground(Color.GREEN.darker()); // Differentiate value per row
            coinRowSleeveQTYLabel.setName(coin.getName());
            coinRowRemainderQTYLabel.setName(coin.getName());

            gbc.gridx = 0; // first column
            gbc.gridy = row;
            coinPanel.add(coinNameLabel, gbc);

            gbc.gridx = 1; // second column
            gbc.gridy = row;
            coinPanel.add(coinTextField, gbc);
            coinTextFields.add(coinTextField);


            gbc.gridx = 2; // third column
            gbc.gridy = row;
            coinPanel.add(coinRowValueLabel, gbc);
            coinRowValues.add(coinRowValueLabel);

            gbc.gridx = 3; //  column
            gbc.gridy = row;
            coinPanel.add(coinRowSleeveQTYLabel, gbc);
            coinRowSleeves.add(coinRowSleeveQTYLabel);

            gbc.gridx = 4; // column
            gbc.gridy = row;
            coinPanel.add(coinRowRemainderQTYLabel, gbc);
            coinRowRemainders.add(coinRowRemainderQTYLabel);
            row++;
        }

        JLabel coinTotalQuantityTitleLabel = new JLabel("Grand Totals:");
        gbc.gridx = 0;
        gbc.gridy = row;
        coinPanel.add(coinTotalQuantityTitleLabel, gbc);

        JLabel coinTotalQuantity = new JLabel(" -- ");
        gbc.gridx = 1;
        gbc.gridy = row;
        coinPanel.add(coinTotalQuantity, gbc);

        coins_TotalValueLabel = new JLabel(" -- ");
        gbc.gridx = 2;
        gbc.gridy = row;
        coinPanel.add(coins_TotalValueLabel, gbc);

        GrandSleeveValue = new JLabel(" -- ");
        gbc.gridx = 3;
        gbc.gridy = row;
        coinPanel.add(GrandSleeveValue, gbc);

        GrandRemainderValue = new JLabel(" -- ");
        gbc.gridx = 4;
        gbc.gridy = row;
        coinPanel.add(GrandRemainderValue, gbc);

        JButton calculateButton = new JButton("Calculate"); // Create calculate button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                grandTotal = 0; // reset sum
                grandQuantity = 0; // Reset totalQuantity
                grandRolledValue= 0;
                grandRemainder = 0;
                for (int i = 0; i < CoinList.size(); i++) {
                    try {
                        int coinQuantity = Integer.parseInt(coinTextFields.get(i).getText());
                        float value = CoinList.get(i).getValue() * coinQuantity;
                        int sleeves = coinQuantity / CoinList.get(i).getRollLimit();
                        int remainder_fromsleeves = coinQuantity % CoinList.get(i).getRollLimit();

                        coinRowValues.get(i).setText(String.format("%.2f", value));


                        if (sleeves >= 1) {
                            int rowRolledValue = (int) CoinList.get(i).getRollValue(sleeves);
                            coinRowSleeves.get(i).setText(String.valueOf(sleeves) + " [$" + rowRolledValue + "]");
                            grandRolledValue += rowRolledValue; // Update value for rolled monies
                            grandRemainder += remainder_fromsleeves * CoinList.get(i).getValue();
                        } else {
                            coinRowSleeves.get(i).setText(String.valueOf(sleeves));
                            grandRemainder += remainder_fromsleeves * CoinList.get(i).getValue();
                        }

                        coinRowRemainders.get(i).setText(String.valueOf(remainder_fromsleeves));
                        grandQuantity += coinQuantity;
                        grandTotal += value;
                    } catch (NumberFormatException ex) {
                        coinRowValues.get(i).setText("0");
                    }
                }
                GrandSleeveValue.setText("  [$" + grandRolledValue + "]");
                GrandRemainderValue.setText("$" + grandRemainder);
                coins_TotalValueLabel.setText(" $" + String.valueOf(grandTotal));
                coinTotalQuantity.setText(String.valueOf(grandQuantity));
                coins_TotalValueLabel.setForeground(Color.GREEN.darker());
            }
        });

        row++; // Add calculate button
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        coinPanel.add(calculateButton, gbc);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CoinCounter coinCounter = new CoinCounter();
            coinCounter.mainWindow.setVisible(true);
        });
    }
}