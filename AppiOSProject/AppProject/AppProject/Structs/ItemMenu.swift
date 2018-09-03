//
//  ItemMenu.swift
//  AppProject
//
//  Created by Kaio Dantas on 03/09/2018.
//  Copyright © 2018 KaioDantas. All rights reserved.
//

import Foundation

struct Products: Decodable{
    let products: [ItemMenu]?
}



struct ItemMenu: Decodable{
    
    let title: String?
    let image: String?
    let size: Int?
    let sugar: Int?
    let additional: [String]?
    
}
