import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Rating} from '../models/Rating';
import {environment} from '../../environments/environment';
import {catchError} from 'rxjs/operators';
import {Observable} from 'rxjs/Observable';
import {Page} from '../models/Page';

@Injectable()
export class RatingsService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  private URL = `${environment.URL}`;

  constructor(private http: HttpClient) {
  }

  getRatings(searchTitle: String, page: Number, size: Number): Observable<Page<Rating>> {
    return this.http.get<Page<Rating>>(`${this.URL}/ratings?title_like=${searchTitle}&page=${page}&size=${size}`)
      .pipe(
        catchError((error: any) => Observable.throw(error.json().error || 'Server error'))
      );
  }

  saveRating(rating: Rating): Observable<Rating> {
    return this.http.post(`${this.URL}/ratings`, JSON.stringify(rating), this.httpOptions)
      .pipe(
        catchError((error: any) => Observable.throw(error.json().error || 'Server error'))
      );
  }

  updateRating(rating: Rating): Observable<Rating> {
    return this.http.put(`${this.URL}/ratings`, JSON.stringify(rating), this.httpOptions)
      .pipe(
        catchError((error: any) => Observable.throw(error.json().error || 'Server error'))
      );
  }

  deleteRating(rating: Rating): Observable<Rating> {
    return this.http.delete(`${this.URL}/ratings/${rating.id}`)
      .pipe(
        catchError((error: any) => Observable.throw(error.json().error || 'Server error'))
      );
  }

}
