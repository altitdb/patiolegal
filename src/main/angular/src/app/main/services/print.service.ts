import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PrintService {

  private urlProtocol = environment.url + "/api/v1/print/protocol/";
  private urlSeal = environment.url + "/api/v1/print/seal/";
  constructor(private _httpClient: HttpClient) { }

  public generateProtocol(protocol) {
    var data = { 'protocol': protocol };
    const HEADERS = new HttpHeaders().set('Content-Type', 'application/json');
    return this._httpClient.post<FileIdentifier>(this.urlProtocol,
      data,
      {
        headers: HEADERS
      }
    );
  }

  public printProcol(identifier) {
    return this.print(this.urlProtocol + identifier);
  }

  public printSeal(id) {
    return this.print(this.urlSeal + id);
  }

  private print(url) {
    return this._httpClient.get(url,
      {
        observe: 'response',
        responseType: 'blob'
      });
  }

}
