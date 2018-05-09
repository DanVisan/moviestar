package upb.acs.movietaste.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import upb.acs.movietaste.entities.Movie;
import upb.acs.movietaste.repositories.MovieRepository;

import java.util.Objects;

import static org.springframework.data.jpa.domain.Specifications.where;
import static upb.acs.movietaste.specificators.MovieSpecification.titleLike;

@Service
public class MoviesService {

    private final MovieRepository movieRepository;

    @Autowired
    public MoviesService(final MovieRepository movieRepository) {
        this.movieRepository = Objects.requireNonNull(movieRepository, "movieRepository must not be null");
    }

    public Page<Movie> getMoviePage(final String searchTerm, final int page, final int size) {
        final Pageable pageable = new PageRequest(page, size);
        return movieRepository.findAll(where(titleLike(searchTerm)), pageable);
    }
}
