package Comunica;

import java.util.Objects;

public class PedidoDeAbertura extends Comunicado
{    
    private String nomeDesenho;
    private String idCliente;
    
    public PedidoDeAbertura (String nomeDesenho, String idCliente)
    {
        this.nomeDesenho = nomeDesenho;
        this.idCliente = idCliente;        
    }
    


    public String getNomeDesenho ()
    {
        return this.nomeDesenho;
    }
    
    public String getIdCliente ()
    {
        return this.idCliente;
    }
   
    /*public String toString ()
    {
        return ("Nome do Desenho: "+this.nomeDesenho+"\n"+
                "Ip do Cliente: "+this.idCliente);
        
    }*/

    @Override
    public String toString() 
    {
        return "PedidoDeAbertura{" + 
                "nomeDesenho=" + nomeDesenho + "\n"+
                "idCliente=" + idCliente + '}';
    }

    @Override
    public int hashCode() 
    {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.nomeDesenho);
        hash = 31 * hash + Objects.hashCode(this.idCliente);
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
        
        final PedidoDeAbertura other = (PedidoDeAbertura) obj;
        
        if (!Objects.equals(this.nomeDesenho, other.nomeDesenho)) 
            return false;
        
        if (!Objects.equals(this.idCliente, other.idCliente)) 
            return false;
        
        return true;
    }
}