package com.example.tacademy.eattogether.Maps;

import java.io.Serializable;

/**
 * Created by Tacademy on 2017-07-20.
 */

public class CoffeeStoreModel implements Serializable{
    String NM;      //` VARCHAR(128) NULL DEFAULT NULL COMMENT '점포명',
    String ADDRESS; //` VARCHAR(512) NULL DEFAULT NULL COMMENT '정포주소',
    int X_AXIS;     //` INT(10) UNSIGNED NOT NULL COMMENT 'x',
    int Y_AXIS;     //` INT(10) UNSIGNED NOT NULL COMMENT 'y',
    String type;    //` VARCHAR(10) NULL DEFAULT NULL COMMENT '커피브랜드'

    // 거리 추가
    double dist;

    public double getDist() {
        return dist;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }

    // 필요에 의해 컬럼을 추가하였다.
    // 서버에서 받은 데이터로 세팅되는것이 아니라, 작위적으로 후에 세팅된다
    int index;


    @Override
    public String toString() {
        return "CoffeeStoreModel{" +
                "NM='" + NM + '\'' +
                ", ADDRESS='" + ADDRESS + '\'' +
                ", X_AXIS=" + X_AXIS +
                ", Y_AXIS=" + Y_AXIS +
                ", type='" + type + '\'' +
                ", dist=" + dist +
                ", index=" + index +
                '}';
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getNM() {
        return NM;
    }

    public void setNM(String NM) {
        this.NM = NM;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public int getX_AXIS() {
        return X_AXIS;
    }

    public void setX_AXIS(int x_AXIS) {
        X_AXIS = x_AXIS;
    }

    public int getY_AXIS() {
        return Y_AXIS;
    }

    public void setY_AXIS(int y_AXIS) {
        Y_AXIS = y_AXIS;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}