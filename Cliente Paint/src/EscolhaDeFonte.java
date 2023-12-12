

import javax.swing.*;
import java.awt.*;

public class EscolhaDeFonte {
    private String fonteSelecionada;
    private boolean italicoSelecionado;
    private boolean negritoSelecionado;
    private int tamanhoSelecionado;

    public Font escolherFonte() {
        String[] fontesDisponiveis = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        Object[] opcoes = {"OK", "Cancelar"};
        int resultado = escolherFonteDialog(fontesDisponiveis, opcoes);

        if (resultado == JOptionPane.OK_OPTION) {
            int estilo = Font.PLAIN;
            if (italicoSelecionado) estilo |= Font.ITALIC;
            if (negritoSelecionado) estilo |= Font.BOLD;

            return new Font(fonteSelecionada, estilo, tamanhoSelecionado);
        } else {
            // Cancelou a seleção
            return null;
        }
    }

    private int escolherFonteDialog(String[] fontesDisponiveis, Object[] opcoes) {
        JPanel panel = new JPanel();
        JComboBox<String> fonteComboBox = new JComboBox<>(fontesDisponiveis);
        JComboBox<String> tamanhoComboBox = new JComboBox<>(new String[]{"12", "14", "16", "18", "20", "24", "28", "32"});
        JCheckBox italicoCheckBox = new JCheckBox("Italico");
        JCheckBox negritoCheckBox = new JCheckBox("Negrito");

        panel.setLayout(new GridLayout(0, 1));
        panel.add(new JLabel("Escolha a fonte:"));
        panel.add(fonteComboBox);
        panel.add(new JLabel("Escolha o tamanho:"));
        panel.add(tamanhoComboBox);
        panel.add(italicoCheckBox);
        panel.add(negritoCheckBox);

        int resultado = JOptionPane.showOptionDialog(
                null,
                panel,
                "Seletor de Fonte",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcoes,
                opcoes[0]);

        if (resultado == JOptionPane.OK_OPTION) {
            fonteSelecionada = (String) fonteComboBox.getSelectedItem();
            italicoSelecionado = italicoCheckBox.isSelected();
            negritoSelecionado = negritoCheckBox.isSelected();
            tamanhoSelecionado = Integer.parseInt((String) tamanhoComboBox.getSelectedItem());
        }

        return resultado;
    }
}
