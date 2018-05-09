package upb.acs.movietaste.mapper;

import upb.acs.movietaste.entities.Rating;
import upb.acs.movietaste.models.RatingModel;

public final class RatingMapper {

    private RatingMapper() {
        //NOSONAR
    }

    public static Rating insertEntityMapping(final RatingModel model) {
        return Rating.builder()
                .movie(model.getMovie())
                .score(model.getScore())
                .build();
    }

    public static Rating fullModelMapping(final RatingModel model) {
        return Rating.builder()
                .id(model.getId())
                .movie(model.getMovie())
                .score(model.getScore())
                .build();
    }

    public static RatingModel toModel(final Rating entity) {
        return RatingModel.builder()
                .id(entity.getId())
                .movie(entity.getMovie())
                .score(entity.getScore())
                .build();
    }
}
