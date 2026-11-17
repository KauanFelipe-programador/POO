


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumirCep {

    public static void main(String[] args) {

        // Identificação
        System.out.println("Aluno: Kauan Felipe");
        System.out.println("RA: 0023597");
        System.out.println("-------------------------");

        String cep = "72550022";
        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        try {
            // Cria o cliente HTTP
            HttpClient client = HttpClient.newHttpClient();

            // Cria a requisição
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            // Envia a requisição e recebe a resposta
            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            // Exibe o resultado
            System.out.println(response.body());

        } catch (Exception e) {
            System.out.println("Erro ao consultar o CEP: " + e.getMessage());
        }
    }
}
