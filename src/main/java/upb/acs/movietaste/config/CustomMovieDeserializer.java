package upb.acs.movietaste.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import upb.acs.movietaste.entities.Movie;

import java.io.IOException;
import java.util.Iterator;

public class CustomMovieDeserializer extends StdDeserializer<Movie> {

    public CustomMovieDeserializer() {
        this(null);
    }

    public CustomMovieDeserializer(final Class<?> vc) {
        super(vc);
    }

    @Override
    public Movie deserialize(JsonParser parser, DeserializationContext deserializer) throws IOException {
        Movie movie = new Movie();
        ObjectCodec codec = parser.getCodec();
        JsonNode node = codec.readTree(parser);

        JsonNode movieNode = node.get("movie_results").iterator().next();
        movie.setTitle(movieNode.get("title").asText());
        movie.setOverview(movieNode.get("overview").asText());
        if (movieNode.get("release_date") != null) {
            movie.setReleaseDate(movieNode.get("release_date").asText());
        } else {
            movie.setReleaseDate("");
        }
        if (movieNode.get("poster_path") != null) {
            movie.setPosterPath(movieNode.get("poster_path").asText());
        } else {
            movie.setPosterPath("");
        }
        if (movieNode.get("backdrop_path") != null) {
            movie.setBackdropPath(movieNode.get("backdrop_path").asText());
        } else {
            movie.setBackdropPath("");
        }
        if (movieNode.get("vote_average") != null) {
            movie.setVoteAverage(movieNode.get("vote_average").asText());
        } else {
            movie.setVoteAverage("");
        }
        if (movieNode.get("vote_count") != null) {
            movie.setVoteCount(movieNode.get("vote_count").asText());
        } else {
            movie.setVoteCount("");
        }

        StringBuilder genres = new StringBuilder("");
        if (movieNode.get("genre_ids") != null) {
            Iterator<JsonNode> iterator = movieNode.get("genre_ids").iterator();
            while (iterator.hasNext()) {
                genres.append(getGenreName(iterator.next()));
                if (iterator.hasNext()) {
                    genres.append(", ");
                }
            }
        }
        movie.setGenres(genres.toString());
        return movie;
    }

    private String getGenreName(final JsonNode id) {
        switch (id.toString()) {
            case "28":
                return "Action";
            case "53":
                return "Thriller";
            case "18":
                return "Drama";
            case "35":
                return "Comedy";
            case "27":
                return "Horror";
            case "12":
                return "Adventure";
            case "16":
                return "Animation";
            case "80":
                return "Crime";
            case "99":
                return "Documentary";
            case "10751":
                return "Family";
            case "14":
                return "Fantasy";
            case "36":
                return "History";
            case "10402":
                return "Music";
            case "9648":
                return "Mystery";
            case "10749":
                return "Romance";
            case "878":
                return "Science Fiction";
            case "10770":
                return "TV Movie";
            case "10752":
                return "War";
            case "37":
                return "Western";
            default:
                return "";
        }
    }
}
