import {NgModule} from '@angular/core';
import {RouterModule , Routes } from '@angular/router';
import {LoginComponent} from './login/login/login.component';
import { RegisterComponent } from './login/register/register.component';
import {SubmissionsComponent} from './pages/submissions/submissions.component';

const routes: Routes = [

    { path:'' , redirectTo : '/login', pathMatch : 'full'},
    {path:"login" , component:LoginComponent},
    {path:"register" , component: RegisterComponent},
    {path:'pages/submissions', component: SubmissionsComponent}
];

@NgModule({
    imports : [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class AppRoutingModule {}
