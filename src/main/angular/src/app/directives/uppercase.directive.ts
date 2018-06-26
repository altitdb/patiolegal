import { Directive, ElementRef, Input } from '@angular/core';
@Directive({
  selector: '[uppercase]',
  host: {
    '(input)': 'toUpperCase($event.target.value)',
    '(blur)': 'toUpperCase($event.target.value)'
  }
})
export class UppercaseDirective {

  constructor(private ref: ElementRef) {
  }

  toUpperCase(value: any) {
    if (typeof value === 'string') {
      this.ref.nativeElement.value = value.toUpperCase();
    }
  }
}