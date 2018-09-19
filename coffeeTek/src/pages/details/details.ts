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
    let products = []
    this.storage.set('cart', products).then(storaged =>{
      if(storaged){
        this.navCtrl.push('ShoppingCartPage');
      }
    })
  }

  private selectSize(sizeSelected: number): void {
    if(this.sizeSelected != sizeSelected){
      this.sizeSelected = sizeSelected;
    }
  }

}
