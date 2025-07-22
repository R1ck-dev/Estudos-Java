package ManipulandoArquivos;

import java.io.File;
import java.io.IOException;

public class main1 {
    public static void main(String[] args) throws IOException {
        FilePersistence persistence = new IOFilePersistence("user.csv");
        System.out.println("--------------------------------------");
        persistence.write("Lucas;lucas@lucas.com;15/01/1990");
        System.out.println("--------------------------------------");
        persistence.write("Maria;maria@maria.com;23/10/2000");
        System.out.println("--------------------------------------");
        persistence.write("João;joao@joao.com;01/12/1995");
        System.out.println("--------------------------------------");
        System.out.println(persistence.findAll());
        System.out.println("--------------------------------------");
        System.out.println(persistence.findBy("Lucas;"));
        System.out.println("--------------------------------------");
        System.out.println(persistence.findBy("95;"));
    }    
}
