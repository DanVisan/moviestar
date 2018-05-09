package upb.acs.movietaste.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import upb.acs.movietaste.entities.Rating;

public interface RatingRepository extends JpaRepository<Rating, String>, JpaSpecificationExecutor<Rating> {
    Page<Rating> findByMovie_TitleContainingIgnoreCase(final String searchTerm, final Pageable pageable);
}
