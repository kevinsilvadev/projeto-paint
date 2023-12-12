
import java.awt.*;

public abstract class Figura
{
    protected Color cor, corDePreenchimento;

    protected Figura ()
    {
        this (Color.BLACK);
    }

    protected Figura (Color corDeContorno)
    {
        this (corDeContorno, Color.BLACK);
    }

    protected Figura (Color corDeContorno, Color corDePreenchimento)
    {
        this.setCor (corDeContorno);
        this.setCorDePreenchimento(corDePreenchimento);
    }

    public void setCor (Color corDeContorno)
    {
        this.cor = corDeContorno;
    }

    public void setCorDePreenchimento (Color corDePreenchimento)
    {
        this.corDePreenchimento = corDePreenchimento;
    }

    // getters
    public Color getCor() // cor do contorno
    {
        return this.cor;
    }

    public Color getCorDePreenchimento()
    {
        return this.corDePreenchimento;
    }


    public abstract boolean equals         (Object obj);
    public abstract int     hashCode       ();
  //public abstract Object  clone          ();
    public abstract String  toString       ();
    public abstract void    torneSeVisivel (Graphics g);
}