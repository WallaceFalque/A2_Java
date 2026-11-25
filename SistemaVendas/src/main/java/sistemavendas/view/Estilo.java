package sistemavendas.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 * Centraliza cores, fontes e o acabamento visual das telas do sistema.
 */
public final class Estilo {

    public static final Color AZUL_ESCURO = new Color(30, 58, 95);
    public static final Color AZUL = new Color(41, 98, 160);
    public static final Color VERDE = new Color(46, 139, 87);
    public static final Color VERMELHO = new Color(192, 57, 43);
    public static final Color CINZA = new Color(108, 117, 125);
    public static final Color FUNDO = new Color(244, 246, 249);
    public static final Color LINHA_ALTERNADA = new Color(235, 241, 248);
    public static final Color SELECAO = new Color(200, 220, 245);
    public static final Color TEXTO_CLARO = new Color(200, 215, 235);

    public static final Font FONTE = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONTE_NEGRITO = new Font("Segoe UI", Font.BOLD, 13);

    private Estilo() {
    }

    /** Configura o tema (Nimbus) do sistema todo. Deve ser chamado antes de abrir a primeira tela. */
    public static void configurarTema() {
        try {
            UIManager.put("control", FUNDO);
            UIManager.put("nimbusBase", AZUL_ESCURO);
            UIManager.put("nimbusBlueGrey", new Color(170, 180, 195));
            UIManager.put("nimbusFocus", AZUL);
            UIManager.put("nimbusSelectionBackground", AZUL);
            UIManager.put("defaultFont", FONTE);
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // se o Nimbus não estiver disponível, segue com o tema padrão do Java
        }
    }

    /** Faixa azul com título e subtítulo usada no topo das telas. */
    public static JPanel criarCabecalho(String titulo, String subtitulo) {
        JPanel cabecalho = new JPanel(new GridLayout(2, 1));
        cabecalho.setBackground(AZUL_ESCURO);
        cabecalho.setBorder(BorderFactory.createEmptyBorder(14, 20, 14, 20));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setForeground(Color.WHITE);

        JLabel lblSubtitulo = new JLabel(subtitulo);
        lblSubtitulo.setFont(FONTE);
        lblSubtitulo.setForeground(TEXTO_CLARO);

        cabecalho.add(lblTitulo);
        cabecalho.add(lblSubtitulo);
        return cabecalho;
    }

