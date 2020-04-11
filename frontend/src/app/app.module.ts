import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {RouterModule , Routes } from '@angular/router';
import {LoginComponent} from './login/login/login.component';
import {HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AppAngularMaterialModule} from './app-angular-material.module';
import {FormsModule} from '@angular/forms';
import {TabDetailsComponent} from './shared/components/tab-details/tab-details.component';
import {ConferencesListComponent} from './pages/conferences-list/conferences-list.component';
import { RegisterComponent } from './login/register/register.component';
import { AppRoutingModule } from './app-routing-module';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    TabDetailsComponent,
    ConferencesListComponent,
    RegisterComponent
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
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
