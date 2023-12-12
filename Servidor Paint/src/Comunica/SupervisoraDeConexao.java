package Comunica;

import BancoDeDados.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class SupervisoraDeConexao extends Thread
{
    private Parceiro            usuario;
    private Socket              conexao;
    private ArrayList<Parceiro> usuarios;

    public SupervisoraDeConexao
    (Socket conexao, ArrayList<Parceiro> usuarios)
    throws Exception
    {
        if (conexao==null)
            throw new Exception ("Conexao ausente");

        if (usuarios==null)
            throw new Exception ("Usuarios ausentes");

        this.conexao  = conexao;
        this.usuarios = usuarios;
    }

    public void run ()
    {
        ObjectOutputStream transmissor;
        try
        {
            transmissor =
            new ObjectOutputStream(
            this.conexao.getOutputStream());
        }
        catch (Exception erro)
        {
            return;
        }
        
        ObjectInputStream receptor=null;
        try
        {
            receptor=
            new ObjectInputStream(
            this.conexao.getInputStream());
        }
        catch (Exception err0)
        {
            try
            {
                transmissor.close();
            }
            catch (Exception falha)
            {} // so tentando fechar antes de acabar a thread
            
            return;
        }

        try
        {
            this.usuario =
            new Parceiro (this.conexao,
                          receptor,
                          transmissor);
        }
        catch (Exception erro)
        {} // sei que passei os parametros corretos

        try
        {
            synchronized (this.usuarios)
            {
                this.usuarios.add (this.usuario);
            }

            for(;;)
            {
                //System.out.println(toString());
                
                Comunicado comunicado = this.usuario.envie ();

                if (comunicado==null)
                    return;
                
                else if (comunicado instanceof PedidoDeSalvamento)
                {
                    PedidoDeSalvamento pedidoDeSalvamento = (PedidoDeSalvamento)comunicado;
                    
                    System.out.println("\nPedido de Salvamento do Desenho:\n" + pedidoDeSalvamento.toString() + "\n");
                    
                    try
                    {
                        boolean verificaDesenho;
                        
                        verificaDesenho = RegistroDesenhos.verificarNome(pedidoDeSalvamento.getNomeDesenho(),
                                                                        pedidoDeSalvamento.getIdCliente());
                        
                        if(!verificaDesenho) //não existe desenho com esse nome desse cliente
                        {
                            RegistroDesenhos.incluir(new RegistroDesenho(pedidoDeSalvamento.getNomeDesenho(),
                                                                        pedidoDeSalvamento.getIdCliente(),          
                                                                        pedidoDeSalvamento.getDataCriacao(),
                                                                        pedidoDeSalvamento.getDataModificacao()));   
                        
                            System.out.println("Linha de RegistroDesenho inserida!");

                            int idAtual = RegistroDesenhos.idAtual();

                            for(int i = 0; i<pedidoDeSalvamento.getQtdFiguras(); i++)
                                Formas.incluir(new Forma (idAtual, pedidoDeSalvamento.getFigura(i)));

                            System.out.println("Linhas de Formas inseridas!");  
                        }
                        else //existe um desenho com esse nome desse cliente
                        {
                            int id = RegistroDesenhos.getIdExistente(pedidoDeSalvamento.getNomeDesenho(),
                                                                     pedidoDeSalvamento.getIdCliente());

                            RegistroDesenhos.alterar(pedidoDeSalvamento.getDataModificacao(), id);

                            System.out.println("Linha de RegistroDesenho alerada!");

                            Formas.excluir(id);

                            for(int i = 0; i<pedidoDeSalvamento.getQtdFiguras(); i++)
                                Formas.incluir(new Forma (id, pedidoDeSalvamento.getFigura(i)));

                            System.out.println("Linhas de Formas alteradas!");
                        }                    
                    }
                    catch (Exception erro)
                    {
                        erro.printStackTrace();
                        System.out.println (erro.getMessage());
                    }
                    
                    this.usuario.adeus();
                    
                }
                else if (comunicado instanceof PedidoDeAbertura)
                {
                    PedidoDeAbertura pedidoDeAbertura = (PedidoDeAbertura)comunicado;
                    
                    System.out.println("\nPedido de Abertura do Desenho:\n" + pedidoDeAbertura.toString() + "\n");
                    
                    boolean verificaDesenho = RegistroDesenhos.verificarNome(pedidoDeAbertura.getNomeDesenho(),
                                                                            pedidoDeAbertura.getIdCliente());
                    
                    if(verificaDesenho) //existe desenho com esse nome desse cliente
                    {
                        System.out.println("Enviando Desenho...");  
                        
                        int id = RegistroDesenhos.getIdExistente(pedidoDeAbertura.getNomeDesenho(),
                                                                 pedidoDeAbertura.getIdCliente());
                        
                        Desenho registroDesenho = RegistroDesenhos.getRegistroDesenho(String.valueOf(id));
                        
                        Desenho d = new Desenho (registroDesenho.getNomeDesenho(),
                                                 registroDesenho.getIdCliente(),
                                                 registroDesenho.getDataCriacao(),
                                                 registroDesenho.getDataModificacao());
                        
                        Formas.setFormasDesenho(id,d);                      
                                
                        this.usuario.receba(d);
                        
                        this.usuario.adeus();
                        
                        System.out.println("Enviado!");  
                    }
                    else
                    {
                        System.out.println("\nEsse desenho não existe!");
                        
                        this.usuario.receba(comunicado);
                        
                        this.usuario.adeus();
                    }
                    
                }
            }
        }
        catch (Exception erro)
        {
            try
            {
                transmissor.close ();
                receptor   .close ();
            }
            catch (Exception falha)
            {} // so tentando fechar antes de acabar a thread

            return;
        }
    }
    
    @Override
    public String toString() 
    {
        return "SupervisoraDeConexao{" + 
                "usuario=" + usuario + 
                ", conexao=" + conexao + 
                ", usuarios=" + usuarios + '}';
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
    public boolean equals(Object obj) 
    {
        if (this == obj) 
            return true;
        
        if (obj == null) 
            return false;
        
        if (getClass() != obj.getClass()) 
            return false;
        
        final SupervisoraDeConexao other = (SupervisoraDeConexao) obj;
        if (!Objects.equals(this.usuario, other.usuario)) 
            return false;
        
        if (!Objects.equals(this.conexao, other.conexao)) 
            return false;
        
        if (!Objects.equals(this.usuarios, other.usuarios)) 
            return false;
        
        return true;
    }    
}
