import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente {
    private String nome;
    private List<Endereco> enderecos = new ArrayList<>();

    private List<Pedido> pedidos = new ArrayList<>();

    public List<Cliente> clientes = new ArrayList<>();

    int finalizadosTotais = 0;

    int emAndamentoTotais = 0;

    Scanner scanner = new Scanner(System.in);



    public Cliente(String nome, List<Endereco> enderecos) {
        this.nome = nome;
        this.enderecos = enderecos;
    }

    public Cliente() {
    }

    public void cadastrarCliente() {

        System.out.println("\nPara fazer um pedido, insira os dados abaixo:\n");

        System.out.println("Nome completo: ");
        String nome = scanner.next();

        System.out.println("Digite sua rua: ");
        String rua = scanner.next();

        System.out.println("Digite seu bairro: ");
        String bairro = scanner.next();

        System.out.println("Digite o número da casa: ");
        int numCasa = scanner.nextInt();

        Cliente novoCliente = new Cliente(nome, enderecos);
        clientes.add(novoCliente);


        Endereco novoEndereco = new Endereco(rua, bairro, numCasa);
        enderecos.add(novoEndereco);

        String nomePedido = "";
        int pedidoOpcao;
        do {
            System.out.println("Digite o nº da opção: ");
            System.out.println("O cardápio de hoje é: ");
            System.out.println("1 - Pizza de calabresa");
            System.out.println("2 - Pizza de frango com catupiry");
            System.out.println("3 - Batata-frita");

            pedidoOpcao = scanner.nextInt();


            switch (pedidoOpcao) {
                case 1:
                    nomePedido = "Pizza de Calabresa";
                    System.out.println("O seu pedido de Pizza de calabresa foi realizado!");
                    break;

                case 2:
                    nomePedido = "Pizza de frango com catupiry";
                    System.out.println("O seu pedido de Pizza de frango com catupiry foi realizado!");
                    break;

                case 3:
                    nomePedido = "Batata-frita";
                    System.out.println("O seu pedido de Batata-Frita foi realizado!");
                    break;

                default:
                    System.out.println("Digite uma opção válida");
                    break;
            }
        } while (pedidoOpcao > 3);

        boolean verificaNum = false;

        System.out.println("Por último, digite o nº do pedido: ");
        int numPedido = scanner.nextInt();
        for (int i = 0; i < pedidos.size(); i++) {
            if (numPedido == pedidos.get(i).getNumPedido()) {
                System.out.println("Digite outro número, pedido já existente");
                verificaNum = true;
            }
        }
        if (!verificaNum){
            System.out.println("Pedido realizado com sucesso, muito obrigado!");
        }

        Pedido pedido = new Pedido(nomePedido, numPedido, false, false);
        pedidos.add(pedido);
    }

    public void alterarStatusPedido(List<Cliente> clientes, List<Endereco> enderecos, List<Pedido> pedidos) {
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println("Nome: " + clientes.get(i).getNome());
            System.out.println("Rua: " + enderecos.get(i).getRua());
            System.out.println("Bairro: " + enderecos.get(i).getBairro());
            System.out.println("Nº casa: " + enderecos.get(i).getNumero());

            System.out.println("Nº pedido: " + pedidos.get(i).getNumPedido());
            System.out.println("Descrição do pedido: " + pedidos.get(i).getNomePedido());

            if (!pedidos.get(i).isAtivo() && !pedidos.get(i).isFinalizado()) {
                System.out.println("Status: Pedido realizado");
            }

            if (pedidos.get(i).isAtivo()) {
                System.out.println("Status: Em andamento");
            }

            if (pedidos.get(i).isFinalizado()) {
                System.out.println("Status: Finalizado");
            }

            System.out.println("");


        }
        finalizarPedido(pedidos);
    }

    public void finalizarPedido(List<Pedido> pedidos) {
        int opcao;

        if (pedidos.size() == 0) {
            System.out.println("\nSem clientes ou pedidos cadastrados\n");
        } else {

            do {
                System.out.println("1 - Colocar pedido em produção:  ");
                System.out.println("2 - Finalizar pedido: ");
                System.out.println("3 - Sair");
                opcao = scanner.nextInt();


                switch (opcao) {

                    case 1:

                        boolean verificaEmAndamento = false;

                        System.out.println("Digite o número do pedido que deseja colocar em produção:  ");
                        int numeroAtendimento = scanner.nextInt();
                        for (int i = 0; i < pedidos.size(); i++) {
                            if (numeroAtendimento == pedidos.get(i).getNumPedido()) {
                                System.out.println("Pedido " + pedidos.get(i).getNumPedido() + " em produção!");
                                emAndamentoTotais++;
                                pedidos.get(i).setAtivo(true);
                                pedidos.get(i).setFinalizado(false);
                                verificaEmAndamento = true;
                            }
                        }
                        if (!verificaEmAndamento) {
                            System.out.println("O número do pedido não existe");
                        }
                        break;

                    case 2:
                        boolean verificaFinalizado = false;

                        System.out.println("Digite o número do pedido que deseja finalizar");
                        int numeroFinalizar = scanner.nextInt();
                        for (int i = 0; i < pedidos.size(); i++) {
                            if (numeroFinalizar == pedidos.get(i).getNumPedido()) {
                                System.out.println("Pedido " + pedidos.get(i).getNumPedido() + " finalizado!");
                                finalizadosTotais++;
                                pedidos.get(i).setAtivo(false);
                                pedidos.get(i).setFinalizado(true);
                                verificaFinalizado = true;
                                salvarFinalizados(pedidos,clientes,enderecos);
                            }
                        }
                        if (!verificaFinalizado) {
                            System.out.println("O número do pedido não existe");
                        }

                        break;

                    case 3:
                        break;
                    default:
                        System.out.println("Digite uma opção válida");
                }

            } while (opcao >= 4);
        }
    }

    public void quantidadePedidos(List<Pedido> pedidos, List<Cliente> clientes) {
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println("Nome: " + clientes.get(i).getNome());
            System.out.println("Rua: " + enderecos.get(i).getRua());
            System.out.println("Bairro: " + enderecos.get(i).getBairro());
            System.out.println("Nº casa: " + enderecos.get(i).getNumero());

            System.out.println("Nº pedido: " + pedidos.get(i).getNumPedido());
            System.out.println("Descrição do pedido: " + pedidos.get(i).getNomePedido());

            if (!pedidos.get(i).isAtivo() && !pedidos.get(i).isFinalizado()) {
                System.out.println("Status: Pedido realizado");
            }

            if (pedidos.get(i).isAtivo()) {
                System.out.println("Status: Em andamento");
            }

            if (pedidos.get(i).isFinalizado()) {
                System.out.println("Status: Finalizado");
            }
            System.out.println("");
        }
        System.out.println("A quantidade total de pedidos é de: " + pedidos.size());
    }

    public void pedidosFinalizados(List<Pedido> pedidos, List<Cliente> clientes) {
        boolean verificaFinalizados = false;

        System.out.println("Pedidos finalizados: ");

        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).isFinalizado()) {
                System.out.println("Pedido nº: " + pedidos.get(i).getNumPedido());
                System.out.println("Descrição do pedido: " + pedidos.get(i).getNomePedido());
                System.out.println("Nome do cliente: " + clientes.get(i).getNome());

                verificaFinalizados = true;

                System.out.println("");
            }
        }
        System.out.println("\nPedidos finalizados totais: " + finalizadosTotais + "\n");

        if (!verificaFinalizados) {
            System.out.println("\nSem pedidos finalizados!\n");
        }
    }

    public void pedidosEmProducao(List<Pedido> pedidos) {

        boolean verificaPedido = false;

        System.out.println("Pedidos em produção: ");

        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).isAtivo()) {
                verificaPedido = true;
                System.out.println("Número do pedido:" + pedidos.get(i).getNumPedido());
                System.out.println("Nome do cliente:" + clientes.get(i).getNome());
                System.out.println("Descrição do pedido: " + pedidos.get(i).getNomePedido());

                System.out.println("");


            }
        }
        System.out.println("A quantidade total de pedidos em andamento é de: " + emAndamentoTotais + "\n");

        if (!verificaPedido) {
            System.out.println("\nSem pedidos em andamento\n");
        }
    }

    public void salvarFinalizados(List<Pedido> pedidos, List<Cliente> clientes, List <Endereco> enderecos) {

        String pastaArquivos = "C:\\Users\\guill\\OneDrive\\Área de Trabalho\\Pedidos\\";

        try {
            for (int i = 0; i < pedidos.size(); i++) {
                String nomeArquivo = pastaArquivos + "pedido_" + pedidos.get(i).getNumPedido() + ".txt";
                BufferedWriter escreverArquivo = new BufferedWriter(new FileWriter(nomeArquivo));
                escreverArquivo.write("Pedido nº: " + pedidos.get(i).getNumPedido());
                escreverArquivo.newLine();
                escreverArquivo.write("Descrição do pedido: " + pedidos.get(i).getNomePedido());
                escreverArquivo.newLine();
                escreverArquivo.write("Nome do cliente: " + clientes.get(i).getNome());
                escreverArquivo.newLine();
                escreverArquivo.write("Rua: " + enderecos.get(i).getRua());
                escreverArquivo.newLine();
                escreverArquivo.write("Bairro: " + enderecos.get(i).getBairro());
                escreverArquivo.newLine();
                escreverArquivo.write("Nº casa: " + enderecos.get(i).getNumero());

                escreverArquivo.close();
            }
            System.out.println("Relatório do pedido criado!");
        } catch (IOException e) {
            System.err.println("Erro ao gerar relatório: " + e.getMessage());
        }
    }


    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    private void setEnderecos() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

}