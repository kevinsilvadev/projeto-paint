
import java.awt.*;
import java.util.*;

public class Elipse extends Figura
{
    protected Ponto centro;

    protected int raio1, raio2;
	
    public Elipse (int x, int y, int r1, int r2)
    {
        this (x, y, r1, r2, Color.BLACK, Color.BLACK);
    }
	
    public Elipse (int x, int y, int r1, int r2, Color cor, Color corDePreenchimento)
    {
        super (cor, corDePreenchimento);

        this.centro = new Ponto (x,y);

        this.raio1  = r1;
        this.raio2  = r2;
    }

    public Elipse (String s)
    {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();

        int   x   = Integer.parseInt(quebrador.nextToken());
        int   y   = Integer.parseInt(quebrador.nextToken());

        int   r1  = Integer.parseInt(quebrador.nextToken());
        int   r2  = Integer.parseInt(quebrador.nextToken());

        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B

        this.centro = new Ponto (x,y,cor);
        this.raio1  = r1;
        this.raio2  = r2;
        this.cor    = cor;
    }


    public void setCentro (int x, int y)
    {
        this.centro = new Ponto (x,y,this.getCor());
    }

    public void setRaio1 (int r1)
    {
        this.raio1 = r1;
    }

    public void setRaio2 (int r2)
    {
        this.raio2 = r2;
    }

    public Ponto getCentro ()
    {
        return this.centro;
    }

    public int setRaio1 ()
    {
        return this.raio1;
    }

    public int setRaio2 ()
    {
        return this.raio2;
    }

    public void torneSeVisivel (Graphics g)
    {
        g.setColor (this.cor);
        g.drawOval (this.centro.getX()-raio1, this.centro.getY()-raio2, 2*raio1, 2*raio2);

        g.setColor (this.corDePreenchimento);
        g.fillOval (this.centro.getX()-raio1, this.centro.getY()-raio2, 2*raio1, 2*raio2);
			
    }

    public String toString()
    {
        return "e:" +
               this.centro.getX() +
               ":" +
               this.centro.getY() +
               ":" +
               this.raio1 +
               ":" +
               this.raio2 +
               ":" +
                this.getCor().getRed() +
                ":" +
                this.getCor().getGreen() +
                ":" +
                this.getCor().getBlue() +
                ":" +
                this.getCorDePreenchimento().getRed() +
                ":" +
                this.getCorDePreenchimento().getGreen() +
                ":" +
                this.getCorDePreenchimento().getBlue();
    }

    @Override
    public int hashCode(){
        int ret = 1;
        //nao sei se eh assim ou como eh um obj ja faz do jeito simplificado
        if (this.centro!=null) ret = ret*13 + this.centro.hashCode();
        ret = ret*13 + Integer.valueOf(this.raio1).hashCode();
        ret = ret*13 + Integer.valueOf(this.raio2).hashCode();

        if(ret <0) ret = -ret;

        return ret;
    }


    @Override
    public boolean equals(Object obj)
    {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
        final Elipse elipse = (Elipse) obj;
        if (this.centro != elipse.centro) return false;
        if (this.raio1 != elipse.raio1) return false;
        if (this.raio2 != elipse.raio2) return false;

        return true;
    }
}