package Set;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class main1 {
    public static void main(String[] args) {
        // Cria um novo conjunto (Set) do tipo HashSet para armazenar objetos User.
        // O HashSet não permite elementos duplicados e não garante a ordem de inserção.
        Set<User> users = new HashSet<>();

        // Adiciona quatro novos objetos User ao conjunto.
        users.add(new User(1, "Jão"));
        users.add(new User(2, "Maria"));
        users.add(new User(3, "Juca"));
        users.add(new User(4, "Leo"));

        // Imprime o resultado da verificação se o conjunto 'users' contém um novo objeto User com id=1 e nome="Jão".
        // AGORA O RESULTADO SERÁ 'true'.
        // Como a classe User implementa tanto 'hashCode()' quanto 'equals()', o HashSet consegue:
        // 1. Calcular o hashCode() de 'new User(1, "Jão")' para encontrar o "balde" correto.
        // 2. Usar o equals() para confirmar que um objeto idêntico já existe naquele balde.
        System.out.println("O conjunto contém o usuário (1, Jão)? " + users.contains(new User(1, "Jão"))); // Saída: true

        System.out.println("\nUsuários no conjunto (a ordem não é garantida):");
        // Itera sobre cada usuário no conjunto e o imprime.
        // System.out::println é uma "referência de método" (method reference),
        // uma forma curta de escrever a expressão lambda: user -> System.out.println(user)
        users.forEach(System.out::println);

        // Remove do conjunto 'users' todos os elementos que também estão na lista fornecida.
        // A remoção também depende dos métodos 'hashCode()' e 'equals()'.
        // - new User(1, "Jão"): Será encontrado e removido, pois um usuário igual existe no conjunto.
        // - new User(2, "Lucas"): NÃO será removido, pois não existe um usuário com id=2 E nome="Lucas" no conjunto (o usuário com id=2 é "Maria").
        users.removeAll(List.of(new User(1, "Jão"), new User(2, "Lucas")));

        System.out.println("\nUsuários no conjunto após a remoção:");
        // Imprime a representação do conjunto após a remoção.
        // O resultado esperado é um conjunto contendo os usuários Maria, Juca e Leo.
        System.out.println(users);
    }
}