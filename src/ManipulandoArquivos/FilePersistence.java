package ManipulandoArquivos;

/**
 * Interface que define um contrato para operações de persistência de dados.
 * Qualquer classe que implementar esta interface DEVE fornecer uma lógica
 * para os métodos aqui definidos. Isso permite trocar a forma de armazenamento
 * (ex: de arquivo para banco de dados) sem alterar o código que a utiliza.
 */
public interface FilePersistence {
    
    /**
     * Escreve uma linha de dados no meio de persistência.
     * @param data A string de dados a ser escrita.
     * @return A própria string que foi escrita.
     */
    String write(final String data);

    /**
     * Remove uma ou mais linhas que contenham a sentença fornecida.
     * @param sentence O texto a ser procurado para a remoção.
     * @return true se a remoção foi bem-sucedida, false caso contrário.
     */
    boolean remove(final String sentence);

    /**
     * Substitui uma linha antiga por uma nova.
     * @param oldContent O conteúdo a ser substituído.
     * @param newContent O novo conteúdo que será inserido.
     * @return O novo conteúdo, se a substituição ocorrer.
     */
    String replace(final String oldContent, final String newContent);

    /**
     * Retorna todo o conteúdo do meio de persistência.
     * @return Uma única String com todo o conteúdo, geralmente com quebras de linha.
     */
    String findAll();

    /**
     * Encontra e retorna a primeira linha que contém a sentença fornecida.
     * @param sentence O texto a ser procurado.
     * @return A primeira linha encontrada que contém a sentença, ou uma string vazia se não encontrar.
     */
    String findBy(final String sentence);
}