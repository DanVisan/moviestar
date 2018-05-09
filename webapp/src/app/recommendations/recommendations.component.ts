import { Component, OnInit } from '@angular/core';
import {MovieRecommendation} from "../models/MovieRecommendation";
import {RecommendationsService} from "./recommendations.service";

@Component({
  selector: 'app-recommendations',
  templateUrl: './recommendations.component.html',
  styleUrls: ['./recommendations.component.css']
})
export class RecommendationsComponent implements OnInit {

  searchTitle = '';
  recommendations: MovieRecommendation[];

  constructor(private recommendationsService: RecommendationsService) { }

  ngOnInit() {
    this.getRecommendations();
  }

  private getRecommendations() {
    this.recommendationsService.getRecommendations(this.searchTitle)
      .subscribe(recommendations => {
        this.recommendations = recommendations;
      })
  }

  getFiltered() {
    this.getRecommendations();
  }

  setPicture(recommendation: MovieRecommendation): String {
    const movie = recommendation.movie;
    const imageURIRoot = 'https://image.tmdb.org/t/p/w500';
    if (movie.posterPath != 'null') {
      return imageURIRoot + movie.posterPath;
    } else if (movie.backdropPath != 'null') {
      return imageURIRoot + movie.backdropPath;
    } else {
      return 'assets/img/default.png'
    }
  }

  getMovieOverview(recommendation: MovieRecommendation) {
    const overview = recommendation.movie.overview;
    return overview.length > 100 ? overview.slice(0, 100) + ' ...' : overview;
  }
}
