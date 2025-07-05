package TiposPrimitivosEWrappers;

public class main1 {
    public static void main(String[] args) {
        // 1. Cria uma nova instância da classe User.
        // A palavra-chave 'var' infere o tipo da variável (User) a partir do valor que está sendo atribuído.
        var user = new User("João", 20);

        // 2. Chama o método printValue, passando a REFERÊNCIA do objeto 'user'.
        // O método printValue irá modificar o objeto original.
        printValue(user);

        // 4. Imprime o objeto 'user' novamente no método main.
        // O resultado mostrará os valores alterados ("Maria", 33), porque o objeto
        // referenciado por 'user' foi modificado dentro do método printValue.
        System.out.println(user); // Saída: User{name='Maria', age=33}
    }

    /**
     * Este método recebe uma referência a um objeto User.
     * @param user A referência ao objeto a ser modificado.
     *
     * PONTO-CHAVE: Java sempre passa parâmetros por valor. Para objetos, o "valor"
     * que é passado é uma cópia da REFERÊNCIA (o endereço de memória) do objeto.
     * Portanto, tanto a variável 'user' no método main quanto o parâmetro 'user' aqui
     * apontam para o MESMO objeto na memória.
     *
     * O 'final' no parâmetro 'user' significa que a referência NÃO pode ser alterada para
     * apontar para um novo objeto (ex: user = new User(...); daria erro de compilação),
     * mas o objeto para o qual ela aponta PODE ter seu conteúdo alterado se for mutável.
     */
    private static void printValue(final User user) {
        // 3. Altera o estado INTERNO do objeto que foi recebido como parâmetro.
        user.setName("Maria");
        user.setAge(33);

        // Imprime o objeto após a modificação.
        System.out.println(user); // Saída: User{name='Maria', age=33}
    }
}