package UI.Functions;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RowFunctions {
    public static void AddTitleRow(GridBagConstraints gbc, JPanel coinPanel) {
        JLabel coinName_title = new JLabel("Type of Coin");
        JLabel coinRollQTY = new JLabel("# of Coins");
        JLabel coinRemainderQTY = new JLabel("Total Value:");
        JLabel coinQuantity_title = new JLabel("Rolls : [Value]");
        JLabel coinValue_title = new JLabel("Remaining Coins:");

        ArrayList<JLabel> Legend = new ArrayList<>();
        Legend.add(coinName_title);
        Legend.add(coinRollQTY);
        Legend.add(coinRemainderQTY);
        Legend.add(coinQuantity_title);
        Legend.add(coinValue_title);

        gbc.gridx = 0; // first column
        gbc.gridy = 0;
        for (JLabel jLabel : Legend) {
            coinPanel.add(jLabel, gbc);
            gbc.gridx++; // Move to the next column
        }
    }
 }

