import Currency.*;

import UI.Functions.RowFunctions;

import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class CoinCounter {
    private JFrame mainWindow;
    private ArrayList<JTextField> coinTextFields;
    private ArrayList<JLabel> coinRowValues;
    private JLabel coinTotalValueLabel;
    private float sum = 0;
    private int totalQuantity = 0;

    public CoinCounter() {
        mainWindow = new JFrame("  Coin Counter");
        mainWindow.setIconImage(new ImageIcon(Objects.requireNonNull(CoinCounter.class.getResource("/Icons/coin.png"))).getImage());
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(400, 315);


        ArrayList<Coin> CoinList = new ArrayList<>();
        Coin Quarter = new Coin("Quarter", 0.25F, 40);
        Coin Dime = new Coin("Dime", 0.10F, 50);
        Coin Nickel = new Coin("Nickel", 0.05F, 40);
        Coin Penny = new Coin("Penny", .01F, 50);

        CoinList.add(Quarter);
        CoinList.add(Dime);
        CoinList.add(Nickel);
        CoinList.add(Penny);


        JPanel coinPanel = new JPanel(new GridBagLayout());
        mainWindow.add(coinPanel);

        coinTextFields = new ArrayList<>();
        coinRowValues = new ArrayList<>();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        coinPanel.setLayout(new GridBagLayout());

        int row = 1;
        RowFunctions.AddTitleRow(gbc, coinPanel);
        for (Coin coin : CoinList) {
            JLabel coinNameLabel = new JLabel(coin.getName() + "s:");
            JTextField coinTextField = new JTextField(5);
            JLabel coinRowValue = new JLabel("--");

            coinNameLabel.setName(coin.getName());
            coinTextField.setName(coin.getName());
            coinRowValue.setName(coin.getName());

            gbc.gridx = 0; // first column
            gbc.gridy = row;

            coinPanel.add(coinNameLabel, gbc);

            gbc.gridx = 1; // second column
            gbc.gridy = row;
            coinPanel.add(coinTextField, gbc);
            coinTextFields.add(coinTextField);

            gbc.gridx = 2; // third column
            gbc.gridy = row;
            coinPanel.add(coinRowValue, gbc);
            coinRowValues.add(coinRowValue);

            /*
            JLabel Sleeves = new JLabel("S");
            Sleeves.setName(coin.getName()+"sleeves");
            gbc.gridx = 3; // fourth column
            gbc.gridy = row;
            coinPanel.add(Sleeves, gbc);
            coinRowValues.add(coinRowValue);

            JLabel remainder = new JLabel("R");
            remainder.setName(coin.getName()+"Remainder");
            gbc.gridx = 4; // fifth column
            gbc.gridy = row;
            coinPanel.add(remainder, gbc);
            coinRowValues.add(coinRowValue);*/
            row++;
        }

        JLabel coinTotalQuantityTitleLabel = new JLabel("Number of coins:");
        gbc.gridx = 0;
        gbc.gridy = row;
        coinPanel.add(coinTotalQuantityTitleLabel, gbc);

        JLabel coinTotalQuantity = new JLabel(" -- ");
        gbc.gridx = 1;
        gbc.gridy = row;
        coinPanel.add(coinTotalQuantity, gbc);

        JLabel coinTotalValueTitleLabel = new JLabel("Total:  $");
        gbc.gridx = 2;
        gbc.gridy = row;
        coinPanel.add(coinTotalValueTitleLabel, gbc);


        coinTotalValueLabel = new JLabel(" -- ");
        gbc.gridx = 3;
        gbc.gridy = row;
        coinPanel.add(coinTotalValueLabel, gbc);

        JButton calculateButton = new JButton("Calculate"); // Create calculate button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sum = 0; // reset sum
                totalQuantity = 0; // Reset totalQuantity
                for (int i = 0; i < CoinList.size(); i++) {
                    try {
                        int coinQuantity = Integer.parseInt(coinTextFields.get(i).getText());
                        float coinValue = CoinList.get(i).getValue() * coinQuantity;
                        coinRowValues.get(i).setText(String.format("%.2f", coinValue));
                        totalQuantity += coinQuantity; // Update total quantity
                        sum += coinValue; // Add the coin value to the sum
                        //int rollLimit = CoinList.get(i).getRollLimit();
                    } catch (NumberFormatException ex) {
                        coinRowValues.get(i).setText("Invalid Input");
                    }
                }


                // Update the total value label
                RowFunctions.UpdateRowValue(CoinList, coinTextFields, coinTotalValueLabel);
                coinTotalQuantity.setText(String.valueOf(totalQuantity));
            }
        });

        // Add calculate button on final row
        row++;
        gbc.gridx = 2;
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