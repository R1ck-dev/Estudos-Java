package ManipulandoArquivos;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Implementação da interface FilePersistence que usa o sistema de arquivos local
 * para armazenar os dados.
 */
public class IOFilePersistence implements FilePersistence {

    // Obtém o diretório de trabalho atual do projeto.
    private final String currentDir = System.getProperty("user.dir");

    // Define um subdiretório padrão para organizar os arquivos gerenciados.
    private final String storedDir = "/managedFiles/IO/";

    // Nome do arquivo que será manipulado.
    private final String fileName;

    /**
     * Construtor da classe.
     * @param fileName O nome do arquivo a ser usado (ex: "user.csv").
     * @throws IOException Se houver um erro ao criar os diretórios.
     */
    public IOFilePersistence(String fileName) throws IOException {
        this.fileName = fileName;
        // Cria um objeto File para representar o diretório de armazenamento.
        var file = new File(currentDir + storedDir);
        // Se o diretório não existe, tenta criá-lo (incluindo diretórios pai, com mkdirs).
        if (!file.exists() && !file.mkdirs()) throw new IOException("Erro ao criar arquivo");

        // Limpa o conteúdo do arquivo toda vez que o programa é iniciado.
        clearFile();
    }

    /**
     * Escreve uma linha de dados no final do arquivo.
     */
    @Override
    public String write(String data) {
        // Usa "try-with-resources", que fecha automaticamente os recursos (arquivos) no final.
        // FileWriter(..., true) abre o arquivo em modo "append" (adicionar no final).
        try (
            var fileWriter = new FileWriter(currentDir + storedDir + fileName, true);
            var bufferedWrite = new BufferedWriter(fileWriter); // Melhora a performance de escrita.
            var printWriter = new PrintWriter(bufferedWrite);  // Fornece métodos úteis como println.
        ) {
            printWriter.println(data); // Escreve a string e adiciona uma nova linha.
        } catch (IOException ex) {
            ex.printStackTrace(); // Imprime o erro se algo falhar.
        }
        return data;
    }

    /**
     * Lê todo o conteúdo do arquivo, reescreve-o sem as linhas que contêm 'sentence'.
     */
    @Override
    public boolean remove(final String sentence) {
        var content = findAll(); // Lê todo o conteúdo atual do arquivo.
        var contentList = new ArrayList<>(Stream.of(content.split(System.lineSeparator())).toList());

        // Se nenhuma linha contiver a sentença, retorna false.
        if (contentList.stream().noneMatch(c -> c.contains(sentence))) return false;

        clearFile(); // Limpa o arquivo completamente.
        // Percorre a lista em memória e escreve de volta no arquivo apenas as linhas que NÃO contêm a sentença.
        contentList.stream().filter(c -> !c.contains(sentence)).forEach(this::write);

        return true;
    }
    
    /**
     * Substitui linhas que contêm 'oldContent' por 'newContent'.
     * ATENÇÃO: A lógica implementada aqui parece ter um bug.
     * A linha .map(c -> !c.contains(oldContent) ? newContent : c) substitui TODAS as linhas que NÃO contêm
     * o texto antigo pelo novo conteúdo, o que provavelmente não é o comportamento desejado.
     * A lógica correta provavelmente seria: .map(c -> c.contains(oldContent) ? newContent : c)
     */
    @Override
    public String replace(String oldContent, String newContent) {
        var content = findAll();
        var contentList = new ArrayList<>(Stream.of(content.split(System.lineSeparator())).toList());

        if (contentList.stream().noneMatch(c -> c.contains(oldContent))) return "";

        clearFile();
        // Mapeia cada linha: se a linha NÃO contém o conteúdo antigo, substitui pelo novo. Caso contrário, mantém a original.
        contentList.stream().map(c -> !c.contains(oldContent) ? newContent : c).forEach(this::write);

        return newContent;
    }

    /**
     * Lê todo o conteúdo do arquivo e o retorna como uma única String.
     */
    @Override
    public String findAll() {
        var content = new StringBuilder(); // Usa StringBuilder para eficiência na concatenação de strings.
        try (var reader = new BufferedReader(new FileReader(currentDir + storedDir + fileName))) {
            String line;
            do {
                line = reader.readLine(); // Lê uma linha do arquivo.
                if ((line != null)) content.append(line).append(System.lineSeparator()); // Adiciona a linha ao StringBuilder.
            } while (line != null); // Continua enquanto houver linhas para ler.
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    /**
     * Procura no arquivo a primeira linha que contém a 'sentence'.
     */
    @Override
    public String findBy(String sentence) {
        String found = "";
        try (var reader = new BufferedReader(new FileReader(currentDir + storedDir + fileName))) {
            String line = reader.readLine();
            while (line != null) {
                if ((line.contains(sentence))) {
                    found = line; // Armazena a linha encontrada.
                    break; // Para o loop, pois já encontrou a primeira ocorrência.
                }
                line = reader.readLine(); // Lê a próxima linha.
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return found;
    }
    
    /**
     * Método privado para limpar o conteúdo do arquivo.
     * Ele faz isso abrindo o arquivo para escrita sem o modo "append",
     * o que efetivamente trunca o arquivo para tamanho zero.
     */
    private void clearFile() {
        try(OutputStream outputStream = new FileOutputStream(currentDir + storedDir + fileName)) {
            System.out.printf("inicializando recursos (%s) \n", currentDir + storedDir + fileName);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Método vazio, não utilizado no código.
    private void createFile() {}
}