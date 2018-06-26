import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SearchComponent } from './search.component';
import { RouterModule } from '@angular/router';
import { SearchRouting } from './search.routing';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    SearchRouting
  ],
  declarations: [SearchComponent]
})
export class SearchModule { }
