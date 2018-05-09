CREATE TABLE movie
(
  id            VARCHAR(255) NOT NULL
    CONSTRAINT movie_pkey
    PRIMARY KEY,
  imdb_id       VARCHAR(255) NOT NULL,
  tmdb_id       VARCHAR(255),
  title         VARCHAR(255) NOT NULL,
  release_date  VARCHAR(255),
  genres        VARCHAR(255),
  overview      VARCHAR(3000),
  poster_path   VARCHAR(255),
  backdrop_path VARCHAR(255),
  vote_average  VARCHAR(10),
  vote_count    VARCHAR(10)
);