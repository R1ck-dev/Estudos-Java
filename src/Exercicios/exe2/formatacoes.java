package Exercicios.exe2;

/**
 * Este enum define as diferentes estratégias de formatação para números de telefone.
 * Cada constante do enum representa um tipo de número (fixo, celular, com ou sem DDD)
 * e é forçada a implementar o método abstrato 'formataString', contendo sua lógica
 * específica de formatação.
 */
public enum formatacoes {
    /**
     * Estratégia para formatar um telefone fixo de 8 dígitos (sem DDD).
     * Formato de saída: xxxx-xxxx
     */
    FIXO_SEM_DDD {
        @Override
        public String formataString(String entrada) {
            // Pega os primeiros 4 caracteres.
            var entrada1 = entrada.substring(0, 4);
            // Pega os caracteres a partir do 4º índice.
            var entrada2 = entrada.substring(4);
            // Concatena as partes com um hífen.
            return entrada1 + "-" + entrada2;
        }
    },
    /**
     * Estratégia para formatar um telefone fixo de 10 dígitos (com DDD).
     * Formato de saída: (xx)xxxx-xxxx
     */
    FIXO_COM_DDD {
        @Override
        public String formataString(String entrada) {
            var entrada1 = entrada.substring(0, 2); // DDD
            var entrada2 = entrada.substring(2, 6); // Primeira parte do número
            var entrada3 = entrada.substring(6);    // Segunda parte do número
            return "(" + entrada1 + ")" + entrada2 + "-" + entrada3;
        }
    },
    /**
     * Estratégia para formatar um celular de 9 dígitos (sem DDD).
     * Formato de saída: xxxxx-xxxx
     */
    CELULAR_SEM_DDD {
        @Override
        public String formataString(String entrada) {
            var entrada1 = entrada.substring(0, 5);
            var entrada2 = entrada.substring(5);
            return entrada1 + "-" + entrada2;
        }
    },
    /**
     * Estratégia para formatar um celular de 11 dígitos (com DDD).
     * Formato de saída: (xx)xxxxx-xxxx
     */
    CELULAR_COM_DDD {
        @Override
        public String formataString(String entrada) {
            var entrada1 = entrada.substring(0, 2); // DDD
            var entrada2 = entrada.substring(2, 7); // Primeira parte do número
            var entrada3 = entrada.substring(7);    // Segunda parte do número
            return "(" + entrada1 + ")" + entrada2 + "-" + entrada3;
        }
    };

    /**
     * Declaração de um método abstrato.
     * Isso força todas as constantes deste enum a fornecerem
     * uma implementação concreta para a formatação de string.
     * @param entrada A string de números puros a ser formatada.
     * @return A string formatada de acordo com a estratégia da constante.
     */
    public abstract String formataString(String entrada);
}