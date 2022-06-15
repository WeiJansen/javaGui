package com.sjw.msg.listener;

import com.sjw.msg.MainRun;
import com.sjw.msg.frame.MsgFrame;
import com.sjw.msg.model.NetWorkModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * 搜索按钮事件监听
 */
public class HandleListener {

    private static Boolean pinFlag = false;
    private static Boolean pinFlagBig = false;
    private static Boolean pinFlagSmall = false;
    private static Thread getIpThreadBig;
    private static Thread getIpThreadSmall;

    /**
     * 获取局域网内所有的IP
     * @return
     */
    public static void getAllIP(DefaultListModel listModel) {
        // 重新查找
        pinFlag = true;
        if(getIpThreadBig.isAlive()) {
            pinFlagBig = true;
        } else {
            getIpThreadBig.start();
        }

        if(getIpThreadSmall.isAlive()) {
            pinFlagSmall = true;
        } else {
            getIpThreadSmall.start();
        }


    }

    /**
     * 获取本机IP
     * @return
     */
    public static String getLocalHost() {

        String ipv4 = "127.0.0.1";
        List<Inet4Address> addresses = new ArrayList<>(1);
        // 所有网络接口信息
        Enumeration<NetworkInterface> networkInterfaces = null;
        try {
            networkInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            e.printStackTrace();
        }

        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            //滤回环网卡、点对点网卡、非活动网卡、虚拟网卡并要求网卡名字是eth或ens开头
            if(!"wlan0".equalsIgnoreCase(networkInterface.getName())) {
                continue;
            }
            // 所有网络接口的IP地址信息
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                InetAddress inetAddress = inetAddresses.nextElement();
                // 判断是否是IPv4，并且内网地址并过滤回环地址.
                String hostAddress = inetAddress.getHostAddress();
                if(hostAddress.contains(".")) {
                     ipv4 = hostAddress;
                }

            }
        }
        System.out.println("本机IP:" + ipv4);
        return ipv4;
    }


    /**
     * 添加网络信息
     * @param listModel
     */
    public static void setNetWorkData(DefaultListModel listModel) {
        listModel.clear();
        // 获取本机IP
        String localHost = getLocalHost();
        // 截取本机IP
        String ipPre = localHost.substring(0, localHost.lastIndexOf(".")) + ".";
        String currentIp = localHost.substring(localHost.lastIndexOf(".") + 1);
        int currentIpNum = Integer.parseInt(currentIp);

        // 找比当前IP大的
        getIpThreadBig = new Thread(() -> {
            for (int i = currentIpNum + 1; i <= 255; i++) {
                // 重新获取
                if(pinFlagBig) {
                    i = currentIpNum + 1;
                    listModel.clear();
                    pinFlagBig = false;
                }
                // 开始Ping 远程所有地址
                String remoteIp = ipPre + i;

                /**  **/
                Process p= null;
                try {
                    p = Runtime.getRuntime().exec ("ping "+remoteIp+ " -w 300 -n 1");
                    InputStreamReader ir = new InputStreamReader(p.getInputStream());
                    LineNumberReader input = new LineNumberReader (ir);
                    for (int j=1 ; j <7; j++) {
                        input.readLine();
                    }
                    String line= input.readLine();
                    System.out.println("线程1-PING: "+ remoteIp + "===" + line);
                    if(null == line) {
                        continue;
                    }
                    if (line.length() <17 || line.substring(8,17).equals("timed out")) {
                        continue;
                    } else {
                        listModel.addElement(remoteIp);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        // 向小的部分查找
        getIpThreadSmall = new Thread(() -> {
            System.out.println("线程2=====");
            for (int i = currentIpNum - 1; i > 0; i--) {
                // 重新获取
                if(pinFlagSmall) {
                    i = currentIpNum;
                    listModel.clear();
                    pinFlagSmall = false;
                }
                // 开始Ping 远程所有地址
                String remoteIp = ipPre + i;
                Process p= null;
                try {
                    p = Runtime.getRuntime().exec ("ping "+remoteIp+ " -w 300 -n 1");
                    InputStreamReader ir = new InputStreamReader(p.getInputStream());
                    LineNumberReader input = new LineNumberReader (ir);
                    for (int j=1 ; j <7; j++) {
                        input.readLine();
                    }
                    String line= input.readLine();
                    System.out.println("线程2-PING: "+ remoteIp + "===" + line);
                    if(null == line) {
                        continue;
                    }
                    if (line.length() <17 || line.substring(8,17).equals("timed out")) {
                        continue;
                    } else {
                        listModel.addElement(remoteIp);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        getIpThreadBig.start();
        getIpThreadSmall.start();

    }

    /**
     * 测试IP 是否能连接
     * @param ip
     * @return
     */
    private static synchronized boolean pinIp(String ip) {
        System.out.println("PING: "+ ip);
        Process p= null;
        try {
            p = Runtime.getRuntime().exec ("ping "+ip+ " -w 300 -n 1");
            InputStreamReader ir = new InputStreamReader(p.getInputStream());
            LineNumberReader input = new LineNumberReader (ir);
            for (int i=1 ; i <7; i++) {
                input.readLine();
            }
            String line= input.readLine();

            //System.out.println("PING: "+ ip + "===" + line);

            if(null == line) {
                return false;
            }

            if (line.length() <17 || line.substring(8,17).equals("timed out")) {
                return true;
            } else {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


}
