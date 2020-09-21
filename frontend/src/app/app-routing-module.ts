import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomePageComponent} from './pages/home-page/home-page.component';
import {TabDetailsComponent} from './pages/tab-details/tab-details.component';
import {TabSubmissionsComponent} from './pages/tab-submissions/tab-submissions.component';
import {TabAssignPapersComponent} from './pages/tab-assign-papers/tab-assign-papers.component';
import {AuthGuard} from './login/auth.guard';
import {Role} from './shared/models/role';
import {TabReviewingComponent} from './pages/tab-reviewing/tab-reviewing.component';
import {TabEvaluationsComponent} from './pages/tab-evaluations/tab-evaluations.component';
import {TabSectionsComponent} from './pages/tab-sections/tab-sections.component';
import {TabResultsComponent} from './pages/tab-results/tab-results.component';
import {TabBiddingComponent} from './pages/tab-bidding/tab-bidding.component';


const routes: Routes = [
  {
    path: '',
    component: HomePageComponent,
  },

  {
    path: 'invitation/:linkId',
    component: HomePageComponent,
  },

  {
    path: 'conference/details',
    component: TabDetailsComponent,
    canActivate: [AuthGuard],
  },

  {
    path: 'conference/sections',
    component: TabSectionsComponent,
    canActivate: [AuthGuard],
  },

  {
    path: 'conference/submissions',
    component: TabSubmissionsComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.Author, Role.PC_Member]}
  },

  {
    path: 'conference/results',
    component: TabResultsComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.Author, Role.PC_Member]}
  },

  {
    path: 'conference/bidding',
    component: TabBiddingComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.PC_Member, Role.CoChair]}
  },

  {
    path: 'conference/assign-papers',
    component: TabAssignPapersComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.CoChair]}
  },

  {
    path: 'conference/reviewing',
    component: TabReviewingComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.PC_Member, Role.CoChair]}
  },

  {
    path: 'conference/evaluations',
    component: TabEvaluationsComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.Chair, Role.CoChair]}
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
