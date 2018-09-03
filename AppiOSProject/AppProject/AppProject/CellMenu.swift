//
//  CellMenu.swift
//  AppProject
//
//  Created by Kaio Dantas on 03/09/2018.
//  Copyright © 2018 KaioDantas. All rights reserved.
//

import UIKit
import Kingfisher

class CellMenu: UITableViewCell {
    
    
    @IBOutlet weak var title: UILabel!
    @IBOutlet weak var img: UIImageView!
    
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }
    
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    
    func setCell(_ item: ItemMenu){
        self.title.text = item.title
        
        if let imgString = item.image?.replacingOccurrences(of: "data:image/png;base64,", with: ""){
            if let dataDecoded : Data = Data(base64Encoded: imgString , options: .ignoreUnknownCharacters){
                let decodedimage = UIImage(data: dataDecoded)
                self.img.image = decodedimage
            }
        }
    }
    
    
}
