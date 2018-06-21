import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MainComponent } from './main.component';
import { AuthGuard } from './auth.guard';

@NgModule({
  imports: [
    RouterModule.forChild([
      { 
        path: 'main', 
        component: MainComponent,
        canActivate: [AuthGuard],
        canActivateChild: [AuthGuard]
      }
    ])
  ]
})
export class MainRouting { }