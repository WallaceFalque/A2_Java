package sistemavendas.view;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sistemavendas.dados.BancoDados;
import sistemavendas.model.Cliente;

public class TelaCliente extends javax.swing.JFrame {

    private final DefaultTableModel modelo = new DefaultTableModel(new Object[]{"ID", "Nome", "CPF", "Telefone"}, 0) {
        @Override
        public boolean isCellEditable(int linha, int coluna) {
            return false;
        }
    };

    public TelaCliente() {
        initComponents();
        tblClientes.setModel(modelo);
        Estilo.aplicarCadastro(this, "Cadastro de Clientes", "Inclua, altere, consulte e exclua os clientes da loja",
                pnlCampos, tblClientes, pnlBotoes, btnIncluir, btnAlterar, btnExcluir, btnConsultar, btnLimpar);
        carregarTabela();
    }

    private void carregarTabela() {
        modelo.setRowCount(0);
        for (Cliente c : BancoDados.CLIENTES.listar()) {
            modelo.addRow(new Object[]{c.getId(), c.getNome(), c.getCpf(), c.getTelefone()});
        }
    }

    private void limpar() {
        txtId.setText("");
        txtNome.setText("");
        txtCpf.setText("");
        txtTelefone.setText("");
        tblClientes.clearSelection();
    }

    private void preencher(Cliente c) {
        txtId.setText(String.valueOf(c.getId()));
        txtNome.setText(c.getNome());
        txtCpf.setText(formatarCpf(c.getCpf()));
        txtTelefone.setText(c.getTelefone());
    }

    private boolean validar() {
        if (txtNome.getText().isBlank() || somenteDigitos(txtCpf.getText()).isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nome e CPF são obrigatórios.");
            return false;
        }
        if (!cpfValido(txtCpf.getText())) {
            JOptionPane.showMessageDialog(this, "CPF inválido. Informe os 11 dígitos de um CPF válido.");
            txtCpf.requestFocus();
            return false;
        }
        return true;
    }

    private static String somenteDigitos(String texto) {
        return texto == null ? "" : texto.replaceAll("\\D", "");
    }

    private static String formatarCpf(String cpf) {
        String d = somenteDigitos(cpf);
        if (d.length() != 11) {
            return cpf;
        }
        return d.substring(0, 3) + "." + d.substring(3, 6) + "." + d.substring(6, 9) + "-" + d.substring(9);
    }

    // Valida o CPF pelos dois dígitos verificadores
    private static boolean cpfValido(String cpf) {
        String d = somenteDigitos(cpf);
        if (d.length() != 11 || d.chars().distinct().count() == 1) {
            return false;
        }
        for (int pos = 9; pos <= 10; pos++) {
            int soma = 0;
            for (int i = 0; i < pos; i++) {
                soma += (d.charAt(i) - '0') * (pos + 1 - i);
            }
            int digito = (soma * 10) % 11 % 10;
            if (digito != d.charAt(pos) - '0') {
                return false;
            }
        }
        return true;
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
        lblNome = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        lblCpf = new javax.swing.JLabel();
        txtCpf = new javax.swing.JFormattedTextField();
        lblTelefone = new javax.swing.JLabel();
        txtTelefone = new javax.swing.JTextField();
        scrTabela = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        pnlBotoes = new javax.swing.JPanel();
        btnIncluir = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnConsultar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Clientes");
        getContentPane().setLayout(new java.awt.BorderLayout(5, 5));

        pnlCampos.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do Cliente"));
        pnlCampos.setLayout(new java.awt.GridLayout(0, 2, 5, 5));
        lblId.setText("ID:");
        pnlCampos.add(lblId);
        pnlCampos.add(txtId);
        lblNome.setText("Nome:");
        pnlCampos.add(lblNome);
        pnlCampos.add(txtNome);
        lblCpf.setText("CPF:");
        pnlCampos.add(lblCpf);

        try {
            txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        pnlCampos.add(txtCpf);
        lblTelefone.setText("Telefone:");
        pnlCampos.add(lblTelefone);
        pnlCampos.add(txtTelefone);

        getContentPane().add(pnlCampos, java.awt.BorderLayout.PAGE_START);
        scrTabela.setPreferredSize(new java.awt.Dimension(600, 250));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        scrTabela.setViewportView(tblClientes);

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
        if (!validar()) {
            return;
        }
        BancoDados.CLIENTES.inserir(new Cliente(txtNome.getText().trim(), txtCpf.getText().trim(), txtTelefone.getText().trim()));
        carregarTabela();
        limpar();
        JOptionPane.showMessageDialog(this, "Cliente incluído.");
    }//GEN-LAST:event_btnIncluirActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        Integer id = lerId();
        if (id == null || !validar()) {
            return;
        }
        Cliente c = BancoDados.CLIENTES.consultar(id);
        if (c == null) {
            JOptionPane.showMessageDialog(this, "Cliente não encontrado.");
            return;
        }
        c.setNome(txtNome.getText().trim());
        c.setCpf(txtCpf.getText().trim());
        c.setTelefone(txtTelefone.getText().trim());
        BancoDados.CLIENTES.alterar(c);
        carregarTabela();
        limpar();
        JOptionPane.showMessageDialog(this, "Cliente alterado.");
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        Integer id = lerId();
        if (id == null) {
            return;
        }
        if (BancoDados.CLIENTES.consultar(id) == null) {
            JOptionPane.showMessageDialog(this, "Cliente não encontrado.");
            return;
        }
        if (BancoDados.VENDAS.listar().stream().anyMatch(v -> v.getIdCliente() == id)) {
            JOptionPane.showMessageDialog(this, "Cliente possui vendas e não pode ser excluído.");
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "Excluir o cliente?") == JOptionPane.YES_OPTION) {
            BancoDados.CLIENTES.excluir(id);
            carregarTabela();
            limpar();
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        Integer id = lerId();
        if (id == null) {
            return;
        }
        Cliente c = BancoDados.CLIENTES.consultar(id);
        if (c == null) {
            JOptionPane.showMessageDialog(this, "Cliente não encontrado.");
        } else {
            preencher(c);
        }
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        limpar();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        int linha = tblClientes.getSelectedRow();
        if (linha >= 0) {
            preencher(BancoDados.CLIENTES.consultar((int) modelo.getValueAt(linha, 0)));
        }
    }//GEN-LAST:event_tblClientesMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlCampos;
    private javax.swing.JLabel lblId;
    private javax.swing.JTextField txtId;
    private javax.swing.JLabel lblNome;
    private javax.swing.JTextField txtNome;
    private javax.swing.JLabel lblCpf;
    private javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JTextField txtTelefone;
    private javax.swing.JScrollPane scrTabela;
    private javax.swing.JTable tblClientes;
    private javax.swing.JPanel pnlBotoes;
    private javax.swing.JButton btnIncluir;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnLimpar;
    // End of variables declaration//GEN-END:variables
}
