

import java.awt.*;
import java.util.StringTokenizer;

public class Quadrado extends Figura {
    protected Ponto cantoSuperiorEsquerdo;
    protected int lado;


    public Quadrado(int x, int y, int lado) {
        this(x, y, lado, Color.BLACK, Color.BLACK);
    }

    public Quadrado(int x, int y, int lado, Color cor, Color corDePreenchimento) {
        super(cor, corDePreenchimento);

        this.cantoSuperiorEsquerdo = new Ponto(x, y, cor);
        this.lado = lado;
    }

    public Quadrado(String s) {
        StringTokenizer quebrador = new StringTokenizer(s, ":");

        quebrador.nextToken();

        int x = Integer.parseInt(quebrador.nextToken());
        int y = Integer.parseInt(quebrador.nextToken());
        int lado = Integer.parseInt(quebrador.nextToken());

        Color cor = new Color(
                Integer.parseInt(quebrador.nextToken()),  // R
                Integer.parseInt(quebrador.nextToken()),  // G
                Integer.parseInt(quebrador.nextToken())   // B
        );

        this.cantoSuperiorEsquerdo = new Ponto(x, y, cor);
        this.lado = lado;
        this.cor = cor;
    }

    public Ponto getCantoSuperiorEsquerdo() {
        return this.cantoSuperiorEsquerdo;
    }

    public void setCantoSuperiorEsquerdo(int x, int y) {
        this.cantoSuperiorEsquerdo = new Ponto(x, y, this.getCor());
    }

    public int getLado() {
        return this.lado;
    }

    public void setLado(int lado) {
        this.lado = lado;
    }

    @Override
    public void torneSeVisivel(Graphics g) {
        g.setColor(this.cor);
        g.drawRect(this.cantoSuperiorEsquerdo.getX(), this.cantoSuperiorEsquerdo.getY(), this.lado, this.lado);

        g.setColor(this.corDePreenchimento);
        g.fillRect(this.cantoSuperiorEsquerdo.getX(), this.cantoSuperiorEsquerdo.getY(), this.lado, this.lado);
    }

    @Override
    public String toString() {
        return "q:" +
                this.cantoSuperiorEsquerdo.getX() +
                ":" +
                this.cantoSuperiorEsquerdo.getY() +
                ":" +
                this.lado +
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
        if (this.cantoSuperiorEsquerdo!=null)
            ret = ret*13 + this.cantoSuperiorEsquerdo.hashCode();
        ret = ret*13 + Integer.valueOf(this.lado).hashCode();

        if(ret <0) ret = -ret;

        return ret;
    }


    @Override
    public boolean equals(Object obj)
    {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
        final Quadrado quadrado = (Quadrado) obj;
        if (this.cantoSuperiorEsquerdo != quadrado.cantoSuperiorEsquerdo) return false;
        if (this.lado != quadrado.lado) return false;

        return true;
    }


}
