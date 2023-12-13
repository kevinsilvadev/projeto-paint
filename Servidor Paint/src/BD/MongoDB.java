package BD;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDB
{
    public static final MeuMongoPreparedStatement COMANDO;

    static {
        MeuMongoPreparedStatement comando = null;

        try {
            System.out.println("\nConectando ao banco MongoDB...");

            String connectionString = "mongodb+srv://teste:123qwer@catalog.qycziwp.mongodb.net/?retryWrites=true&w=majority";
            ServerApi serverApi = ServerApi.builder()
                    .version(ServerApiVersion.V1)
                    .build();
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(new ConnectionString(connectionString))
                    .serverApi(serverApi)
                    .build();

            // Cria um novo cliente e conecta ao servidor MongoDB
            MongoClient mongoClient = MongoClients.create(settings);
            MongoDatabase database = mongoClient.getDatabase("Paint");

            comando = new MeuMongoPreparedStatement(database);

            System.out.println("Banco de Dados MongoDB Conectado!");
        } catch (MongoException e) {
            System.err.println("Problemas de conexao com o BD MongoDB");
            System.exit(0); // aborta o programa
        }

        COMANDO = comando;
    }
}
