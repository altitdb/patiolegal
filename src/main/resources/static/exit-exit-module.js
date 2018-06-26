(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["exit-exit-module"],{

/***/ "./src/app/main/exit/exit.component.css":
/*!**********************************************!*\
  !*** ./src/app/main/exit/exit.component.css ***!
  \**********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/main/exit/exit.component.html":
/*!***********************************************!*\
  !*** ./src/app/main/exit/exit.component.html ***!
  \***********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<p>\n  exit works!\n</p>\n"

/***/ }),

/***/ "./src/app/main/exit/exit.component.ts":
/*!*********************************************!*\
  !*** ./src/app/main/exit/exit.component.ts ***!
  \*********************************************/
/*! exports provided: ExitComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ExitComponent", function() { return ExitComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var ExitComponent = /** @class */ (function () {
    function ExitComponent() {
    }
    ExitComponent.prototype.ngOnInit = function () {
    };
    ExitComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-exit',
            template: __webpack_require__(/*! ./exit.component.html */ "./src/app/main/exit/exit.component.html"),
            styles: [__webpack_require__(/*! ./exit.component.css */ "./src/app/main/exit/exit.component.css")]
        }),
        __metadata("design:paramtypes", [])
    ], ExitComponent);
    return ExitComponent;
}());



/***/ }),

/***/ "./src/app/main/exit/exit.module.ts":
/*!******************************************!*\
  !*** ./src/app/main/exit/exit.module.ts ***!
  \******************************************/
/*! exports provided: ExitModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ExitModule", function() { return ExitModule; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _exit_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./exit.component */ "./src/app/main/exit/exit.component.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};



var ExitModule = /** @class */ (function () {
    function ExitModule() {
    }
    ExitModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_1__["CommonModule"]
            ],
            declarations: [_exit_component__WEBPACK_IMPORTED_MODULE_2__["ExitComponent"]]
        })
    ], ExitModule);
    return ExitModule;
}());



/***/ })

}]);
//# sourceMappingURL=exit-exit-module.js.map