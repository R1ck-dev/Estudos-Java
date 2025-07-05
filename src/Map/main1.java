package Map;

import java.util.HashMap;
import java.util.Map;

public class main1 {
    public static void main(String[] args) {
        // Cria um novo Mapa (Map) do tipo HashMap.
        // Um Map armazena pares de chave-valor. Neste caso, a chave é uma String (email)
        // e o valor é um objeto User. O HashMap não garante a ordem das chaves.
        Map<String, User> users = new HashMap<>();

        // Adiciona quatro pares de chave-valor ao mapa usando o método put().
        // Se a chave não existe, ela é inserida. Se já existe, o valor antigo é substituído.
        users.put("joao@joao.com", new User("João", 22));
        users.put("maria@maria.com", new User("Maria", 32));
        users.put("juca@juca.com", new User("Juca", 18));
        users.put("leo@leo.com", new User("Leo", 40));

        /*
         * Uso do método merge() para atualizar um valor existente no mapa.
         * A assinatura é: merge(chave, novoValor, funcaoDeRemapeamento)
         *
         * Como funciona:
         * 1. Ele procura pela chave "leo@leo.com".
         * 2. Como a chave EXISTE, ele NÃO insere o 'novoValor' diretamente. Em vez disso,
         * ele executa a 'funcaoDeRemapeamento' (a expressão lambda).
         * 3. A função lambda recebe dois argumentos:
         * - user: O valor ATUAL associado à chave no mapa (o User("Leo", 40)).
         * - user2: O 'novoValor' que foi passado para o merge (o User(" ", -1)).
         * 4. O valor RETORNADO pela função lambda se tornará o NOVO valor para a chave no mapa.
         *
         * Se a chave "leo@leo.com" NÃO existisse, o 'novoValor' (User(" ", -1)) seria
         * simplesmente inserido no mapa, e a função lambda não seria executada.
         */
        users.merge("leo@leo.com", new User(" ", -1), (user, user2) -> {
            // Imprime o valor antigo encontrado no mapa.
            System.out.println("Valor antigo: " + user); // Saída: Valor antigo: User[name=Leo, age=40]
            // Imprime o novo valor passado para o merge.
            System.out.println("Valor novo: " + user2);   // Saída: Valor novo: User[name= , age=-1]
            // Retorna o segundo usuário (user2), que substituirá o valor antigo no mapa.
            return user2;
        });

        System.out.println("\nMapa final após o merge:");
        // Imprime o estado final do mapa. Note que o valor para a chave "leo@leo.com" foi atualizado.
        System.out.println(users);
    }
}