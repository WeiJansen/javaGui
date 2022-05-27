package com.sjw.msg.ui;

import com.sjw.msg.listener.HandleListener;

import javax.swing.*;
import java.awt.event.ActionListener;

public class SearchButton extends JButton {

    public SearchButton(ActionListener actionListener) {
        this.setText("搜索");
        this.setActionCommand("gtAll");
        this.addActionListener(actionListener);
    }



}
