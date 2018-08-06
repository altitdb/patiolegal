import { Component, OnInit, Inject, ViewEncapsulation } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { PrintService } from '../../services/print.service';
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-seal',
  templateUrl: './seal.component.html',
  styleUrls: ['./seal.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class SealComponent implements OnInit {

  form: FormGroup;
  
  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
              private _formBuilder: FormBuilder,
              private _printService: PrintService) { }

  ngOnInit() {
    this.form = this._formBuilder.group({
      protocol: new FormControl({value: this.data, disabled: true}, Validators.required),
      amount: ['', Validators.required],
      reason: ['', Validators.required]
    });
  }

  print() {
    this._printService.printSeal(this.form.value).subscribe(
      suc => {
        saveAs(suc.body, 'lacre.pdf')
      });
  }

}
