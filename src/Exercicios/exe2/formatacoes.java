package Exercicios.exe2;

public enum formatacoes {
    FIXO_SEM_DDD {
        @Override
        public String formataString(String entrada) {
            var entrada1 = entrada.substring(0, 4);
            var entrada2 = entrada.substring(4);
            return entrada1 + "-" + entrada2;
        }
    },
    FIXO_COM_DDD {
        @Override
        public String formataString(String entrada) {
            var entrada1 = entrada.substring(0, 2);
            var entrada2 = entrada.substring(2, 6);
            var entrada3 = entrada.substring(6);
            return "(" + entrada1 + ")" + entrada2 + "-" + entrada3;
        }
    },
    CELULAR_SEM_DDD {
        @Override
        public String formataString(String entrada) {
            var entrada1 = entrada.substring(0, 5);
            var entrada2 = entrada.substring(5);
            return entrada1 + "-" + entrada2;
        }
    },
    CELULAR_COM_DDD {
        @Override
        public String formataString(String entrada) {
            var entrada1 = entrada.substring(0, 2);
            var entrada2 = entrada.substring(2, 7);
            var entrada3 = entrada.substring(7);
            return "(" + entrada1 + ")" + entrada2 + "-" + entrada3;
        }
    };

    public abstract String formataString(String entrada);
}
