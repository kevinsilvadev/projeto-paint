package Comunica;

import BD.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Objects;

public class Observador extends Thread {
    private final ComunicaServerSocket usuario;
    private final Socket conexao;
    private final ArrayList<ComunicaServerSocket> usuarios;

    public Observador(Socket conexao, ArrayList<ComunicaServerSocket> usuarios) throws Exception {
        if (conexao == null) {
            throw new Exception("Conexao ausente");
        }

        if (usuarios == null) {
            throw new Exception("Usuarios ausentes");
        }

        this.conexao = conexao;
        this.usuarios = usuarios;
        this.usuario = criarParceiro();
    }

    private ComunicaServerSocket criarParceiro() throws Exception {
        ObjectOutputStream transmissor = new ObjectOutputStream(this.conexao.getOutputStream());
        ObjectInputStream receptor = new ObjectInputStream(this.conexao.getInputStream());

        return new ComunicaServerSocket(this.conexao, receptor, transmissor);
    }

    @Override
    public void run() {
        try {
            synchronized (this.usuarios) {
                this.usuarios.add(this.usuario);
            }

            while (true) {
                Comunicado comunicado = this.usuario.envie();

                if (comunicado == null) {
                    break;
                }

                if (comunicado instanceof PedidoDeSalvamento) {
                    processarPedidoDeSalvamento((PedidoDeSalvamento) comunicado);
                } else if (comunicado instanceof PedidoDeAbertura) {
                    processarPedidoDeAbertura((PedidoDeAbertura) comunicado);
                }
            }
        } catch (Exception erro) {
            encerrarConexao();
        }
    }

    private void processarPedidoDeSalvamento(PedidoDeSalvamento pedido) {
        try {
            boolean verificaDesenho = RegistroDesenhos.verificarNome(pedido.getNomeDesenho(), pedido.getIdCliente());

            if (!verificaDesenho) {
                incluirRegistroDesenhoEFormas(pedido);
            } else {
                String id = RegistroDesenhos.getIdExistente(pedido.getNomeDesenho(), pedido.getIdCliente());
                System.out.println(id);

                // Implemente a lógica para alterar o desenho e as formas
                // RegistroDesenhos.alterar(pedido.getDataModificacao(), id);
                // Formas.excluir(id);
                // for (int i = 0; i < pedido.getQtdFiguras(); i++)
                //     Formas.incluir(new Forma(id, pedido.getFigura(i)));

                System.out.println("Linhas de Formas alteradas!");
            }
        } catch (Exception erro) {
            erro.printStackTrace();
            System.out.println(erro.getMessage());
        } finally {
            encerrarConexao();
        }
    }

    private void incluirRegistroDesenhoEFormas(PedidoDeSalvamento pedido) {
        try {
            RegistroDesenhos.incluir(new RegistroDesenho(pedido.getNomeDesenho(), pedido.getIdCliente(),
                    pedido.getDataCriacao(), pedido.getDataModificacao()));

            System.out.println("Linha de RegistroDesenho inserida!");

            int idAtual = RegistroDesenhos.idAtual();

            for (int i = 0; i < pedido.getQtdFiguras(); i++)
                FormatoDesenho.incluir(new FormaDesenho(idAtual, pedido.getFigura(i)));

            System.out.println("Linhas de Formas inseridas!");
        } catch (Exception erro) {
            erro.printStackTrace();
            System.out.println(erro.getMessage());
        }
    }

    private void processarPedidoDeAbertura(PedidoDeAbertura pedido) {
        try {
            boolean verificaDesenho = RegistroDesenhos.verificarNome(pedido.getNomeDesenho(), pedido.getIdCliente());

            if (verificaDesenho) {
                String id = RegistroDesenhos.getIdExistente(pedido.getNomeDesenho(), pedido.getIdCliente());
                System.out.println(id);

                Desenho registroDesenho = RegistroDesenhos.getRegistroDesenho(id);

                System.out.println(registroDesenho);

                Desenho d = new Desenho(registroDesenho.getNomeDesenho(), registroDesenho.getIdCliente(),
                        registroDesenho.getDataCriacao(), registroDesenho.getDataModificacao());

                System.out.println(d);

                FormatoDesenho.setFormasDesenho(4, d);

                this.usuario.receba(d);
                this.usuario.adeus();

                System.out.println("Enviado!");
            } else {
                System.out.println("\nEsse desenho não existe!");

                this.usuario.receba(pedido);
                this.usuario.adeus();
            }
        } catch (Exception erro) {
            erro.printStackTrace();
            System.out.println(erro.getMessage());
        } finally {
            encerrarConexao();
        }
    }

    private void encerrarConexao() {
        try {
            this.usuario.adeus();
        } catch (Exception erro) {
            erro.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Observador{" + "usuario=" + usuario + ", conexao=" + conexao + ", usuarios=" + usuarios + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.usuario);
        hash = 23 * hash + Objects.hashCode(this.conexao);
        hash = 23 * hash + Objects.hashCode(this.usuarios);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Observador other = (Observador) obj;
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        if (!Objects.equals(this.conexao, other.conexao)) {
            return false;
        }
        return Objects.equals(this.usuarios, other.usuarios);
    }
}
