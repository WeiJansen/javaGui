package com.sjw.msg.frame;

import com.sjw.msg.listener.HandleListener;
import com.sjw.msg.model.NetWorkModel;
import com.sjw.msg.ui.SearchButton;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 主窗口
 */
public class MsgFrame extends JFrame implements ActionListener {

    /**
     * 存所有IP
     */
    DefaultListModel<String> ipList;

    public MsgFrame getMainFrame() {
        return this;
    }

    public MsgFrame() {
        this.setTitle("测试");
        // 禁止调整大小, 绝对定位
        this.setResizable(false);
        this.setLayout(null);
        this.setBounds(200, 200, 1000, 800);
        initCompent();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * 初始化各个组件
     */
    private void initCompent() {
        // 添加工具栏按钮
        setToolsPanel();
        // 添加左侧网络列表
        setNetWorkPanel();
    }

    /**
     * 设置工具栏paenl
     */
    private void setToolsPanel() {
        JPanel toolsPanel = new JPanel();
        toolsPanel.setLayout(null);
        toolsPanel.setBounds(0,0, 1000, 30);
        // 设置添加搜索按钮
        SearchButton searchButton = new SearchButton(this);
        searchButton.setBounds(20,5,60, 20);
        toolsPanel.setBorder(new LineBorder(Color.black));
        toolsPanel.add(searchButton);


        this.add(toolsPanel);
    }

    /**
     * 设置网络面板
     */
    private void setNetWorkPanel() {
        JScrollPane netWorkPanel = new JScrollPane();
        netWorkPanel.setBounds(10, 40, 200, 680);
        netWorkPanel.setBorder(new LineBorder(Color.black));
        //设置网络列表
        ipList = new DefaultListModel<>();
        HandleListener.setNetWorkData(ipList);

        JList<String> netWorkModelJList = new JList<>(ipList);
        netWorkPanel.setViewportView(netWorkModelJList);
        this.add(netWorkPanel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand) {
            // 刷新获取所有的ip
            case "gtAll":
                HandleListener.getAllIP(ipList);
                break;
            case "":
                break;


        }
    }
}
