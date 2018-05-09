package upb.acs.movietaste.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class Rating {
    @Id
    @GeneratedValue(generator = "rating_seq_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "rating_seq_gen", sequenceName = "rating_seq", allocationSize = 10)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @OneToOne
    private Movie movie;

    private String score;
}
