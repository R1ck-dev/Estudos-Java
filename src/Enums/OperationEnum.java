package Enums;

import java.util.function.BiFunction;

/**
 * Este é um enum "inteligente". Em vez de ser apenas uma lista de constantes,
 * cada constante (SUM, SUBTRACTION, etc.) é um objeto que carrega consigo:
 * 1. A lógica de cálculo (através de um BiFunction).
 * 2. O símbolo da operação (uma String).
 * Isso implementa um padrão de projeto conhecido como "Strategy Pattern".
 */
public enum OperationEnum {
    // Definição das constantes do enum. Cada uma é uma instância de OperationEnum.
    // Ao serem criadas, o construtor do enum é chamado para cada uma delas.
    // Ex: SUM é criado passando a lambda de soma e o símbolo "+".
    SUM((Integer v1, Integer v2) -> v1 + v2, "+"),
    SUBTRACTION((Integer v1, Integer v2) -> v1 - v2, "-"),
    MULTIPLY((Integer v1, Integer v2) -> v1 * v2, "*"),
    DIVISION((Integer v1, Integer v2) -> v1 / v2, "/");

    // Atributo para guardar a função de cálculo.
    private final BiFunction<Integer, Integer, Integer> calculate;

    // Atributo para guardar o símbolo da operação.
    private final String symbol;

    /**
     * Construtor do enum. É sempre privado por padrão.
     * Ele recebe a lógica de cálculo e o símbolo e os armazena nos atributos da constante.
     * @param calculate Uma função que implementa BiFunction. Aqui, estamos usando expressões lambda.
     * @param symbol O símbolo da operação.
     */
    OperationEnum(BiFunction<Integer, Integer, Integer> calculate, String symbol) {
        this.calculate = calculate;
        this.symbol = symbol;
    }

    // "Getter" para obter a função de cálculo associada a uma constante.
    public BiFunction<Integer, Integer, Integer> getCalculate() {
        return calculate;
    }

    // "Getter" para obter o símbolo associado a uma constante.
    public String getSymbol() {
        return symbol;
    }
}