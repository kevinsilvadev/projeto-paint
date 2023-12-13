package Comunica;

import java.util.*;

public class Desenho extends Comunicado
{
    private String nomeDesenho;
    private String idCliente;
    private String dataCriacao;
    private String dataModificacao;
    private ArrayList<String> figuras;
    
    public void setNomeDesenho(String nomeDesenho) throws Exception {
        if (nomeDesenho==null || nomeDesenho.equals(""))
            throw new Exception ("Nome não fornecido");
        
        this.nomeDesenho = nomeDesenho;
    }
    
    public void setIdCliente(String idCliente) throws Exception  {
        if (idCliente==null || idCliente.equals(""))
            throw new Exception ("Id não fornecido");
        
        this.idCliente = idCliente;
    }
    
    public void setDataCriacao(String dataCriacao) throws Exception {
        if (dataCriacao==null || dataCriacao.equals(""))
            throw new Exception ("Data de criação não fornecida");
        
        this.dataCriacao = dataCriacao;
    }
    
    public void setDataModificacao(String dataModificacao) throws Exception {
        if (dataModificacao==null || dataModificacao.equals(""))
            throw new Exception ("Data de modificação não fornecida");
        
        this.dataModificacao = dataModificacao;
    }
    

    public Desenho (String nomeDesenho, String idCliente, String dataCriacao, String dataModificacao) throws Exception
    {
        this.setNomeDesenho (nomeDesenho);
        this.setIdCliente (idCliente);
        this.setDataCriacao (dataCriacao);
        this.setDataModificacao (dataModificacao);
        
        this.figuras = new ArrayList<String>();
    }
    
    public void addFigura (String fig)
    {
        figuras.add(fig);
    }
    
    public String getFigura (int i)
    {
        return figuras.get(i);
    }
    
    public int getQtdFiguras ()
    {
        return figuras.size();
    }

    @Override
    public String toString() 
    {
        return "Desenho{" + 
                "nomeDesenho=" + nomeDesenho + "\n"+
                "idCliente=" + idCliente + "\n"+
                "dataCriacao=" + dataCriacao + "\n"+
                "dataModificacao=" + dataModificacao + "\n"+
                "figuras=" + figuras + '}';
    }  
    
    @Override
    public int hashCode() 
    {
        int ret = 666;
        ret = 13 * ret + this.nomeDesenho.hashCode();
        ret = 13 * ret + this.idCliente.hashCode();
        ret = 13 * ret + this.dataCriacao.hashCode();
        ret = 13 * ret + this.dataModificacao.hashCode();
        ret = 13 * ret + this.figuras.hashCode();
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
        
        final Desenho other = (Desenho) obj;
        
        if (!Objects.equals(this.nomeDesenho, other.nomeDesenho)) 
            return false;
        
        if (!Objects.equals(this.idCliente, other.idCliente)) 
            return false;
        
        if (!Objects.equals(this.dataCriacao, other.dataCriacao)) 
            return false;
        
        if (!Objects.equals(this.dataModificacao, other.dataModificacao)) 
            return false;
        
        if (!Objects.equals(this.figuras, other.figuras)) 
            return false;
        
        return true;
    }
}