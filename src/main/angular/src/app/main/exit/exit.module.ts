import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ExitComponent } from './exit.component';
import { RouterModule } from '@angular/router';
import { ExitRouting } from './exit.routing';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    ExitRouting
  ],
  declarations: [ExitComponent]
})
export class ExitModule { }
