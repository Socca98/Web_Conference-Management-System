import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginDialogComponent} from './login/login/login-dialog.component';
import {RegisterDialogComponent} from './login/register/register-dialog.component';
import {HomePageComponent} from './pages/home-page/home-page.component';


const routes: Routes = [
  {path: '', component: HomePageComponent},

  {path: 'register', component: RegisterDialogComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
