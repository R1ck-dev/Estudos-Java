package Exercicios.exe3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insira o separador que será utilizado: ");
        String separador = scanner.nextLine();
        // Lista para armazenar todos os dados inseridos pelo usuário.
        // Cada item da lista é um Mapa que representa uma linha de dados.
        List<Map<String, String>> dados = new ArrayList<>();

        // Loop infinito para coletar dados até que o usuário digite "sair".
        while (true) {
            System.out.println("Insira os dados ou 'sair' para finalizar!");
            System.out.println("NOME_CAMPO" + separador + "VALOR" + separador + "TIPO");
            String entrada_dados = scanner.nextLine();

            if (entrada_dados.equalsIgnoreCase("sair")) {
                break; // Encerra o loop.
            }

            // Valida se a entrada tem o formato esperado (2 separadores e sem campos vazios).
            if (verificaSeparador(entrada_dados, separador)) {
                // Divide a entrada em 3 partes usando o separador.
                String[] partes = entrada_dados.split(separador, -1);
                String campoValor = partes[1];
                String campoTipo = partes[2];

                // Converte o tipo em string (ex: "data") para a constante enum correspondente (tiposValidos.DATA).
                tiposValidos tipoEnum = tiposValidos.fromString(campoTipo);

                if (tipoEnum != null) {
                    // Se o tipo é válido, chama o método de validação específico daquele enum.
                    if (tipoEnum.verificaEnum(campoValor)) {
                        // Se o valor for válido para o tipo, cria um mapa e o adiciona à lista de dados.
                        Map<String, String> dadoEstruturado = new HashMap<>();
                        dadoEstruturado.put("nome", partes[0]);
                        dadoEstruturado.put("valor", partes[1]);
                        dadoEstruturado.put("tipo", partes[2]);
                        
                        dados.add(dadoEstruturado);
                        System.out.println("--> Dado adicionado com sucesso!");
                    } else {
                        System.out.println("--> Erro: O valor '" + campoValor + "' não é válido para o tipo '" + campoTipo + "'.");
                    }
                } else {
                    System.out.println("--> Erro: O tipo '" + campoTipo + "' não é aceito.");
                }   
            } else {
                System.out.println("--> Entrada Inválida (formato incorreto ou campos vazios).");
            }                
        }
        
        // Após o loop, exibe os dados coletados nos três formatos.
        System.out.println("\n===Formato JSON===");
        System.out.println(formataJSON(dados));
        System.out.println("\n===Formato YAML===");
        System.out.println(formataYMAL(dados));
        System.out.println("\n===Formato XML===");
        System.out.println(formataXML(dados));
        scanner.close();
    }

    /**
     * Verifica se a string de entrada contém exatamente 2 separadores e se nenhuma das partes é vazia.
     */
    public static boolean verificaSeparador(String entrada_dados, String separador) {
        int cont_separador = 0;
        // Conta a ocorrência do separador.
        for (char valor : entrada_dados.toCharArray()) {
            if (valor == separador.charAt(0)) {
                cont_separador++;
            }
        }
        if (cont_separador != 2) return false;

        // Verifica se alguma parte entre os separadores está vazia.
        String[] partes = entrada_dados.split(separador, -1); // O -1 garante que partes vazias no final sejam contadas.
        for (String parte : partes) {
            if (parte.trim().isEmpty()) return false;
        }
        return true;
    }

    /**
     * Constrói uma string no formato JSON a partir da lista de dados.
     */
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

            // Para tipos numéricos e booleanos, o valor não deve ter aspas no JSON.
            if (tipo.contains("números") || tipo.contains("booleanos")) {
                jsonBuilder.append("    \"valor\": ").append(valor).append("\n");
            } else {
                jsonBuilder.append("    \"valor\": \"").append(valor).append("\"\n");
            }
            
            jsonBuilder.append("  }");

            // Adiciona uma vírgula se não for o último elemento.
            if (i < dados.size() - 1) {
                jsonBuilder.append(",\n");
            } else {
                jsonBuilder.append("\n");
            }
        }

        jsonBuilder.append("]"); 
        return jsonBuilder.toString();      
    }
    
    /**
     * Constrói uma string no formato YAML a partir da lista de dados.
     */
    public static String formataYMAL(List<Map<String, String>> dados) {
        StringBuilder yamlBuilder = new StringBuilder();

        for (Map<String, String> mapa : dados) {
            String nome = mapa.get("nome");
            String valor = mapa.get("valor");
            String tipo = mapa.get("tipo");

            yamlBuilder.append("- nome: \"").append(nome).append("\"\n");
            yamlBuilder.append("  tipo: \"").append(tipo).append("\"\n");

            // Similar ao JSON, tipos numéricos e booleanos não precisam de aspas em YAML.
            if (tipo.contains("números") || tipo.contains("booleanos")) {
                yamlBuilder.append("  valor: ").append(valor).append("\n");
            } else {
                yamlBuilder.append("  valor: \"").append(valor).append("\"\n");
            }
        }
        return yamlBuilder.toString();
    }
    
    /**
     * Constrói uma string no formato XML a partir da lista de dados.
     */
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