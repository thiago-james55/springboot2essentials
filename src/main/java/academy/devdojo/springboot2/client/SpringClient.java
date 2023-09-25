package academy.devdojo.springboot2.client;

import academy.devdojo.springboot2.domain.Anime;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        ResponseEntity<Anime> entity = new RestTemplate().getForEntity("http://localhost:8080/animes/{id}", Anime.class, 2);
        log.info(entity);

        Anime object = new RestTemplate().getForObject("http://localhost:8080/animes/{id}", Anime.class, 2);
        log.info(object);

        Anime[] entityList = new RestTemplate().getForObject("http://localhost:8080/animes/all", Anime[].class);
        for (Anime anime : entityList) {
            System.out.println(anime);
        }

        ResponseEntity<List<Anime>> exchange = new RestTemplate().exchange("http://localhost:8080/animes/all",
                HttpMethod.GET,
                null, new ParameterizedTypeReference<>() {
                });

        exchange.getBody().forEach(log::info);

        /* POST */
//        Anime anime = Anime.builder().name("Kingdom").build();
//        Anime animeSaved = new RestTemplate().postForObject("http://localhost:8080/animes", anime, Anime.class);
//        log.info(animeSaved);

//        Anime anime = Anime.builder().name("Bayonetta").build();
//        ResponseEntity<Anime> bayonettaSaved = new RestTemplate().exchange("http://localhost:8080/animes",
//                HttpMethod.POST,
//                new HttpEntity<>(anime, createJsonHeader()),
//                Anime.class);
//        log.info(bayonettaSaved);
        /* POST */

        Anime bayonettaSaved = Anime.builder().name("Bayonetta").build();
        bayonettaSaved.setName("Bayonetta 2 ");
        bayonettaSaved.setId(106L);

        ResponseEntity<Void> bayonettaUpdated = new RestTemplate().exchange("http://localhost:8080/animes",
                HttpMethod.PUT,
                new HttpEntity<>(bayonettaSaved, createJsonHeader()),
                Void.class);

        log.info(bayonettaUpdated);

        ResponseEntity<Void> bayonettaDeleted = new RestTemplate().exchange("http://localhost:8080/animes/{id}",
                HttpMethod.DELETE,
                null,
                Void.class,"106");

        log.info(bayonettaDeleted);


    }

    private static HttpHeaders createJsonHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
