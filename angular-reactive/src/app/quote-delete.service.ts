import {Injectable} from '@angular/core';

import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";

@Injectable()
export class QuoteDeleteService {

  url: string = 'http://localhost:8080/quotes-delete';

  constructor(private http: HttpClient) {
  }

  deleteQuote(quoteId: number): Observable<any> {
    let url = this.url + '/' + quoteId;

    return this.http.delete<void>(url);
  }

}
