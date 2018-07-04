import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import * as moment from 'moment';
import { EntranceService } from './entrance.service';
import { Router } from '@angular/router';
import { ShedService } from '../services/shed.service';

@Component({
  selector: 'app-entrance',
  templateUrl: './entrance.component.html',
  styleUrls: ['./entrance.component.css']
})
export class EntranceComponent implements OnInit {

  form: FormGroup;

  public maxDate = new Date();
  public trueValue = true;
  public falseValue = false;
  public taxIdentifierMask = [/\d/, /\d/, /\d/, '.', /\d/, /\d/, /\d/, '.', /\d/, /\d/, /\d/, '-', /\d/, /\d/];
  public yearMask = [/\d/, /\d/, /\d/, /\d/];
  public plateMask = [/[A-Z]/, /[A-Z]/, /[A-Z]/, '-', /\d/, /\d/, /\d/, /\d/];
  public parts = [
    { initials: 'PC', description: 'POLÍCIA CIVIL' },
    { initials: 'PM', description: 'POLÍCIA MILITAR' }
  ];
  public sheds: Array<Shed>;
  public categories = [
    { id: '1', description: 'OFICIAL' },
    { id: '2', description: 'DIPLOMÁTICO' },
    { id: '3', description: 'PARTICULAR' },
    { id: '4', description: 'ALUGUEL' },
    { id: '5', description: 'APRENDIZAGEM' }
  ];
  public states = [
    { description: 'ORIGINAL' },
    { description: 'ADULTERADO' },
    { description: 'SUPRIMIDO' }
  ];
  public fuels = [
    { description: 'ÁLCOOL' },
    { description: 'GASOLINA' },
    { description: 'ÁLCOOL/GASOLINA' },
    { description: 'DIESEL' },
    { description: 'GNV' },
    { description: 'OUTRO' }
  ];

  constructor(private _formBuilder: FormBuilder, 
              private _router: Router, 
              private _entranceService: EntranceService,
              private _shedService: ShedService) { }

  ngOnInit() {
    var date = new Date();
    var option = false;
    this.form = this._formBuilder.group({
      part: ['', Validators.required],
      date: [date, Validators.required],
      policeInvestigation: '',
      eventBulletin: '',
      taxIdentifier: ['', Validators.required],
      name: ['', Validators.required],
      theyRenamed: ['', Validators.required],
      ownerName: ['', Validators.required],
      ownerTaxIdentifier: ['', Validators.required],
      brand: ['', Validators.required],
      model: ['', Validators.required],
      category: ['', Validators.required],
      color: ['', Validators.required],
      fuel: ['', Validators.required],
      factoryYear: ['', Validators.required],
      modelYear: ['', Validators.required],
      sportingPlate: ['', Validators.required],
      originalPlate: '',
      chassisState: ['', Validators.required],
      chassis: '',
      motorState: ['', Validators.required],
      motor: '',
      insured: [option, Validators.required],
      financed: [option, Validators.required],
      stolen: [option, Validators.required],
      drugTrafficking: [option, Validators.required],
      moneyLaundry: [option, Validators.required],
      perquisite: [option, Validators.required],
      papillaryExpertise: [option, Validators.required],
      ownerIntimate: [option, Validators.required],
      authorizedAlienation: [option, Validators.required],
      debits: [option, Validators.required],
      shed: ['', Validators.required],
      row: ['', Validators.required],
      column: ['', Validators.required],
      floor: ['', Validators.required]
    });
    this.findSheds();
  }

  save() {
    this.form.value.date = moment(this.form.value.date).format('YYYY-MM-DD');
    this.form.value.taxIdentifier = this.form.value.taxIdentifier.replace(/\D+/g, '');
    
    this.form.value.ownerTaxIdentifier = this.form.value.ownerTaxIdentifier.replace(/\D+/g, '');
    this.form.value.sportingPlate = this.form.value.sportingPlate.toUpperCase();
    this.form.value.originalPlate = this.form.value.originalPlate.toUpperCase();

    this._entranceService.save(this.form.value).subscribe(
      suc=>{
        this._router.navigate(["/main/search"]);
      },
      err=>{
        console.log(err);
        //this.error = err;
      }
    );
  }

  findSheds() {
    this._shedService.findAll().subscribe(
      suc => {
        this.sheds = suc;
      }
    );
  }

}
