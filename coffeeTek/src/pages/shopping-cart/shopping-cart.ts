import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { Storage } from '@ionic/storage';

@IonicPage()
@Component({
  selector: 'page-shopping-cart',
  templateUrl: 'shopping-cart.html',
})
export class ShoppingCartPage {
  private selectedProducts = [];

  constructor(public navCtrl: NavController, public navParams: NavParams, private storage: Storage) {
    this.storage.get('products').then(coffees =>{
      if(coffees){
        this.selectedProducts = coffees;
        console.log(this.selectedProducts);
      }
      
    })
  }

  ionViewDidLoad() {}

}
