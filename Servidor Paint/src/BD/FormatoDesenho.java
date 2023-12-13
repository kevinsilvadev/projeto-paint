package BD;


import Comunica.Desenho;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class FormatoDesenho
{
    public static void incluir(FormaDesenho formaDesenho) throws Exception {
        if (formaDesenho == null)
            throw new Exception("Forma não fornecida");

        try {
            MongoCollection<Document> collection = MongoDB.COMANDO.getCollectionByName("Desenhos");

            Document document = new Document("idDesenho", formaDesenho.getIdDesenho())
                    .append("figura", formaDesenho.getFigura());

            collection.insertOne(document);
        } catch (Exception e) {
            throw new Exception("Erro ao inserir forma no MongoDB", e);
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
