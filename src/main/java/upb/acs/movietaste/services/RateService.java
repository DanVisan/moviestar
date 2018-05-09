package upb.acs.movietaste.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import upb.acs.movietaste.entities.Rating;
import upb.acs.movietaste.mapper.RatingMapper;
import upb.acs.movietaste.models.RatingModel;
import upb.acs.movietaste.repositories.RatingRepository;

import java.util.Objects;

@Service
public class RateService {

    private final RatingRepository ratingRepository;

    @Autowired
    public RateService(final RatingRepository ratingRepository) {
        this.ratingRepository = Objects.requireNonNull(ratingRepository, "ratingRepository must not be null");
    }

    public Page<RatingModel> getRatings(final String searchTerm, final int page, final int size) {
        final Pageable pageable = new PageRequest(page, size);
        return ratingRepository.findByMovie_TitleContainingIgnoreCase(searchTerm.trim(), pageable)
                .map(RatingMapper::toModel);
    }

    public void saveRating(final RatingModel ratingModel) {
        final Rating rating = RatingMapper.insertEntityMapping(ratingModel);
        ratingRepository.save(rating);
    }

    public void updateRating(final RatingModel ratingModel) {
        final Rating rating = RatingMapper.fullModelMapping(ratingModel);
        ratingRepository.save(rating);
    }

    public void deleteRating(final Long ratingId) {
        final Rating rating = Rating.builder().id(ratingId).build();
        ratingRepository.delete(rating);
    }
}
