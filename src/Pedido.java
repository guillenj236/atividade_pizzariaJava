
public class Pedido {
    private String nomePedido;
    private boolean ativo;
    private boolean finalizado;

    private int numPedido;

    public Pedido (String nome, String endereco){}

    public Pedido() {

    }

    public int getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(int numPedido) {
        this.numPedido = numPedido;
    }

    public Pedido(String nomePedido, int numPedido, boolean ativo, boolean finalizado) {
        this.nomePedido = nomePedido;
        this.numPedido = numPedido;
        this.ativo = ativo;
        this.finalizado = finalizado;
    }


    public String getNomePedido() {
        return nomePedido;
    }

    public void setNomePedido(String nomePedido) {
        this.nomePedido = nomePedido;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

}