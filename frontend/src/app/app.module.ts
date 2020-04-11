import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {RouterModule} from '@angular/router';
import {LoginComponent} from './login/login/login.component';
import {HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AppAngularMaterialModule} from './app-angular-material.module';
import {FormsModule} from '@angular/forms';
import {TabDetailsComponent} from './shared/components/tab-details/tab-details.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    TabDetailsComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    RouterModule,
    HttpClientModule,
    AppAngularMaterialModule,
    FormsModule,

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
