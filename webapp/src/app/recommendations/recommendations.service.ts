import { Injectable } from '@angular/core';
import {Observable} from "rxjs/Observable";
import {MovieRecommendation} from "../models/MovieRecommendation";
import {catchError} from "rxjs/operators";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class RecommendationsService {

  private URL = `${environment.URL}`;
  numberOfRecommendations = 5;

  constructor(private http: HttpClient) { }

  getRecommendations(searchTerm: string): Observable<MovieRecommendation[]> {
    return this.http.get<MovieRecommendation[]>(`${this.URL}/recommendations?title_like=${searchTerm}&nr=${this.numberOfRecommendations}`)
      .pipe(
        catchError((error: any) => Observable.throw(error.json().error || 'Server error'))
      );
  }

}
