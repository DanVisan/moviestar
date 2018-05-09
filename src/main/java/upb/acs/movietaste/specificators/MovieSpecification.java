package upb.acs.movietaste.specificators;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import upb.acs.movietaste.entities.Movie;

public class MovieSpecification {

    public MovieSpecification() {
        //NOSONAR
    }

    public static Specification<Movie> titleLike(final String searchTerm) {
        return (root, query, builder) -> builder.like(
                builder.lower(root.get("title")), getNameLikePattern(searchTerm));
    }

    private static String getNameLikePattern(final String searchTerm) {
        return StringUtils.isEmpty(searchTerm) ? "%" : "%" + searchTerm.trim().toLowerCase() + "%";
    }
}
