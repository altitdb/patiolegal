import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EntranceService {

  private url = environment.url + "/api/v1/entrance";

  constructor(private _httpCliente: HttpClient) { }

  public save(data) {
    const HEADERS = new HttpHeaders().set('Content-Type', 'application/json');
    return this._httpCliente.post(this.url,
      data,
      {
        headers: HEADERS
      }
    );
  }

}
