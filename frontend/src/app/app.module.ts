import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {RouterModule} from '@angular/router';
import {LoginDialogComponent} from './login/login/login-dialog.component';
import {HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AppAngularMaterialModule} from './app-angular-material.module';
import {FormsModule} from '@angular/forms';
import {RegisterDialogComponent} from './login/register/register-dialog.component';
import {AppRoutingModule} from './app-routing-module';
import {HomePageComponent} from './pages/home-page/home-page.component';
import {ConferenceComponent} from './shared/components/conference/conference.component';
import {TabDetailsComponent} from './pages/tab-details/tab-details.component';
import {AuthGuard} from './login/auth.guard';
import {AuthService} from './login/auth.service';
import {TabSubmissionsComponent} from './pages/tab-submissions/tab-submissions.component';
import {MatTooltipModule} from '@angular/material/tooltip';

@NgModule({
  declarations: [
    AppComponent,
    LoginDialogComponent,
    RegisterDialogComponent,
    HomePageComponent,
    ConferenceComponent,
    TabDetailsComponent,
    TabSubmissionsComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    RouterModule,
    HttpClientModule,
    AppAngularMaterialModule,
    FormsModule,
    AppRoutingModule,
  ],

  providers: [AuthGuard, AuthService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
