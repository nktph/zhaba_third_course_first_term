package lab_6.part_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame
{
    public TextField param_1, param_2, result;
    private JRadioButton addition, subtraction, multiplication, division;
    private JButton calculate;
    private ButtonGroup operations;
    private Color scheme;

    public Calculator()
    {
        super("Никому не нужный калькулятор");
        scheme = new Color(193, 232, 168);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        getContentPane().setBackground(scheme);
        setLayout(null);

        param_1 = new TextField();
        param_1.setBounds(25, 125, 170, 50);
        add(param_1);

        param_2 = new TextField();
        param_2.setBounds(285, 125, 170, 50);
        add(param_2);

        result = new TextField();
        result.setBounds(165, 325, 170, 50);
        result.setEditable(false);
        add(result);

        addition = new JRadioButton("Сложение");
        addition.setBounds(50, 215, 90, 20); // сдвинуть всё правее
        addition.setBackground(scheme);
        addition.setActionCommand("Сложение");
        add(addition);

        subtraction = new JRadioButton("Вычитание");
        subtraction.setBounds(145, 215, 90, 20);
        subtraction.setBackground(scheme);
        subtraction.setActionCommand("Вычитание");
        add(subtraction);

        multiplication = new JRadioButton("Умножение");
        multiplication.setBounds(250, 215, 100, 20);
        multiplication.setBackground(scheme);
        multiplication.setActionCommand("Умножение");
        add(multiplication);

        division = new JRadioButton("Деление");
        division.setBounds(350, 215, 90, 20);
        division.setBackground(scheme);
        division.setActionCommand("Деление");
        add(division);

        operations = new ButtonGroup();
        operations.add(addition); operations.add(subtraction); operations.add(multiplication); operations.add(division);

        calculate = new JButton("Расчитать");
        calculate.setBounds(175, 260, 150, 30);
        calculate.addActionListener(new ButtonListener());

        add(calculate);
    }

    public class ButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            double value_1, value_2;
            try
            {
                value_1 = Double.parseDouble(param_1.getText());
                value_2 = Double.parseDouble(param_2.getText());
            }
            catch (Exception e) { result.setText("Расчёт невозможен"); return; }

            double value_result = 0;

            String operationType;
            try
            {
                operationType = operations.getSelection().getActionCommand();
            }
            catch (NullPointerException e) { result.setText("Расчёт невозможен"); return; }

            if (operationType.equals("Сложение")) value_result = value_1 + value_2;
            if (operationType.equals("Вычитание")) value_result = value_1 - value_2;
            if (operationType.equals("Умножение")) value_result = value_1 * value_2;
            if (operationType.equals("Деление")) value_result = value_1 / value_2;

            result.setText(Double.toString(value_result));
        }
    }
}
