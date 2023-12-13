package Comunica;

import java.net.*;
import java.util.ArrayList;

public class AceitaSocket extends Thread {
    private final ServerSocket pedido;
    private final ArrayList<ComunicaServerSocket> usuarios;

    public AceitaSocket(String porta, ArrayList<ComunicaServerSocket> usuarios) throws Exception {
        if (porta == null) {
            throw new Exception("Porta ausente");
        }

        try {
            this.pedido = new ServerSocket(Integer.parseInt(porta));
        } catch (Exception erro) {
            throw new Exception("Porta invalida", erro);
        }

        if (usuarios == null) {
            throw new Exception("Usuarios ausentes");
        }

        this.usuarios = usuarios;
    }

    public void run() {
        for (;;) {
            try {
                Socket conexao = this.pedido.accept();
                Observador observador = criarObservador(conexao);
                observador.start();
            } catch (Exception erro) {
                erro.printStackTrace();
            }
        }
    }

    private Observador criarObservador(Socket conexao) {
        Observador observador = null;
        try {
            observador = new Observador(conexao, usuarios);
        } catch (Exception erro) {
            erro.printStackTrace();
        }
        return observador;
    }
}
