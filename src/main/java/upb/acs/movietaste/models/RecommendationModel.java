package upb.acs.movietaste.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RecommendationModel {
    private String item;
    private Double score;
}
