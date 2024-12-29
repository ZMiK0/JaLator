package me.zmik0;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Engine implements ActionListener {
    private JFrame frame;
    private JPanel contentPanel;
    private JPanel displayPanel;
    private JPanel buttonPanel;
    private JTextField display;
    private String displayText;

    private JButton n0;
    private JButton n1;
    private JButton n2;
    private JButton n3;
    private JButton n4;
    private JButton n5;
    private JButton n6;
    private JButton n7;
    private JButton n8;
    private JButton n9;

    private JButton divide;
    private JButton multiply;
    private JButton substract;
    private JButton add;
    private JButton equal;
    private JButton reset;

    private enum ButtonType {REGULAR, OPERATOR}

    private LinkedHashMap<JButton, ButtonType> buttons;

    private int num1, num2, result;
    private char operation;

    /**
     * Performs the button action
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals("R")) {
            this.displayText="";
        } else if (e.getActionCommand().equals("=")) {
            this.displayText=operation().toString();
        } else {this.displayText+=e.getActionCommand();}

        this.display.setText(this.displayText);
    }


    /**
     * Builds the window and the calculator itself
     * @param msg
     */
    public Engine(String msg) {
        this.frame = new JFrame(msg);
        this.contentPanel = new JPanel();
        this.displayPanel = new JPanel();
        this.buttonPanel = new JPanel();
        this.display = new JTextField(20);
        this.displayText = "";

        this.n0 = new JButton("0");
        this.n1 = new JButton("1");
        this.n2 = new JButton("2");
        this.n3 = new JButton("3");
        this.n4 = new JButton("4");
        this.n5 = new JButton("5");
        this.n6 = new JButton("6");
        this.n7 = new JButton("7");
        this.n8 = new JButton("8");
        this.n9 = new JButton("9");

        this.divide = new JButton("%");
        this.multiply = new JButton("x");
        this.substract = new JButton("-");
        this.add = new JButton("+");
        this.equal = new JButton("=");
        this.reset = new JButton("R");

        this.buttons = new LinkedHashMap<>();

        buttons.put(this.n7, ButtonType.REGULAR);
        buttons.put(this.n8, ButtonType.REGULAR);
        buttons.put(this.n9, ButtonType.REGULAR);
        buttons.put(this.divide, ButtonType.OPERATOR);

        buttons.put(this.n4, ButtonType.REGULAR);
        buttons.put(this.n5, ButtonType.REGULAR);
        buttons.put(this.n6, ButtonType.REGULAR);
        buttons.put(this.multiply, ButtonType.OPERATOR);

        buttons.put(this.n1, ButtonType.REGULAR);
        buttons.put(this.n2, ButtonType.REGULAR);
        buttons.put(this.n3, ButtonType.REGULAR);
        buttons.put(this.add, ButtonType.OPERATOR);

        buttons.put(this.n0, ButtonType.REGULAR);
        buttons.put(this.reset, ButtonType.OPERATOR);
        buttons.put(this.substract, ButtonType.OPERATOR);
        buttons.put(this.equal, ButtonType.OPERATOR);

        setSettings();
        addActionEvent(this);
    }

    /**
     * Sets the calculator settings
     */
    private void setSettings() {
        this.contentPanel.setLayout(new GridLayout(0, 1, 2, 2));
        this.frame.add(this.contentPanel);

        this.displayPanel.setLayout(new FlowLayout());
        this.contentPanel.add(this.displayPanel);
        this.displayPanel.add(this.display);
        this.display.setEditable(false);

        this.buttonPanel.setLayout(new GridLayout(4, 4, 2, 2));
        this.contentPanel.add(this.buttonPanel);

        for(JButton but : buttons.keySet()) {
            this.buttonPanel.add(but);
            setFeaturesButton(but, buttons.get(but));
        }

        this.frame.setResizable(false);
        this.frame.setLocation(50, 100);
        this.frame.setSize(300, 400);
        this.frame.setVisible(true);
        this.frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    /**
     * Modifies the button color depending on the button type
     * @param _button
     * @param _type
     */
    private void setFeaturesButton(JButton _button, ButtonType _type) {
        if (_type.equals(ButtonType.REGULAR)) {
            _button.setForeground(Color.CYAN);
        } else { _button.setForeground(Color.GREEN); }
    }

    /**
     * Waits for a button to be pressed
     * @param engine
     */
    private void addActionEvent(Engine engine) {
        for(JButton but: engine.buttons.keySet()) {
            but.addActionListener(engine);
        }
    }

    private Integer operation() {
        System.out.println(this.display.getText());
        String str = this.display.getText();
        return 1;
    }
}
