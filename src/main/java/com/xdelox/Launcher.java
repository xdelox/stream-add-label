package com.xdelox;

import com.xdelox.DBService;

public class Launcher {

    private static final DBService service = new DBService();

    public static void main(String[] args) {

        service.deleteDatabase();
        //service.fillDatabase();
        //service.fillDatabase();
        service.fillDatabase();
        service.addLabelsToNodes();
    }


}