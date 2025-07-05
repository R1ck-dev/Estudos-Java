package TiposPrimitivosEWrappers;

/**
 * Define uma classe User padrão (POJO - Plain Old Java Object).
 * Esta é uma classe "mutável", pois seus atributos 'name' e 'age'
 * podem ser modificados após a criação do objeto através dos métodos set.
 */
public class User {
    // Atributos privados que guardam o estado do objeto.
    private String name;
    private int age;

    // Construtor público para inicializar um novo objeto User com valores iniciais.
    // A palavra-chave 'final' nos parâmetros do construtor é uma convenção
    // para indicar que esses parâmetros não serão reatribuídos dentro do próprio construtor.
    public User(final String name, final int age) {
        this.name = name;
        this.age = age;
    }

    // Métodos "getters" para acessar os valores dos atributos.
    public String getName() {
        return this.name;
    }

    // Métodos "setters" para modificar os valores dos atributos.
    // A presença de setters é o que torna esta classe mutável.
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Sobrescreve o método toString() para fornecer uma representação textual clara do objeto,
    // o que é muito útil para depuração e logs.
    @Override
    public String toString() {
        return "User{name='" + name + "', age=" + age + "}";
    }
}