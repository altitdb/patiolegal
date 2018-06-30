import { Directive, ElementRef } from '@angular/core';
@Directive({
  selector: '[uppercase]'
})
export class UppercaseDirective {

  constructor(private ref: ElementRef) {
    if (typeof this.ref.nativeElement.value === 'string') {
      this.ref.nativeElement.value = this.ref.nativeElement.value.toUpperCase();
      //this.ref.nativeElement.dispatchEvent(new Event('input'));
    }
  }

  toUpperCase(value: any) {
    if (typeof value === 'string') {
      this.ref.nativeElement.value = value.toUpperCase();
      this.ref.nativeElement.dispatchEvent(new Event('input'));
    }
  }
}