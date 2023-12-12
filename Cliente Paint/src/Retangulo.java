
import java.awt.*;
import java.util.*;

public class Retangulo extends Figura {
    protected Ponto pontoSuperiorEsquerdo;
    protected int largura, altura;

    public Retangulo(int x, int y, int largura, int altura, Color cor, Color corDePreenchimento) {
        super(cor, corDePreenchimento);
        this.pontoSuperiorEsquerdo = new Ponto(x, y, cor);
        this.largura = largura;
        this.altura = altura;
    }

    public Retangulo(String s) {
        StringTokenizer quebrador = new StringTokenizer(s, ":");
        quebrador.nextToken(); // Pula a letra identificadora da figura

        int x = Integer.parseInt(quebrador.nextToken());
        int y = Integer.parseInt(quebrador.nextToken());
        int largura = Integer.parseInt(quebrador.nextToken());
        int altura = Integer.parseInt(quebrador.nextToken());

        Color cor = new Color(Integer.parseInt(quebrador.nextToken()),
                Integer.parseInt(quebrador.nextToken()),
                Integer.parseInt(quebrador.nextToken()));

        Color corDePreenchimento = new Color(Integer.parseInt(quebrador.nextToken()),
                Integer.parseInt(quebrador.nextToken()),
                Integer.parseInt(quebrador.nextToken()));

        this.pontoSuperiorEsquerdo = new Ponto(x, y, cor);
        this.largura = largura;
        this.altura = altura;
        this.cor = cor;
        this.corDePreenchimento = corDePreenchimento;
    }

    public void setPontoSuperiorEsquerdo(int x, int y) {
        this.pontoSuperiorEsquerdo = new Ponto(x, y, this.cor);
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public Ponto getPontoSuperiorEsquerdo() {
        return this.pontoSuperiorEsquerdo;
    }

    public int getLargura() {
        return this.largura;
    }

    public int getAltura() {
        return this.altura;
    }

    public void torneSeVisivel(Graphics g) {
        g.setColor(this.cor);
        g.drawRect(this.pontoSuperiorEsquerdo.getX(), this.pontoSuperiorEsquerdo.getY(), this.largura, this.altura);

        g.setColor(this.corDePreenchimento);
        g.fillRect(this.pontoSuperiorEsquerdo.getX(), this.pontoSuperiorEsquerdo.getY(), this.largura, this.altura);
    }

    public String toString() {
        return "r:" +
                this.pontoSuperiorEsquerdo.getX() +
                ":" +
                this.pontoSuperiorEsquerdo.getY() +
                ":" +
                this.largura +
                ":" +
                this.altura +
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
}