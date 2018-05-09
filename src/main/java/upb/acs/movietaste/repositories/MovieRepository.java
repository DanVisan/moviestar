package upb.acs.movietaste.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import upb.acs.movietaste.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, String>, JpaSpecificationExecutor<Movie> {
}
