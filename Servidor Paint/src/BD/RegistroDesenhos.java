package BD;

import Comunica.Desenho;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class RegistroDesenhos
{
    public static void incluir(RegistroDesenho registroDesenho) throws Exception {
        if (registroDesenho == null) {
            throw new Exception("Registro de Desenho não fornecido");
        }
        try {
            MongoCollection<Document> collection = MongoDB.COMANDO.getCollectionByName("RegistroDesenho");

            Document document = new Document()
                    .append("nomeDesenho", registroDesenho.getNomeDesenho())
                    .append("idCliente", registroDesenho.getIdCliente())
                    .append("dataCriacao", registroDesenho.getDataCriacao())
                    .append("dataModificacao", registroDesenho.getDataModificacao());

            collection.insertOne(document);
        } catch (Exception e) {
            throw new Exception("Erro ao inserir desenho no MongoDB", e);
        }
    }

    public static int idAtual() throws Exception {
        try {
            MongoCollection<Document> collection = MongoDB.COMANDO.getCollectionByName("RegistroDesenho");
            long count = collection.countDocuments();
            return (int) count + 1; // Incrementando para obter um valor equivalente ao próximo ID
        } catch (Exception e) {
            throw new Exception("Erro ao procurar ID no MongoDB", e);
        }
    }

    public static String getIdExistente(String nomeDesenho, String idCliente) throws Exception {
        String id = "";

        try {
            MongoCollection<Document> collection = MongoDB.COMANDO.getCollectionByName("RegistroDesenho");

            BasicDBObject query = new BasicDBObject();
            query.put("nomeDesenho", nomeDesenho);
            query.put("idCliente", idCliente);

            Document document = collection.find(query).first();

            document.getString("idCliente");

            if (document != null) {
                System.out.println("teste");
                id = document.getString("idCliente");
                System.out.println(id);
            }
        } catch (Exception e) {
            throw new Exception("Erro ao procurar id no MongoDB", e);
        }

        return id;
    }

    public static Desenho getRegistroDesenho(String idCliente) throws Exception {
        try {
            MongoCollection<Document> collection = MongoDB.COMANDO.getCollectionByName("RegistroDesenho");
            Document query = new Document("idCliente", idCliente);
            Document result = collection.find(query).first();

            System.out.println(result);

            System.out.println("Passei pelo metodo");

            if (result == null) {
                throw new Exception("Registro de desenho não encontrado");
            }

            return new Desenho(
                    result.getString("nomeDesenho"),
                    result.getString("idCliente"),
                    result.getString("dataCriacao"),
                    result.getString("dataModificacao")
            );
        } catch (Exception e) {
            throw new Exception("Erro ao procurar registro de desenho", e);
        }
    }



    public static boolean verificarNome(String nomeDesenho, String idCliente) throws Exception {
        try {
            MongoCollection<Document> collection = MongoDB.COMANDO.getCollectionByName("RegistroDesenho");

            // Crie um filtro combinando nomeDesenho e idCliente
            BasicDBObject query = new BasicDBObject();
            query.put("nomeDesenho", nomeDesenho);
            query.put("idCliente", idCliente);

            Document document = collection.find(query).first();
            return document != null; // Retorna true se o documento existe (nome já em uso para o cliente), false caso contrário
        } catch (Exception e) {
            throw new Exception("Erro ao verificar nome no MongoDB", e);
        }
    }


}