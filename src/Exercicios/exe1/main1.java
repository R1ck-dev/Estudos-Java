package Exercicios.exe1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main1 {
    /**
     * Escreva um código que cria uma calculadora para as operações de soma e subtração, o usuário deve informar todos os números que serão usados na conta de uma só vez utilizando virgulas para separa-los
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String valores = scanner.nextLine();
        System.out.println(valores);
        String[] listaValores = valores.split(",");
        
        List<Integer> valoresInt = new ArrayList<>();
        for (String valor : listaValores) {
            valoresInt.add(Integer.parseInt(valor));
        }

        int[] valoresArray = valoresInt.stream().mapToInt(Integer::intValue).toArray();
         
        double resultadoSoma = calculadora.SOMA.calcular(valoresArray);
        System.out.printf("Resultado SOMA: %s\n", resultadoSoma);
        double resultadoSubt = calculadora.SUBT.calcular(valoresArray);
        System.out.printf("Resultado SUBTRAÇÃO: %s", resultadoSubt);
        scanner.close();
    }
}


