package Exercicios.exe3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insira o separador que será utilizado: ");
        String separador = scanner.nextLine();
        List<String> dados = new ArrayList<>();

        while (true) {
            System.out.println("Insira os dados!");
            System.out.println("NOME_CAMPO" + separador + "VALOR" + separador + "TIPO");
            String entrada_dados = scanner.nextLine();

            if (entrada_dados.equalsIgnoreCase("sair")) {break;}

            if (verificaSeparador(entrada_dados, separador)) {
                String[] partes = entrada_dados.split(separador, -1);
                String campoValor = partes[1];
                String campoTipo = partes[2];

                tiposValidos tipoEnum = tiposValidos.fromString(campoTipo);

                if (tipoEnum != null) {
                    if (tipoEnum.verificaEnum(campoValor)) {
                        dados.add(entrada_dados);
                        System.out.println("--> Dado adicionado com sucesso!");
                    } else {
                        System.out.println("--> Erro: O valor '" + campoValor + "' não é válido para o tipo '" + campoTipo + "'.");
                    }
                } else { System.out.println("--> Erro: O tipo '" + campoTipo + "' não é aceito."); }   
            } else { System.out.println("--> Entrada Inválida (formato incorreto ou campos vazios)."); }                
        }
        System.out.println(dados);
    }

    public static boolean verificaSeparador(String entrada_dados, String separador) {
        int cont_separador = 0;
        for (char valor : entrada_dados.toCharArray()) {
            if (valor == separador.charAt(0)) { cont_separador++; }}
        if (cont_separador != 2) { return false; }
        String[] partes = entrada_dados.split(separador, -1);
        for (String parte : partes) {
        if (parte.trim().isEmpty()) { return false; }
        }
        return true;
    }

    // public static boolean verificaCampoTipo(String campoTipo) {
    //     campoTipo = campoTipo.toLowerCase().trim();
    //     Set<String> tiposPermitidos = Set.of(
    //                                 "texto", 
    //                                 "data", 
    //                                 "data e hora", 
    //                                 "números inteiros",
    //                                 "números flutuantes",
    //                                 "booleanos");
    //     if (tiposPermitidos.contains(campoTipo)) {return true;}
    //     if (campoTipo.startsWith("array de ")) {
    //         String tipoArray = campoTipo.substring(0,9).trim();
    //         if (tiposPermitidos.contains(tipoArray)) {return true;}
    //     }
    //     return false;
    // }
}
