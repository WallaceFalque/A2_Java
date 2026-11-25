package sistemavendas.view;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sistemavendas.dados.BancoDados;
import sistemavendas.model.Produto;

public class TelaProduto extends javax.swing.JFrame {

    private final DefaultTableModel modelo = new DefaultTableModel(new Object[]{"ID", "Descrição", "Preço (R$)", "Estoque"}, 0) {
        @Override
        public boolean isCellEditable(int linha, int coluna) {
            return false;
        }
    };

    public TelaProduto() {
        initComponents();
        tblProdutos.setModel(modelo);
        Estilo.aplicarCadastro(this, "Cadastro de Produtos", "Controle de produtos, preços e estoque",
                pnlCampos, tblProdutos, pnlBotoes, btnIncluir, btnAlterar, btnExcluir, btnConsultar, btnLimpar);
        carregarTabela();
    }

    private void carregarTabela() {
        modelo.setRowCount(0);
        for (Produto p : BancoDados.PRODUTOS.listar()) {
            modelo.addRow(new Object[]{p.getId(), p.getDescricao(), String.format("%.2f", p.getPreco()), p.getEstoque()});
        }
    }

    private void limpar() {
        txtId.setText("");
        txtDescricao.setText("");
        txtPreco.setText("");
        txtEstoque.setText("");
        tblProdutos.clearSelection();
    }

    private void preencher(Produto p) {
        txtId.setText(String.valueOf(p.getId()));
        txtDescricao.setText(p.getDescricao());
        txtPreco.setText(String.format("%.2f", p.getPreco()));
        txtEstoque.setText(String.valueOf(p.getEstoque()));
    }

    private Produto lerCampos() {
        if (txtDescricao.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Descrição é obrigatória.");
            return null;
        }
        try {
            double preco = Double.parseDouble(txtPreco.getText().trim().replace(",", "."));
            int estoque = Integer.parseInt(txtEstoque.getText().trim());
            if (preco <= 0 || estoque < 0) {
                throw new NumberFormatException();
            }
            return new Produto(txtDescricao.getText().trim(), preco, estoque);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Preço deve ser maior que zero e estoque não pode ser negativo.");
            return null;
        }
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
        lblDescricao = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        lblPreco = new javax.swing.JLabel();
        txtPreco = new javax.swing.JTextField();
        lblEstoque = new javax.swing.JLabel();
        txtEstoque = new javax.swing.JTextField();
        scrTabela = new javax.swing.JScrollPane();
        tblProdutos = new javax.swing.JTable();
        pnlBotoes = new javax.swing.JPanel();
        btnIncluir = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnConsultar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Produtos");
        getContentPane().setLayout(new java.awt.BorderLayout(5, 5));

        pnlCampos.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do Produto"));
        pnlCampos.setLayout(new java.awt.GridLayout(0, 2, 5, 5));
        lblId.setText("ID:");
        pnlCampos.add(lblId);
        pnlCampos.add(txtId);
        lblDescricao.setText("Descrição:");
        pnlCampos.add(lblDescricao);
        pnlCampos.add(txtDescricao);
        lblPreco.setText("Preço (R$):");
        pnlCampos.add(lblPreco);
        pnlCampos.add(txtPreco);
        lblEstoque.setText("Estoque:");
        pnlCampos.add(lblEstoque);
        pnlCampos.add(txtEstoque);

        getContentPane().add(pnlCampos, java.awt.BorderLayout.PAGE_START);
        scrTabela.setPreferredSize(new java.awt.Dimension(600, 250));
        tblProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProdutosMouseClicked(evt);
            }
        });
        scrTabela.setViewportView(tblProdutos);

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
        Produto p = lerCampos();
        if (p == null) {
            return;
        }
        BancoDados.PRODUTOS.inserir(p);
        carregarTabela();
        limpar();
        JOptionPane.showMessageDialog(this, "Produto incluído.");
    }//GEN-LAST:event_btnIncluirActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        Integer id = lerId();
        if (id == null) {
            return;
        }
        Produto p = BancoDados.PRODUTOS.consultar(id);
        if (p == null) {
            JOptionPane.showMessageDialog(this, "Produto não encontrado.");
            return;
        }
        Produto novo = lerCampos();
        if (novo == null) {
            return;
        }
        p.setDescricao(novo.getDescricao());
        p.setPreco(novo.getPreco());
        p.setEstoque(novo.getEstoque());
        BancoDados.PRODUTOS.alterar(p);
        carregarTabela();
        limpar();
        JOptionPane.showMessageDialog(this, "Produto alterado.");
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        Integer id = lerId();
        if (id == null) {
            return;
        }
        if (BancoDados.PRODUTOS.consultar(id) == null) {
            JOptionPane.showMessageDialog(this, "Produto não encontrado.");
            return;
        }
        if (BancoDados.VENDAS.listar().stream().anyMatch(v -> v.getIdProduto() == id)) {
            JOptionPane.showMessageDialog(this, "Produto possui vendas e não pode ser excluído.");
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "Excluir o produto?") == JOptionPane.YES_OPTION) {
            BancoDados.PRODUTOS.excluir(id);
            carregarTabela();
            limpar();
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        Integer id = lerId();
        if (id == null) {
            return;
        }
        Produto p = BancoDados.PRODUTOS.consultar(id);
        if (p == null) {
            JOptionPane.showMessageDialog(this, "Produto não encontrado.");
        } else {
            preencher(p);
        }
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        limpar();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void tblProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdutosMouseClicked
        int linha = tblProdutos.getSelectedRow();
        if (linha >= 0) {
            preencher(BancoDados.PRODUTOS.consultar((int) modelo.getValueAt(linha, 0)));
        }
    }//GEN-LAST:event_tblProdutosMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlCampos;
    private javax.swing.JLabel lblId;
    private javax.swing.JTextField txtId;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JLabel lblPreco;
    private javax.swing.JTextField txtPreco;
    private javax.swing.JLabel lblEstoque;
    private javax.swing.JTextField txtEstoque;
    private javax.swing.JScrollPane scrTabela;
    private javax.swing.JTable tblProdutos;
    private javax.swing.JPanel pnlBotoes;
    private javax.swing.JButton btnIncluir;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnLimpar;
    // End of variables declaration//GEN-END:variables
}
