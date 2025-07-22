package Exercicios.exe3;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Set;

/**
 * Enum que define e valida os tipos de dados aceitos pelo sistema.
 * Cada constante representa um tipo e é forçada a implementar o método de validação 'verificaEnum'.
 * Isso centraliza e organiza todas as regras de validação do projeto.
 */
public enum tiposValidos {
    // ---- TIPOS SIMPLES ----
    TEXTO("texto") {
        @Override
        public boolean verificaEnum(String campoValor) {
            // Qualquer texto não vazio é considerado válido.
            return campoValor != null && !campoValor.trim().isEmpty();
        }
    },
    DATA("data") {
        // Define um formatador de data padrão para ser reutilizado.
        private static final DateTimeFormatter FORMATADOR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        @Override
        public boolean verificaEnum(String campoValor) {
            try {
                // Tenta analisar (parse) a string. Se conseguir, a data é válida.
                LocalDate.parse(campoValor.trim(), FORMATADOR);
                return true;
            } catch (DateTimeParseException e) {
                // Se o parse falhar (lançar exceção), a data é inválida.
                return false;
            }
        }
    },
    DATA_E_HORA("data e hora") {
        private static final DateTimeFormatter FORMATADOR_HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm");
        @Override
        public boolean verificaEnum(String campoValor) {
            try {
                LocalDateTime.parse(campoValor.trim(), FORMATADOR_HORA);
                return true;
            } catch (DateTimeParseException e) {
                return false;
            }
        }
    },
    NUMEROS_INTEIROS("números inteiros") {
        @Override
        public boolean verificaEnum(String campoValor) {
            try {
                Integer.parseInt(campoValor.trim());
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    },
    NUMEROS_FLUTUANTES("números flutuantes") {
        @Override
        public boolean verificaEnum(String campoValor) {
            try {
                Double.parseDouble(campoValor.trim());
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    },
    BOOLEANOS("booleanos") {
        // Usa um Set para uma verificação rápida e case-insensitive (após toLowerCase).
        private static final Set<String> VALORES_BOOLEANOS = Set.of("true", "false");
        @Override
        public boolean verificaEnum(String campoValor) {
            return VALORES_BOOLEANOS.contains(campoValor.toLowerCase().trim());
        }
    },
    // ---- TIPOS ARRAY ----
    ARRAY_TEXTO("array de texto") {
        @Override
        public boolean verificaEnum(String campoValor) {
            // Para array de texto, qualquer valor separado por vírgula é aceito.
            return true;
        }
    },
    ARRAY_DATA("array de data") {
        @Override
        public boolean verificaEnum(String campoValor) {
            String[] elementos = campoValor.split(",");
            if (elementos.length == 0) return false;
            // Reutiliza a lógica de validação do enum DATA para cada elemento.
            for (String elemento : elementos) {
                if (!DATA.verificaEnum(elemento)) {
                    return false;
                }
            }
            return true;
        }
    },
    ARRAY_DATA_E_HORA("array de data e hora") {
        @Override
        public boolean verificaEnum(String campoValor) {
            String[] elementos = campoValor.split(",");
            if (elementos.length == 0) return false;
            // Reutiliza a lógica de validação do enum DATA_E_HORA.
            for (String elemento : elementos) {
                if (!DATA_E_HORA.verificaEnum(elemento)) {
                    return false;
                }
            }
            return true;
        }
    },
    ARRAY_INTEIROS("array de números inteiros") {
        @Override
        public boolean verificaEnum(String campoValor) {
            String[] elementos = campoValor.split(",");
            if (elementos.length == 0) return false;
            // Reutiliza a lógica de validação do enum NUMEROS_INTEIROS.
            for (String elemento : elementos) {
                if (!NUMEROS_INTEIROS.verificaEnum(elemento)) {
                    return false;
                }
            }
            return true;
        }
    },
    ARRAY_FLUTUANTES("array de números flutuantes") {
        @Override
        public boolean verificaEnum(String campoValor) {
            String[] elementos = campoValor.split(",");
            if (elementos.length == 0) return false;
            // Reutiliza a lógica de validação do enum NUMEROS_FLUTUANTES.
            for (String elemento : elementos) {
                if (!NUMEROS_FLUTUANTES.verificaEnum(elemento)) {
                    return false;
                }
            }
            return true;
        }
    },
    ARRAY_BOOLEANOS("array de booleanos") {
        @Override
        public boolean verificaEnum(String campoValor) {
            String[] elementos = campoValor.split(",");
            if (elementos.length == 0) return false;
            // Reutiliza a lógica de validação do enum BOOLEANOS.
            for (String elemento : elementos) {
                if (!BOOLEANOS.verificaEnum(elemento)) {
                    return false;
                }
            }
            return true;
        }
    };

    // Atributo para armazenar o nome amigável do tipo (ex: "data e hora").
    private final String nomeAmigavel;

    // Construtor do enum, que associa o nome amigável a cada constante.
    tiposValidos(String nome) {
        this.nomeAmigavel = nome;
    }

    /**
     * Método abstrato que força cada constante a ter sua própria lógica de validação.
     * @param campoValor A string a ser validada.
     * @return true se for válida, false caso contrário.
     */
    public abstract boolean verificaEnum(String campoValor);

    /**
     * Método de fábrica estático para encontrar uma constante do enum a partir de sua representação em String.
     * Isso permite que o programa converta a entrada do usuário (ex: "texto") na constante correspondente (tiposValidos.TEXTO).
     * @param texto O nome do tipo a ser procurado.
     * @return A constante do enum correspondente ou null se não for encontrada.
     */
    public static tiposValidos fromString(String texto) {
        for (tiposValidos tipo : tiposValidos.values()) {
            if (tipo.nomeAmigavel.equalsIgnoreCase(texto.trim())) {
                return tipo;
            }
        }
        return null; // Retorna nulo se nenhum tipo correspondente for encontrado.
    }
}