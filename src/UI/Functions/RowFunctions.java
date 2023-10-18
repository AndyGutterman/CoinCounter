package UI.Functions;

import Currency.Coin;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RowFunctions {
    public static void AddTitleRow(GridBagConstraints gbc, JPanel coinPanel) {
        JLabel coinName_title = new JLabel("Type of Coin");
        JLabel coinRollQTY = new JLabel("# of coins");
        JLabel coinRemainderQTY = new JLabel("Total Value:");
        JLabel coinQuantity_title = new JLabel("Full Rolls");
        JLabel coinValue_title = new JLabel("Leftover coins:");

        ArrayList<JLabel> Legend = new ArrayList<>();
        Legend.add(coinName_title);
        Legend.add(coinRollQTY);
        Legend.add(coinRemainderQTY);
        Legend.add(coinQuantity_title);
        Legend.add(coinValue_title);

        gbc.gridx = 0; // first column
        gbc.gridy = 0;
        for (int i = 0; i < Legend.size(); i++) {
            coinPanel.add(Legend.get(i), gbc);
            gbc.gridx++; // Move to the next column
        }
    }
 }

