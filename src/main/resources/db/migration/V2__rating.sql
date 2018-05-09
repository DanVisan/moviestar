CREATE SEQUENCE rating_seq;
CREATE TABLE rating
(
  id       BIGINT       NOT NULL
    CONSTRAINT ratings_pkey
    PRIMARY KEY,
  movie_id VARCHAR(255) NOT NULL UNIQUE,
  score    VARCHAR(3)   NOT NULL
);