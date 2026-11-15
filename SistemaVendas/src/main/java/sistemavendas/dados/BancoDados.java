package sistemavendas.dados;

import sistemavendas.model.Cliente;
import sistemavendas.model.Produto;
import sistemavendas.model.Venda;

public class BancoDados {

    public static final Tabela<Cliente> CLIENTES = Tabela.abrir("clientes");
    public static final Tabela<Produto> PRODUTOS = Tabela.abrir("produtos");
    public static final Tabela<Venda> VENDAS = Tabela.abrir("vendas");

    private BancoDados() {
    }
}
