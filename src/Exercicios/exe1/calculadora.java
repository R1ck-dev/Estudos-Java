package Exercicios.exe1;

/**
 * Este enum representa as operações de uma calculadora.
 * A abordagem usada aqui é a de um "enum com comportamento", onde cada constante
 * não é apenas um valor, mas também implementa uma lógica específica.
 *
 * Ao declarar um método abstrato no enum, somos obrigados a implementar
 * este método para cada uma das constantes.
 *
 * OBS: Pela convenção do Java, nomes de enums e classes deveriam começar com letra maiúscula (ex: "Calculadora").
 */
public enum calculadora {
    /**
     * Constante que representa a operação de SOMA.
     * O bloco de código a seguir é a implementação específica do método 'calcular'
     * para esta constante.
     */
    SOMA {
        /**
         * Sobrescreve o método abstrato 'calcular' com a lógica de soma.
         * @param valoresArray O array de números a serem somados.
         * @return A soma de todos os valores no array.
         */
        @Override
        public double calcular(int[] valoresArray) {
            var soma = 0;
            // Loop "for-each" que percorre cada valor no array.
            for (int valor : valoresArray) {
                soma += valor; // Acumula o valor na variável 'soma'.
            }
            return soma;
        }
    },
    /**
     * Constante que representa a operação de SUBTRAÇÃO.
     * Implementa a sua própria versão do método 'calcular'.
     */
    SUBT {
        /**
         * Sobrescreve o método abstrato 'calcular' com a lógica de subtração.
         * A lógica implementada é: primeiro_valor - segundo_valor - terceiro_valor...
         * @param valoresArray O array de números a serem subtraídos.
         * @return O resultado da subtração sequencial.
         */
        @Override
        public double calcular(int[] valoresArray) {
            // Inicia a variável 'subt' com o primeiro valor do array.
            var subt = valoresArray[0];
            // O loop começa do segundo elemento (índice 1) em diante.
            for (var i = 1; i < valoresArray.length; i++) {
                subt -= valoresArray[i]; // Subtrai cada valor subsequente.
            }
            return subt;
        }
    };

    /**
     * Declaração de um método abstrato.
     * Isso força todas as constantes deste enum (SOMA, SUBT) a fornecerem
     * uma implementação concreta para este método.
     * @param valoresArray Um array de inteiros para a operação.
     * @return O resultado da operação, como um double.
     */
    public abstract double calcular(int[] valoresArray);
}