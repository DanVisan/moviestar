import {Component, OnInit} from '@angular/core';
import {RatingsService} from './ratings.service';
import {Rating} from '../models/Rating';

@Component({
  selector: 'app-ratings',
  templateUrl: './ratings.component.html',
  styleUrls: ['./ratings.component.css']
})
export class RatingsComponent implements OnInit {

  searchTitle = '';
  ratings: Rating[];

  constructor(private ratingService: RatingsService) {
  }

  ngOnInit() {
    this.getRatings();
  }

  getRatings() {
    this.ratingService.getRatings(this.searchTitle, 0, 16)
      .subscribe(ratings => {
          this.ratings = ratings.content;
        },
        error => console.log(error)
      );
  }

  getMovieOverview(rating: Rating) {
    const overview = rating.movie.overview;
    return overview.length > 100 ? overview.slice(0, 100) + ' ...' : overview;
  }

  selectRating(rating: Rating, score: string) {
    const updatedRating = new Rating(rating.id, rating.movie, score);
    this.ratings.find(elem => elem.id == rating.id).score = score;
    this.ratingService.updateRating(updatedRating)
      .subscribe(() => {},
        (error) => console.log(error)
      );
  }

  delete(rating: Rating) {
    this.ratingService.deleteRating(rating)
      .subscribe(
        () => {
          this.getRatings();
        },
        error => console.log(error)
      );
  }

  getFiltered() {
    this.getRatings();
  }
}
