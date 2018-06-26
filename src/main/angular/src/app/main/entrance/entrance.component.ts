import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import * as moment from 'moment';

@Component({
  selector: 'app-entrance',
  templateUrl: './entrance.component.html',
  styleUrls: ['./entrance.component.css']
})
export class EntranceComponent implements OnInit {

  seizureFormGroup: FormGroup;
  vehicleFormGroup: FormGroup;
  policeFormGroup: FormGroup;
  yardFormGroup: FormGroup;

  public maxDate = new Date();
  public trueValue = true;
  public falseValue = false;
  public taxIdentifierMask = [/\d/, /\d/, /\d/, '.', /\d/, /\d/, /\d/, '.', /\d/, /\d/, /\d/, '-', /\d/, /\d/];
  public yearMask = [/\d/, /\d/, /\d/, /\d/];
  public plateMask = [/[azA-Z]/, /[azA-Z]/, /[azA-Z]/, '-', /\d/, /\d/, /\d/, /\d/];
  public parts = [
    { initials: 'PC', description: 'POLÍCIA CIVIL' },
    { initials: 'PM', description: 'POLÍCIA MILITAR' }
  ];
  public sheds = [
    { initials: 'BC', description: 'Barracão Carros' },
    { initials: 'BM', description: 'Barracão de Motos' }
  ];
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

  constructor(private _formBuilder: FormBuilder) { }

  ngOnInit() {
    var date = new Date();
    var option = false;
    this.seizureFormGroup = this._formBuilder.group({
      part: ['', Validators.required],
      date: [date, Validators.required],
      policeInvestigation: '',
      eventBulletin: '',
      taxIdentifier: ['', Validators.required],
      name: ['', Validators.required]
    });
    this.vehicleFormGroup = this._formBuilder.group({
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
      motor: ''
    });
    this.policeFormGroup = this._formBuilder.group({
      insured: [option, Validators.required],
      financed: [option, Validators.required],
      stolen: [option, Validators.required],
      drugTrafficking: [option, Validators.required],
      moneyLaundry: [option, Validators.required],
      perquisite: [option, Validators.required],
      papillaryExpertise: [option, Validators.required],
      ownerIntimate: [option, Validators.required],
      authorizedAlienation: [option, Validators.required],
      debits: [option, Validators.required]
    });
    this.yardFormGroup = this._formBuilder.group({
      seals: ['', Validators.required],
      shed: ['', Validators.required],
      row: ['', Validators.required],
      column: ['', Validators.required],
      floor: ['', Validators.required]
    });
  }

  save() {
    var seizureForm = this.seizureFormGroup.value;
    seizureForm.date = moment(seizureForm.date).format('YYYY-MM-DD');
    seizureForm.taxIdentifier = seizureForm.taxIdentifier.replace(/\D+/g, '');
    
    var vehicleForm = this.vehicleFormGroup.value;
    vehicleForm.ownerTaxIdentifier = vehicleForm.ownerTaxIdentifier.replace(/\D+/g, '');
    vehicleForm.sportingPlate = vehicleForm.sportingPlate.toUpperCase();
    vehicleForm.originalPlate = vehicleForm.originalPlate.toUpperCase();

    var json = JSON.stringify({
      "seizure": this.seizureFormGroup.value,
      "vehicle": this.vehicleFormGroup.value,
      "police": this.policeFormGroup.value,
      "yard": this.yardFormGroup.value
    });

    console.log(json);
    /*
    this._entranceService.save(json).subscribe(
      suc=>{
        this._router.navigate(["/main/search"]);
      },
      err=>{        
        this.error = err;
      }
    );*/
  }

}
