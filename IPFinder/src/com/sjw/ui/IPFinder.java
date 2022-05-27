package com.sjw.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPFinder extends JFrame implements ActionListener {

    JLabel jLable;
    JTextField jTextField;

    public IPFinder() {
        this.setTitle("IPFinder");
        this.setBounds(200,200, 300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init(this);
        this.setVisible(true);
    }

    /**
     * 初始化
     * @param jFrame
     */
    private void init(JFrame jFrame) {
        jFrame.setLayout(null);
        jLable = new JLabel("输入地址: ");
        jLable.setBounds(50, 80, 150, 20);
        jTextField = new JTextField();
        jTextField.setBounds(50, 100, 200, 20);
        jFrame.add(jLable);
        jFrame.add(jTextField);
        JButton btn = new JButton("测试");
        btn.setBounds(75, 130, 100, 20);
        btn.addActionListener(this);
        jFrame.add(btn);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String url = jTextField.getText();
        System.out.println(url);
        try {
            InetAddress netInfo = InetAddress.getByName(url);
            String ip = netInfo.getHostAddress();
            JOptionPane.showMessageDialog(this, ip);

        } catch (UnknownHostException unknownHostException) {
            unknownHostException.printStackTrace();
        }


    }
}
