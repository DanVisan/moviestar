import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { MoviesComponent } from './movies/movies.component';
import { MoviesService } from './movies/movies.service';
import {FormsModule} from "@angular/forms";
import { HeaderComponent } from './header/header.component';
import { HttpClientModule } from "@angular/common/http";
import { RatingsComponent } from './ratings/ratings.component';
import { RatingsService } from './ratings/ratings.service';
import { AppRoutingModule } from './/app-routing.module';
import { RecommendationsComponent } from './recommendations/recommendations.component';
import { RecommendationsService } from './recommendations/recommendations.service';


@NgModule({
  declarations: [
    AppComponent,
    MoviesComponent,
    HeaderComponent,
    RatingsComponent,
    RecommendationsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [MoviesService, RatingsService, RecommendationsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
