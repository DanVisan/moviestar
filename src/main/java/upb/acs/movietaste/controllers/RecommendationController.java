package upb.acs.movietaste.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import upb.acs.movietaste.models.MovieRecommendation;
import upb.acs.movietaste.services.RecommendationService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("recommendations")
public class RecommendationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecommendationController.class);

    private final RecommendationService recommendationService;

    @Autowired
    public RecommendationController(final RecommendationService recommendationService) {
        this.recommendationService = Objects.requireNonNull(recommendationService, "recommendationService must not be null");
    }

    @GetMapping
    public List<MovieRecommendation> getRecommendations(@RequestParam(value = "title_like", defaultValue = "") final String searchTerm,
                                                        @RequestParam("nr") final Integer numberOfRecommendations) {
        /*
         * In a scenario where this user could be uniquely identified through a register-login process,
         * a real userId will be used to query the deployed recommendations engine
         */
        LOGGER.info("Fetching recommendations from PredictionIO");
        return recommendationService.getMovieRecommendations(searchTerm, "newUserId", numberOfRecommendations);
    }
}
