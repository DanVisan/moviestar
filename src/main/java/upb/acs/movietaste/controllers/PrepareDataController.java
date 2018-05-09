package upb.acs.movietaste.controllers;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import upb.acs.movietaste.config.CustomMovieDeserializer;
import upb.acs.movietaste.entities.Movie;
import upb.acs.movietaste.models.Link;
import upb.acs.movietaste.repositories.MovieRepository;

import java.io.*;
import java.util.*;

@RestController
public class PrepareDataController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrepareDataController.class);

    private final MovieRepository movieRepository;
    private final String appKey;

    public PrepareDataController(final MovieRepository movieRepository,
                                 @Value("${api.key}") final String appKey) {
        this.movieRepository = Objects.requireNonNull(movieRepository, "movieRepository must not be null");
        this.appKey = Objects.requireNonNull(appKey, "appKey must not be null");
    }

    @GetMapping("/crawlMoviesFromApi")
    public String startImportingDataFromAPI() {
        long start = System.currentTimeMillis();
        List<Link> linkList = buildLinksList();
        RestTemplate restTemplate = new RestTemplate();
        final String URL = "https://api.themoviedb.org/3/find/tt{imdbId}?api_key={appKey}&language=en-US&external_source=imdb_id";
        linkList.forEach(link -> {
            String movieStringJson = restTemplate.getForObject(URL, String.class, link.getImdbId(), appKey);
            try {
                Thread.sleep(129);
                Movie movie = parseJson(movieStringJson, link);
                movieRepository.save(movie);
            } catch (Exception e) {
                LOGGER.error("Bad request at link: {}", link);
            }
        });
        long end = System.currentTimeMillis();
        return end - start + "";
    }

    private Movie parseJson(String movieStringJson, Link link) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("CustomMovieDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(Movie.class, new CustomMovieDeserializer());
        mapper.registerModule(module);
        Movie movie = mapper.readValue(movieStringJson, Movie.class);
        movie.setId(link.getMovieId());
        movie.setImdbId(link.getImdbId());
        movie.setTmdbId(link.getTmdbId());

        return movie;
    }

    private List<Link> buildLinksList() {
        List<Link> list = new ArrayList<>();
        String csvFile = "src/main/resources/movielens/links.csv";
        CSVReader reader;
        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            while ((line = reader.readNext()) != null) {
                list.add(new Link(line[0] + "", line[1] + "", line[2] + ""));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!list.isEmpty()) {
            list.remove(0);
        }
        return list;
    }

    @GetMapping("/rateEventsFromCsv")
    public void rateEventJsonFileMaker() {
        String csvFile = "src/main/resources/movielens/ratings.csv";

        CSVReader reader;
        int count = 0;
        try {
            reader = new CSVReader(new FileReader(csvFile));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/main/resources/movielens/ratingsJson")));
            String[] line;
            reader.readNext();
            while ((line = reader.readNext()) != null) {
                String jsonLine = "{\"event\":\"rate\", " +
                                  "\"entityType\":\"user\" ," +
                                  "\"entityId\":\"" + line[0] + "\", " +
                                  "\"targetEntityType\":\"item\", " +
                                  "\"targetEntityId\":\"" + line[1] + "\", " +
                                  "\"properties\":{\"rating\":" + line[2] + "}}";
                writer.write(jsonLine);
                writer.newLine();
                count++;
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(count);
    }

    @GetMapping("/buyEventsFromCsv")
    public void buyEventJsonFileMaker() {
        String csvFile = "src/main/resources/movielens/ratings.csv";

        CSVReader reader;
        try {
            reader = new CSVReader(new FileReader(csvFile));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/main/resources/movielens/buyJson")));
            String[] line;
            reader.readNext();
            while ((line = reader.readNext()) != null) {
                if (Double.valueOf(line[2]) >= 4.5) {
                    String jsonLine = "{\"event\":\"buy\", " +
                            "\"entityType\":\"user\" ," +
                            "\"entityId\":\"" + line[0] + "\", " +
                            "\"targetEntityType\":\"item\", " +
                            "\"targetEntityId\":\"" + line[1] + "\"}";
                    writer.write(jsonLine);
                    writer.newLine();
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/findMovieIds")
    public void findMoviesFromRatingsCsv() {
        String csvFile = "src/main/resources/movielens/ratings.csv";
        CSVReader reader;
        try {
            reader = new CSVReader(new FileReader(csvFile));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/main/resources/movielens/itemsJson")));
            String[] line;
            reader.readNext();
            Set<String> movieIDs = new HashSet<>();
            while ((line = reader.readNext()) != null) {
                if (movieIDs.add(line[1])) {
                    String jsonLine = "{\"event\":\"$set\", " +
                            "\"entityType\":\"item\" ," +
                            "\"entityId\":\"" + line[1] + "\"}";
                    writer.write(jsonLine);
                    writer.newLine();
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
