import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { Http, ResponseContentType } from '@angular/http';
import { Observable } from 'rxjs/Observable';

@Injectable({
  providedIn: 'root'
})
export class PrintService {

  private urlProtocol = environment.url + "/api/v1/print/protocol/";
  private urlSeal = environment.url + "/api/v1/print/seal/";

  constructor(private _httpClient: HttpClient, private _router: Router) { }

  public printProcol(protocol) {
    return this._httpClient.get(this.urlProtocol + protocol,
      {
        observe: 'response',
        responseType: 'blob'
      });
  }

  public printSeal(protocol) {
    return this._httpClient.get(this.urlSeal + protocol,
      {
        observe: 'response',
        responseType: 'blob'
      });
  }

}
