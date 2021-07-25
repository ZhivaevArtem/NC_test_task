import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";

@Injectable()
export class AuthHeaderInterceptor implements HttpInterceptor {

  constructor() {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const header = localStorage.getItem("NC_auth_header");
    if (header && req.url != `${environment.apiUrl}/auth/sign_up`
        && req.url != `${environment.apiUrl}/auth/sign_in`) {
      const nextReq = req.clone({
        setHeaders: {"Authorization": header}
      });
      return next.handle(nextReq);
    }
    return next.handle(req);
  }
}
