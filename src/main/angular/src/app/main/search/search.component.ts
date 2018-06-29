import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';

export interface Protocol {
  entranceDate: Date;
  exitDate: Date;
  protocol: string;  
  sportingPlate: string;
  originalPlate: string;
}

const ELEMENT_DATA: Protocol[] = [
  {entranceDate: new Date(), exitDate: new Date(), protocol: 'PC120318200012018', sportingPlate: 'ASV-7099', originalPlate: ''},
  {entranceDate: new Date(), exitDate: null, protocol: 'PM120318200012018', sportingPlate: 'ATX-8080', originalPlate: 'ATX-8080'},
  {entranceDate: new Date(), exitDate: null, protocol: 'PM120318200012018', sportingPlate: 'AAA-9587', originalPlate: 'AAA-9587'}
];

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  filtred: boolean = false;
  form: FormGroup;
  displayedColumns: string[] = ['entranceDate', 'exitDate', 'protocol', 'sportingPlate', 'originalPlate', 'printProtocol', 'printSeals', 'exit'];
  dataSource = ELEMENT_DATA;

  constructor(private _formBuilder: FormBuilder, private _router: Router) { }

  ngOnInit() {
    this.form = this._formBuilder.group({
      protocol: '',
      startDate: '',
      endDate: ''
    });
  }

  search() {
    this.filtred = true;
  }

  printProtocol(protocol) {

  }

  printSeals(protocol) {
    
  }

  exit(protocol) {
    this._router.navigate(["/main/exit", protocol]);
  }
}
