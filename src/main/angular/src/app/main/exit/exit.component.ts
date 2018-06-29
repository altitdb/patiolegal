import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-exit',
  templateUrl: './exit.component.html',
  styleUrls: ['./exit.component.css']
})
export class ExitComponent implements OnInit {

  protocol: string;
  
  constructor(private _activateRoute: ActivatedRoute) { }

  ngOnInit() {
    this._activateRoute.params.subscribe(params=>{
      this.protocol = params['protocol'];
    });
  }

}
