

import java.awt.*;
import java.util.*;

public class Circulo extends Figura
{
    protected Ponto centro;
    protected int   raio;
	
    public Circulo (int x, int y, int r)
    {
        this (x, y, r, Color.BLACK, Color.BLACK);
    }
    public Circulo(){}
	
    public Circulo (int x, int y, int r, Color corDeContorno, Color corDePreenchimento)
    {
        super(corDeContorno, corDePreenchimento);

        this.centro = new Ponto (x,y);
        this.raio   = r;
    }

    public Circulo (String s)
    {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();

        int   x   = Integer.parseInt(quebrador.nextToken());
        int   y   = Integer.parseInt(quebrador.nextToken());

        int   r   = Integer.parseInt(quebrador.nextToken());

        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B

        this.centro = new Ponto (x,y,cor);
        this.raio   = r;
        this.cor = cor;
    }

    public void setCentro (int x, int y)
    {
        this.centro = new Ponto (x,y);
    }

    public void setRaio (int r)
    {
        this.raio = r;
    }

    public Ponto getCentro ()
    {
        return this.centro;
    }

    public int getRaio ()
    {
        return this.raio;
    }

    public void torneSeVisivel(Graphics g) {
        g.setColor(this.cor);
        g.drawOval(this.centro.getX() - raio, this.centro.getY() - raio, 2 * raio, 2 * raio);

        // Preencher o círculo
        g.setColor(this.corDePreenchimento);
        g.fillOval(this.centro.getX() - raio, this.centro.getY() - raio, 2 * raio, 2 * raio);
    }

    public String toString()
    {
        return "c:" +
               this.centro.getX() +
               ":" +
               this.centro.getY() +
               ":" +
               this.raio +
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
        ret = ret*13 + Integer.valueOf(this.raio).hashCode();

        if(ret <0) ret = -ret;

        return ret;
    }


    @Override
    public boolean equals(Object obj)
    {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
        final Circulo circulo = (Circulo) obj;
        if (this.centro != circulo.centro || this.raio != circulo.raio) return false;

        return true;
    }
}