//
//  CellCartItem.swift
//  AppProject
//
//  Created by Kaio Dantas on 03/09/2018.
//  Copyright © 2018 KaioDantas. All rights reserved.
//

import UIKit

class CellCartItem: UITableViewCell {
    
    
    @IBOutlet weak var title: UILabel!
    @IBOutlet weak var img: UIImageView!
    @IBOutlet weak var sizeLb: UILabel!
    @IBOutlet weak var sugarLb: UILabel!
    @IBOutlet weak var optionalsLb: UILabel!
    @IBOutlet weak var qtdLb: UILabel!
    
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }
    
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    
    func setCell(_ item: ItemCart){
        title.text = item.title
        qtdLb.text = "\(item.quantity)"
        
        let imgString = item.image.replacingOccurrences(of: "data:image/png;base64,", with: "")
        if let dataDecoded : Data = Data(base64Encoded: imgString , options: .ignoreUnknownCharacters){
            let decodedimage = UIImage(data: dataDecoded)
            self.img.image = decodedimage
        }
        
        switch item.size {
        case 0:
            sizeLb.text = NSLocalizedString("pequeno", comment: "")
        case 1:
            sizeLb.text = NSLocalizedString("medio", comment: "")
        case 2:
            sizeLb.text = NSLocalizedString("grande", comment: "")
        default:
            sizeLb.text = NSLocalizedString("medio", comment: "")
        }
        
        
        switch item.sugar {
        case 0:
            sugarLb.text = NSLocalizedString("sem_acucar", comment: "")
        case 1:
            sugarLb.text = NSLocalizedString("um_torrao", comment: "")
        case 2:
            sugarLb.text = NSLocalizedString("dois_torroes", comment: "")
        case 3:
            sugarLb.text = NSLocalizedString("tres_torroes", comment: "")
        default:
            sugarLb.text = NSLocalizedString("sem_acucar", comment: "")
        }
        
        
        var aditionals = ""
        
        if(item.addChocolate){
            aditionals += NSLocalizedString("chocolate", comment: "")
        }
        
        if(item.addCinnamon){
            if(aditionals != ""){
                aditionals += ", "
            }
            aditionals += NSLocalizedString("canela", comment: "")
        }
        
        if(item.addCream){
            if(aditionals != ""){
                aditionals += ", "
            }
            aditionals += NSLocalizedString("chantilly", comment: "")
        }
        
        if(item.addCoffee){
            if(aditionals != ""){
                aditionals += ", "
            }
            aditionals += NSLocalizedString("cafe", comment: "")
        }
        
        if(item.addMilk){
            if(aditionals != ""){
                aditionals += ", "
            }
            aditionals += NSLocalizedString("leite", comment: "")
        }
        if(aditionals == ""){
            aditionals = NSLocalizedString("nenhum", comment: "")
        }
        
        optionalsLb.text = aditionals
    }
    
    
}
