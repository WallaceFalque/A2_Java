package sistemavendas.view;


public class TelaPrincipal extends javax.swing.JFrame {

    public TelaPrincipal() {
        initComponents();
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
