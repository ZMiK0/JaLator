package me.zmik0;

import javax.swing.*;
import java.awt.*;

public class PopUp {
    private JFrame frame;
    private JPanel panel;
    private JTextArea text;
    private Color bg;
    private Color fg;

    public PopUp(String msg, String _message, int _w, int _h) {

        this.bg = new Color(29, 32, 33);
        this.fg = new Color(235, 219, 178);

        this.frame = new JFrame(msg);
        this.frame.setBackground(this.bg);
        this.panel = new JPanel();
        this.panel.setBackground(this.bg);
        this.text = new JTextArea(_message);
        this.text.setBackground(this.bg);
        this.text.setForeground(this.fg);
        this.text.setFont(new Font("JetBrainsMono Nerd Font", Font.BOLD, 16));
        this.text.setEditable(false);

        this.panel.setLayout(new FlowLayout());
        this.frame.add(this.panel);
        this.panel.add(this.text);

        this.frame.setResizable(false);
        this.frame.setLocation(0, 0);
        this.frame.setSize(_w, _h);
        this.frame.setVisible(true);
        this.frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }
}
