package com.example.tacademy.eattogether.Maps;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2017-07-20.
 */

public class ResCoffeeStoresModel
{
    int code;
    ArrayList<CoffeeStoreModel> body;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ArrayList<CoffeeStoreModel> getBody() {
        return body;
    }

    public void setBody(ArrayList<CoffeeStoreModel> body) {
        this.body = body;
    }
}
