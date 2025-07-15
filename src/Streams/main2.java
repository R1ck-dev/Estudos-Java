package Streams;

import java.util.stream.Stream;

public class main2 {
    public static void main(String[] args) {
        // Esta linha cria e processa uma stream de Strings em várias etapas:
        //
        // 1. Stream.of("Maria", "João", "Marcio", "Luana", "Leandro", "Marcia"):
        //    - Stream.of(): Cria uma stream finita a partir dos elementos fornecidos diretamente.
        //      Esta é uma das formas mais comuns de se iniciar uma stream.
        //
        // 2. .filter(name -> name.endsWith("a")):
        //    - .filter(): É uma "operação intermediária" que permite manter apenas os elementos
        //      que satisfazem uma determinada condição.
        //    - name -> name.endsWith("a"): É um "Predicate" (predicado), uma expressão lambda que
        //      recebe um elemento (name) e retorna um booleano (true ou false).
        //      A stream manterá apenas os nomes para os quais a condição (terminar com "a") for verdadeira.
        //      Elementos que passarão pelo filtro: "Maria", "Luana", "Marcia".
        //
        // 3. .toList():
        //    - É uma "operação terminal" (disponível a partir do Java 16) que coleta todos os
        //      elementos restantes da stream e os coloca em uma Lista (List).
        //
        // A variável 'value' será uma lista contendo ["Maria", "Luana", "Marcia"].
        var value = Stream.of("Maria", "João", "Marcio", "Luana", "Leandro", "Marcia")
                                   .filter(name -> name.endsWith("a"))
                                   .toList();

        // Imprime a lista resultante no console.
        System.out.println(value);
    }
}