import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EntranceComponent } from './entrance.component';
import { RouterModule } from '@angular/router';
import { EntranceRouting } from './entrance.routing';
import { MatStepperModule } from '@angular/material/stepper';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { FormBuilder } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatRadioModule } from '@angular/material/radio';
import { MatNativeDateModule } from '@angular/material';
import { MAT_DATE_LOCALE } from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';
import { TextMaskModule } from 'angular2-text-mask';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    EntranceRouting,
    FlexLayoutModule,
    MatStepperModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatRadioModule,
    TextMaskModule
  ],
  providers: [
    FormBuilder,
    {provide: MAT_DATE_LOCALE, useValue: 'pt-BR'}
  ],
  declarations: [EntranceComponent]
})
export class EntranceModule { }
