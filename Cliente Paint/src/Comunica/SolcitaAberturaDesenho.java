package Comunica;

import java.util.Objects;

public class SolcitaAberturaDesenho extends Comunicado
{    
    private String nomeDesenho;
    private String idCliente;
    
    public SolcitaAberturaDesenho(String nomeDesenho, String idCliente)
    {
        this.nomeDesenho = nomeDesenho;
        this.idCliente = idCliente;        
    }
    
    public void setNomeDesenho (String nomeDesenho)
    {
        this.nomeDesenho = nomeDesenho;
    }
    
    public void setIdCliente (String idCliente)
    {
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
        int ret = 666;
        ret = 13 * ret +  this.nomeDesenho.hashCode();
        ret = 13 * ret +  this.idCliente.hashCode();
        return ret;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) 
            return true;
        
        if (obj == null) 
            return false;
        
        if (getClass() != obj.getClass()) 
            return false;
        
        final SolcitaAberturaDesenho solcitaAberturaDesenho = (SolcitaAberturaDesenho) obj;
        
        if (!Objects.equals(this.nomeDesenho, solcitaAberturaDesenho.nomeDesenho))
            return false;
        
        if (!Objects.equals(this.idCliente, solcitaAberturaDesenho.idCliente))
            return false;
        
        return true;
    }
}