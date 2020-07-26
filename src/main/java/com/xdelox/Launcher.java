package com.xdelox;

public class Launcher {

    public static void main(String[] args) {
        final DBService service = new DBService();
        service.deleteDatabase();
        //service.fillDatabase();
        service.fillDatabase();
        service.fillDatabase();
        service.addLabelsToNodes();
    }
}