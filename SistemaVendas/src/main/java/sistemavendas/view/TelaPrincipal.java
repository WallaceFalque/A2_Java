package sistemavendas.view;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import sistemavendas.dados.BancoDados;

public class TelaPrincipal extends javax.swing.JFrame {

    private final JLabel lblResumo = new JLabel();

    public TelaPrincipal() {
        initComponents();
        aplicarVisual();
    }

    private void aplicarVisual() {
        getContentPane().setBackground(Estilo.FUNDO);
        getContentPane().remove(lblTitulo);
        getContentPane().add(Estilo.criarCabecalho(lblTitulo.getText(), "Controle de clientes, produtos e vendas"),
                BorderLayout.PAGE_START);

        pnlMenu.setOpaque(false);
        pnlMenu.setBorder(BorderFactory.createEmptyBorder(16, 24, 8, 24));
        pnlMenu.setPreferredSize(null);
        ((java.awt.GridLayout) pnlMenu.getLayout()).setVgap(12);
        estilizarOpcao(btnClientes, "Clientes", "Cadastro, alteração, consulta e exclusão", Estilo.AZUL);
        estilizarOpcao(btnProdutos, "Produtos", "Produtos, preços e estoque", Estilo.AZUL);
        estilizarOpcao(btnVendas, "Vendas", "Tela de negócio: registrar vendas e baixar estoque", Estilo.VERDE);

        lblResumo.setForeground(Estilo.CINZA);
        JLabel lblData = new JLabel(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        lblData.setForeground(Estilo.CINZA);
        JPanel rodape = new JPanel(new BorderLayout());
        rodape.setOpaque(false);
        rodape.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(215, 220, 228)),
                BorderFactory.createEmptyBorder(8, 16, 8, 16)));
        rodape.add(lblResumo, BorderLayout.WEST);
        rodape.add(lblData, BorderLayout.EAST);
        getContentPane().add(rodape, BorderLayout.PAGE_END);
        atualizarResumo();

        // Atualiza os totais sempre que o usuário volta para o menu
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                atualizarResumo();
            }
        });

        pack();
        setMinimumSize(getSize());
        setLocationRelativeTo(null);
    }

    private void estilizarOpcao(JButton botao, String titulo, String descricao, java.awt.Color cor) {
        Estilo.estilizarBotao(botao, cor);
        botao.setPreferredSize(new java.awt.Dimension(380, 64));
        botao.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        botao.setText("<html><span style='font-size:13pt'><b>" + titulo + "</b></span><br>"
                + "<span style='font-size:10pt'>" + descricao + "</span></html>");
    }

    private void atualizarResumo() {
        lblResumo.setText("Clientes: " + BancoDados.CLIENTES.listar().size()
                + "   |   Produtos: " + BancoDados.PRODUTOS.listar().size()
                + "   |   Vendas: " + BancoDados.VENDAS.listar().size());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        pnlMenu = new javax.swing.JPanel();
        btnClientes = new javax.swing.JButton();
        btnProdutos = new javax.swing.JButton();
        btnVendas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Vendas");
        getContentPane().setLayout(new java.awt.BorderLayout(5, 5));

        lblTitulo.setText("Sistema de Vendas");
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 20));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        getContentPane().add(lblTitulo, java.awt.BorderLayout.PAGE_START);
        pnlMenu.setPreferredSize(new java.awt.Dimension(320, 160));
        pnlMenu.setLayout(new java.awt.GridLayout(3, 1, 5, 5));
        btnClientes.setText("Cadastro de Clientes");
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });
        pnlMenu.add(btnClientes);
        btnProdutos.setText("Cadastro de Produtos");
        btnProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProdutosActionPerformed(evt);
            }
        });
        pnlMenu.add(btnProdutos);
        btnVendas.setText("Vendas (Tela de Negócio)");
        btnVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVendasActionPerformed(evt);
            }
        });
        pnlMenu.add(btnVendas);

        getContentPane().add(pnlMenu, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        new TelaCliente().setVisible(true);
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProdutosActionPerformed
        new TelaProduto().setVisible(true);
    }//GEN-LAST:event_btnProdutosActionPerformed

    private void btnVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVendasActionPerformed
        new TelaVenda().setVisible(true);
    }//GEN-LAST:event_btnVendasActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnProdutos;
    private javax.swing.JButton btnVendas;
    // End of variables declaration//GEN-END:variables
}
