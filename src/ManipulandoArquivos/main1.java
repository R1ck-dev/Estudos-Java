package ManipulandoArquivos;

import java.io.File;
import java.io.IOException;

public class main1 {
    /**
     * Ponto de entrada do programa.
     * @param args Argumentos de linha de comando (não utilizados).
     * @throws IOException Pode ser lançada pelo construtor de IOFilePersistence.
     */
    public static void main(String[] args) throws IOException {
        // PONTO-CHAVE: "Programação voltada a interfaces".
        // A variável 'persistence' é do tipo da INTERFACE (FilePersistence),
        // mas a instância criada é da CLASSE CONCRETA (IOFilePersistence).
        // Isso torna o código flexível: poderíamos trocar para uma implementação de banco de dados
        // sem alterar este arquivo, apenas a linha abaixo.
        FilePersistence persistence = new IOFilePersistence("user.csv");
        
        System.out.println("--------------------------------------");
        // Escreve três linhas de dados no arquivo "user.csv".
        persistence.write("Lucas;lucas@lucas.com;15/01/1990");
        System.out.println("--------------------------------------");
        persistence.write("Maria;maria@maria.com;23/10/2000");
        System.out.println("--------------------------------------");
        persistence.write("João;joao@joao.com;01/12/1995");
        
        System.out.println("--------------------------------------");
        // Busca e imprime todo o conteúdo do arquivo.
        System.out.println(persistence.findAll());

        System.out.println("--------------------------------------");
        // Busca e imprime a primeira linha que contém "Lucas;".
        System.out.println(persistence.findBy("Lucas;"));

        System.out.println("--------------------------------------");
        // Busca e imprime a primeira linha que contém "95;".
        System.out.println(persistence.findBy("95;"));
    }    
}