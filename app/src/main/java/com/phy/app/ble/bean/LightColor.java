package com.phy.app.ble.bean;

/**
 * LightColor
 *
 * @author:zhoululu
 * @date:2018/4/17
 */

public enum LightColor {

    RED(0),GREE(1),BLUE(2),Color1(40),Color2(41),Color3(42),Color4(43),Color5(44),Color6(45),
    Model1(60),Model2(61),Model3(62),Model4(63),Model5(64),Model6(65),ModelOff(66);

    private int color;
    // 构造方法
    private LightColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }
}
