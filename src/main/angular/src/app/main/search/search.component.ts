import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { SearchService } from './search.service';
import { MatTableDataSource } from '@angular/material/table';
import { PrintService } from '../services/print.service';
import { saveAs } from 'file-saver';
import { MatDialog } from '@angular/material';
import { SealComponent } from './seal/seal.component';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  filtred: boolean = false;
  form: FormGroup;
  displayedColumns: string[] = ['protocol', 'entranceDate', 'exitDate', 'sportingPlate', 'originalPlate', 'printProtocol', 'printSeals', 'exit'];
  dataSource: MatTableDataSource<Protocol>;

  constructor(private _formBuilder: FormBuilder, 
              private _router: Router,
              private _searchService: SearchService,
              private _printService: PrintService,
              private _sealDialog: MatDialog) { }

  ngOnInit() {
    this.form = this._formBuilder.group({
      protocol: '',
      startDate: '',
      endDate: ''
    });
  }

  search() {
    this.filtred = true;
    this._searchService.search(this.form.value).subscribe(
      suc => {
        this.dataSource = new MatTableDataSource(suc);
      }
    );
  }

  printProtocol(protocol) {
    this._printService.printProcol(protocol).subscribe(
      suc => {
        saveAs(suc.body, 'protocolo.pdf')
      }
    );
  }

  printSeals(protocol) {
    this._sealDialog.open(SealComponent, {
      data: protocol
    });
  }

  exit(protocol) {
    this._router.navigate(["/main/exit", protocol]);
  }

}
