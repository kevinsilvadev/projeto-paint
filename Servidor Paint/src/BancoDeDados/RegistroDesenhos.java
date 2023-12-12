package BancoDeDados;

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
            MongoCollection<Document> collection = MongoDB.COMANDO.getCollectionByName("Desenhos");

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
            MongoCollection<Document> collection = MongoDB.COMANDO.getCollectionByName("Desenhos");
            long count = collection.countDocuments();
            return (int) count + 1; // Incrementando para obter um valor equivalente ao próximo ID
        } catch (Exception e) {
            throw new Exception("Erro ao procurar ID no MongoDB", e);
        }
    }

    public static int getIdExistente(String nomeDesenho, String idCliente) throws Exception {
        int id = 0;

        try {
            MongoCollection<Document> collection = MongoDB.COMANDO.getCollectionByName("Desenhos");

            BasicDBObject query = new BasicDBObject();
            query.put("nomeDesenho", nomeDesenho);
            query.put("idCliente", idCliente);

            Document document = collection.find(query).first();
            if (document != null) {
                id = document.getInteger("idClient");
                System.out.println(id);
            }
        } catch (Exception e) {
            throw new Exception("Erro ao procurar id no MongoDB", e);
        }

        return id;
    }

    public static Desenho getRegistroDesenho(String idDesenho) throws Exception {
        try {
            MongoCollection<Document> collection = MongoDB.COMANDO.getCollectionByName("Desenhos");
            Document query = new Document("_id", idDesenho);
            Document result = collection.find(query).first();

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

    public static void alterar(String dataModificacao, int idDesenho) throws Exception {
        try {
            MongoCollection<Document> collection = MongoDB.COMANDO.getCollectionByName("Desenhos");

            BasicDBObject query = new BasicDBObject("_id", idDesenho);

            BasicDBObject update = new BasicDBObject();
            update.put("$set", new BasicDBObject("dataModificacao", dataModificacao));

            collection.updateOne(query, update);
        } catch (Exception e) {
            throw new Exception("Erro ao atualizar dados do desenho no MongoDB", e);
        }
    }

    public static boolean verificarNome(String nomeDesenho, String idCliente) throws Exception {
        try {
            MongoCollection<Document> collection = MongoDB.COMANDO.getCollectionByName("Desenhos");

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