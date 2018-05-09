package upb.acs.movietaste.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import upb.acs.movietaste.entities.Movie;
import upb.acs.movietaste.services.MoviesService;

import java.util.Objects;

@RestController
@RequestMapping("movies")
public class MovieController {

    private final MoviesService moviesService;

    @Autowired
    public MovieController(final MoviesService moviesService) {
        this.moviesService = Objects.requireNonNull(moviesService, "moviesService must not be null");
    }

    @GetMapping("paginated")
    public Page<Movie> getMoviePage(@RequestParam(value = "title_like", defaultValue = "") final String searchTerm,
                                    @RequestParam("page") final int page,
                                    @RequestParam("size") final int size) {
        return moviesService.getMoviePage(searchTerm, page, size);
    }
}
