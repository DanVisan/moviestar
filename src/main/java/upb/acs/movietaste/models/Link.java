package upb.acs.movietaste.models;

public class Link {
    private String movieId;
    private String imdbId;
    private String tmdbId;

    public Link(final String movieId, final String imdbId, final String tmdbId) {
        this.movieId = movieId;
        this.imdbId = imdbId;
        this.tmdbId = tmdbId;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getImdbId() {
        return imdbId;
    }

    public String getTmdbId() {
        return tmdbId;
    }

    @Override
    public String toString() {
        return "Link{" +
                "movieId='" + movieId + '\'' +
                ", imdbId='" + imdbId + '\'' +
                ", tmdbId='" + tmdbId + '\'' +
                '}';
    }
}
