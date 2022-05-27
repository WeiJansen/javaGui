package com.sjw.msg.listener;

import com.sjw.msg.MainRun;
import com.sjw.msg.frame.MsgFrame;
import com.sjw.msg.model.NetWorkModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * 搜索按钮事件监听
 */
public class HandleListener {

    /**
     * 获取局域网内所有的IP
     * @return
     */
    public static List<String> getAllIP() {
        return null;
    }

    /**
     * 获取本机IP
     * @return
     */
    public String getLocalHost() {
        return null;
    }


    /**
     * 添加网络信息
     * @param jList
     */
    public static void setNetWorkData(JList<String> jList) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (int i= 0; i < 20; i++) {
            listModel.addElement("测试"+ i);
        }
        jList.setModel(listModel);

    }



}
