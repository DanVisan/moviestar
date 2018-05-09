package upb.acs.movietaste.UT

import spock.lang.Specification
import upb.acs.movietaste.repositories.MovieRepository
import upb.acs.movietaste.services.MoviesService

class MovieServiceSpec extends Specification {

    def movieRepository = Mock(MovieRepository)
    def movieService = new MoviesService(movieRepository)

}
