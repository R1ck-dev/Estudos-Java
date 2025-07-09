package String;

import java.util.HashMap;
import java.util.Map;

public class main1 {
    public static void main(String[] args) {
        // 1. Definição da String de entrada usando um "Text Block".
        // Text Blocks (iniciados com três aspas duplas """) são um recurso do Java moderno
        // que permite escrever strings com múltiplas linhas sem a necessidade de caracteres de escape como \n.
        // A variável 'value' é do tipo String (o tipo é inferido pela palavra-chave 'var').
        var value = """
                {"name":"João","age":18}""";

        // 2. Criação de um Mapa (Map) para armazenar o resultado.
        // Um HashMap guardará os dados no formato chave-valor, ambos do tipo String.
        Map<String, String> map = new HashMap<>();

        // 3. Limpeza e preparação da String.
        // O método replace() é usado em cadeia para remover todos os caracteres {, } e ".
        // Antes: {"name":"João","age":18}
        // Depois: name:João,age:18
        value = value.replace("{", "").replace("}", "").replace("\"", "");

        // 4. Divisão da String em pares de chave-valor.
        // O método split(",") quebra a string em um array de strings, usando a vírgula como delimitador.
        // O array 'valueArr' conterá: ["name:João", "age:18"]
        var valueArr = value.split(",");

        // 5. Iteração sobre os pares e preenchimento do Mapa.
        // Este é um loop "for-each", que itera sobre cada elemento do array 'valueArr'.
        for (var v : valueArr) {
            // Em cada iteração, 'v' será um par como "name:João".
            // O método split(":") é usado para separar a chave do valor.
            // Para "name:João", o array 'keyValue' será: ["name", "João"]
            // Para "age:18", o array 'keyValue' será: ["age", "18"]
            var keyValue = v.split(":");

            // 6. Adição da chave e do valor ao Mapa.
            // O primeiro elemento do array (índice 0) é a chave, e o segundo (índice 1) é o valor.
            map.put(keyValue[0], keyValue[1]);
        }

        // 7. Impressão do resultado final.
        // Imprime a representação textual do objeto Map, que mostrará as chaves e valores extraídos.
        System.out.println(map);
    }
}