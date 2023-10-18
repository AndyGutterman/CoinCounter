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
    private float grandSum = 0;
    private int grandQuantity = 0;

    public CoinCounter() {
        mainWindow = new JFrame("  Coin Counter");
        mainWindow.setIconImage(new ImageIcon(Objects.requireNonNull(CoinCounter.class.getResource("/Icons/coin.png"))).getImage());
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(500, 350);


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

        JPanel spacerPanel = new JPanel();
        spacerPanel.setPreferredSize(new Dimension(400, 250)); // Panel to act as spacer

        JPanel rollSummaryPanel = new JPanel();
        rollSummaryPanel.setPreferredSize(new Dimension(400, 200));



        coinTextFields = new ArrayList<>();

        coinRowValues = new ArrayList<>();

        coinRowSleeves = new ArrayList<>();

        coinRowRemainders = new ArrayList<>();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        coinPanel.setLayout(new GridBagLayout());

        gbc.gridx = 0; // Set the column position
        gbc.gridy = 1; // Set the row position to place the spacerPanel below the coinPanel


        mainWindow.setLayout(new BoxLayout(mainWindow.getContentPane(), BoxLayout.Y_AXIS));
        mainWindow.add(coinPanel);
        mainWindow.add(spacerPanel);


        //contentPanel.setLayout(new GridBagLayout()); // @ ChatGPT could you help me figure out how to make these go in ordder of apearance?

        int row = 1;
        RowFunctions.AddTitleRow(gbc, coinPanel);
        for (Coin coin : CoinList) {
            JLabel coinNameLabel = new JLabel(coin.getName());
            JTextField coinTextField = new JTextField(5);
            JLabel coinRowValueLabel = new JLabel(" -.--" );
            JLabel coinRowSleeveQTYLabel = new JLabel(" --");
            JLabel coinRowRemainderQTYLabel = new JLabel(" -");

            coinNameLabel.setName(coin.getName());
            coinTextField.setName(coin.getName());
            coinRowValueLabel.setName(coin.getName());
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

        JLabel coinTotalQuantityTitleLabel = new JLabel("Number of coins:");
        gbc.gridx = 0;
        gbc.gridy = row;
        coinPanel.add(coinTotalQuantityTitleLabel, gbc);

        JLabel coinTotalQuantity = new JLabel(" -- ");
        gbc.gridx = 1;
        gbc.gridy = row;
        coinPanel.add(coinTotalQuantity, gbc);

        JLabel coinTotalValueTitleLabel = new JLabel("Grand Total:  $");
        gbc.gridx = 2;
        gbc.gridy = row;
        coinPanel.add(coinTotalValueTitleLabel, gbc);


        coins_TotalValueLabel = new JLabel(" -- ");
        gbc.gridx = 3;
        gbc.gridy = row;
        coinPanel.add(coins_TotalValueLabel, gbc);


        JButton calculateButton = new JButton("Calculate"); // Create calculate button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                grandSum = 0; // reset sum
                grandQuantity = 0; // Reset totalQuantity
                for (int i = 0; i < CoinList.size(); i++) {
                    try {
                        int coinQuantity = Integer.parseInt(coinTextFields.get(i).getText());
                        float value = CoinList.get(i).getValue() * coinQuantity;        //
                        int sleeves = (Integer.parseInt(coinTextFields.get(i).getText()) / CoinList.get(i).getRollLimit());   // calculate # of sleeves
                        int remainder_fromsleeves = (Integer.parseInt(coinTextFields.get(i).getText()) % CoinList.get(i).getRollLimit()); // calculate remainder

                        //float row_rolls_value = sleeves * CoinList.get(i).getValue();

                        coinRowValues.get(i).setText(String.format("%.2f", value)); // Set values label at i's text to 'sleeves' value.
                        coinRowSleeves.get(i).setText(String.valueOf(sleeves)); // Set sleeves label at i's text to 'sleeves' value.
                        coinRowRemainders.get(i).setText(String.valueOf(remainder_fromsleeves));

                        grandQuantity += coinQuantity;           // Add to running QTY
                        grandSum += value;                       //Add to running sum
                    } catch (NumberFormatException ex) {
                        coinRowValues.get(i).setText("Invalid Input");
                    }
                }
                coins_TotalValueLabel.setText(String.valueOf(grandSum));
                coinTotalQuantity.setText(String.valueOf(grandQuantity));
            }
        });

        // Add calculate button on final row
        row++;
        gbc.gridx = 2;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        coinPanel.add(calculateButton, gbc);

        row++;
        //mainWindow.add(SummaryPanelCreator.createSummaryRow(CoinList, coinTextFields, rollSummaryPanel, gbc, row)); // Create and add summary panel

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CoinCounter coinCounter = new CoinCounter();
            coinCounter.mainWindow.setVisible(true);
        });
    }
}