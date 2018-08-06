import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainComponent } from './main.component';

import { MainRouting } from './main.routing';
import { RouterModule } from '@angular/router';

import { FlexLayoutModule } from '@angular/flex-layout';

import { MatIconModule, MatSidenavModule, MatCardModule, MatListModule, MatToolbarModule, MatButtonModule, MatProgressBarModule } from '@angular/material';

import { AuthGuard } from './auth.guard';
import { LoadingService } from './services/loading.service';
import { ErrorModule } from '../error/error.module';

@NgModule({
  imports: [
    CommonModule,
    MainRouting,
    RouterModule,
    FlexLayoutModule,
    MatIconModule,
    MatSidenavModule,
    MatCardModule,
    MatListModule,
    MatToolbarModule,
    MatButtonModule,
    MatProgressBarModule,
    ErrorModule
  ],
  declarations: [MainComponent],
  providers: [
    AuthGuard,
    LoadingService
  ]
})
export class MainModule { }