package Map;

/**
 * Declaração da classe User como um "record".
 * Records são uma funcionalidade do Java (a partir da versão 14) que simplifica a criação de classes
 * cujo principal objetivo é armazenar dados. Eles são imutáveis por padrão.
 *
 * Ao declarar 'User' como um record, o compilador Java gera automaticamente para nós:
 * 1.  Um construtor que aceita todos os componentes (String name, int age).
 * 2.  Campos privados e finais (private final) para cada componente ('name' e 'age').
 * 3.  Métodos de acesso públicos para cada componente (ex: public String name() e public int age()).
 * 4.  Uma implementação dos métodos .equals(), .hashCode() e .toString() baseada em todos os componentes.
 *
 * Isso elimina a necessidade de escrever código repetitivo ("boilerplate") e garante que
 * a classe funcione perfeitamente com coleções como HashMap e HashSet.
 */
public record User(String name, int age) {
    // O corpo do record pode ser usado para adicionar construtores customizados,
    // métodos estáticos ou métodos de instância, mas para um simples portador de dados,
    // ele pode ficar vazio como aqui.
}