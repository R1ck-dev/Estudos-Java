package Set;

import java.util.Objects;

public class User {
    // Atributos privados da classe.
    private int id;
    private String name;

    // Construtor padrão (vazio).
    public User() {

    }

    // Construtor que recebe id e nome para inicializar o objeto.
    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Métodos getters e setters para acessar e modificar os atributos privados.
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Sobrescreve o método toString() para fornecer uma representação textual do objeto.
    // Útil para imprimir o objeto de forma legível (ex: em um System.out.println).
    @Override
    public String toString() {
        return String.format("{'id': %d, 'name': %s}", id, name);
    }

    // Sobrescreve o método equals() para definir o critério de igualdade entre dois objetos User.
    // Dois objetos User são considerados iguais se tiverem o mesmo 'id' e o mesmo 'name'.
    @Override
    public boolean equals(final Object obj) {
        // 1. Verifica se é a mesma instância de objeto na memória.
        if (obj == this) return true;
        // 2. Verifica se o objeto comparado é nulo ou de uma classe diferente.
        if (!(obj instanceof User)) return false;
        // 3. Converte (faz o cast) do objeto para User e compara os atributos relevantes.
        User user = (User) obj;
        return this.id == user.getId() && Objects.equals(this.name, user.getName());
    }
    
    /**
     * Sobrescreve o método hashCode() para garantir o funcionamento correto com coleções de hash.
     *
     * O "contrato" entre equals() e hashCode() exige que:
     * 1. Se dois objetos são iguais de acordo com o método equals(), eles DEVEM ter o mesmo hashCode.
     * 2. Se dois objetos têm o mesmo hashCode, eles NÃO precisam ser iguais (isso é chamado de colisão).
     *
     * O HashSet usa o hashCode() para encontrar rapidamente o "balde" (bucket) onde o objeto deveria estar
     * e, em seguida, usa o equals() para verificar se o objeto exato está nesse balde.
     * A classe Objects.hash() é um utilitário que calcula um código hash combinado a partir dos campos fornecidos.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}