
import java.awt.*;
import java.util.*;

public class Texto extends Figura {


    protected String text;
    protected Font fontOpc;
    protected Ponto pos;

    public Texto(int x, int y, String text) throws Exception {
        this(x, y, text, new Font("Arial", Font.PLAIN, 12), Color.BLACK);
    }

    public Texto(int x, int y, String text, Font font, Color cor) throws Exception {
        super(cor);

        if ("".equals(text)) throw new Exception("INSIRA ALGUM TEXTO");

        this.pos = new Ponto(x, y);

        this.text = text;
        this.fontOpc = font;
    }

    public Texto(String s) {
        try {
            StringTokenizer quebrador = new StringTokenizer(s, ":");

            quebrador.nextToken();

            int x = Math.abs(Integer.parseInt(quebrador.nextToken()));
            int y = Math.abs(Integer.parseInt(quebrador.nextToken()));

            String texto = quebrador.nextToken();

            Color cor = new Color(Integer.parseInt(quebrador.nextToken()),
                    Integer.parseInt(quebrador.nextToken()),
                    Integer.parseInt(quebrador.nextToken()));


            Font fontOpc = new Font(quebrador.nextToken(),Integer.parseInt(quebrador.nextToken()), Integer.parseInt(quebrador.nextToken()));

            this.pos = new Ponto(x, y);

            this.text = texto;

            this.cor = cor;

            this.fontOpc = fontOpc;
        } catch (NumberFormatException i) {
            System.err.println(i.getMessage());
        }
    }

    //setters
    public void setPosicao(int x, int y) {
        this.pos = new Ponto(x, y, this.getCor());
    }

    public void setText(String text) {
        this.text = text;
    }

    //getters
    public Ponto getPos() {
        return this.pos;
    }

    public String getText() {
        return this.text;
    }

    public Font getFontOpc() {
        return fontOpc;
    }

    public void setFontOpc(Font fontOpc) {
        this.fontOpc = fontOpc;
    }

    public void torneSeVisivel(Graphics g) {
        g.setColor(this.cor);

        g.setFont(this.fontOpc);

        g.drawString(this.text, this.pos.getX(), this.pos.getY());
    }

    public String toString() {
        return "t:" +
                this.pos.getX() +
                ":" +
                this.pos.getY() +
                ":" +
                this.text +
                ":" +
                this.getCor().getRed() +
                ":" +
                this.getCor().getGreen() +
                ":" +
                this.getCor().getBlue() +
                ":" +
                this.fontOpc.getFamily() +
                ":" +
                this.fontOpc.getStyle() +
                ":" +
                this.fontOpc.getSize();
    }



}
