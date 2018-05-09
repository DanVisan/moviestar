package upb.acs.movietaste.IT

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import spock.lang.Specification
import upb.acs.movietaste.services.MoviesService

@ContextConfiguration
@SpringBootTest
@SqlGroup([
        @Sql(value = ["/sql/insert_movies.sql"], executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = ["/sql/clear_movies.sql"], executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
])
class MoviesServiceIT extends Specification {

    @Autowired
    MoviesService moviesService

    def 'getMovieByImdbId'() {

    }
}
