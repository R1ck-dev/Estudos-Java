package Exercicios.exe3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insira o separador que será utilizado: ");
        String separador = scanner.nextLine();
        List<Map<String, String>> dados = new ArrayList<>();

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
                        Map<String, String> dadoEstruturado = new HashMap<>();
                        dadoEstruturado.put("nome", partes[0]);
                        dadoEstruturado.put("valor", partes[1]);
                        dadoEstruturado.put("tipo", partes[2]);
                        
                        dados.add(dadoEstruturado);
                        System.out.println("--> Dado adicionado com sucesso!");
                    } else {
                        System.out.println("--> Erro: O valor '" + campoValor + "' não é válido para o tipo '" + campoTipo + "'.");
                    }
                } else { System.out.println("--> Erro: O tipo '" + campoTipo + "' não é aceito."); }   
            } else { System.out.println("--> Entrada Inválida (formato incorreto ou campos vazios)."); }                
        }
        System.out.println("===Formato JSON===");
        System.out.println(formataJSON(dados));
        System.out.println("===Formato YMAL===");
        System.out.println(formataYMAL(dados));
        System.out.println("===Formato XML===");
        System.out.println(formataXML(dados));
        scanner.close();
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

    public static String formataJSON(List<Map<String, String>> dados) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[\n");

        for (int i = 0; i < dados.size(); i++) {
            Map<String, String> mapa = dados.get(i);
            jsonBuilder.append("  {\n"); 

            String nome = mapa.get("nome");
            String valor = mapa.get("valor");
            String tipo = mapa.get("tipo");

            jsonBuilder.append("    \"nome\": \"").append(nome).append("\",\n");
            jsonBuilder.append("    \"tipo\": \"").append(tipo).append("\",\n");

            if (tipo.contains("números") || tipo.contains("booleanos")) {
                jsonBuilder.append("    \"valor\": ").append(valor).append("\n");
            } else {
                jsonBuilder.append("    \"valor\": \"").append(valor).append("\"\n");
            }
            
            jsonBuilder.append("  }");

            if (i < dados.size() - 1) {
                jsonBuilder.append(",\n");
            } else {
                jsonBuilder.append("\n");
            }
        }

        jsonBuilder.append("]"); 
        return jsonBuilder.toString();      
    }
    
    public static String formataYMAL(List<Map<String, String>> dados) {
        StringBuilder yamlBuilder = new StringBuilder();

        for (Map<String, String> mapa : dados) {
            String nome = mapa.get("nome");
            String valor = mapa.get("valor");
            String tipo = mapa.get("tipo");

            yamlBuilder.append("- nome: \"").append(nome).append("\"\n");
            yamlBuilder.append("  tipo: \"").append(tipo).append("\"\n");

            if (tipo.contains("números") || tipo.contains("booleanos")) {
                yamlBuilder.append("  valor: ").append(valor).append("\n");
            } else {
                yamlBuilder.append("  valor: \"").append(valor).append("\"\n");
            }
        }
        return yamlBuilder.toString();
        }
    
    public static String formataXML(List<Map<String, String>> dados) {
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xmlBuilder.append("<dados>\n");

        for (Map<String, String> mapa : dados) {
            String nomeCampo = mapa.get("nome");
            String valor = mapa.get("valor");
            String tipo = mapa.get("tipo");

            xmlBuilder.append("    <item>\n");
            xmlBuilder.append("        <nome>").append(nomeCampo).append("</nome>\n");
            xmlBuilder.append("        <valor>").append(valor).append("</valor>\n");
            xmlBuilder.append("        <tipo>").append(tipo).append("</tipo>\n");
            xmlBuilder.append("    </item>\n");
        }

        xmlBuilder.append("</dados>");
        return xmlBuilder.toString();
        }
}
