package sistemavendas.model;

import java.time.LocalDate;

public class Venda extends Entidade {

    private int idCliente;
    private int idProduto;
    private int quantidade;
    private double valorTotal;
    private LocalDate data;

    public Venda(int idCliente, int idProduto, int quantidade) {
        this.idCliente = idCliente;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.data = LocalDate.now();
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDate getData() {
        return data;
    }
}
