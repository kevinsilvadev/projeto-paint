import Comunica.*;
import java.util.*;

public class Servidor
{
    public static String PORTA_PADRAO = "3000";
	
    public static void main (String[] args)
    {
        if (args.length>1)
        {
            System.err.println ("Uso esperado: java Servidor [PORTA]\n");
            return;
        }

        String porta=Servidor.PORTA_PADRAO;
        
        if (args.length==1)
            porta = args[0];

        ArrayList<ComunicaServerSocket> usuarios = new ArrayList<ComunicaServerSocket> ();

        AceitaSocket aceitaSocket =null;
        try
        {
            aceitaSocket =
            new AceitaSocket(porta, usuarios);
            aceitaSocket.start();
        }
        catch (Exception erro)
        {
            System.err.println ("Escolha uma porta apropriada e liberada para uso!\n");
            return;
        }

        for(;;)
        {
            System.out.println ("O servidor está ativo! Para desativá-lo, digite \"desativar\"\n");

            String comando=null;
            try
            {
                comando = Teclado.getUmString();
            }
            catch (Exception erro)
            {}

            if (comando.toLowerCase().equals("desativar"))
            {
                synchronized (usuarios)
                {
					ComunicadoDeDesligamento comunicadoDeDesligamento =
                    new ComunicadoDeDesligamento ();
                    
                    for (ComunicaServerSocket usuario:usuarios)
                    {
                        try
                        {
                            usuario.receba (comunicadoDeDesligamento);
                            usuario.adeus  ();
                        }
                        catch (Exception erro)
                        {}
                    }
                }

                System.out.println ("O servidor foi desativado!\n");
                System.exit(0);
            }
            else
                System.err.println ("Comando inválido!\n");
        }
    }
    
    //não há atributo variável para toString    
    
}
