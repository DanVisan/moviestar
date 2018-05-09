package upb.acs.movietaste.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Movie {
    @Id
    private String id;

    private String imdbId;

    private String tmdbId;

    private String title;

    private String releaseDate;

    private String genres;

    private String overview;

    private String posterPath;

    private String backdropPath;

    private String voteAverage;

    private String voteCount;
}
