import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomePageComponent} from './pages/home-page/home-page.component';
import {TabDetailsComponent} from './pages/tab-details/tab-details.component';
import {TabSubmissionsComponent} from './pages/tab-submissions/tab-submissions.component';
import {AuthGuard} from './login/auth.guard';
import {Role} from './shared/models/role';
import {TabReviewingComponent} from './pages/tab-reviewing/tab-reviewing.component';


const routes: Routes = [
  {
    path: '',
    component: HomePageComponent,
  },

  {
    path: 'conference/details',
    component: TabDetailsComponent,
    canActivate: [AuthGuard],
  },

  {
    path: 'conference/submissions',
    component: TabSubmissionsComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.Author, Role.PC_Member]}
  },

  {
    path: 'conference/reviewing',
    component: TabReviewingComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.PC_Member]}
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
