package me.zmik0;

import javax.swing.*;

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
        this.contentPanel = (JPanel) this.frame.getContentPane();
    }

}
