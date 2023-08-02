import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Cliente cliente = new Cliente();
        Pedido pedido = new Pedido();

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n== Olá! Seja bem-vindo(a) à pizzaria Papa's Pizza ==");
        System.out.println("Insira uma das opções abaixo: \n");

        int opcao;
        do {
            System.out.println("1 - Realizar um pedido");
            System.out.println("2 - Exibir todos os pedidos");
            System.out.println("3 - Exibir pedidos encerrados");
            System.out.println("4 - Exibir pedidos em andamento");
            System.out.println("5 - Alterar status dos pedidos");
            System.out.println("6 - Sair\n");
            System.out.print("Digite a opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    cliente.cadastrarCliente();
                    break;

                case 2:
                    cliente.quantidadePedidos(cliente.getPedidos(), cliente.clientes);
                    break;

                case 3:
                    cliente.pedidosFinalizados(cliente.getPedidos(), cliente.clientes);
                    break;

                case 4:
                    cliente.pedidosEmProducao(cliente.getPedidos());
                    break;

                case 5:
                    cliente.alterarStatusPedido(cliente.clientes, cliente.getEnderecos(), cliente.getPedidos());
                    break;

                case 6:
                    System.exit(1);
                    break;

                default:
                    System.out.println("Opção inválida, digite um valor de 1 até 6");
                    break;
            }
        } while (opcao != 6);
    }
}