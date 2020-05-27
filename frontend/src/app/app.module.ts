import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {RouterModule} from '@angular/router';
import {LoginDialogComponent} from './login/login/login-dialog.component';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
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
import {TokenInterceptorService} from './login/token-interceptor.service';
import {TabReviewingComponent} from './pages/tab-reviewing/tab-reviewing.component';
import {TabBiddingComponent} from './pages/tab-bidding/tab-bidding.component';
import {TabAssignPapersComponent} from './pages/tab-assign-papers/tab-assign-papers.component';
import {ShowRecommendationsDialogComponent} from './shared/components/show-recommendations-dialog/show-recommendations-dialog.component';
import {TabEvaluationsComponent} from './pages/tab-evaluations/tab-evaluations.component';
import {TabAbstractDialogComponent} from './shared/components/tab-abstract-dialog/tab-abstract-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginDialogComponent,
    RegisterDialogComponent,
    ShowRecommendationsDialogComponent,
    TabAbstractDialogComponent, // it should be addabstractdialog

    HomePageComponent,
    ConferenceComponent,

    TabDetailsComponent,
    TabSubmissionsComponent,
    TabBiddingComponent,
    TabAssignPapersComponent,
    TabReviewingComponent,
    TabEvaluationsComponent,
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

  providers: [AuthGuard, AuthService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true,
    }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
