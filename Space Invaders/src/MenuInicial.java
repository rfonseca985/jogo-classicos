import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuInicial extends JFrame {

    private Jogo jogo = new Jogo();
    public MenuInicial() {
        // Configurações básicas do frame
        setTitle("Spacer Invaders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Centraliza o frame na tela

        // Layout para o painel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Título do jogo
        JLabel titleLabel = new JLabel("Spacer Invaders");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        // Painel para os botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 0, 20));

        // Botão "Jogar"
        JButton jogarButton = new JButton("Jogar");
        jogarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarJogo(); // Adicione aqui a lógica para iniciar o jogo
            }
        });
        buttonPanel.add(jogarButton);

        // Botão "Créditos"
        JButton creditosButton = new JButton("Créditos");
        creditosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exibirCreditos(); // Adicione aqui a lógica para exibir os créditos
            }
        });
        buttonPanel.add(creditosButton);

        // Adiciona o painel de botões ao centro
        panel.add(buttonPanel, BorderLayout.CENTER);

        // Adiciona o painel ao frame
        add(panel);

        setVisible(true);
    }

    private void iniciarJogo() {
        // Fecha a janela do menu
        dispose();

        // Cria e executa um SwingWorker para iniciar o jogo em segundo plano
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                jogo.iniciarJogo();
                return null;
            }
        };

        // Adiciona um listener para a conclusão do SwingWorker
        worker.addPropertyChangeListener(evt -> {
            if (SwingWorker.StateValue.DONE == evt.getNewValue()) {
                // Exibe a caixa de diálogo após o término do jogo
                JOptionPane.showMessageDialog(null, "Jogo concluído!");
                // Termina o programa
                System.exit(0);
            }
        });

        // Executa o SwingWorker
        worker.execute();

        JOptionPane.showMessageDialog(this, "Iniciar Jogo!");
    }

    private void exibirCreditos() {
        // Adicione aqui a lógica para exibir os créditos
        JOptionPane.showMessageDialog(this, "Desenvolvido por Rafael Fonseca\n " +
                "Link do projeto no github:https://github.com/rfonseca985/jogo-classicos");
    }

    public static void main(String[] args) {
        // Crie uma instância do MenuInicial ao iniciar o programa
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MenuInicial();
            }
        });
    }
}
