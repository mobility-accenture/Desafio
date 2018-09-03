//
//  Cart.swift
//  AppProject
//
//  Created by Kaio Dantas on 03/09/2018.
//  Copyright © 2018 KaioDantas. All rights reserved.
//

import Foundation
import UIKit

class Cart: UIViewController, UITableViewDelegate, UITableViewDataSource{
    
    @IBOutlet weak var tableview: UITableView!
    @IBOutlet weak var noItemsLB: UILabel!
    
    
    var listItemsInCart: [ItemCart] = []
    

    
    override func viewDidLoad() {
        super.viewDidLoad()
      
        listItemsInCart = Globals.cartItems
    }
    
    
    
    
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    
    
    
    
    
    // TABLE VIEW //
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        
        if(self.listItemsInCart.count == 0){
            tableview.isHidden = true
            noItemsLB.isHidden = false
        } else {
            tableview.isHidden = false
            noItemsLB.isHidden = true
        }
        
        return self.listItemsInCart.count
        
    }
    
    
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return 1.0
    }
    
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        let cell = tableView.dequeueReusableCell(withIdentifier: "cartcell") as! CellCartItem
        let item = self.listItemsInCart[indexPath.row]
        cell.setCell(item)
        return cell
        
    }
    
    
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
    }
    
    
    
    func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        return true
    }
    
    func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath) {
        if (editingStyle == UITableViewCellEditingStyle.delete) {
            tableView.beginUpdates()
            listItemsInCart.remove(at: indexPath.row)
            
            let userDefaults = UserDefaults.standard
            let encodedData: Data = NSKeyedArchiver.archivedData(withRootObject: listItemsInCart)
            userDefaults.set(encodedData, forKey: "cart")
            userDefaults.synchronize()
            
            Globals.cartItems = listItemsInCart
            
            
            tableView.deleteRows(at: [indexPath], with: .automatic)
            tableView.endUpdates()
        }
    }
    
    
    
    //** TABLE VIEW **//
    
    
    
    
    
    
    
    
    
    
    
    
    
