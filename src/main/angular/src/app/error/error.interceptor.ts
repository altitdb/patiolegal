import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/do';
import { ErrorComponent } from './error.component';
import { MatDialog } from '@angular/material';
import { Router } from '@angular/router';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

  constructor(private dialog: MatDialog, private _router: Router) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    return next.handle(request).do((event: HttpEvent<any>) => { }, (err: any) => {
      if (err instanceof HttpErrorResponse) {
        if (err.status === 400) {
          this.openDialog({ message: err.error.message })
        } else if (err.status === 401) {
          this.openDialog({ message: "Sessão expirada" });
          this._router.navigate(["/login"]);
        } else if (err.status === 404) {
          this.openDialog({ message: "Serviço não disponível"});
        } else {
          this.openDialog({ message: "Não foi possível validar a causa do erro" })  
        }
      } else {
        this.openDialog({ code: "Indisponível", message: "Não foi possível validar a causa do erro" })
      }
    });
  }

  private openDialog(info) {
    this.dialog.open(ErrorComponent, {
      data: info
    });
  }

}