package BancoDeDados;


import Comunica.Desenho;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.sql.*;

public class Formas
{
    public static void incluir(Forma forma) throws Exception {
        if (forma == null)
            throw new Exception("Forma não fornecida");

        try {
            MongoCollection<Document> collection = MongoDB.COMANDO.getCollectionByName("Desenhos");

            Document document = new Document("idDesenho", forma.getIdDesenho())
                    .append("figura", forma.getFigura());

            collection.insertOne(document);
        } catch (Exception e) {
            throw new Exception("Erro ao inserir forma no MongoDB", e);
        }
    }

    public static void excluir(int idDesenho) throws Exception {
        try {
            MongoCollection<Document> collection = MongoDB.COMANDO.getCollectionByName("Desenhos");
            Document query = new Document("idDesenho", idDesenho);
            collection.deleteMany(query);
        } catch (Exception e) {
            throw new Exception("Erro ao excluir forma no MongoDB", e);
        }
    }

    public static void setFormasDesenho(int idDesenho, Desenho d) throws Exception {
        try {
            MongoCollection<Document> collection = MongoDB.COMANDO.getCollectionByName("Desenhos");

            Document query = new Document("idDesenho", idDesenho);
            for (Document document : collection.find(query)) {
                d.addFigura(document.getString("figura"));
            }
        } catch (Exception e) {
            throw new Exception("Erro ao procurar forma no MongoDB", e);
        }
    }

}
