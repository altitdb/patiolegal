import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { SearchService } from './search.service';
import { MatTableDataSource } from '@angular/material/table';
import { PrintService } from '../services/print.service';
import { saveAs } from 'file-saver';
import { MatDialog } from '@angular/material';
import { SealComponent } from './seal/seal.component';
import { LoadingService } from '../services/loading.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  results = 0;
  form: FormGroup;
  displayedColumns: string[] = ['protocol', 'entranceDate', 'exitDate', 'sportingPlate', 'originalPlate', 'printProtocol', 'printSeals', 'exit'];
  dataSource: MatTableDataSource<Protocol>;

  constructor(private _formBuilder: FormBuilder, 
              private _router: Router,
              private _searchService: SearchService,
              private _printService: PrintService,
              private _sealDialog: MatDialog,
              private _loadingService: LoadingService) { }

  ngOnInit() {
    this.form = this._formBuilder.group({
      protocol: '',
      startDate: '',
      endDate: ''
    });
  }

  search() {
    this._loadingService.show();
    this._searchService.search(this.form.value).subscribe(
      suc => {
        this.results = suc.length;
        this.dataSource = new MatTableDataSource(suc);
        this._loadingService.hide();
      }
    );
  }

  printProtocol(protocol) {
    this._loadingService.show();
    this._printService.printProcol(protocol).subscribe(
      suc => {
        saveAs(suc.body, 'protocolo.pdf')
        this._loadingService.hide();
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
