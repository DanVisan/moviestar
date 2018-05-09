package upb.acs.movietaste.services;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.prediction.EngineClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import upb.acs.movietaste.entities.Movie;
import upb.acs.movietaste.models.MovieRecommendation;
import upb.acs.movietaste.models.RecommendationModel;
import upb.acs.movietaste.repositories.MovieRepository;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    private final String predictionIO_URL;
    private final MovieRepository movieRepository;

    @Autowired
    public RecommendationService(@Value("${predictionio.url}") final String predictionIO_URL,
                                 final MovieRepository movieRepository) {
        this.predictionIO_URL = Objects.requireNonNull(predictionIO_URL, "predictionIO_URL must not be null!");
        this.movieRepository = Objects.requireNonNull(movieRepository, "movieRepository must not be null");
    }

    public List<MovieRecommendation> getMovieRecommendations(final String searchTerm, final String userId, final Integer numberOfRecommendations) {
        return callPredictionEngine(userId, numberOfRecommendations).stream()
                .map(recommendation -> {
                    Movie movie = movieRepository.findOne(recommendation.getItem());
                    return new MovieRecommendation(movie, recommendation.getScore().toString());
                })
                .filter(recomm -> recomm.getMovie().getTitle().toLowerCase().contains(searchTerm.trim().toLowerCase()))
                .collect(Collectors.toList());
    }

    private List<RecommendationModel> callPredictionEngine(final String userId, final Integer numberOfRecommendations) {
        EngineClient engineClient = new EngineClient(predictionIO_URL);
        try {
            JsonObject response = engineClient.sendQuery(ImmutableMap.of("user", userId, "num", numberOfRecommendations));

            JsonArray recommendationsJsonArray = response.getAsJsonArray("itemScores");

            Iterator<JsonElement> iterator = recommendationsJsonArray.iterator();
            List<JsonElement> recommendationsJsonElements = new ArrayList<>();
            while (iterator.hasNext()) {
                recommendationsJsonElements.add(iterator.next());
            }
            Gson gson = new Gson();
            return recommendationsJsonElements.stream()
                    .map(el -> gson.fromJson(el, RecommendationModel.class))
                    .collect(Collectors.toList());
        } catch (ExecutionException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
