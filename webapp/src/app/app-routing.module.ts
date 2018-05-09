import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {MoviesComponent} from "./movies/movies.component";
import {RatingsComponent} from "./ratings/ratings.component";
import {RecommendationsComponent} from "./recommendations/recommendations.component";

const routes: Routes = [
  { path: '', component: MoviesComponent },
  { path: 'movies', component: MoviesComponent },
  { path: 'ratings', component: RatingsComponent },
  { path: 'recommendations', component: RecommendationsComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
