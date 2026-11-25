package sistemavendas.view;

import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sistemavendas.dados.BancoDados;
import sistemavendas.model.Cliente;
import sistemavendas.model.Produto;
import sistemavendas.model.Venda;

public class TelaVenda extends javax.swing.JFrame {

    private final DefaultTableModel modelo = new DefaultTableModel(new Object[]{"ID", "Cliente", "Produto", "Qtd", "Total (R$)", "Data"}, 0) {
        @Override
        public boolean isCellEditable(int linha, int coluna) {
            return false;
        }
    };

    public TelaVenda() {
        initComponents();
        tblVendas.setModel(modelo);
        Estilo.aplicarCadastro(this, "Vendas", "Registre vendas: o estoque do produto é atualizado automaticamente",
                pnlCampos, tblVendas, pnlBotoes, btnIncluir, btnAlterar, btnExcluir, btnConsultar, btnLimpar);
        btnIncluir.setText("Vender");
        txtTotal.setFont(Estilo.FONTE_NEGRITO);
        txtTotal.setForeground(Estilo.VERDE);
        limpar();
    }

    private void carregarTabela() {
        modelo.setRowCount(0);
        for (Venda v : BancoDados.VENDAS.listar()) {
            Cliente c = BancoDados.CLIENTES.consultar(v.getIdCliente());
            Produto p = BancoDados.PRODUTOS.consultar(v.getIdProduto());
            modelo.addRow(new Object[]{v.getId(), c.getNome(), p.getDescricao(), v.getQuantidade(),
                String.format("%.2f", v.getValorTotal()), v.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))});
        }
    }

    private void limpar() {
        cbCliente.removeAllItems();
        BancoDados.CLIENTES.listar().forEach(cbCliente::addItem);
        cbProduto.removeAllItems();
        BancoDados.PRODUTOS.listar().forEach(cbProduto::addItem);
        txtId.setText("");
        txtQuantidade.setText("");
        txtTotal.setText("");
        carregarTabela();
    }

    private void preencher(Venda v) {
        txtId.setText(String.valueOf(v.getId()));
        cbCliente.setSelectedItem(BancoDados.CLIENTES.consultar(v.getIdCliente()));
        cbProduto.setSelectedItem(BancoDados.PRODUTOS.consultar(v.getIdProduto()));
        txtQuantidade.setText(String.valueOf(v.getQuantidade()));
        txtTotal.setText(String.format("%.2f", v.getValorTotal()));
    }

    private int lerQuantidade() {
        try {
            int qtd = Integer.parseInt(txtQuantidade.getText().trim());
            if (qtd > 0) {
                return qtd;
            }
        } catch (NumberFormatException e) {
        }
        JOptionPane.showMessageDialog(this, "Informe uma quantidade maior que zero.");
        return 0;
    }

    private boolean validar(Cliente c, Produto p, int qtd, int disponivel) {
        if (c == null || p == null) {
            JOptionPane.showMessageDialog(this, "Selecione o cliente e o produto.");
            return false;
        }
        if (qtd == 0) {
            return false;
        }
        if (qtd > disponivel) {
            JOptionPane.showMessageDialog(this, "Estoque insuficiente. Disponível: " + disponivel);
            return false;
        }
        return true;
    }

    private void baixarEstoque(Produto p, int qtd) {
        p.setEstoque(p.getEstoque() - qtd);
        BancoDados.PRODUTOS.alterar(p);
    }

    private Integer lerId() {
        try {
            return Integer.valueOf(txtId.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Informe um ID válido.");
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCampos = new javax.swing.JPanel();
        lblId = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        lblCliente = new javax.swing.JLabel();
        cbCliente = new javax.swing.JComboBox<>();
        lblProduto = new javax.swing.JLabel();
        cbProduto = new javax.swing.JComboBox<>();
        lblQuantidade = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JTextField();
        lblTotal = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        scrTabela = new javax.swing.JScrollPane();
        tblVendas = new javax.swing.JTable();
        pnlBotoes = new javax.swing.JPanel();
        btnIncluir = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnConsultar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Vendas (Tela de Negócio)");
        getContentPane().setLayout(new java.awt.BorderLayout(5, 5));

        pnlCampos.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados da Venda"));
        pnlCampos.setLayout(new java.awt.GridLayout(0, 2, 5, 5));
        lblId.setText("ID:");
        pnlCampos.add(lblId);
        pnlCampos.add(txtId);
        lblCliente.setText("Cliente:");
        pnlCampos.add(lblCliente);
        pnlCampos.add(cbCliente);
        lblProduto.setText("Produto:");
        pnlCampos.add(lblProduto);
        pnlCampos.add(cbProduto);
        lblQuantidade.setText("Quantidade:");
        pnlCampos.add(lblQuantidade);
        pnlCampos.add(txtQuantidade);
        lblTotal.setText("Valor total (R$):");
        pnlCampos.add(lblTotal);
        txtTotal.setEditable(false);
        pnlCampos.add(txtTotal);

        getContentPane().add(pnlCampos, java.awt.BorderLayout.PAGE_START);
        scrTabela.setPreferredSize(new java.awt.Dimension(600, 250));
        tblVendas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVendasMouseClicked(evt);
            }
        });
        scrTabela.setViewportView(tblVendas);

        getContentPane().add(scrTabela, java.awt.BorderLayout.CENTER);
        pnlBotoes.setLayout(new java.awt.FlowLayout());
        btnIncluir.setText("Incluir");
        btnIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirActionPerformed(evt);
            }
        });
        pnlBotoes.add(btnIncluir);
        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });
        pnlBotoes.add(btnAlterar);
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        pnlBotoes.add(btnExcluir);
        btnConsultar.setText("Consultar");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
        pnlBotoes.add(btnConsultar);
        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });
        pnlBotoes.add(btnLimpar);

        getContentPane().add(pnlBotoes, java.awt.BorderLayout.PAGE_END);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirActionPerformed
        Cliente c = (Cliente) cbCliente.getSelectedItem();
        Produto p = (Produto) cbProduto.getSelectedItem();
        int qtd = lerQuantidade();
        if (!validar(c, p, qtd, p == null ? 0 : p.getEstoque())) {
            return;
        }
        Venda v = new Venda(c.getId(), p.getId(), qtd);
        v.setValorTotal(p.getPreco() * qtd);
        baixarEstoque(p, qtd);
        BancoDados.VENDAS.inserir(v);
        limpar();
        JOptionPane.showMessageDialog(this, String.format("Venda registrada. Total: R$ %.2f", v.getValorTotal()));
    }//GEN-LAST:event_btnIncluirActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        Integer id = lerId();
        if (id == null) {
            return;
        }
        Venda v = BancoDados.VENDAS.consultar(id);
        if (v == null) {
            JOptionPane.showMessageDialog(this, "Venda não encontrada.");
            return;
        }
        Cliente c = (Cliente) cbCliente.getSelectedItem();
        Produto p = (Produto) cbProduto.getSelectedItem();
        int qtd = lerQuantidade();
        int disponivel = p == null ? 0 : p.getEstoque() + (p.getId() == v.getIdProduto() ? v.getQuantidade() : 0);
        if (!validar(c, p, qtd, disponivel)) {
            return;
        }
        baixarEstoque(BancoDados.PRODUTOS.consultar(v.getIdProduto()), -v.getQuantidade());
        baixarEstoque(p, qtd);
        v.setIdCliente(c.getId());
        v.setIdProduto(p.getId());
        v.setQuantidade(qtd);
        v.setValorTotal(p.getPreco() * qtd);
        BancoDados.VENDAS.alterar(v);
        limpar();
        JOptionPane.showMessageDialog(this, "Venda alterada.");
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        Integer id = lerId();
        if (id == null) {
            return;
        }
        Venda v = BancoDados.VENDAS.consultar(id);
        if (v == null) {
            JOptionPane.showMessageDialog(this, "Venda não encontrada.");
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "Excluir a venda? O estoque será devolvido.") == JOptionPane.YES_OPTION) {
            baixarEstoque(BancoDados.PRODUTOS.consultar(v.getIdProduto()), -v.getQuantidade());
            BancoDados.VENDAS.excluir(id);
            limpar();
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        Integer id = lerId();
        if (id == null) {
            return;
        }
        Venda v = BancoDados.VENDAS.consultar(id);
        if (v == null) {
            JOptionPane.showMessageDialog(this, "Venda não encontrada.");
        } else {
            preencher(v);
        }
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        limpar();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void tblVendasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVendasMouseClicked
        int linha = tblVendas.getSelectedRow();
        if (linha >= 0) {
            preencher(BancoDados.VENDAS.consultar((int) modelo.getValueAt(linha, 0)));
        }
    }//GEN-LAST:event_tblVendasMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlCampos;
    private javax.swing.JLabel lblId;
    private javax.swing.JTextField txtId;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JComboBox<Cliente> cbCliente;
    private javax.swing.JLabel lblProduto;
    private javax.swing.JComboBox<Produto> cbProduto;
    private javax.swing.JLabel lblQuantidade;
    private javax.swing.JTextField txtQuantidade;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JScrollPane scrTabela;
    private javax.swing.JTable tblVendas;
    private javax.swing.JPanel pnlBotoes;
    private javax.swing.JButton btnIncluir;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnLimpar;
    // End of variables declaration//GEN-END:variables
}
