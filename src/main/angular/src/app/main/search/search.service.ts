import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  private url = environment.url + "/api/v1/entrance";

  constructor(private _httpClient: HttpClient, private _router: Router) { }

  public search() {
    const HEADERS = new HttpHeaders().set('Content-Type', 'application/json');
    return this._httpClient.get<Array<Protocol>>(this.url,
      {
        headers: HEADERS
      }
    );
  }

}
