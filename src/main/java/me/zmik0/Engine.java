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

    private JPanel topPanel;
    private JPanel toolPanel;
    private JPanel displayPanel;

    private JPanel buttonPanel;
    private JTextField display;
    private String displayText;

    private JButton b2;
    private JButton b8;
    private JButton b10;
    private JButton b16;

    private JButton CASIO;
    private JButton INFO;

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

    private JButton ret;
    private JButton ans;

    private enum ButtonType {REGULAR, OPERATOR, BASE, EXTRA}

    private LinkedHashMap<JButton, ButtonType> buttons;
    private LinkedHashMap<JButton, ButtonType> toolButtons;

    private int num1, num2, result;
    private char operation;

    private int base;

    private Color bg;
    private Color a1;
    private Color a2;
    private Color bt;
    private Color fg;
    private Color off;

    /**
     * Performs the button action
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals("R")) {
            this.displayText="";
            this.base = setBase(e.getActionCommand());
        } else if (e.getActionCommand().equals("=")) {
            this.result = operation();
            this.displayText = Integer.toString(this.result);
        } else if (e.getActionCommand().equals("<-")) {
            this.displayText= displayText.substring(0, this.display.getText().length() - 1) ;
        } else if (e.getActionCommand().equals("ANS")) {
            this.displayText+= this.result;
        } else if(e.getActionCommand().charAt(0) == 'b') {

            if(this.displayText.equals("Select Base")) {
                this.displayText = "";
            }

            this.base = setBase(e.getActionCommand());

        } else {
            if(this.base!=0) {
                this.displayText += e.getActionCommand();
            } else {this.displayText = "Select Base";}
        }

        this.display.setText(this.displayText);
    }


    /**
     * Builds the window and the calculator itself
     * @param msg window title
     */
    public Engine(String msg) {
        this.result = 0;
        this.frame = new JFrame(msg);
        this.contentPanel = new JPanel();

        this.topPanel = new JPanel();
        this.toolPanel = new JPanel();
        this.displayPanel = new JPanel();

        this.buttonPanel = new JPanel();
        this.display = new JTextField(12);
        this.displayText = "";

        this.bg = new Color(29,32,33);
        this.a1 = new Color(184,187,38);
        this.a2 = new Color(250,189,47);
        this.bt = new Color(40,40,40);
        this.fg = new Color(235,219,178);
        this.off = new Color(60, 56, 54);

        this.b2 = new JButton("b2");
        this.b8 = new JButton("b8");
        this.b10 = new JButton("b10");
        this.b16 = new JButton("b16");

        this.CASIO = new JButton("CASIO");
        this.INFO = new JButton("INFO");

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
        this.ret = new JButton("<-");
        this.ans = new JButton("ANS");

        this.power = new JButton("^");
        this.sqrt = new JButton("√");

        this.reset = new JButton("R");
        this.equal = new JButton("=");

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

        buttons.put(this.ret, ButtonType.OPERATOR);
        buttons.put(this.ans, ButtonType.OPERATOR);

        buttons.put(this.reset, ButtonType.OPERATOR);
        buttons.put(this.equal, ButtonType.OPERATOR);

        this.toolButtons = new LinkedHashMap<>();
        toolButtons.put(this.b2, ButtonType.BASE);
        toolButtons.put(this.b8, ButtonType.BASE);
        toolButtons.put(this.b10, ButtonType.BASE);
        toolButtons.put(this.b16, ButtonType.BASE);

        toolButtons.put(this.INFO, ButtonType.EXTRA);
        toolButtons.put(this.CASIO, ButtonType.EXTRA);

        this.base = 0;

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

        this.topPanel.setLayout(new FlowLayout()); //sadjhvgajhgdfasghjdfgjhafdvghj
        this.topPanel.setBackground(this.bg);
        this.contentPanel.add(this.topPanel);

        this.toolPanel.setLayout(new GridLayout(1,6,4,0));
        this.toolPanel.setBackground(this.bg);
        this.topPanel.add(this.toolPanel);

        this.displayPanel.setLayout(new FlowLayout());
        this.displayPanel.setBackground(this.bg);
        this.topPanel.add(this.displayPanel);
        this.displayPanel.add(this.display);
        this.display.setEditable(false);
        this.display.setForeground(this.fg);
        this.display.setBackground(this.bt);
        this.display.setFont(new Font("JetBrainsMono Nerd Font",Font.BOLD,24));

        this.buttonPanel.setLayout(new GridLayout(5, 4, 2, 2));
        this.buttonPanel.setBackground(this.bg);
        this.contentPanel.add(this.buttonPanel);

        for(JButton but: toolButtons.keySet()) {
            this.toolPanel.add(but);
            setFeaturesButton(but, toolButtons.get(but));
        }

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
        } else if (_type.equals(ButtonType.BASE)) {
            _button.setBorder(new LineBorder(this.off));
        } else if (_type.equals(ButtonType.EXTRA)) {
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
        for(JButton but: engine.toolButtons.keySet()) {
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
        Pattern patternSqrt = Pattern.compile(regexSqrt);<
        Matcher matcher = pattern.matcher(str);
        Matcher matcherSqrt = patternSqrt.matcher(str);

        if(matcher.matches()) {
            this.num1 = Integer.parseInt(matcher.group(1));
            this.operation = matcher.group(2).toCharArray()[0];
            this.num2 = Integer.parseInt(matcher.group(3));
        } else if (matcherSqrt.matches()) {
            if(matcherSqrt.group(1).toCharArray()[0] == '-') {
                this.operation = matcherSqrt.group(1).toCharArray()[1];
            } else {this.operation = matcherSqrt.group(1).toCharArray()[0];}
            this.num1 = Integer.parseInt(matcherSqrt.group(2));
        } else {
            return 0;
        }


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

    private int setBase(String buttonType) {

        for(JButton but: toolButtons.keySet()) {
            but.setBorder(new LineBorder(this.off));
        }

        switch (buttonType) {
            case "b2":
                this.b2.setBorder(new LineBorder(this.a1));
                return 2;
            case "b8":
                this.b8.setBorder(new LineBorder(this.a1));
                return 8;
            case "b10":
                this.b10.setBorder(new LineBorder(this.a1));
                return 10;
            case "b16":
                this.b16.setBorder(new LineBorder(this.a1));
                return 16;
            default:
                return 0;
        }
    }
}
