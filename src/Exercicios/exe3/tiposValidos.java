package Exercicios.exe3;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Set;

public enum tiposValidos {
    TEXTO("texto") {
        @Override
        public boolean verificaEnum(String campoValor) {
            if (campoValor != null && !campoValor.trim().isEmpty()) {return true;}
            return false;
        }
    },
    DATA("data") {
        private static final DateTimeFormatter FORMATADOR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        @Override
        public boolean verificaEnum(String campoValor) {
            try {
                LocalDate.parse(campoValor.trim(), FORMATADOR);
                return true;
            } catch (DateTimeParseException e) {
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
                Integer.parseInt(campoValor);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    },
    NUMEROS_FLUTUANTES("números flutantes") {
        @Override
        public boolean verificaEnum(String campoValor) {
            try {
                Double.parseDouble(campoValor);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    },
    BOOLEANOS("booleanos") {
        private static final Set<String> VALORES_BOOLEANOS = Set.of("true", "false");
        @Override
        public boolean verificaEnum(String campoValor) {
            if (VALORES_BOOLEANOS.contains(campoValor.toLowerCase().trim())) {
                return true;
            }
            return false;
        }
    },
    ARRAY_TEXTO("array de texto") {
        @Override
        public boolean verificaEnum(String campoValor) {
            return true;
        }
    },
    ARRAY_DATA("array de data") {
        @Override
        public boolean verificaEnum(String campoValor) {
            String[] elementos = campoValor.split(",");
            if (elementos.length == 0) return false;
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
            for (String elemento : elementos) {
                if (!BOOLEANOS.verificaEnum(elemento)) {
                    return false;
                }
            }
            return true;
        }
    };

    private final String nomeAmigavel;

    tiposValidos(String nome) {
        this.nomeAmigavel = nome;
    }

    public abstract boolean verificaEnum(String campoValor);

    public static tiposValidos fromString(String texto) {
        for (tiposValidos tipo : tiposValidos.values()) {
            if (tipo.nomeAmigavel.equalsIgnoreCase(texto.trim())) {
                return tipo;
            }
        }
        return null;
    }
    
}
