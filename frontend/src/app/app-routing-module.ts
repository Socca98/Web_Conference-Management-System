import {NgModule} from '@angular/core';
import {RouterModule , Routes } from '@angular/router';
import {LoginComponent} from './login/login/login.component';
import { RegisterComponent } from './login/register/register.component';


const routes: Routes = [

    { path:'' , redirectTo : '/login/login', pathMatch : 'full'},
    {path:"login" , component:LoginComponent},
    {path:"register" , component: RegisterComponent}
];

@NgModule({
    imports : [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class AppRoutingModule {}