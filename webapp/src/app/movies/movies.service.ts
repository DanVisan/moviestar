import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {Movie} from '../models/movie';
import {HttpClient} from '@angular/common/http';
import {Page} from '../models/Page';
import {catchError} from 'rxjs/operators';
import {environment} from '../../environments/environment';
import {MovieRecommendation} from "../models/MovieRecommendation";

@Injectable()
export class MoviesService {

  private URL = `${environment.URL}`;

  constructor(private http: HttpClient) {
  }

  getMovies(searchTitle: String, page: Number, size: Number): Observable<Page<Movie>> {
    return this.http.get<Page<Movie>>(`${this.URL}/movies/paginated?title_like=${searchTitle}&page=${page}&size=${size}`)
      .pipe(
        catchError((error: any) => Observable.throw(error.json().error || 'Server error'))
      );
  }

}
