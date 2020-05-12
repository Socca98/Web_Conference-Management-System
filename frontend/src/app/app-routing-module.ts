import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomePageComponent} from './pages/home-page/home-page.component';
import {TabDetailsComponent} from './pages/tab-details/tab-details.component';
import {SubmissionsComponent} from './pages/submissions/submissions.component';
import {AuthGuard} from './login/auth.guard';
import {Role} from './shared/models/role';


const routes: Routes = [
  {
    path: '',
    component: HomePageComponent,
  },

  {
    path: 'conference/details',
    component: TabDetailsComponent,
    //canActivate: [AuthGuard],
  },

  {
    path: 'conference/submissions',
    component: SubmissionsComponent,
    //canActivate: [AuthGuard],
    data: {roles: [Role.Author, Role.PC_Member]}
  },

  {
    path: '**',
    redirectTo: '/'
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
