import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.NoRouteToHostException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConversorDeMoedas {
    public static void main(String[] args) throws IOException {
        List<Moeda> listaDeMoedas = adicionaMoedasNaLista();
        ExchangeRateApi api = new ExchangeRateApi();
        Scanner scanner = new Scanner(System.in);
        int opcao = 1;
        while (opcao != 0) {
            System.out.print("""

                    *************** CONVERSOR DE MOEDAS **************

                    >>>>> MENU DE OPÇÕES

                    """);

            int numeroOpcaoMenu = 0;
            ArrayList<OpcoesMenu> listaDeOpcoesMenu = new ArrayList<>();

            for (int i = 0; i < listaDeMoedas.size(); i++) {
                for (int x = 0; x < listaDeMoedas.size(); x++) {
                    if (x != i) {
                        numeroOpcaoMenu++;
                        System.out.printf("""
                                %d - %s -> %s
                                """, numeroOpcaoMenu, listaDeMoedas.get(i), listaDeMoedas.get(x));
                        OpcoesMenu opcoes = new OpcoesMenu(listaDeMoedas.get(i), listaDeMoedas.get(x));
                        listaDeOpcoesMenu.add(opcoes);
                    }

                }
            }
            System.out.print("Escolha o número da opção desejada: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 0) {
                System.out.println("Encerrando aplicação...");
            } else if (opcao >= 1 && opcao <= numeroOpcaoMenu) {
                OpcoesMenu opcaoEscolhida = listaDeOpcoesMenu.get(opcao - 1);
                System.out.printf("Agora escolha o valor em %s que deseja converter em %s: ",
                        opcaoEscolhida.getMoeda1().toString(), opcaoEscolhida.getMoeda2().toString());
                double valorASerConvertido = scanner.nextDouble();
                scanner.nextLine();

                try {
                    double taxaDeCambio = api.taxaDeCambio(opcaoEscolhida.getMoeda1().getSiglaMoeda(),
                            opcaoEscolhida.getMoeda2().getSiglaMoeda());
                    double valorConvertido = valorASerConvertido * taxaDeCambio;
                    BigDecimal valorASerConvertidoFormatado = new BigDecimal(valorASerConvertido);
                    valorASerConvertidoFormatado = valorASerConvertidoFormatado.setScale(2, RoundingMode.HALF_UP);

                    BigDecimal valorConvertidoFormatado = new BigDecimal(valorConvertido);
                    valorConvertidoFormatado = valorConvertidoFormatado.setScale(2, RoundingMode.HALF_UP);
                    System.out.println("O valor de " + valorASerConvertidoFormatado + "("
                            + opcaoEscolhida.getMoeda1().getSiglaMoeda() + ") corresponde a " + valorConvertidoFormatado
                            + " (" + opcaoEscolhida.getMoeda2().getSiglaMoeda() + ")");
                    System.out.print("""

                            Pressione enter para continuar""");
                    scanner.nextLine();
                } catch (ApiException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Pressione enter para continuar");
                    scanner.nextLine();
                } catch (UnknownHostException e) {
                    System.out
                            .print("""

                                    >>>>>> ERRO <<<<<<

                                    Não foi possível estabelecer uma conexão com o servidor da API. Verifique sua conexão com a internet.

                                    Pressione enter para continuar""");
                    scanner.nextLine();
                } catch (NoRouteToHostException e) {
                    System.out
                            .print("""

                                    >>>>>> ERRO <<<<<<

                                    O servidor não está respondendo à solicitação no momento. Tente novamente após alguns instantes.

                                    Pressione enter para continuar""");
                    scanner.nextLine();
                }

            } else {
                System.out.print("""

                        Opção inválida! Tente novamente!

                        Pressione enter para continuar
                        """);
                scanner.nextLine();
            }
        }
    }

    private static List<Moeda> adicionaMoedasNaLista() {
        Moeda realBrasileiro = new Moeda("Real Brasileiro", "BRL");
        Moeda dolarAmericano = new Moeda("Dólar Americano", "USD");
        Moeda euro = new Moeda("Euro", "EUR");
        Moeda dolarAustraliano = new Moeda("Dólar Australiano", "AUD");
        Moeda pesoArgentino = new Moeda("Peso Argentino", "ARS");
        Moeda dolarCanadense = new Moeda("Dólar Canadense", "CAD");

        List<Moeda> listaDeMoedas = new ArrayList<>();
        listaDeMoedas.add(realBrasileiro);
        listaDeMoedas.add(dolarAmericano);
        listaDeMoedas.add(euro);
        listaDeMoedas.add(dolarAustraliano);
        listaDeMoedas.add(pesoArgentino);
        listaDeMoedas.add(dolarCanadense);
        return listaDeMoedas;
    }

}