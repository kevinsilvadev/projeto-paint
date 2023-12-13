package Comunica;

import java.util.Objects;

public class PedidoDeSalvamento extends Comunicado {
    private Desenho desenho;
    
    public PedidoDeSalvamento (Desenho desenho)
    {
        this.desenho = desenho;
    }

    @Override
    public String toString() 
    {
        return this.desenho.toString();
    }

    @Override
    public int hashCode() 
    {
        int ret = 666;
        ret = 13 * ret +this.desenho.hashCode();
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
        
        final PedidoDeSalvamento pedidoDeSalvamento = (PedidoDeSalvamento) obj;
        
        if (!Objects.equals(this.desenho, pedidoDeSalvamento.desenho))
            return false;
        
        return true;
    } 
}