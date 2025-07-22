package Enums;

import java.util.Scanner;

public class main1 {
    public static void main(String[] args) {
        // Cria um objeto Scanner para ler a entrada do teclado.
        var scanner = new Scanner(System.in);
        var option = -1;

        // Loop principal do programa, que continua executando até o usuário digitar a opção 5.
        while(option != 5) {
            // Imprime o menu de opções para o usuário.
            System.out.println("Escolha uma opção pelo número");
            System.out.println("1 - Soma");
            System.out.println("2 - Subtração");
            System.out.println("3 - Multiplicação");
            System.out.println("4 - Divisão");
            System.out.println("5 - Sair");
            option = scanner.nextInt();

            // Validação da entrada. Se o usuário digitar um número fora do intervalo 1-5,
            // uma mensagem é exibida e o loop recomeça com 'continue'.
            if ((option > 5) || (option < 1)) {
                System.out.println("Selecione uma opção válida");
                continue;
            }

            // Condição para sair do loop. 'break' encerra o laço 'while'.
            if (option == 5) {
                break;
            }

            // PONTO-CHAVE 1: Selecionando a constante do Enum.
            // OperationEnum.values() retorna um array com todas as constantes: [SUM, SUBTRACTION, MULTIPLY, DIVISION].
            // Como o usuário digita de 1 a 4 e o array tem índices de 0 a 3, subtraímos 1.
            // A variável 'selectedOption' agora guarda o objeto Enum correspondente (ex: OperationEnum.SUM).
            var selectedOption = OperationEnum.values()[option - 1];

            // Pede os valores numéricos ao usuário.
            System.out.println("Informe o primeiro valor");
            var value1 = scanner.nextInt();
            System.out.println("Informe o segundo valor");
            var value2 = scanner.nextInt();

            // PONTO-CHAVE 2: Executando o cálculo.
            // 1. selectedOption.getCalculate(): Obtém o objeto BiFunction guardado na constante do enum.
            //    (Ex: para SUM, retorna a lambda (v1, v2) -> v1 + v2).
            // 2. .apply(value1, value2): Executa a lógica da função, passando os valores do usuário.
            var result = selectedOption.getCalculate().apply(value1, value2);

            // Imprime o resultado formatado, usando o símbolo obtido do enum (ex: "+", "-").
            System.out.printf("%s %s %s = %s\n\n", value1, selectedOption.getSymbol(), value2, result);
        }
        // Fecha o scanner para liberar os recursos do sistema.
        scanner.close();
    }
}