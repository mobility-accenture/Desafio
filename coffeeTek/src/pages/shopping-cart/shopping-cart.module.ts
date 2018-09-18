import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { ShoppingCartPage } from './shopping-cart';

@NgModule({
  declarations: [
    ShoppingCartPage,
  ],
  imports: [
    IonicPageModule.forChild(ShoppingCartPage),
  ],
})
export class ShoppingCartPageModule {}
