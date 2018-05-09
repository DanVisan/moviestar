package upb.acs.movietaste.models;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;
import upb.acs.movietaste.entities.Movie;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class RatingModel {
    private long id;

    @NotNull
    private Movie movie;

    @NotEmpty
    @Pattern(regexp = "([1-4].[05])|(0.5)|(5.0)")
    private String score;
}
