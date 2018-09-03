//
//  ItemCart.swift
//  AppProject
//
//  Created by Kaio Dantas on 03/09/2018.
//  Copyright © 2018 KaioDantas. All rights reserved.
//

import Foundation

class ItemCart: NSObject, NSCoding {
    
    var title: String? = ""
    var image: String? = ""
    var size: Int? = 0
    var sugar: Int? = 0
    var addCoffee: Bool? = false
    var addChocolate: Bool? = false
    var addCream: Bool? = false
    var addMilk: Bool? = false
    var addCinnamon: Bool? = false
    var quantity: Int? = 1
    
    
    
    init(title: String, image: String, size: Int, sugar: Int, addCoffee: Bool, addChocolate: Bool, addCream: Bool, addMilk: Bool, addCinnamon: Bool, quantity: Int) {
        self.title = title
        self.image = image
        self.size = size
        self.sugar = sugar
        self.addCoffee = addCoffee
        self.addChocolate = addChocolate
        self.addCream = addCream
        self.addMilk = addMilk
        self.addCinnamon = addCinnamon
        self.quantity = quantity
        
    }
    
    required convenience init(coder aDecoder: NSCoder) {
        let title = aDecoder.decodeObject(forKey: "title") as! String
        let image = aDecoder.decodeObject(forKey: "image") as! String
        let size = aDecoder.decodeInteger(forKey: "size")
        let sugar = aDecoder.decodeInteger(forKey: "sugar")
        let quantity = aDecoder.decodeInteger(forKey: "quantity")
        let addCoffee = aDecoder.decodeBool(forKey: "addCoffee")
        let addChocolate = aDecoder.decodeBool(forKey: "addChocolate")
        let addCream = aDecoder.decodeBool(forKey: "addCream")
        let addMilk = aDecoder.decodeBool(forKey: "addMilk")
        let addCinnamon = aDecoder.decodeBool(forKey: "addCinnamon")
        
        self.init(title: title,
                  image: image,
                  size: size,
                  sugar: sugar,
                  addCoffee: addCoffee,
                  addChocolate: addChocolate,
                  addCream: addCream,
                  addMilk: addMilk,
                  addCinnamon: addCinnamon,
                  quantity: quantity
        )
        
    }
    
    func encode(with aCoder: NSCoder) {
        aCoder.encode(title, forKey: "title")
        aCoder.encode(image, forKey: "image")
        aCoder.encode(size, forKey: "size")
        aCoder.encode(sugar, forKey: "sugar")
        aCoder.encode(quantity, forKey: "quantity")
        aCoder.encode(addCoffee, forKey: "addCoffee")
        aCoder.encode(addChocolate, forKey: "addChocolate")
        aCoder.encode(addCream, forKey: "addCream")
        aCoder.encode(addMilk, forKey: "addMilk")
        aCoder.encode(addCinnamon, forKey: "addCinnamon")
    }
}

