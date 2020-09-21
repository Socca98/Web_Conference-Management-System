import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {RouterModule} from '@angular/router';
import {LoginDialogComponent} from './login/login/login-dialog.component';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AppAngularMaterialModule} from './app-angular-material.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
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
import {AddAbstractDialogComponent} from './shared/components/add-abstract-dialog/add-abstract-dialog.component';
import {TabSectionsComponent} from './pages/tab-sections/tab-sections.component';
import {AddSectionDialogComponent} from './shared/components/add-section-dialog/add-section-dialog.component';
import {AddRecommendationDialogComponent} from './shared/components/add-recommendation-dialog/add-recommendation-dialog.component';
import {AttendSectionDialogComponent} from './shared/components/attend-section-dialog/attend-section-dialog.component';
import {TabResultsComponent} from './pages/tab-results/tab-results.component';
import {CreateConferenceDialogComponent} from './shared/components/create-conference-dialog/create-conference-dialog.component';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {EditAbstractDialogComponent} from './shared/components/edit-abstract-dialog/edit-abstract-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginDialogComponent,
    RegisterDialogComponent,
    ShowRecommendationsDialogComponent,
    AddAbstractDialogComponent,
    EditAbstractDialogComponent,
    AddSectionDialogComponent,
    AddRecommendationDialogComponent,
    AttendSectionDialogComponent,
    CreateConferenceDialogComponent,

    HomePageComponent,
    ConferenceComponent,

    TabDetailsComponent,
    TabSubmissionsComponent,
    TabBiddingComponent,
    TabAssignPapersComponent,
    TabReviewingComponent,
    TabEvaluationsComponent,
    TabSectionsComponent,
    TabResultsComponent,
  ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        RouterModule,
        HttpClientModule,
        AppAngularMaterialModule,
        FormsModule,
        AppRoutingModule,
        MatCheckboxModule,
        ReactiveFormsModule,
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
