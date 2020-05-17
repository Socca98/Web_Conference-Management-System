import {NgModule} from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatDialogModule} from '@angular/material/dialog';
import {MatTabsModule} from '@angular/material/tabs';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatCardModule} from '@angular/material/card';
import {MatIconModule} from '@angular/material/icon';
import {MatTooltipModule} from '@angular/material/tooltip';

const MD_COMPONENT_MODULES = [
  MatButtonModule,
  MatInputModule,
  MatFormFieldModule,
  MatDialogModule,
  MatTabsModule,
  MatSnackBarModule,
  MatToolbarModule,
  MatCardModule,
  MatIconModule,
  MatTooltipModule,
];

@NgModule({
  imports: MD_COMPONENT_MODULES,
  exports: MD_COMPONENT_MODULES
})
export class AppAngularMaterialModule {
}
