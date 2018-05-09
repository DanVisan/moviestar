import {Movie} from "./movie";

export class Rating {
  constructor(public id: number,
              public movie: Movie,
              public score: string) {
  }
}
