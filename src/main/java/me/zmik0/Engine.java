package me.zmik0;

import javax.swing.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Engine {
    private JFrame frame;
    private JPanel contentPanel;
    private JPanel displayPanel;
    private JPanel buttonPanel;
    private JTextField display;

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

    private int num1, num2, result;
    private char operation;

    public Engine(String msg) {
        this.frame = new JFrame(msg);
        this.contentPanel = new JPanel();
        this.displayPanel = new JPanel();
        this.buttonPanel = new JPanel();
        this.display = new JTextField();

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
        this.multiply = new JButton("*");
        this.substract = new JButton("-");
        this.add = new JButton("+");
        this.equal = new JButton("=");
        this.reset = new JButton("R");

        setSettings(this.frame);
    }

    private void setSettings(JFrame frame) {

        frame.setResizable(false);
        frame.setLocation(50, 100);
        frame.setSize(300, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
}
