import {NgModule} from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatDialogModule} from '@angular/material/dialog';

const MD_COMPONENT_MODULES = [
  MatButtonModule,
  MatInputModule,
  MatFormFieldModule,
  MatDialogModule,

];

@NgModule({
  imports: MD_COMPONENT_MODULES,
  exports: MD_COMPONENT_MODULES
})
export class AppAngularMaterialModule {
}
