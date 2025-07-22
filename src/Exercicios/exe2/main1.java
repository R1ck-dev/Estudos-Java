package Exercicios.exe2;

import java.util.Scanner;

/*
 * [Requisitos do Exercício]
 * Escreva um código que receba entradas sem formatação e as retorne formatadas, os tipos de entradas que o código deve retornar são as seguintes:
 * - Telefone Fixo (8 dígitos sem DDD xxxx-xxxx, 10 dígitos com DDD (xx)xxxx-xxxx)
 * - Celular (9 dígitos sem DDD xxxxx-xxxx, 11 dígitos com DDD (xx)xxxxx-xxxx)
 * O código deve ser capaz de detectar as seguintes situações:
 * - Se receber somente números detectear se corresponde com algum dos formatos aceitos e retornar formatado; OK
 * - Se receber uma entrada com quantidade de números diferentes dos padrões descritos acima, informar que não se trata de um número válido; OK
 * - Se receber um número formatado, retorna-lo do mesmo jeito e informar de qual tipo de dispositivo se trata;
 * - Se receber com mascara incorreta, corrigir e retornar;
 * - Se receber qualquer entrada que tenha números e outros caracteres verificar se tem números para compor um dos tipos aceitos e retornar do que se trata ou retornar que foi uma entrada inválida.
 */
public class main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite um número de telefone para ser formatado:");
        var entrada = scanner.nextLine();

        // Limpa a entrada, removendo espaços em branco no início/fim e no meio.
        entrada = entrada.trim().replace(" ", "");

        // Verifica se a entrada contém apenas números (dígitos de 0 a 9).
        boolean allNumbers = entrada.chars().allMatch(c -> c >= 48 && c <= 57);

        // Verifica se a entrada, caso seja composta apenas por números, tem um comprimento válido.
        boolean valido_invalido = (entrada.length() >= 8 && entrada.length() <= 11);

        // Verifica se a entrada contém pelo menos um dígito.
        boolean oneNumber = entrada.chars().anyMatch(c -> c >= 48 && c <= 57);

        if (allNumbers) {
            // Se a entrada contiver apenas números, chama o método para lidar com esse caso.
            apenasNumeros(entrada, valido_invalido, allNumbers);
        } else {
            // Se a entrada contiver outros caracteres...
            if (outrosCaracteres(entrada, oneNumber)) {
                // ...e for considerada válida (tem pelo menos um dígito e a contagem de dígitos está no intervalo correto)...
                System.out.println("Número Válido!");
                // ...chama o método que verifica o formato e o corrige se necessário.
                System.out.println(verificandoFormato(entrada, contarDigitos(entrada)));
            } else {
                System.out.println("Número Inválido!");
            }
        }
        scanner.close();
    }

    /**
     * Lida com o caso em que a string de entrada contém apenas números.
     */
    public static void apenasNumeros(String entrada, boolean valido_invalido, boolean allNumbers) {
        String formate = null;
        if (valido_invalido && allNumbers) {
            // Usa um operador ternário para determinar o nome da constante do enum com base no comprimento da string.
            formate = (entrada.length() == 8) ? "FIXO_SEM_DDD" :
                      (entrada.length() == 10) ? "FIXO_COM_DDD" :
                      (entrada.length() == 9) ? "CELULAR_SEM_DDD" :
                      (entrada.length() == 11) ? "CELULAR_COM_DDD" : null;
        } else {
            System.out.println("Número Inválido");
            return; // Encerra o método se o número for inválido.
        }
        
        System.out.println("Tipo detectado: " + formate);
        // Converte a string com o nome da formatação para a constante do enum correspondente.
        formatacoes tipo = formatacoes.valueOf(formate);
        // Chama o método da constante do enum para formatar a string e imprime o resultado.
        System.out.println("Formatado: " + tipo.formataString(entrada));
        return;
    }

    /**
     * Conta quantos caracteres em uma string são dígitos.
     */
    public static int contarDigitos(String entrada) {
        int soma_numeros = 0;
        for (char valor : entrada.toCharArray()) {
            if (Character.isDigit(valor)) {
                soma_numeros++;
            }
        }
        return soma_numeros;
    }

    /**
     * Verifica se uma string que contém outros caracteres além de números é válida.
     */
    public static boolean outrosCaracteres(String entrada, boolean oneNumber) {
        if (!oneNumber) { // Se não tiver nenhum número, é inválida.
            return false;
        }
        int qtdDigitos = contarDigitos(entrada);
        // É válida se a quantidade de dígitos estiver entre 8 e 11.
        return qtdDigitos >= 8 && qtdDigitos <= 11;
    }

    /**
     * Verifica o formato de uma string que já pode conter caracteres de máscara e a formata.
     */
    public static String verificandoFormato(String entrada, int qtd_numeros) {
        // Converte a string para um array de caracteres para facilitar o acesso por índice.
        char[] entrada_lista = entrada.toCharArray();
        String numerosPuros = cortandoCaracteres(entrada);

        // Usa um switch para tratar cada caso com base na quantidade de dígitos.
        switch (qtd_numeros) {
            case 8: // Fixo sem DDD
                String verifica_fixo_sem_ddd = (entrada_lista[4] == '-') ?
                        "[Formatado Corretamente] Fixo sem DDD - " + entrada :
                        formatacoes.FIXO_SEM_DDD.formataString(numerosPuros);
                return verifica_fixo_sem_ddd;
            case 9: // Celular sem DDD
                String verifica_celular_sem_ddd = (entrada_lista[5] == '-') ?
                        "[Formatado Corretamente] Celular sem DDD - " + entrada :
                        formatacoes.CELULAR_SEM_DDD.formataString(numerosPuros);
                return verifica_celular_sem_ddd;
            case 10: // Fixo com DDD
                String verifica_fixo_com_ddd = ((entrada_lista[0] == '(') && (entrada_lista[3] == ')') && (entrada_lista[8]) == '-') ?
                        "[Formatado Corretamente] Fixo com DDD - " + entrada :
                        formatacoes.FIXO_COM_DDD.formataString(numerosPuros);
                return verifica_fixo_com_ddd;
            case 11: // Celular com DDD
                String verifica_celular_com_ddd = ((entrada_lista[0] == '(') && (entrada_lista[3] == ')') && (entrada_lista[9]) == '-') ?
                        "[Formatado Corretamente] Celular com DDD - " + entrada :
                        formatacoes.CELULAR_COM_DDD.formataString(numerosPuros);
                return verifica_celular_com_ddd;
        }
        return "Formato não reconhecido";
    }

    /**
     * Extrai apenas os dígitos de uma string de entrada, removendo qualquer outro caractere.
     */
    public static String cortandoCaracteres(String entrada) {
        StringBuilder sb_entrada = new StringBuilder();
        char[] entrada_lista = entrada.toCharArray();
        for (char valor : entrada_lista) {
            if (Character.isDigit(valor)) {
                sb_entrada.append(valor);
            }
        }
        return sb_entrada.toString();
    }
}