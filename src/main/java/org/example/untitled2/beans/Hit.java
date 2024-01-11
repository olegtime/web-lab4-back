package org.example.untitled2.beans;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Hit implements java.io.Serializable {
    private double x;
    private double y;
    private double r;
    private boolean status;
    private String requestTime;
    private String scriptTime;


    public Hit() {
        this.x = 0;
        this.y = 0;
        this.r = 0;
        this.status = false;
        this.requestTime = "";
        this.scriptTime = "";
    }

    public Hit(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.status = false;
        this.requestTime = "";
        this.scriptTime = "";
    }

    @Override
    public String toString() {
        return "Hit{" + "x=" + x + ", y=" + y + ", r=" + r +
                ", hit=" + status +
                ", requestTime='" + requestTime + '\'' + ", scriptTime='" + scriptTime + '\'' +
                '}';
    }
}
