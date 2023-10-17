import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class CoinCounter {
    private JFrame mainWindow;

    public CoinCounter() {
        mainWindow = new JFrame("Coin Counter");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(400, 300);

        ArrayList<Coin> CoinList = new ArrayList<>();
        Coin Quarter = new Coin("Quarter", 0.25F, 40);
        Coin Dime = new Coin("Dime", 0.10F, 50);
        Coin Nickel = new Coin("Nickel", .05F, 50);

        CoinList.add(Quarter);
        CoinList.add(Dime);
        CoinList.add(Nickel);

        JPanel coinPanel = new JPanel(new GridBagLayout());
        mainWindow.add(coinPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Add padding

        for (Coin coin : CoinList) {
            JLabel coinNameLabel = new JLabel(coin.getName()+"s:");
            JTextField coin_textfield = new JTextField(8); // Set the desired width

            coinNameLabel.setName(coin.getName());
            coin_textfield.setName(coin.getName());

            coinPanel.add(coinNameLabel, gbc);
            gbc.gridwidth = GridBagConstraints.REMAINDER; // New line
            coinPanel.add(coin_textfield, gbc);
            gbc.gridwidth = GridBagConstraints.RELATIVE; // Back to previous line
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CoinCounter coinCounter = new CoinCounter();
            coinCounter.mainWindow.setVisible(true);
        });
    }
}
