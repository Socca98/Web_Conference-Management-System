import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomePageComponent} from './pages/home-page/home-page.component';
import {TabDetailsComponent} from './pages/tab-details/tab-details.component';


const routes: Routes = [
  {path: '', component: HomePageComponent},
  {path: 'conference', component: TabDetailsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
