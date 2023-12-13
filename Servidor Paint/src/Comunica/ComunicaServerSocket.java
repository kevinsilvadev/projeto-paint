package Comunica;

import java.io.*;
import java.net.*;
import java.util.concurrent.Semaphore;

public class ComunicaServerSocket {
    private final Socket conexao;
    private final ObjectInputStream receptor;
    private final ObjectOutputStream transmissor;

    private Comunicado proximoComunicado = null;

    private final Semaphore mutEx = new Semaphore(1, true);

    public ComunicaServerSocket(Socket conexao, ObjectInputStream receptor, ObjectOutputStream transmissor)
            throws Exception // se parametro nulos
    {
        if (conexao == null || receptor == null || transmissor == null) {
            throw new Exception("Parâmetros ausentes");
        }

        this.conexao = conexao;
        this.receptor = receptor;
        this.transmissor = transmissor;
    }

    public void receba(Comunicado x) throws Exception {
        try {
            this.transmissor.writeObject(x);
            this.transmissor.flush();
        } catch (IOException erro) {
            throw new Exception("Erro de transmissao", erro);
        }
    }


    public Comunicado envie() throws Exception {
        try {
            if (this.proximoComunicado == null) {
                this.proximoComunicado = (Comunicado) this.receptor.readObject();
            }
            Comunicado ret = this.proximoComunicado;
            this.proximoComunicado = null;
            return ret;
        } catch (Exception erro) {
            throw new Exception("Erro de recepcao", erro);
        }
    }

    public void adeus() throws Exception {
        try {
            this.transmissor.close();
            this.receptor.close();
            this.conexao.close();
        } catch (Exception erro) {
            throw new Exception("Erro de desconexao", erro);
        }
    }
}
