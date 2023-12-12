import Comunica.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Janela extends JFrame {
    protected static final long serialVersionUID = 1L;

    protected JButton btnPonto = new JButton("Ponto"),
            btnLinha = new JButton("Linha"),
            btnCirculo = new JButton("Circulo"),
            btnElipse = new JButton("Elipse"),
            btnQuadrado = new JButton("Quadrado"),
            btnRetangulo = new JButton("Retangulo"),
            btnTexto = new JButton("Texto"),
            btnCores = new JButton("Cores"),
            btnFont = new JButton("Fontes"),
            btnCoresPreenchimento = new JButton("Cores de Preenchimento"),
            btnAbrir = new JButton("Abrir"),
            btnSalvar = new JButton("Salvar"),
            btnApagar = new JButton("Apagar"),
            btnSair = new JButton("Sair");

    protected MeuJPanel pnlDesenho = new MeuJPanel();

    protected JLabel statusBar1 = new JLabel("Mensagem:"),
            statusBar2 = new JLabel("Coordenada:");

    protected boolean esperaPonto, esperaInicioReta, esperaFimReta, esperaCentroCirculo, esperaRaioCirculo, esperaInicioElipse, esperaFimElipse,
            esperaInicioQuadrado, esperaFimQuadrado, esperaInicioRetangulo, esperaFimRetangulo, esperaInicioTexto,
            salvo = false, aberto = false, modificado = false;
    protected Figura desenhoTemp = null;

    protected Color corDePreenchimento = new Color(0, 0, 0, 0);
    protected Color corDeContorno = Color.BLACK;
    protected Font fonte = new Font("Arial", Font.PLAIN, 12);


    protected Ponto p1, p1Retangulo;


    protected Vector<Figura> figuras = new Vector<Figura>();

    public Janela() {

        super("Editor Gr�fico");

        try {
            Image btnPontoImg = ImageIO.read(getClass().getResource("resources/ponto.jpg"));
            btnPonto.setIcon(new ImageIcon(btnPontoImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,

                    "Arquivo ponto.jpg n�o foi encontrado",

                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnLinhaImg = ImageIO.read(getClass().getResource("resources/linha.jpg"));
            btnLinha.setIcon(new ImageIcon(btnLinhaImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,

                    "Arquivo linha.jpg n�o foi encontrado",

                    "Arquivo linha.jpg n?o foi encontrado",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnCirculoImg = ImageIO.read(getClass().getResource("resources/circulo.jpg"));
            btnCirculo.setIcon(new ImageIcon(btnCirculoImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Arquivo circulo.jpg n�o foi encontrado",

                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnElipseImg = ImageIO.read(getClass().getResource("resources/elipse.jpg"));
            btnElipse.setIcon(new ImageIcon(btnElipseImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Arquivo elipse.jpg n�o foi encontrado",

                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }
        try {
            Image btnQuadradoImg = ImageIO.read(getClass().getResource("resources/elipse.jpg"));
            btnQuadrado.setIcon(new ImageIcon(btnQuadradoImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,

                    "Arquivo elipse.jpg n�o foi encontrado",
                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }
        try {
            Image btnRetanguloImg = ImageIO.read(getClass().getResource("resources/elipse.jpg"));
            btnRetangulo.setIcon(new ImageIcon(btnRetanguloImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Arquivo elipse.jpg n�o foi encontrado",

                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }
        try {
            Image btnTextoImg = ImageIO.read(getClass().getResource("resources/elipse.jpg"));
            btnTexto.setIcon(new ImageIcon(btnTextoImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,

                    "Arquivo elipse.jpg n�o foi encontrado",

                    "Arquivo elipse.jpg n?o foi encontrado",

                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnCoresImg = ImageIO.read(getClass().getResource("resources/cores.jpg"));
            btnCores.setIcon(new ImageIcon(btnCoresImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,

                    "Arquivo cores.jpg n�o foi encontrado",

                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnCoresPreenchimentoImg = ImageIO.read(getClass().getResource("resources/cores.jpg"));
            btnCores.setIcon(new ImageIcon(btnCoresPreenchimentoImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,

                    "Arquivo cores.jpg n�o foi encontrado",

                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnAbrirImg = ImageIO.read(getClass().getResource("resources/abrir.jpg"));
            btnAbrir.setIcon(new ImageIcon(btnAbrirImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,

                    "Arquivo abrir.jpg n?o foi encontrado",

                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnSalvarImg = ImageIO.read(getClass().getResource("resources/salvar.jpg"));
            btnSalvar.setIcon(new ImageIcon(btnSalvarImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,

                    "Arquivo salvar.jpg n?o foi encontrado",

                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnApagarImg = ImageIO.read(getClass().getResource("resources/apagar.jpg"));
            btnApagar.setIcon(new ImageIcon(btnApagarImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,

                    "Arquivo apagar.jpg n?o foi encontrado",

                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }
        try {
            Image btnSairImg = ImageIO.read(getClass().getResource("resources/sair.jpg"));
            btnSair.setIcon(new ImageIcon(btnSairImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,

                    "Arquivo sair.jpg n�o foi encontrado",
                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        btnPonto.addActionListener(new DesenhoDePonto());
        btnLinha.addActionListener(new DesenhoDeReta());
        btnCirculo.addActionListener(new DesenhoCirculo());
        btnElipse.addActionListener(new DesenhoElipse());
        btnQuadrado.addActionListener(new DesenhoQuadrado());
        btnRetangulo.addActionListener(new DesenhoRetangulo());

        btnCores.addActionListener(new EscolherCor());
        btnCoresPreenchimento.addActionListener(new EscolherCorPreenchimento());
        btnFont.addActionListener(new EscolherFonte());
        btnSalvar.addActionListener(new SalvarDesenho());
        btnTexto.addActionListener(new EscreveTexto());
        btnAbrir.addActionListener(new AbrirDesenho());
        btnApagar.addActionListener(new ApagarUltimoDesenho());


        JPanel pnlBotoes = new JPanel();
        FlowLayout flwBotoes = new FlowLayout();
        pnlBotoes.setLayout(flwBotoes);

        pnlBotoes.add(btnAbrir);
        pnlBotoes.add(btnSalvar);
        pnlBotoes.add(btnPonto);
        pnlBotoes.add(btnLinha);
        pnlBotoes.add(btnCirculo);
        pnlBotoes.add(btnElipse);
        pnlBotoes.add(btnQuadrado);
        pnlBotoes.add(btnRetangulo);
        pnlBotoes.add(btnTexto);
        pnlBotoes.add(btnCores);
        pnlBotoes.add(btnCoresPreenchimento);
        pnlBotoes.add(btnFont);
        pnlBotoes.add(btnApagar);
        pnlBotoes.add(btnSair);

        JPanel pnlStatus = new JPanel();
        GridLayout grdStatus = new GridLayout(1, 2);
        pnlStatus.setLayout(grdStatus);

        pnlStatus.add(statusBar1);
        pnlStatus.add(statusBar2);

        Container cntForm = this.getContentPane();
        cntForm.setLayout(new BorderLayout());
        cntForm.add(pnlBotoes, BorderLayout.NORTH);
        cntForm.add(pnlDesenho, BorderLayout.CENTER);
        cntForm.add(pnlStatus, BorderLayout.SOUTH);

        this.addWindowListener(new FechamentoDeJanela());

        this.setSize(1250, 600);
        this.setVisible(true);
    }





    protected class AbrirDesenho implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
            esperaPonto               = false;

            esperaInicioReta          = false;
            esperaFimReta             = false;

            esperaCentroCirculo       = false;
            esperaRaioCirculo         = false;

            esperaInicioElipse        = false;
            esperaFimElipse           = false;

            esperaInicioQuadrado      = false;
            esperaFimQuadrado         = false;

            esperaInicioRetangulo     = false;
            esperaFimRetangulo        = false;


            esperaInicioTexto         = false;

            try
            {
                Abrir();
            }
            catch (Exception ex)
            {
                Logger.getLogger(Janela.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void Abrir() throws Exception
    {
        //fazer conxão do cliente

        try
        {
            Socket conexao = new Socket ("localhost", 3000);

            ObjectOutputStream transmissor = null;
            try
            {
                transmissor =
                        new ObjectOutputStream(
                                conexao.getOutputStream());
            }
            catch (Exception erro)
            {
                System.err.println ("Indique o servidor e a porta corretos!\n");
                return;
            }

            ObjectInputStream receptor = null;
            try
            {
                receptor =
                        new ObjectInputStream(
                                conexao.getInputStream());
            }
            catch (Exception erro)
            {
                System.err.println ("Indique o servidor e a porta corretos!\n");
                return;
            }

            Parceiro servidor;
            try
            {
                servidor = new Parceiro (conexao, receptor, transmissor);
            }
            catch (Exception erro)
            {
                System.err.println ("Indique o servidor e a porta corretos!\n");
                return;
            }

            //conexão feita

            try
            {
                String nomeDesenho = "";

                nomeDesenho = JOptionPane.showInputDialog(null, "Nome:", "Digite o nome do desenho para abrir", JOptionPane.PLAIN_MESSAGE);

                if(nomeDesenho != null)
                {
                    if(nomeDesenho.isEmpty())
                    {
                        JOptionPane.showMessageDialog (null,
                                "Você precisa inserir um nome para seu desenho",
                                "Tente Novamente",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                    else
                    {
                        System.out.println(nomeDesenho);
                        String ipString = InetAddress.getLocalHost().getHostAddress();
                        PedidoDeAbertura pda = new PedidoDeAbertura(nomeDesenho, ipString);
                        System.out.println("Enviando Pedido de Abertura...");
                        servidor.receba(pda);
                        System.out.println("Pedido Recebido!");

                        Comunicado comunicado = servidor.envie();

                        if (comunicado == null) {
                            System.out.println("Recebido um Comunicado nulo. Verifique o que o servidor está enviando.");
                            return;
                        }

                        if (comunicado instanceof PedidoDeAbertura) {
                            System.out.println("Esse nome de desenho não existe. Tente novamente.");

                            JOptionPane.showMessageDialog(null,
                                    "Esse nome de desenho não existe",
                                    "Tente novamente",
                                    JOptionPane.INFORMATION_MESSAGE);

                            // Aqui, você pode permitir que o usuário insira um novo nome de desenho ou fazer outra ação apropriada.
                        } else if (comunicado instanceof Desenho) {
                            Desenho d = (Desenho) comunicado;
                            // Restante do código para processar o desenho...

                            System.out.println("Desenhando...");

                            if (!figuras.isEmpty()) {
                                figuras.clear();
                                repaint();
                            }

                            // Para pegar todas as figuras do desenho
                            for (int i = 0; i < d.getQtdFiguras(); i++) {
                                String s = d.getFigura(i);

                                switch (s.charAt(0)) {
                                    case 'p':
                                        figuras.add(new Ponto(s));
                                        break;

                                    case 'l':
                                        figuras.add(new Linha(s));
                                        break;

                                    case 'c':
                                        figuras.add(new Circulo(s));
                                        break;

                                    case 'e':
                                        figuras.add(new Elipse(s));
                                        break;

                                    case 'q':
                                        figuras.add(new Quadrado(s));
                                        break;

                                    case 'r':
                                        figuras.add(new Retangulo(s));
                                        break;

                                    case 't':
                                        figuras.add(new Texto(s));
                                        break;
                                }

                                figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
                            }

                            System.out.println("Desenho Pronto!");
                        } else {
                            System.out.println("Recebido um tipo de Comunicado inesperado: " + comunicado.getClass().getSimpleName());
                            // Adicione logs ou lógica adicional conforme necessário.
                        }
                        }
                    }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            servidor.adeus();
        }
        catch(ConnectException e)
        {
            JOptionPane.showMessageDialog (null,
                    "O servidor está desligado. Tente novamente mais tarde.",
                    "Servidor OFF",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    protected class MeuJPanel extends JPanel implements MouseListener, MouseMotionListener {
        public MeuJPanel() {
            super();

            this.addMouseListener(this);
            this.addMouseMotionListener(this);
        }

        public void paint(Graphics g) {
            super.paint(g); //chama o paint JComponent

            //Graphics2D graphics2D = (Graphics2D)g.create();
            for (int i = 0; i < figuras.size(); i++) {
                figuras.get(i).torneSeVisivel((Graphics2D) g);
            }
            if (desenhoTemp != null) desenhoTemp.torneSeVisivel((Graphics2D) g);

        }

        public void mousePressed(MouseEvent e) {
            if (esperaPonto) {
                figuras.add(new Ponto(e.getX(), e.getY(), corDeContorno));
                figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
                esperaPonto = true;
            } else if (esperaInicioReta) {
                p1 = new Ponto(e.getX(), e.getY(), corDeContorno);
                esperaInicioReta = false;
                esperaFimReta = true;
                statusBar1.setText("Mensagem: ");
            } else if (esperaFimReta) {
                esperaInicioReta = true;
                esperaFimReta = false;
                figuras.add(new Linha(p1.getX(), p1.getY(), e.getX(), e.getY(), corDeContorno));
                figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
                statusBar1.setText("Mensagem: ");
            } else if (esperaCentroCirculo) {
                p1 = new Ponto(e.getX(), e.getY(), corDeContorno);
                esperaCentroCirculo = false;
                esperaRaioCirculo = true;
                statusBar1.setText("Mensagem: ");
            } else if (esperaRaioCirculo) {
                try {
                    esperaRaioCirculo = false;

                    int x = e.getX() - p1.getX();
                    int y = e.getY() - p1.getY();

                    int raio = (int) Math.abs(Math.round(Math.sqrt(x * x + y * y)));

                    Circulo circulo = new Circulo();
                    circulo.setCentro(p1.getX(), p1.getY());
                    circulo.setRaio(raio);
                    circulo.setCor(corDeContorno);
                    circulo.setCorDePreenchimento(corDePreenchimento);

                    figuras.add(circulo);
                    figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());

                    statusBar1.setText("Dica: clique no centro do c�rculo e arraste o mouse");

                    statusBar1.setText("Mensagem: ");


                    desenhoTemp = null;
                    esperaCentroCirculo = true;
                    System.out.println(circulo.toString());
                    System.out.println(figuras);
                } catch (Exception x) {
                    System.out.println(x.getMessage());
                }
            } else if (esperaInicioElipse) {
                try {
                    p1 = new Ponto(e.getX(), e.getY(), corDeContorno);
                    esperaInicioElipse = false;
                    esperaFimElipse = true;
                    statusBar1.setText("Mensagem: ");


                } catch (Exception x) {
                    System.out.println(x.getMessage());
                }
            } else if (esperaFimElipse) {
                try {
                    esperaFimElipse = false;

                    int x, y, r1, r2;

                    // calculando x, y, r1 e r2
                    if (p1.getX() < e.getX()) {
                        x = (p1.getX() + e.getX()) / 2;
                        r1 = Math.abs((e.getX() - p1.getX()) / 2);
                    } else {
                        x = (e.getX() + p1.getX()) / 2;
                        r1 = Math.abs((p1.getX() - e.getX()) / 2);
                    }

                    if (p1.getY() < e.getY()) {
                        y = (p1.getY() + e.getY()) / 2;
                        r2 = Math.abs((e.getY() - p1.getY()) / 2);
                    } else {
                        y = (e.getY() + p1.getY()) / 2;
                        r2 = Math.abs((p1.getY() - e.getY()) / 2);
                    }

                    figuras.add(new Elipse(x, y, r1, r2, corDeContorno, corDePreenchimento));
                    figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());

                    statusBar1.setText("Mensagem: ");

                    esperaInicioElipse = true;
                } catch (Exception x) {
                    statusBar1.setText(x.getMessage());
                }
            } else if (esperaInicioQuadrado) {
                p1 = new Ponto(e.getX(), e.getY(), corDeContorno);
                esperaInicioQuadrado = false;
                esperaFimQuadrado = true;
                statusBar1.setText("Mensagem: ");
            } else if (esperaFimQuadrado) {
                esperaInicioQuadrado = true;
                esperaFimQuadrado = false;
                try {
                    int lado = Math.max(Math.abs(e.getX() - p1.getX()), Math.abs(e.getY() - p1.getY()));
                    figuras.add(new Quadrado(p1.getX(), p1.getY(), lado, corDeContorno, corDePreenchimento));
                    figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
                } catch (Exception ex) {
                    statusBar1.setText(ex.getMessage());
                }
            } else if (esperaInicioRetangulo) {
                p1Retangulo = new Ponto(e.getX(), e.getY(), corDeContorno);
                esperaInicioRetangulo = false;
                esperaFimRetangulo = true;
                statusBar1.setText("Mensagem: clique o ponto final do ret�ngulo");
                statusBar1.setText("Mensagem: ");
            } else if (esperaFimRetangulo) {
                esperaInicioRetangulo = true;
                esperaFimRetangulo = false;
                int largura = Math.abs(e.getX() - p1Retangulo.getX());
                int altura = Math.abs(e.getY() - p1Retangulo.getY());

                // Verifica a dire��o do movimento do mouse p ajustar as coordenadas
                int x = (e.getX() > p1Retangulo.getX()) ? p1Retangulo.getX() : e.getX();
                int y = (e.getY() > p1Retangulo.getY()) ? p1Retangulo.getY() : e.getY();

                figuras.add(new Retangulo(x, y, largura,  altura, corDeContorno, corDePreenchimento));
                figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());

            } else if (esperaInicioTexto) {
                try {
                    esperaInicioTexto = false;

                    String texto = JOptionPane.showInputDialog("Digite o texto: ");

                    if (texto != null && !texto.isEmpty()) {

                        figuras.add(new Texto(e.getX(), e.getY(), texto, fonte, corDeContorno));

                        figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
                    } else {
                        statusBar1.setText("Mensagem: clique no bot�o do que deseja desenhar");

                        statusBar1.setText("Mensagem: clique no botao do que deseja desenhar");

                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }




        public void mouseReleased(MouseEvent e) {
        }

        public void mouseClicked(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mouseDragged(MouseEvent e) {
        }

        public void mouseMoved(MouseEvent e) {
            statusBar2.setText("Coordenada: " + e.getX() + "," + e.getY());
            if (esperaFimReta) {
                try {
                    desenhoTemp = new Linha(p1.getX(), p1.getY(), e.getX(), e.getY(), corDeContorno);
                    repaint();
                    statusBar1.setText("Mensagem: clique no ponto final da reta");
                } catch (Exception x) {
                    statusBar1.setText(x.getMessage());
                }
            } else if (esperaRaioCirculo) {
                try {
                    int x = e.getX() - p1.getX();
                    int y = e.getY() - p1.getY();
                    int raio = (int) Math.abs(Math.round(Math.sqrt(x * x + y * y)));

                    Circulo circuloTemp = new Circulo();
                    circuloTemp.setCentro(p1.getX(), p1.getY());
                    circuloTemp.setRaio(raio);
                    circuloTemp.setCor(corDeContorno);
                    circuloTemp.setCorDePreenchimento(corDePreenchimento);


                    // Desenho tempor�rio
                    desenhoTemp = circuloTemp;
                    repaint();
                    statusBar1.setText("Mensagem: Arraste o mouse e clique no fim do c�rculo");
                    // Desenho tempor?rio
                    desenhoTemp = circuloTemp;
                    repaint();
                    statusBar1.setText("Mensagem: Arraste o mouse e clique no fim do circulo");


                } catch (Exception x) {
                    System.out.println(x.getMessage());
                }
            } else if (esperaFimElipse) {
                try {

                    int x1, y1, r1, r2;

                    if (p1.getX() < e.getX()) {
                        x1 = (p1.getX() + e.getX()) / 2;
                        r1 = Math.abs((e.getX() - p1.getX()) / 2);
                    } else {
                        x1 = (e.getX() + p1.getX()) / 2;
                        r1 = Math.abs((p1.getX() - e.getX()) / 2);
                    }

                    if (p1.getY() < e.getY()) {
                        y1 = (p1.getY() + e.getY()) / 2;
                        r2 = Math.abs((e.getY() - p1.getY()) / 2);
                    } else {
                        y1 = (e.getY() + p1.getY()) / 2;
                        r2 = Math.abs((p1.getY() - e.getY()) / 2);
                    }

                    // Desenho tempor?rio
                    desenhoTemp = new Elipse(x1, y1, r1, r2, corDeContorno, corDePreenchimento);
                    repaint();

                    statusBar1.setText("Mensagem: Arraste o mouse e clique no fim da elipse");

                } catch (Exception x) {
                    statusBar1.setText(x.getMessage());
                }
            } else if (esperaFimQuadrado) {
                int lado = Math.max(Math.abs(e.getX() - p1.getX()), Math.abs(e.getY() - p1.getY()));
                desenhoTemp = new Quadrado(p1.getX(), p1.getY(), lado, corDeContorno, corDePreenchimento);
                repaint();
                statusBar1.setText("Mensagem: Arraste o mouse e clique para finalizar o quadrado");
            } else if (esperaFimRetangulo) {
                try {
                    int largura = Math.abs(e.getX() - p1Retangulo.getX());
                    int altura = Math.abs(e.getY() - p1Retangulo.getY());

                    // Verifica a dire��o do movimento do mouse p ajustar as coordenadas
                    int x = (e.getX() > p1Retangulo.getX()) ? p1Retangulo.getX() : e.getX();
                    int y = (e.getY() > p1Retangulo.getY()) ? p1Retangulo.getY() : e.getY();

                    desenhoTemp = new Retangulo(x, y, largura, altura, corDeContorno, corDePreenchimento);
                    repaint();
                    statusBar1.setText("Mensagem: Arraste o mouse e clique para finalizar o ret�ngulo");

                    statusBar1.setText("Mensagem: Arraste o mouse e clique para finalizar o retangulo");
                } catch (Exception x) {
                    statusBar1.setText(x.getMessage());
                }
            }

        }

    }

    protected class DesenhoDePonto implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            esperaPonto = true;
            esperaInicioReta = false;
            esperaFimReta = false;
            esperaCentroCirculo = false;
            esperaRaioCirculo = false;
            esperaInicioElipse = false;
            esperaFimElipse = false;
            esperaInicioQuadrado = false;
            esperaFimQuadrado = false;
            esperaInicioRetangulo = false;
            esperaFimRetangulo = false;
            esperaInicioTexto = false;

            statusBar1.setText("Mensagem: clique o local do ponto desejado");
        }
    }

    protected class DesenhoDeReta implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            esperaPonto = false;
            esperaInicioReta = true;
            esperaFimReta = false;
            esperaCentroCirculo = false;
            esperaRaioCirculo = false;
            esperaInicioElipse = false;
            esperaFimElipse = false;
            esperaInicioQuadrado = false;
            esperaFimQuadrado = false;
            esperaInicioRetangulo = false;
            esperaFimRetangulo = false;
            esperaInicioTexto = false;

            statusBar1.setText("Mensagem: clique o ponto inicial da reta");
        }
    }

    protected class DesenhoCirculo implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            esperaPonto = false;
            esperaInicioReta = false;
            esperaFimReta = false;
            esperaCentroCirculo = true;
            esperaRaioCirculo = false;
            esperaInicioElipse = false;
            esperaFimElipse = false;
            esperaInicioQuadrado = false;
            esperaFimQuadrado = false;
            esperaInicioRetangulo = false;
            esperaFimRetangulo = false;
            esperaInicioTexto = false;

            statusBar1.setText("Mensagem: Clique onde deseja inserir o circulo!");
        }
    }

    protected class DesenhoElipse implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            esperaPonto = false;
            esperaInicioReta = false;
            esperaFimReta = false;
            esperaCentroCirculo = false;
            esperaRaioCirculo = false;
            esperaInicioElipse = true;
            esperaFimElipse = false;
            esperaInicioQuadrado = false;
            esperaFimQuadrado = false;
            esperaInicioRetangulo = false;
            esperaFimRetangulo = false;
            esperaInicioTexto = false;
            statusBar1.setText("Mensagem: Clique onde deseja inserir a elipse!");
        }
    }

    protected class DesenhoQuadrado implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            esperaPonto = false;
            esperaInicioReta = false;
            esperaFimReta = false;
            esperaCentroCirculo = false;
            esperaRaioCirculo = false;
            esperaInicioElipse = false;
            esperaFimElipse = false;
            esperaInicioQuadrado = true;
            esperaFimQuadrado = false;
            esperaInicioRetangulo = false;
            esperaFimRetangulo = false;
            esperaInicioTexto = false;
            statusBar1.setText("Mensagem: Clique onde deseja inserir o quadrado!");

        }
    }

    protected class DesenhoRetangulo implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            esperaPonto = false;
            esperaInicioReta = false;
            esperaFimReta = false;
            esperaCentroCirculo = false;
            esperaRaioCirculo = false;
            esperaInicioElipse = false;
            esperaFimElipse = false;
            esperaInicioQuadrado = false;
            esperaFimQuadrado = false;
            esperaInicioRetangulo = true;
            esperaFimRetangulo = false;
            esperaInicioTexto = false;
            statusBar1.setText("Mensagem: Clique onde deseja inserir o retangulo!");
        }
    }

    protected class EscreveTexto implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            esperaPonto = false;
            esperaInicioReta = false;
            esperaFimReta = false;
            esperaCentroCirculo = false;
            esperaRaioCirculo = false;
            esperaInicioElipse = false;
            esperaFimElipse = false;
            esperaInicioQuadrado = false;
            esperaFimQuadrado = false;
            esperaInicioRetangulo = false;
            esperaFimRetangulo = false;
            esperaInicioTexto = true;
            statusBar1.setText("Mensagem: Clique onde deseja inserir o texto!");
        }
    }

    private class EscolherCor implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            esperaPonto = false;
            esperaInicioReta = false;
            esperaFimReta = false;
            esperaCentroCirculo = false;
            esperaRaioCirculo = false;
            esperaInicioElipse = false;
            esperaFimElipse = false;
            esperaInicioQuadrado = false;
            esperaFimQuadrado = false;
            esperaInicioRetangulo = false;
            esperaFimRetangulo = false;
            esperaInicioTexto = false;

            statusBar1.setText("Dica:");

            JColorChooser javacor = new JColorChooser();

            Color setCor = javacor.showDialog(btnCores, "Selecione a Cor Desejada", Color.black);

            corDeContorno = setCor;

            statusBar1.setText("Mensagem: Clique na figura que deseja fazer o desenho");
        }
    }

    private class EscolherCorPreenchimento implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            esperaPonto = false;
            esperaInicioReta = false;
            esperaFimReta = false;
            esperaCentroCirculo = false;
            esperaRaioCirculo = false;
            esperaInicioElipse = false;
            esperaFimElipse = false;
            esperaInicioQuadrado = false;
            esperaFimQuadrado = false;
            esperaInicioRetangulo = false;
            esperaFimRetangulo = false;
            esperaInicioTexto = false;

            statusBar1.setText("Dica:");

            JColorChooser javacor = new JColorChooser();

            Color setCorPreenchimento = javacor.showDialog(btnCoresPreenchimento, "Selecione a Cor Desejada", new Color(0, 0, 0, 0));

            corDePreenchimento = setCorPreenchimento;

            statusBar1.setText("Mensagem: Clique na figura que deseja fazer o desenho");
        }
    }

    private class EscolherFonte implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            esperaPonto = false;
            esperaInicioReta = false;
            esperaFimReta = false;
            esperaCentroCirculo = false;
            esperaRaioCirculo = false;
            esperaInicioElipse = false;
            esperaFimElipse = false;
            esperaInicioQuadrado = false;
            esperaFimQuadrado = false;
            esperaInicioRetangulo = false;
            esperaFimRetangulo = false;
            esperaInicioTexto = false;

            statusBar1.setText("Mensagem: clique onde deseja adicionar o texto");

            EscolhaDeFonte fonteEscolha = new EscolhaDeFonte();
            Font setFont = fonteEscolha.escolherFonte();
            fonte = setFont;
            repaint();
        }

    }

    public class ApagarUltimoDesenho implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            esperaPonto = false;
            esperaInicioReta = false;
            esperaFimReta = false;
            esperaCentroCirculo = false;
            esperaRaioCirculo = false;
            esperaInicioElipse = false;
            esperaFimElipse = false;
            esperaInicioQuadrado = false;
            esperaFimQuadrado = false;
            esperaInicioRetangulo = false;
            esperaFimRetangulo = false;
            esperaInicioTexto = false;

            if (!figuras.isEmpty()) {
                figuras.remove(figuras.size() - 1);
                limparTela();
                redesenharFiguras();
            }
        }


        private void limparTela() {
            // Limpa a tela
            Graphics g = pnlDesenho.getGraphics();
            g.setColor(pnlDesenho.getBackground());
            g.fillRect(0, 0, pnlDesenho.getWidth(), pnlDesenho.getHeight());
        }

        private void redesenharFiguras() {
            // Redesenha todas as figuras restantes
            for (Figura figura : figuras) {
                figura.torneSeVisivel(pnlDesenho.getGraphics());
            }
        }
    }





    protected class SalvarDesenho implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {

            esperaPonto               = false;
            esperaInicioReta          = false;
            esperaFimReta             = false;

            esperaCentroCirculo       = false;
            esperaRaioCirculo         = false;

            esperaInicioElipse        = false;
            esperaFimElipse           = false;

            esperaInicioQuadrado      = false;
            esperaFimQuadrado         = false;

            esperaInicioRetangulo     = false;
            esperaFimRetangulo        = false;

            esperaInicioTexto         = false;

            try
            {
                Salvar();
            }
            catch (ArrayIndexOutOfBoundsException erro)
            {
                JOptionPane.showMessageDialog (null,
                        "Não há nada para salvar!",
                        "Tela Limpa",
                        JOptionPane.WARNING_MESSAGE);
            } catch (IOException ex)
            {
                Logger.getLogger(Janela.class.getName()).log(Level.SEVERE, null, ex);
            }

            statusBar1.setText("Dica: clique no botão do que deseja desenhar");
        }
    }


    public void Salvar() throws ArrayIndexOutOfBoundsException, IOException
    {
        if(figuras.isEmpty()) throw new ArrayIndexOutOfBoundsException ("Sem desenho na tela");

        //fazer conexão do cliente

        try
        {
            Socket conexao = new Socket ("localhost", 3000);

            ObjectOutputStream transmissor = null;
            try
            {
                transmissor =
                        new ObjectOutputStream(
                                conexao.getOutputStream());
            }
            catch (Exception erro)
            {
                System.err.println ("Indique o servidor e a porta corretos!\n");
                return;
            }

            ObjectInputStream receptor = null;
            try
            {
                receptor =
                        new ObjectInputStream(
                                conexao.getInputStream());
            }
            catch (Exception erro)
            {
                System.err.println ("Indique o servidor e a porta corretos!\n");
                return;
            }

            Parceiro servidor = null;
            try
            {
                servidor = new Parceiro(conexao, receptor, transmissor);
            }
            catch (Exception erro)
            {
                System.err.println ("Indique o servidor e a porta corretos!\n");
                return;
            }
            //conexão feita

            try
            {
                String nomeDesenho = JOptionPane.showInputDialog(null, "Nome:", "Digite o nome do desenho", JOptionPane.PLAIN_MESSAGE);

                if(nomeDesenho != null)
                {
                    if(nomeDesenho.isEmpty())
                    {
                        JOptionPane.showMessageDialog (null,
                                "Você precisa inserir um nome para seu desenho",
                                "Não Salvo",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                    else
                    {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
                        String dataString = sdf.format(new Date());

                        String ipString = InetAddress.getLocalHost().getHostAddress();

                        Desenho d = new Desenho (nomeDesenho, ipString, dataString, dataString);

                        for (Figura f : this.figuras)
                            d.addFigura (f.toString());
                        System.out.println(d);

                        System.out.println("Enviando Pedido de Salvamento...");

                        servidor.receba(new PedidoDeSalvamento(d));

                        System.out.println("Pedido de Salvamento Recebido!");

                        System.out.println("Desenho Salvo!");

                        JOptionPane.showMessageDialog (null,
                                "Desenho salvo como: " + nomeDesenho,
                                "Salvo",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }

                servidor.adeus();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        catch(ConnectException e)
        {
            JOptionPane.showMessageDialog (null,
                    "O servidor está desligado. Tente novamente mais tarde.",
                    "Servidor OFF",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }







    protected class FechamentoDeJanela extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }
}