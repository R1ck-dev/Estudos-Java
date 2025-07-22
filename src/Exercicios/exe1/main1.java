package Exercicios.exe1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main1 {
    /**
     * Escreva um código que cria uma calculadora para as operações de soma e subtração,
     * o usuário deve informar todos os números que serão usados na conta de uma só vez,
     * utilizando vírgulas para separá-los.
     * @param args
     */
    public static void main(String[] args) {
        // 1. PREPARAÇÃO DA ENTRADA
        // Cria um objeto Scanner para ler a entrada do console.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite os números separados por vírgula (ex: 10,5,3):");
        // Lê a linha inteira de texto digitada pelo usuário.
        String valores = scanner.nextLine();

        // 2. PROCESSAMENTO DA STRING DE ENTRADA
        // O método split(",") quebra a string em um array de strings, usando a vírgula como delimitador.
        // Ex: "10,5,3" se torna o array ["10", "5", "3"].
        String[] listaValores = valores.split(",");
        
        // Cria uma lista de Integers para armazenar os números convertidos.
        List<Integer> valoresInt = new ArrayList<>();
        // Loop "for-each" para percorrer o array de strings.
        for (String valor : listaValores) {
            // Converte cada string para um inteiro e adiciona na lista.
            // Integer.parseInt() pode lançar uma exceção (NumberFormatException) se o texto não for um número válido.
            valoresInt.add(Integer.parseInt(valor.trim())); // .trim() remove espaços em branco antes e depois do número.
        }

        // 3. CONVERSÃO DA LISTA PARA UM ARRAY DE INTEIROS PRIMITIVOS (int[])
        // Usa a API de Streams do Java para uma conversão concisa.
        // - valoresInt.stream(): Cria um fluxo (Stream) a partir da lista de Integers.
        // - .mapToInt(Integer::intValue): Converte o Stream<Integer> (de objetos) para um IntStream (de primitivos).
        // - .toArray(): Converte o IntStream para um array de int[].
        int[] valoresArray = valoresInt.stream().mapToInt(Integer::intValue).toArray();
         
        // 4. EXECUÇÃO DOS CÁLCULOS E EXIBIÇÃO DOS RESULTADOS
        // Chama diretamente o método 'calcular' da constante SOMA do enum 'calculadora'.
        double resultadoSoma = calculadora.SOMA.calcular(valoresArray);
        System.out.printf("Resultado SOMA: %s\n", resultadoSoma);

        // Chama diretamente o método 'calcular' da constante SUBT do enum 'calculadora'.
        double resultadoSubt = calculadora.SUBT.calcular(valoresArray);
        System.out.printf("Resultado SUBTRAÇÃO: %s", resultadoSubt);
        
        // Fecha o scanner para liberar os recursos do sistema. É uma boa prática.
        scanner.close();
    }
}