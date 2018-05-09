export class Page<T> {
  constructor(public content: Array<T>,
              public totalPages: number,
              public totalElements: number,
              public last: boolean,
              public first: boolean,
              public numberOfElements: number,
              public sort: any,
              public size: number,
              public number: number) {
  }
}
