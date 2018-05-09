import {Component, OnInit} from '@angular/core';
import {MoviesService} from "./movies.service";
import {Movie} from "../models/movie";
import {RatingsService} from "../ratings/ratings.service";
import {Rating} from "../models/Rating";

@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.css']
})
export class MoviesComponent implements OnInit {

  searchTitle = '';
  movies: Movie[];

  constructor(private moviesService: MoviesService,
              private ratingsService: RatingsService) {
  }

  ngOnInit() {
    this.getMovies();
  }

  getMovies(): void {
    this.moviesService.getMovies(this.searchTitle, 0, 16)
      .subscribe(movies => {
        this.movies = movies.content;
      });
  }

  getMovieOverview(movie: Movie) {
    return movie.overview.length > 100 ? movie.overview.slice(0, 100) + ' ...' : movie.overview;
  }

  setPicture(movie: Movie): String {
    const imageURIRoot = 'https://image.tmdb.org/t/p/w500';
    if (movie.posterPath != 'null') {
      return imageURIRoot + movie.posterPath;
    } else if (movie.backdropPath != 'null') {
      return imageURIRoot + movie.backdropPath;
    } else {
      return 'assets/img/default.png'
    }
  }

  getFiltered() {
    this.getMovies();
  }

  rate(movie: Movie, score: string) {
    const rating = new Rating(null, movie, score);
    this.ratingsService.saveRating(rating)
      .subscribe(() => {},
        (error) => console.log(error)
      );
  }
}
