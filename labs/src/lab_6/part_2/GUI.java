package lab_6.part_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GUI extends JFrame // сохранение данных в файл, реализация механизма закрытия окна (вот тут и сохранять в файл) [использовать события окна]
{
    private JTextField price;
    private JTextArea cartOutput;
    private JRadioButton cash, card;
    private ButtonGroup checkoutType;
    private JLabel name, desc, priceLabel, cartLabel;
    private JButton checkout, add, del;
    private JComboBox<String> box;
    private Color scheme;

    private Shop shop;

    private String[] items = {"Медовуха", "Пшеничка", "Richmond Cherry", "Parliament Platinum Blue", "Sobranie Black"};

    public class Shop
    {
        public List<String> cart;
        public Shop() { cart = new ArrayList<String>(); }
    }

    public GUI()
    {
        super("Ковбой Пивкос Сигаретов");
        shop = new Shop();
        scheme = new Color(193, 232, 168);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 800);
        getContentPane().setBackground(scheme);
        addWindowListener(new WinListener());
        setLayout(null);

        name = new JLabel("Добро пожаловать в Ковбой Пивкос Сигаретов!");
        name.setBounds(400, 50, 300, 15);
        add(name);

        desc = new JLabel("АКЦИЯ: Каждый товар за 2 рубля! Бери бери всё бери");
        desc.setBounds(350, 75, 500, 15);
        desc.setForeground(Color.red);
        add(desc);

        box = new JComboBox<String>(items);
        box.setBounds(225, 200, 150, 20);
        add(box);

        add = new JButton("Добавить");
        add.setBounds(425, 180, 100, 20);
        add.setActionCommand("Добавить");
        add.addActionListener(new CartListener());
        add(add);

        del = new JButton("Удалить");
        del.setBounds(425, 220, 100, 20);
        del.setActionCommand("Удалить");
        del.addActionListener(new CartListener());
        add(del);

        cartOutput = new JTextArea();
        cartOutput.setBounds(590, 150, 150, 200);
        cartOutput.setEditable(false);
        add(cartOutput);

        cartLabel = new JLabel("Корзина");
        cartLabel.setBounds(640, 120, 100, 30);
        add(cartLabel);

        price = new JTextField();
        price.setBounds(410, 450, 210, 50);
        price.setText("0");
        price.setEditable(false);
        add(price);

        priceLabel = new JLabel("Стоимость");
        priceLabel.setBounds(485, 410, 100, 50);
        add(priceLabel);

        checkout = new JButton("Оплатить");
        checkout.setBounds(415, 550, 200, 50);
        checkout.addActionListener(new CheckoutListener());
        add(checkout);

        cash = new JRadioButton("Наличными");
        cash.setBounds(415, 515, 100, 25);
        cash.setBackground(scheme);
        add(cash);

        card = new JRadioButton("Картой");
        card.setBounds(540, 515, 100, 25);
        card.setBackground(scheme);
        add(card);

        checkoutType = new ButtonGroup();
        checkoutType.add(cash); checkoutType.add(card);
    }

    public class CheckoutListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent event)
        {
            try
            {
                String type = checkoutType.getSelection().getActionCommand();
            }
            catch (NullPointerException e) { cash.setForeground(Color.red); card.setForeground(Color.red); return; }
            price.setText("Оплата произведена!");
            cash.setForeground(Color.darkGray); card.setForeground(Color.darkGray);
        }
    }

    public class CartListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent event)
        {
            String type = event.getActionCommand();
            String item = (String) box.getSelectedItem();
            if (type.equals("Добавить")) shop.cart.add(item);
            if (type.equals("Удалить")) shop.cart.remove(item);

            StringBuilder output = new StringBuilder();

            for (String obj: shop.cart)
            {
                output.append(obj).append("\n");
            }

            cartOutput.setText(output.toString());

            int tmp_price = Integer.parseInt(price.getText());

            if (type.equals("Добавить")) tmp_price += 2;
            if (type.equals("Удалить")) tmp_price -= 2;

            price.setText(Integer.toString(tmp_price));
        }
    }

    public class WinListener implements WindowListener
    {

        @Override
        public void windowOpened(WindowEvent e) { }

        @Override
        public void windowClosing(WindowEvent event)
        {
            File file = new File("A:\\University\\prog\\labs\\src\\info_6.txt");
            try
            {
                FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                fileOutputStream.write(cartOutput.getText().getBytes());
                fileOutputStream.close();
            }
            catch (FileNotFoundException e) { System.out.println("\nФайл не найден. Запись невозможна."); }
            catch (IOException e) { System.out.println("\nСистемная ошибка записи в файл."); }
        }

        @Override
        public void windowClosed(WindowEvent e) { }

        @Override
        public void windowIconified(WindowEvent e) { }

        @Override
        public void windowDeiconified(WindowEvent e) { }

        @Override
        public void windowActivated(WindowEvent e) { }

        @Override
        public void windowDeactivated(WindowEvent e) { }
    }
}
