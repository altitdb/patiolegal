import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { SearchService } from './search.service';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  filtred: boolean = false;
  form: FormGroup;
  displayedColumns: string[] = ['entranceDate', 'exitDate', 'protocol', 'sportingPlate', 'originalPlate', 'printProtocol', 'printSeals', 'exit'];
  dataSource: MatTableDataSource<Protocol>;

  constructor(private _formBuilder: FormBuilder, private _router: Router,
              private _searchService: SearchService) { }

  ngOnInit() {
    this.form = this._formBuilder.group({
      protocol: '',
      startDate: '',
      endDate: ''
    });
  }

  search() {
    this.filtred = true;
    this._searchService.search().subscribe(
      suc => {
        this.dataSource = new MatTableDataSource(suc);
      }
    );
  }

  printProtocol(protocol) {

  }

  printSeals(protocol) {
    
  }

  exit(protocol) {
    this._router.navigate(["/main/exit", protocol]);
  }

}
