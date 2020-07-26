package com.xdelox;

import org.neo4j.driver.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DBService {
    final Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "password"));
    final ExecutorService executor = Executors.newSingleThreadExecutor();

    public void addLabelsToNodes() {
        try (Session session = driver.session()) {
            session.readTransaction(tx -> {
                Result result = tx.run("MATCH (a:MyLabel) RETURN ID(a) as identify ORDER BY ID(a)");
                result
                        .forEachRemaining((e) ->
                                executor.submit(() ->
                                        updateRecordById(e.get(0).asInt())
                                ));
                return result;
            });

        }
        return;
    }

    public void fillDatabase(){
        executeWriteQuery("UNWIND RANGE(1,1000) AS a CREATE(n:MyLabel {value:a}) return n");
    }

    public void deleteDatabase() {
        executeWriteQuery("MATCH (n:MyLabel) DETACH DELETE n");
        executeWriteQuery("MATCH (n:NewLabel) DETACH DELETE n");
    }

    private void executeWriteQuery(String query){
        executeWriteQuery(query, Map.of());
    }

    private void updateRecordById(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        String query = "MATCH (n) where ID(n)=$id set n :NewLabel return n";
        System.out.println("Updating node with id: " + params.get("id"));
        executeWriteQuery(query, params);
    }

    private void executeWriteQuery(String query, Map<String, Object> params){
        try (Session session = driver.session()) {
            session.writeTransaction(tx -> tx.run(query, params));
        }
    }
}
