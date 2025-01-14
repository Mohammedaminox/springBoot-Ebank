import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';
import { routes } from './app.routes';
import { provideHttpClient, withFetch } from '@angular/common/http'; // Import withFetch
import { provideAnimations } from '@angular/platform-browser/animations'; // Optional, if you use animations

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideHttpClient(withFetch()), // Enable fetch API
    provideAnimations(), // Optional, if you use animations
  ],
};
