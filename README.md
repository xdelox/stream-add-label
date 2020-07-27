# Neo4j - Add label to a node in a stream

This demo will create 1000 Neo4J nodes of type `MyLabel`.
If already there, the already existing nodes of that type will be deleted. 
Finally, all the nodes will be updated by adding the `NewLabel` label.
## Prerequisite

Having a running instance of Neo4j on your localhost

## How to run

- `mvn clean package`
- `java -jar target/stream-add-label-1.0-SNAPSHOT-jar-with-dependencies.jar`
---

Follow us for futher amazing pushes!