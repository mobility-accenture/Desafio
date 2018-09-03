//
//  ItemDetails.swift
//  AppProject
//
//  Created by Kaio Dantas on 03/09/2018.
//  Copyright © 2018 KaioDantas. All rights reserved.
//


import UIKit

class ItemDetails: UIViewController {
    
    @IBOutlet weak var imgItem: UIImageView!
    @IBOutlet weak var titleItem: UILabel!
    
    @IBOutlet weak var btSize0: UIButton!
    @IBOutlet weak var btSize1: UIButton!
    @IBOutlet weak var btSize2: UIButton!
    
    @IBOutlet weak var btSugar0: UIButton!
    @IBOutlet weak var btSugar1: UIButton!
    @IBOutlet weak var btSugar2: UIButton!
    @IBOutlet weak var btSugar3: UIButton!
    
    @IBOutlet weak var btChocolate: UIButton!
    @IBOutlet weak var btCinnamon: UIButton!
    @IBOutlet weak var btChantilly: UIButton!
    @IBOutlet weak var btMilk: UIButton!
    @IBOutlet weak var btCoffe: UIButton!
    
    @IBOutlet weak var qtdItems: UILabel!
    
    
    
    var item: ItemMenu?
    
    var qtdTotal = 1
    var itemSizeSelected = 0
    var itemSugarSelected = 0
    var aditionalMilk = false
    var aditionalChocolate = false
    var aditionalCoffee = false
    var aditionalCream = false
    var aditionalCinnamon = false
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        if let imgString = item?.image?.replacingOccurrences(of: "data:image/png;base64,", with: ""){
            if let dataDecoded : Data = Data(base64Encoded: imgString , options: .ignoreUnknownCharacters){
                let decodedimage = UIImage(data: dataDecoded)
                self.imgItem.image = decodedimage
            }
        }
        
        
        titleItem.text = item?.title ?? ""
        
        
        //TAMANHO SELECIONADO
        switch item?.size {
        case 0:
            btSizeSelect0( btSize0)
        case 1:
            btSizeSelect1( btSize0)
        case 2:
            btSizeSelect2( btSize0)
        default:
            btSizeSelect0( btSize0)
        }
        
        
        //AÇÚCAR SELECIONADO
        switch item?.sugar {
        case 0:
            btSugarSelect0( btSugar0)
        case 1:
            btSugarSelect1( btSugar0)
        case 2:
            btSugarSelect2( btSugar0)
        case 3:
            btSugarSelect3( btSugar0)
        default:
            btSizeSelect0( btSugar0)
        }
        
        
        
        
        //OPCIONAIS
        setAdditionals()


    }
    
    
    
    
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    
    
    
    
    func setAdditionals(){
        
        let additionals = item?.additional ?? []
        var chocActive = false
        var creamActive = false
        var cinnamonActive = false
        var coffeeActive = false
        var milkActive = false
        
        for a in additionals {
            if(a == "chocolate"){
                chocActive = true
            }
            else if(a == "cinnamon"){
                cinnamonActive = true
            }
            else if(a == "coffee"){
                coffeeActive = true
            }
            else if(a == "milk"){
                milkActive = true
            }
            else if(a == "chantilly"){
                creamActive = true
            }
        }
        
        if(!chocActive){
            btChocolate.removeFromSuperview()
        } else {
            btAddChocolate(btChocolate)
        }
        
        if(!creamActive){
            btChantilly.removeFromSuperview()
        } else {
            btAddCream(btChantilly)
        }
        
        if(!cinnamonActive){
            btCinnamon.removeFromSuperview()
        } else {
            btAddCinnamon(btCinnamon)
        }
        
        if(!coffeeActive){
            btCoffe.removeFromSuperview()
        } else {
            btAddCoffee(btCoffe)
        }
        
        if(!milkActive){
            btMilk.removeFromSuperview()
        } else {
            btAddMilk(btMilk)
        }
        
        
    }
    
    
    
    
    
    @IBAction func btSizeSelect0(_ sender: Any) {
        btSize0.isSelected = true
        btSize1.isSelected = false
        btSize2.isSelected = false
        
        itemSizeSelected = 0
    }
    
    @IBAction func btSizeSelect1(_ sender: Any) {
        btSize0.isSelected = false
        btSize1.isSelected = true
        btSize2.isSelected = false
        
        itemSizeSelected = 1
    }
    
    @IBAction func btSizeSelect2(_ sender: Any) {
        btSize0.isSelected = false
        btSize1.isSelected = false
        btSize2.isSelected = true
        
        itemSizeSelected = 2
    }
    
    
    
    
    
    
    
    
    
    @IBAction func btSugarSelect0(_ sender: Any) {
        btSugar0.isSelected = true
        btSugar1.isSelected = false
        btSugar2.isSelected = false
        btSugar3.isSelected = false

        itemSugarSelected = 0
    }
    
    @IBAction func btSugarSelect1(_ sender: Any) {
        btSugar0.isSelected = false
        btSugar1.isSelected = true
        btSugar2.isSelected = false
        btSugar3.isSelected = false
        
        itemSugarSelected = 1
    }
    
    @IBAction func btSugarSelect2(_ sender: Any) {
        btSugar0.isSelected = false
        btSugar1.isSelected = false
        btSugar2.isSelected = true
        btSugar3.isSelected = false
        
        itemSugarSelected = 2
    }
    
    @IBAction func btSugarSelect3(_ sender: Any) {
        btSugar0.isSelected = false
        btSugar1.isSelected = false
        btSugar2.isSelected = false
        btSugar3.isSelected = true
        
        itemSugarSelected = 3
    }
    
    
    
    
    
    
    @IBAction func btAddChocolate(_ sender: Any) {
        if (aditionalChocolate){
            btChocolate.isSelected = false
            aditionalChocolate = false
        } else {
            btChocolate.isSelected = true
            aditionalChocolate = true
        }
    }
    
    @IBAction func btAddCinnamon(_ sender: Any) {
        if (aditionalCinnamon){
            btCinnamon.isSelected = false
            aditionalCinnamon = false
        } else {
            btCinnamon.isSelected = true
            aditionalCinnamon = true
        }
    }
    
    @IBAction func btAddCream(_ sender: Any) {
        if (aditionalCream){
            btChantilly.isSelected = false
            aditionalCream = false
        } else {
            btChantilly.isSelected = true
            aditionalCream = true
        }
    }
    
    @IBAction func btAddMilk(_ sender: Any) {
        if (aditionalMilk){
            btMilk.isSelected = false
            aditionalMilk = false
        } else {
            btMilk.isSelected = true
            aditionalMilk = true
        }
    }
    
    @IBAction func btAddCoffee(_ sender: Any) {
        if (aditionalCoffee){
            btCoffe.isSelected = false
            aditionalCoffee = false
        } else {
            btCoffe.isSelected = true
            aditionalCoffee = true
        }
    }
    
    
    
    
    
    
    
    
    
    
    @IBAction func removeQtd(_ sender: Any) {
        if(qtdTotal > 1){
            qtdTotal -= 1
        }
        qtdItems.text = "\(qtdTotal)"
    }
    
    @IBAction func addQtd(_ sender: Any) {
        if(qtdTotal < 99){
            qtdTotal += 1
        }
        qtdItems.text = "\(qtdTotal)"
    }
    
    
    
    
    
    @IBAction func addToCart(_ sender: Any) {
        let itemCart = ItemCart.init(
            title: item?.title ?? "",
            image: item?.image ?? "",
            size: itemSizeSelected,
            sugar: itemSugarSelected,
            addCoffee: aditionalCoffee,
            addChocolate: aditionalChocolate,
            addCream: aditionalCream,
            addMilk: aditionalMilk,
            addCinnamon: aditionalCinnamon,
            quantity: qtdTotal)

        
        let userDefaults = UserDefaults.standard
        var itemsInCart: [ItemCart]
        
        if let previouslyItems  = userDefaults.object(forKey: "cart"){
            let decodedItems = NSKeyedUnarchiver.unarchiveObject(with: previouslyItems as! Data) as! [ItemCart]
            itemsInCart = decodedItems
        } else {
            itemsInCart = []
        }

        itemsInCart.append(itemCart)
        
        let encodedData: Data = NSKeyedArchiver.archivedData(withRootObject: itemsInCart)
        userDefaults.set(encodedData, forKey: "cart")
        userDefaults.synchronize()
        
        Globals.cartItems = itemsInCart
        
    }
    
   
}

