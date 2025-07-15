package Streams;

import java.util.Random;
import java.util.stream.Stream;

public class main1 {
    public static void main(String[] args) {
        // Esta linha cria e processa uma "Stream" (fluxo) de dados em várias etapas:
        //
        // 1. Stream.generate(() -> new Random().nextInt()):
        //    - Stream.generate(): Cria uma stream "infinita", onde os elementos são gerados sob demanda.
        //    - () -> new Random().nextInt(): É um "Supplier" (fornecedor), uma expressão lambda que
        //      não recebe argumentos e simplesmente gera um valor. Neste caso, gera um número inteiro aleatório.
        //
        // 2. .limit(5):
        //    - Esta é uma "operação intermediária". Ela transforma a stream infinita em uma finita,
        //      pegando apenas os 5 primeiros elementos gerados. Sem o limit(), a stream nunca terminaria.
        //
        // 3. .toArray(Integer[]::new):
        //    - Esta é uma "operação terminal". Ela inicia o processamento da stream e a converte em um array.
        //    - Integer[]::new é uma "referência de construtor", que informa ao método 'toArray' como
        //      criar um novo array do tipo Integer.
        //
        // A variável 'value1' será um array de 5 números inteiros aleatórios.
        var value1 = Stream.generate(() -> new Random().nextInt()).limit(5).toArray(Integer[]::new);

        // Um loop 'for-each' simples para imprimir cada um dos 5 números aleatórios no console.
        for(var v : value1) {
            System.out.println(v);
        }
    }
}