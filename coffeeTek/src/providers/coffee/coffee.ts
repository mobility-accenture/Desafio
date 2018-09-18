import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class CoffeeProvider {

  constructor(private http: HttpClient) {}

  public getCoffees(): Observable<any>{
    return this.http.get('https://desafio-mobility.herokuapp.com/products.json');
  }

}
