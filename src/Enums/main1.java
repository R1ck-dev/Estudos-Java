package Enums;

import java.util.Scanner;

public class main1 {
    public static void main(String[] args) {
        // Cria um objeto Scanner para ler a entrada do teclado.
        var scanner = new Scanner(System.in);
        var option = -1;

        // Loop principal do programa, continua até o usuário digitar 5.
        while(option != 5) {
            // Imprime o menu de opções.
            System.out.println("Escolha uma opção pelo número");
            System.out.println("1 - Soma");
            System.out.println("2 - Subtração");
            System.out.println("3 - Multiplicação");
            System.out.println("4 - Divisão");
            System.out.println("5 - Sair");
            option = scanner.nextInt();

            // Validação da entrada. Se for inválida, pula para a próxima iteração do loop.
            if ((option > 5) || (option < 1)) {
                System.out.println("Selecione uma opção válida");
                continue;
            }

            // Condição para sair do loop.
            if (option == 5) {
                break;
            }

            // PONTO-CHAVE 1: Selecionando a constante do Enum.
            // OperationEnum.values() retorna um array com todas as constantes: [SUM, SUBTRACTION, MULTIPLY, DIVISION].
            // Como o usuário digita 1, 2, 3, 4 e o array tem índices 0, 1, 2, 3, subtraímos 1.
            // A variável 'selectedOption' agora guarda o objeto Enum correspondente (ex: OperationEnum.SUM).
            var selectedOption = OperationEnum.values()[option - 1];

            // Pede os valores ao usuário.
            System.out.println("Informe o primeiro valor");
            var value1 = scanner.nextInt();
            System.out.println("Informe o segundo valor");
            var value2 = scanner.nextInt();

            // PONTO-CHAVE 2: Executando o cálculo.
            // 1. selectedOption.getCalculate(): Obtém o objeto BiFunction guardado na constante do enum.
            //    (Ex: para SUM, retorna a lambda (v1, v2) -> v1 + v2).
            // 2. .apply(value1, value2): Executa a lógica da função passando os valores do usuário.
            var result = selectedOption.getCalculate().apply(value1, value2);

            // Imprime o resultado formatado, usando o símbolo obtido do enum.
            System.out.printf("%s %s %s = %s\n\n", value1, selectedOption.getSymbol(), value2, result);
        }
        scanner.close(); // Fecha o scanner para liberar recursos.
    }
}