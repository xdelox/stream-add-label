package com.xdelox;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.GraphDatabase;

public class Launcher {

    public static void main(String[] args) {
        final DBService service = new DBService(GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "password")));
        service.deleteDatabase();
        service.fillDatabase();
        service.addLabelsToNodes();
    }
}