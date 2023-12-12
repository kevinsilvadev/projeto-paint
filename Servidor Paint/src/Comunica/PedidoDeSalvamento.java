package Comunica;

import java.util.Objects;

public class PedidoDeSalvamento extends Comunicado
{
    private Desenho desenho;
    
    public PedidoDeSalvamento (Desenho desenho)
    {
        this.desenho = desenho;
    }
    
    public void setDesenho (Desenho desenho)
    {
        this.desenho = desenho;
    }
    
    public Desenho getDesenho ()
    {
        return this.desenho;
    }
    
    public String getNomeDesenho ()
    {
        return this.desenho.getNomeDesenho();
    }

    public String getIdCliente ()
    {
        return this.desenho.getIdCliente();
    }
    public String getDataCriacao ()
    {
        return this.desenho.getDataCriacao();
    }
    
    public String getDataModificacao ()
    {
        return this.desenho.getDataModificacao();
    }
    
    public String getFigura (int i)
    {
        return desenho.getFigura(i);
    }
    
    public int getQtdFiguras ()
    {
        return desenho.getQtdFiguras();
    }

    @Override
    public String toString() 
    {
        return this.desenho.toString();
    }

    @Override
    public int hashCode() 
    {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.desenho);
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
        
        final PedidoDeSalvamento other = (PedidoDeSalvamento) obj;
        
        if (!Objects.equals(this.desenho, other.desenho)) 
            return false;
        
        return true;
    } 
}