    /**
     * Aplica o visual padrão nas telas de cadastro (Cliente, Produto e Venda),
     * que seguem a mesma estrutura: painel de campos, tabela e painel de botões.
     */
    public static void aplicarCadastro(JFrame tela, String titulo, String subtitulo, JPanel pnlCampos,
            JTable tabela, JPanel pnlBotoes, JButton btnIncluir, JButton btnAlterar,
            JButton btnExcluir, JButton btnConsultar, JButton btnLimpar) {

        tela.getContentPane().setBackground(FUNDO);

        // Topo: cabeçalho + campos
        tela.getContentPane().remove(pnlCampos);
        organizarCampos(pnlCampos);
        JPanel topo = new JPanel(new BorderLayout(0, 10));
        topo.setOpaque(false);
        topo.add(criarCabecalho(titulo, subtitulo), BorderLayout.NORTH);
        JPanel margemCampos = new JPanel(new BorderLayout());
        margemCampos.setOpaque(false);
        margemCampos.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 12));
        margemCampos.add(pnlCampos);
        topo.add(margemCampos, BorderLayout.CENTER);
        tela.getContentPane().add(topo, BorderLayout.PAGE_START);

        // Centro: tabela
        estilizarTabela(tabela);
        JComponent scroll = (JComponent) tabela.getParent().getParent();
        scroll.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 12, 0, 12),
                BorderFactory.createLineBorder(new Color(210, 215, 222))));
        scroll.setPreferredSize(new Dimension(720, 260));

        // Rodapé: botões + contador de registros
        estilizarBotao(btnIncluir, VERDE);
        estilizarBotao(btnAlterar, AZUL);
        estilizarBotao(btnExcluir, VERMELHO);
        estilizarBotao(btnConsultar, AZUL_ESCURO);
        estilizarBotao(btnLimpar, CINZA);
        btnIncluir.setToolTipText("Cadastra um novo registro com os dados informados");
        btnAlterar.setToolTipText("Altera o registro do ID informado");
        btnExcluir.setToolTipText("Exclui o registro do ID informado");
        btnConsultar.setToolTipText("Busca o registro pelo ID informado");
        btnLimpar.setToolTipText("Limpa os campos da tela");

        tela.getContentPane().remove(pnlBotoes);
        pnlBotoes.setOpaque(false);
        JLabel lblTotal = new JLabel();
        lblTotal.setForeground(CINZA);
        Runnable atualizarTotal = () -> lblTotal.setText(tabela.getRowCount() + " registro(s)");
        tabela.getModel().addTableModelListener(e -> atualizarTotal.run());
        atualizarTotal.run();

        JPanel rodape = new JPanel(new BorderLayout());
        rodape.setOpaque(false);
        rodape.setBorder(BorderFactory.createEmptyBorder(4, 14, 8, 14));
        rodape.add(lblTotal, BorderLayout.WEST);
        rodape.add(pnlBotoes, BorderLayout.EAST);
        tela.getContentPane().add(rodape, BorderLayout.PAGE_END);

        tela.pack();
        tela.setMinimumSize(tela.getSize());
        tela.setLocationRelativeTo(null);
    }

    /** Reorganiza os pares rótulo/campo em duas colunas, com o rótulo à esquerda do campo. */
    private static void organizarCampos(JPanel pnlCampos) {
        Component[] componentes = pnlCampos.getComponents();
        pnlCampos.removeAll();
        pnlCampos.setLayout(new GridBagLayout());
        pnlCampos.setBackground(Color.WHITE);
        TitledBorder borda = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(210, 215, 222)),
                ((TitledBorder) pnlCampos.getBorder()).getTitle());
        borda.setTitleFont(FONTE_NEGRITO);
        borda.setTitleColor(AZUL_ESCURO);
        pnlCampos.setBorder(BorderFactory.createCompoundBorder(borda,
                BorderFactory.createEmptyBorder(6, 10, 10, 10)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 6, 5, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        for (int i = 0; i + 1 < componentes.length; i += 2) {
            int par = i / 2;
            gbc.gridy = par / 2;

            gbc.gridx = (par % 2) * 2;
            gbc.weightx = 0;
            JLabel rotulo = (JLabel) componentes[i];
            rotulo.setFont(FONTE_NEGRITO);
            rotulo.setForeground(new Color(60, 70, 85));
            rotulo.setHorizontalAlignment(SwingConstants.RIGHT);
            pnlCampos.add(rotulo, gbc);

            gbc.gridx++;
            gbc.weightx = 1;
            componentes[i + 1].setPreferredSize(new Dimension(220, 28));
            pnlCampos.add(componentes[i + 1], gbc);
        }
    }

    public static void estilizarTabela(JTable tabela) {
        tabela.setRowHeight(26);
        tabela.setShowVerticalLines(false);
        tabela.setGridColor(new Color(225, 230, 236));
        tabela.setSelectionBackground(SELECAO);
        tabela.setSelectionForeground(Color.BLACK);
        tabela.setFillsViewportHeight(true);
        tabela.setBackground(Color.WHITE);
        tabela.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JTableHeader cabecalho = tabela.getTableHeader();
        cabecalho.setReorderingAllowed(false);
        cabecalho.setPreferredSize(new Dimension(0, 30));
        cabecalho.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object valor, boolean selecionado,
                    boolean foco, int linha, int coluna) {
                super.getTableCellRendererComponent(t, valor, false, false, linha, coluna);
                setOpaque(true);
                setBackground(AZUL_ESCURO);
                setForeground(Color.WHITE);
                setFont(FONTE_NEGRITO);
                setHorizontalAlignment(SwingConstants.CENTER);
                setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, AZUL));
                return this;
            }
        });

        tabela.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object valor, boolean selecionado,
                    boolean foco, int linha, int coluna) {
                super.getTableCellRendererComponent(t, valor, selecionado, false, linha, coluna);
                if (!selecionado) {
                    setBackground(linha % 2 == 0 ? Color.WHITE : LINHA_ALTERNADA);
                }
                setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
                String nomeColuna = t.getColumnName(coluna);
                if (nomeColuna.contains("R$")) {
                    setHorizontalAlignment(SwingConstants.RIGHT);
                } else if (nomeColuna.equals("ID") || nomeColuna.equals("Qtd")
                        || nomeColuna.equals("Estoque") || nomeColuna.equals("Data")) {
                    setHorizontalAlignment(SwingConstants.CENTER);
                } else {
                    setHorizontalAlignment(SwingConstants.LEFT);
                }
                return this;
            }
        });
    }

    public static void estilizarBotao(JButton botao, Color cor) {
        botao.setBackground(cor);
        botao.setForeground(Color.WHITE);
        botao.setFont(FONTE_NEGRITO);
        botao.setFocusPainted(false);
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botao.setPreferredSize(new Dimension(110, 34));
    }
}
