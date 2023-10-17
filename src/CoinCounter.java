import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.swing.*;


public class CoinCounter{
    JFrame mainWindow = new JFrame();



    public CoinCounter(){
        ArrayList<Coin> CoinList = new ArrayList();
        Coin Quarter = new Coin("Quarter", 0.25F, 40);
        CoinList.add(Quarter);

        JPanel coinPanel = new JPanel();

        for (Coin coin: CoinList){
            JLabel coinNameLabel = new JLabel(coin.getName());
            coinPanel.add(coinNameLabel);
            JTextField coin_textfield = new JTextField();
        }
    };

    public static void main(String[] args) {

    }
}

