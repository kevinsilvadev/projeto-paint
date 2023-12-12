
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


    @Override
    public int hashCode(){
        int ret = 1;
        //nao sei se eh assim ou como eh um obj ja faz do jeito simplificado
        if (this.pos!=null)
            ret = ret*13 + this.pos.hashCode();
        ret = ret*13 + Integer.valueOf(this.text).hashCode();

        if(ret <0) ret = -ret;

        return ret;
    }


    @Override
    public boolean equals(Object obj)
    {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
        final Texto texto = (Texto) obj;
        if (this.fontOpc != texto.fontOpc) return false;
        if (this.pos != texto.pos) return false;

        return true;
    }


}
