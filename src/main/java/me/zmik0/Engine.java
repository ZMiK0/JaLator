package me.zmik0;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private JButton power;
    private JButton sqrt;

    private enum ButtonType {REGULAR, OPERATOR}

    private LinkedHashMap<JButton, ButtonType> buttons;

    private int num1, num2, result;
    private char operation;

    private Color bg;
    private Color a1;
    private Color a2;
    private Color bt;
    private Color fg;

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
     * @param msg window title
     */
    public Engine(String msg) {
        this.frame = new JFrame(msg);
        this.contentPanel = new JPanel();
        this.displayPanel = new JPanel();
        this.buttonPanel = new JPanel();
        this.display = new JTextField(20);
        this.displayText = "";

        this.bg = new Color(29,32,33);
        this.a1 = new Color(184,187,38);
        this.a2 = new Color(250,189,47);
        this.bt = new Color(40,40,40);
        this.fg = new Color(235,219,178);

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

        this.divide = new JButton("/");
        this.multiply = new JButton("x");
        this.substract = new JButton("-");
        this.add = new JButton("+");
        this.equal = new JButton("=");
        this.reset = new JButton("R");

        this.power = new JButton("^");
        this.sqrt = new JButton("√");

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
        buttons.put(this.power, ButtonType.OPERATOR);
        buttons.put(this.sqrt, ButtonType.OPERATOR);
        buttons.put(this.substract, ButtonType.OPERATOR);

        buttons.put(this.reset, ButtonType.OPERATOR);
        buttons.put(this.equal, ButtonType.OPERATOR);

        setSettings();
        addActionEvent(this);
    }

    /**
     * Sets the calculator settings
     */
    private void setSettings() {
        this.contentPanel.setLayout(new GridLayout(0, 1, 2, 2));
        this.contentPanel.setBackground(this.bg);
        this.frame.add(this.contentPanel);
        this.frame.setBackground(this.bg);

        this.displayPanel.setLayout(new FlowLayout());
        this.displayPanel.setBackground(this.bg);
        this.contentPanel.add(this.displayPanel);
        this.displayPanel.add(this.display);
        this.display.setEditable(false);
        this.display.setForeground(this.fg);
        this.display.setBackground(this.bt);
        this.display.setFont(new Font("JetBrainsMono Nerd Font",Font.BOLD,24));

        this.buttonPanel.setLayout(new GridLayout(5, 4, 2, 2));
        this.buttonPanel.setBackground(this.bg);
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
        _button.setForeground(this.fg);
        _button.setBackground(this.bt);
        if (_type.equals(ButtonType.REGULAR)) {
            _button.setBorder(new LineBorder(this.a1));
        } else { _button.setBorder(new LineBorder(this.a2)); }
    }

    /**
     * Waits for a button to be pressed
     * @param engine this engine
     */
    private void addActionEvent(Engine engine) {
        for(JButton but: engine.buttons.keySet()) {
            but.addActionListener(engine);
        }
    }

    /**
     * This method is the Jesus Christ incarnation, regex the display and operates it
     * @return the result operation
     */
    private Integer operation() {
        System.out.println(this.display.getText());
        String str = this.display.getText();

        String regex = "(-?\\d+)([+-/^x])(-?\\d+)";
        String regexSqrt = "(-?√)(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Pattern patternSqrt = Pattern.compile(regexSqrt);
        Matcher matcher = pattern.matcher(str);
        Matcher matcherSqrt = patternSqrt.matcher(str);

        if(matcher.matches()) {
            this.num1 = Integer.parseInt(matcher.group(1));
            this.operation = matcher.group(2).toCharArray()[0];
            this.num2 = Integer.parseInt(matcher.group(3));
        } else if (matcherSqrt.matches()) {
            System.out.println(matcherSqrt.group(1));
            if(matcherSqrt.group(1).toCharArray()[0] == '-') {
                this.operation = matcherSqrt.group(1).toCharArray()[1];
            } else {this.operation = matcherSqrt.group(1).toCharArray()[0];}
            this.num1 = Integer.parseInt(matcherSqrt.group(2));
        } else {
            System.out.println("WRONG");
            return 0;
        }

        System.out.println("N1: " + this.num1 + " " + this.operation + " N2: " + this.num2 );

        switch (this.operation) {
            case '+': return this.num1 + this.num2;
            case '-': return this.num1 - this.num2;
            case 'x': return this.num1 * this.num2;
            case '/': return this.num2 != 0 ? this.num1 / this.num2 : 0;
            case '^': return powerOf(this.num1,this.num2);
            case '√': return matcherSqrt.group(1).toCharArray()[0] == '-' ? -sqrtOf(this.num1) : sqrtOf(this.num1);
            default: return 0;
        }
    }

    /**
     * Power function
     * @param n1
     * @param n2
     * @return the power
     */
    private int powerOf(int n1, int n2) {
        int res = 1;
        for (int i = 0; i < n2; i++ ) {
            res *= n1;
        }
        return res;
    }

    /**
     * Sqrt of a number function
     * @param n
     * @return the square root
     */
    private int sqrtOf(int n) {
        return (int) Math.sqrt(n);
    }
}
