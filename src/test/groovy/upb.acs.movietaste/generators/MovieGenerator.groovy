package upb.acs.movietaste.generators

import upb.acs.movietaste.entities.Movie

class MovieGenerator {
    static aMovie(Map overrides = [:]) {
        Map values = [
                id          : '1',
                imdbId      : 'tt0114709',
                tmdbId      : '862',
                title       : 'Toy Story',
                releaseDate : '1995-10-30',
                genres      : 'Animation|Comedy|Family',
                overview    : 'description',
                posterPath  : '/rhIRbceoE9lR4veEXuwCC2wARtG.jpg',
                backdropPath: '/dji4Fm0gCDVb9DQQMRvAI8YNnTz.jpg'
        ]
        values << overrides
        return Movie.newInstance(values)
    }
}
