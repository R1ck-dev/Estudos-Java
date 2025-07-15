package Exercicios.exe1;

public enum calculadora {
    SOMA {
        @Override
        public double calcular(int[] valoresArray) {
            var soma = 0;
            for (int valor : valoresArray) {
                soma += valor;
            }
            return soma;
        }
    },
    SUBT {
        @Override
        public double calcular(int[] valoresArray) {
            var subt = valoresArray[0];
            for (var i = 1; i < valoresArray.length; i++) {
                subt -= valoresArray[i];
            }
            return subt;
        }
    };

    public abstract double calcular(int[] valoresArray);

}
