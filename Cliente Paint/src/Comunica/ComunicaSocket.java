package Comunica;

import java.io.*;
import java.net.*;
import java.util.concurrent.Semaphore;

public class ComunicaSocket {
    private final Socket conexao;
    private final ObjectInputStream receptor;
    private final ObjectOutputStream transmissor;
    private final Semaphore mutEx = new Semaphore(1, true);

    private Comunicado proximoComunicado = null;

    public ComunicaSocket(Socket conexao, ObjectInputStream receptor, ObjectOutputStream transmissor)
            throws Exception // se parametro nulos
    {
        if (conexao == null || receptor == null || transmissor == null) {
            throw new Exception("Parâmetros ausentes");
        }

        this.conexao = conexao;
        this.receptor = receptor;
        this.transmissor = transmissor;
    }

    public void recebaProximoComunicado(Comunicado x) throws Exception {
        try {
            this.transmissor.writeObject(x);
            this.transmissor.flush();
        } catch (IOException erro) {
            throw new Exception("Erro de transmissao", erro);
        }
    }


    public Comunicado envieComunicadoSocket() throws Exception {
        if (this.receptor == null) {
            throw new Exception("Receptor não inicializado. Verifique se foi corretamente configurado.");
        }

        try {
            System.out.println("Antes da leitura do objeto");
            this.proximoComunicado = (Comunicado) this.receptor.readObject();
            System.out.println("Depois da leitura do objeto");

            Comunicado ret = this.proximoComunicado;
            this.proximoComunicado = null;
            return ret;
        } catch (EOFException eofException) {
            // Tratar o fim do arquivo, retornar null ou outra ação apropriada
            return null;
        } catch (IOException | ClassNotFoundException erro) {
            erro.printStackTrace();
            throw new Exception("Erro de recepcao: " + erro.getMessage(), erro);
        }
    }

    public void encerrarConexao() throws Exception {
        try {
            this.transmissor.close();
            this.receptor.close();
            this.conexao.close();
        } catch (Exception erro) {
            throw new Exception("Erro de desconexao", erro);
        }
    }
}
