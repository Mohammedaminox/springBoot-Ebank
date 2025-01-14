import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { routes } from './app.routes';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http'; // Import HTTP_INTERCEPTORS
import { AuthInterceptor } from './auth/authInterceptor/auth.interceptor';

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
    HttpClientModule, // Add HttpClientModule here
  ],
  exports: [RouterModule],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }, // Add interceptor here
  ],
})
export class AppModule {}
