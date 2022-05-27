package com.sjw.msg.model;

import java.io.Serializable;

public class NetWorkModel implements Serializable {

    private String ip;

    private String name;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NetWorkModel{" +
                "ip='" + ip + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
