package BancoDeDados;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MeuMongoPreparedStatement {
    private final MongoDatabase database;

    public MeuMongoPreparedStatement(MongoDatabase database) {
        this.database = database;
    }

    public MongoCollection<Document> getCollectionByName(String collectionName) {
        try {
            return database.getCollection(collectionName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
