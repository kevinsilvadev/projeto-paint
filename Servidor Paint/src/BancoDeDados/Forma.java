package BancoDeDados;

import java.util.Objects;

public class Forma 
{
    private int    idDesenho;
    private String figura;
    
    public void setIdDesenho(int idDesenho) throws Exception
    {
        if (idDesenho <= 0)
            throw new Exception ("Id inválido");
        
        this.idDesenho = idDesenho;
    }
    
    public void setFigura(String figura) throws Exception
    {
        if (figura==null || figura.equals(""))
            throw new Exception ("Figura não fornecida");
        
        this.figura = figura;
    }
    
    public int getIdDesenho ()
    {
        return this.idDesenho;
    }
    
    public String getFigura ()
    {
        return this.figura;
    }
    
    public Forma (int idDesenho, String figura) throws Exception
    {
        this.setIdDesenho (idDesenho);
        this.setFigura (figura);
    }
    
    @Override
    public String toString ()
    {
        String ret="";

        ret+="Id Desenho: "+this.idDesenho;
        ret+="Id Figura: "+this.figura;
        
        return ret;
    }

    @Override
    public int hashCode() 
    {
        int hash = 3;
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
        
        final Forma other = (Forma) obj;
        
        if (this.idDesenho != other.idDesenho) 
            return false;
        
        if (!Objects.equals(this.figura, other.figura)) 
            return false;
        
        return true;
    }
}
