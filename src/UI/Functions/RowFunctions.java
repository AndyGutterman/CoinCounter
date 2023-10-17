package UI.Functions;

import Currency.Coin;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RowFunctions {
    public static void AddTitleRow(GridBagConstraints gbc, JPanel coinPanel){
        JLabel coinName_title = new JLabel("Coin Name");
        JLabel coinQuantity_title = new JLabel("Quantity");
        JLabel coinValue_title = new JLabel("Value");

        ArrayList<JLabel> Legend = new ArrayList<>();
        Legend.add(coinName_title);
        Legend.add(coinQuantity_title);
        Legend.add(coinValue_title);

        gbc.gridx = 0; // first column
        gbc.gridy = 0;
        coinPanel.add(coinName_title, gbc);

        gbc.gridx = 1; // second column
        gbc.gridy = 0;
        coinPanel.add(coinQuantity_title, gbc);

        gbc.gridx = 2; // third column
        gbc.gridy = 0;
        coinPanel.add(coinValue_title, gbc);
    }

    public static void UpdateRowValue(ArrayList<Coin> CoinList, ArrayList<JTextField> coinTextFields, JLabel coinTotalValueLabel) {
        float totalValue = 0.0f;

        for (int i = 0; i < CoinList.size(); i++) {
            try {
                int coinQuantity = Integer.parseInt(coinTextFields.get(i).getText());
                totalValue += CoinList.get(i).getValue() * coinQuantity;
            } catch (NumberFormatException ex) {
                // Handle invalid input if necessary
            }
        }

        coinTotalValueLabel.setText(String.format("%.2f", totalValue));
    }


 }

