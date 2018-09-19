import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { Storage } from '@ionic/storage';

@IonicPage()
@Component({
  selector: 'page-details',
  templateUrl: 'details.html',
})
export class DetailsPage {
  private coffee: any;
  private coffeeSize: string;
  private count: number;
  private sizeSelected: number;

  private smallSize: boolean = false;
  private mediumSize: boolean = false;
  private largeSize: boolean = false;

  private noSugar: boolean = false;
  private oneSugar: boolean = false;
  private twoSugar: boolean = false;
  private threeSugar: boolean = false;

  constructor(public navCtrl: NavController, public navParams: NavParams, private storage: Storage) {
    this.coffee = this.navParams.data;
    this.count = 1;
  }

  ionViewDidLoad() {}

  private increaseCount(): void{
    this.count += 1;
  }

  private decreaseCount(): void{
    if(this.count > 1){
      this.count -= 1;
    }
  }

  private addToCart(): void {
    let selectedProducts = []
    this.storage.get('products').then(products => {
      if(products){
        selectedProducts = products;

        this.coffee.count = this.count;
        selectedProducts.push(this.coffee);
      }else{
        this.coffee.count = this.count;
        selectedProducts.push(this.coffee);
      }

      this.storage.set('products', selectedProducts).then(storaged =>{
        if(storaged){
          this.navCtrl.push('ShoppingCartPage');
        }
      })
    })
  }

  private selectSize(sizeSelected: string): void {
    switch (sizeSelected) {
      case 'smallSize':
        this.smallSize = true;

        this.largeSize = false;
        this.mediumSize = false;
        break;
      case 'mediumSize':
        this.mediumSize = true;

        this.smallSize = false;
        this.largeSize = false;
        break
      case 'largeSize':
        this.largeSize = true;

        this.smallSize = false;
        this.mediumSize = false;
        break  
      default:
        break;
    }
  }

  private selectSugar(sugar: number): void {
    switch (sugar) {
      case 0:
        this.noSugar = true;

        this.oneSugar = false;
        this.twoSugar = false;
        this.threeSugar = false;
        break;
      case 1:
        this.oneSugar = true;

        this.noSugar = false;
        this.twoSugar = false;
        this.threeSugar = false;
        break;
      case 2:
        this.twoSugar = true;

        this.noSugar = false;
        this.oneSugar = false;
        this.threeSugar = false;
        break;
      case 3:
        this.threeSugar = true;

        this.noSugar = false;
        this.oneSugar = false;
        this.twoSugar = false;
        break;  
      default:
        break;
    }
  }

}
