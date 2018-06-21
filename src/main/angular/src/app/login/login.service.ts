import { Injectable } from '@angular/core';
import { environment }  from '../../environments/environment'
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { Promise } from 'q';
import { HttpParams } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';

@Injectable()
export class LoginService {

  private url = window.location.origin + "/api/v1/login";

  constructor(private _httpCliente: HttpClient, private _router:Router) { }

  public login(data) {
    const ACCESS_DATA = new HttpParams()
                          .set('username', data.user)
                          .append('password', data.password);
    const HEADERS = new HttpHeaders().set('Content-Type', 'application/json');

    return this._httpCliente.post(this.url,
                                  ACCESS_DATA.toString(),
                                 {
                                   headers: HEADERS
                                  }
                                );
 }

  public logoff(){
    sessionStorage.removeItem("access");
    this._router.navigate(["/login"]);
  }

}