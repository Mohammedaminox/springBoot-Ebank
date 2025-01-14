import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, catchError, map, Observable, of, tap, throwError } from 'rxjs';
import { Router } from '@angular/router';
import { environment } from '../../../environments/environment';
import { TokenService } from '../tokenService/token.service';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private router = inject(Router);
  private apiUrl = environment.apiUrl;
  private isAuthenticatedSubject = new BehaviorSubject<boolean>(false);
  isAuthenticated$ = this.isAuthenticatedSubject.asObservable();

  constructor(private http: HttpClient, private tokenService: TokenService) {
    // Check if localStorage is available (browser environment)
    const token = typeof localStorage !== 'undefined' ? localStorage.getItem('token') : null;
    this.isAuthenticatedSubject.next(!!token);
  }

  login(email: string, password: string): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const body = { email, password };
    return this.http.post(`${this.apiUrl}/login`, body, { headers, responseType: 'text' }).pipe(
      tap((response: any) => {
        if (response && typeof localStorage !== 'undefined') {
          localStorage.setItem('accessToken', response);
          localStorage.setItem('role', <string>this.tokenService.getUserRole());
          this.isAuthenticatedSubject.next(true);
        }
      }),
      catchError((error) => {
        console.error('Login failed', error);
        return of(null);
      })
    );
  }

  register(username: string, password: string): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const body = { username, password };
    return this.http.post(`${this.apiUrl}/register`, body, { headers, responseType: 'json' }).pipe(
      catchError((error) => {
        console.error('Registration failed', error);
        return of(null);
      })
    );
  }

  refreshToken(refreshToken: string): Observable<any> {
    return this.http.post<{ accessToken: string }>('/api/auth/refresh', { refreshToken }).pipe(
      map((response) => response.accessToken),
      catchError((error) => {
        this.router.navigate(['/login']);
        return throwError(error);
      })
    );
  }

  logout(): Observable<any> {
    return this.http.post(`${this.apiUrl}/logout`, {}).pipe(
      tap(() => {
        if (typeof localStorage !== 'undefined') {
          localStorage.removeItem('accessToken');
          localStorage.removeItem('refreshToken');
          localStorage.removeItem('role');
        }
        this.isAuthenticatedSubject.next(false);
      }),
      catchError((error) => {
        console.error('Logout failed', error);
        if (typeof localStorage !== 'undefined') {
          localStorage.removeItem('accessToken');
          localStorage.removeItem('refreshToken');
          localStorage.removeItem('role');
        }
        this.isAuthenticatedSubject.next(false);
        return of(null);
      })
    );
  }

  getToken(): string | null {
    return typeof localStorage !== 'undefined' ? localStorage.getItem('accessToken') : null;
  }

  getRefreshToken(): string | null {
    return typeof localStorage !== 'undefined' ? localStorage.getItem('refreshToken') : null;
  }
}
