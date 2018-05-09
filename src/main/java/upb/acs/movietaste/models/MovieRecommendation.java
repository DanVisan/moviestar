package upb.acs.movietaste.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import upb.acs.movietaste.entities.Movie;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieRecommendation {
    private Movie movie;
    private String score;
}