//
//
//    func setAdditionals(){
//
//        let additionals = item?.additional ?? []
//        var chocActive = false
//        var creamActive = false
//        var cinnamonActive = false
//        var coffeeActive = false
//        var milkActive = false
//
//        for a in additionals {
//            if(a == "chocolate"){
//                chocActive = true
//            }
//            else if(a == "cinnamon"){
//                cinnamonActive = true
//            }
//            else if(a == "coffee"){
//                coffeeActive = true
//            }
//            else if(a == "milk"){
//                milkActive = true
//            }
//            else if(a == "chantilly"){
//                creamActive = true
//            }
//        }
//
//        if(!chocActive){
//            btChocolate.removeFromSuperview()
//        } else {
//            btAddChocolate(btChocolate)
//        }
//
//        if(!creamActive){
//            btChantilly.removeFromSuperview()
//        } else {
//            btAddCream(btChantilly)
//        }
//
//        if(!cinnamonActive){
//            btCinnamon.removeFromSuperview()
//        } else {
//            btAddCinnamon(btCinnamon)
//        }
//
//        if(!coffeeActive){
//            btCoffe.removeFromSuperview()
//        } else {
//            btAddCoffee(btCoffe)
//        }
//
//        if(!milkActive){
//            btMilk.removeFromSuperview()
//        } else {
//            btAddMilk(btMilk)
//        }
//
//
//    }
//
//
//
//
//
//    @IBAction func btSizeSelect0(_ sender: Any) {
//        btSize0.isSelected = true
//        btSize1.isSelected = false
//        btSize2.isSelected = false
//
//        itemSizeSelected = 0
//    }
//
//    @IBAction func btSizeSelect1(_ sender: Any) {
//        btSize0.isSelected = false
//        btSize1.isSelected = true
//        btSize2.isSelected = false
//
//        itemSizeSelected = 1
//    }
//
//    @IBAction func btSizeSelect2(_ sender: Any) {
//        btSize0.isSelected = false
//        btSize1.isSelected = false
//        btSize2.isSelected = true
//
//        itemSizeSelected = 2
//    }
//
//
//
//
//
//
//
//
//
//    @IBAction func btSugarSelect0(_ sender: Any) {
//        btSugar0.isSelected = true
//        btSugar1.isSelected = false
//        btSugar2.isSelected = false
//        btSugar3.isSelected = false
//
//        itemSugarSelected = 0
//    }
//
//    @IBAction func btSugarSelect1(_ sender: Any) {
//        btSugar0.isSelected = false
//        btSugar1.isSelected = true
//        btSugar2.isSelected = false
//        btSugar3.isSelected = false
//
//        itemSugarSelected = 1
//    }
//
//    @IBAction func btSugarSelect2(_ sender: Any) {
//        btSugar0.isSelected = false
//        btSugar1.isSelected = false
//        btSugar2.isSelected = true
//        btSugar3.isSelected = false
//
//        itemSugarSelected = 2
//    }
//
//    @IBAction func btSugarSelect3(_ sender: Any) {
//        btSugar0.isSelected = false
//        btSugar1.isSelected = false
//        btSugar2.isSelected = false
//        btSugar3.isSelected = true
//
//        itemSugarSelected = 3
//    }
//
//
//
//
//
//
//    @IBAction func btAddChocolate(_ sender: Any) {
//        if (aditionalChocolate){
//            btChocolate.isSelected = false
//            aditionalChocolate = false
//        } else {
//            btChocolate.isSelected = true
//            aditionalChocolate = true
//        }
//    }
//
//    @IBAction func btAddCinnamon(_ sender: Any) {
//        if (aditionalCinnamon){
//            btCinnamon.isSelected = false
//            aditionalCinnamon = false
//        } else {
//            btCinnamon.isSelected = true
//            aditionalCinnamon = true
//        }
//    }
//
//    @IBAction func btAddCream(_ sender: Any) {
//        if (aditionalCream){
//            btChantilly.isSelected = false
//            aditionalCream = false
//        } else {
//            btChantilly.isSelected = true
//            aditionalCream = true
//        }
//    }
//
//    @IBAction func btAddMilk(_ sender: Any) {
//        if (aditionalMilk){
//            btMilk.isSelected = false
//            aditionalMilk = false
//        } else {
//            btMilk.isSelected = true
//            aditionalMilk = true
//        }
//    }
//
//    @IBAction func btAddCoffee(_ sender: Any) {
//        if (aditionalCoffee){
//            btCoffe.isSelected = false
//            aditionalCoffee = false
//        } else {
//            btCoffe.isSelected = true
//            aditionalCoffee = true
//        }
//    }
//
//
//
//
//
//
//
//
//
//
//    @IBAction func removeQtd(_ sender: Any) {
//        if(qtdTotal > 1){
//            qtdTotal -= 1
//        }
//        qtdItems.text = "\(qtdTotal)"
//    }
//
//    @IBAction func addQtd(_ sender: Any) {
//        if(qtdTotal < 99){
//            qtdTotal += 1
//        }
//        qtdItems.text = "\(qtdTotal)"
//    }
//
//
//
//
//
//    @IBAction func addToCart(_ sender: Any) {
//        let itemCart = ItemCart.init(
//            title: item?.title ?? "",
//            image: item?.image ?? "",
//            size: itemSizeSelected,
//            sugar: itemSugarSelected,
//            addCoffee: aditionalCoffee,
//            addChocolate: aditionalChocolate,
//            addCream: aditionalCream,
//            addMilk: aditionalMilk,
//            addCinnamon: aditionalCinnamon,
//            quantity: qtdTotal)
//
//
//        let userDefaults = UserDefaults.standard
//        var itemsInCart: [ItemCart]
//
//        if let previouslyItems  = userDefaults.object(forKey: "cart"){
//            let decodedItems = NSKeyedUnarchiver.unarchiveObject(with: previouslyItems as! Data) as! [ItemCart]
//            itemsInCart = decodedItems
//        } else {
//            itemsInCart = []
//        }
//
//        itemsInCart.append(itemCart)
//
//        let encodedData: Data = NSKeyedArchiver.archivedData(withRootObject: itemsInCart)
//        userDefaults.set(encodedData, forKey: "cart")
//        userDefaults.synchronize()
//
//        Globals.cartItems = itemsInCart
//
//    }
//
    
}

