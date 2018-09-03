//
//  ViewController.swift
//  AppProject
//
//  Created by Kaio Dantas on 03/09/2018.
//  Copyright © 2018 KaioDantas. All rights reserved.
//

import UIKit
import Alamofire

class ViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {

    @IBOutlet weak var tableview: UITableView!
    @IBOutlet weak var loading: UIActivityIndicatorView!
    
    let refreshControl = UIRefreshControl()
    
    var menuList: [ItemMenu] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        loading.stopAnimating()
        
        self.refreshControl.addTarget(self, action: #selector(didPullToRefresh), for: .valueChanged)
        tableview.addSubview(self.refreshControl)
        
        
        checkCartItems()
        
        conect()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


    
    func checkCartItems(){
        let userDefaults = UserDefaults.standard
        
//        userDefaults.removeObject(forKey: "cart")
        
        if let previouslyItems  = userDefaults.object(forKey: "cart"){
            let decodedItems = NSKeyedUnarchiver.unarchiveObject(with: previouslyItems as! Data) as! [ItemCart]
            Globals.cartItems = decodedItems
        } else {
            Globals.cartItems = []
        }

    }
    
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        
        if(Globals.cartItems.count > 0){
            let rightBarButtons = self.navigationItem.rightBarButtonItems
            let lastBarButton = rightBarButtons?.last
            lastBarButton?.addBadge(number: Globals.cartItems.count)
        } else {
            let rightBarButtons = self.navigationItem.rightBarButtonItems
            let lastBarButton = rightBarButtons?.last
            lastBarButton?.removeBadge()
        }
    }
    
    
    
    // TABLE VIEW //
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.menuList.count
    }
    
    
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return 1.0
    }
    
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        let cell = tableView.dequeueReusableCell(withIdentifier: "cell") as! CellMenu
        let item = self.menuList[indexPath.row]
        cell.setCell(item)
        return cell
        
    }
    
    
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
        
        let item = self.menuList[indexPath.row]
        
        let viewController = self.storyboard!.instantiateViewController(withIdentifier: "item_details") as! ItemDetails
        viewController.item = item
        self.navigationController?.pushViewController(viewController, animated: true)
    }
    
    
    
    //** TABLE VIEW **//
    
    
    
    
    
    @objc func didPullToRefresh(){
        tableview.scrollsToTop = true
        conect()
    }
    
    
    
    
    
    
    
    func conect(){
        self.refreshControl.endRefreshing()
        self.loading.startAnimating()
        
        

        let url = "https://desafio-mobility.herokuapp.com/products.json"

        Alamofire.request(url, method: .get, encoding: JSONEncoding.default, headers: [:])
            .validate()
            .responseJSON { response in

                self.loading.stopAnimating()
                self.menuList.removeAll()

                if(response.result.error == nil){
                    //SE NAO HOUVE ERRO

                    do {
                        let list = try JSONDecoder().decode(Products.self, from: response.data!)
                        self.menuList = list.products!
                        
                        if(self.menuList.count <= 0){
                            self.showError(mensagem: NSLocalizedString("nenhum_item", comment: ""))
                        }
                        self.tableview.reloadData()


                    } catch {
                        self.showError(mensagem: NSLocalizedString("erro_carregamento", comment: ""))
                    }

                } else {
                    //SE HOUVE ERRO
                    self.showError(mensagem: NSLocalizedString("erro_carregamento", comment: ""))
                }

        }
    }
    
    
    
    
    
    
    func showError(mensagem:String){
        loading.stopAnimating()
        
        if ((self.view.window != nil) && (self.isViewLoaded )){
            
            let alert = MyUIAlertController(title: "", message: mensagem, preferredStyle: UIAlertControllerStyle.alert)
            let cancelAction = UIAlertAction(title: NSLocalizedString("ok", comment: "OK"), style: UIAlertActionStyle.cancel) { (UIAlertAction) -> Void in }
            alert.addAction(cancelAction)
            self.present(alert, animated: true) { () -> Void in }
            
        }
    }
}

















private var handle: UInt8 = 0;

extension CAShapeLayer {
    func drawCircleAtLocation(location: CGPoint, withRadius radius: CGFloat, andColor color: UIColor, filled: Bool) {
        fillColor = filled ? color.cgColor : UIColor.white.cgColor
        strokeColor = color.cgColor
        let origin = CGPoint(x: location.x - radius, y: location.y - radius)
        path = UIBezierPath(ovalIn: CGRect(origin: origin, size: CGSize(width: radius * 2, height: radius * 2))).cgPath
    }
}

extension UIBarButtonItem {
    private var badgeLayer: CAShapeLayer? {
        if let b: AnyObject = objc_getAssociatedObject(self, &handle) as AnyObject? {
            return b as? CAShapeLayer
        } else {
            return nil
        }
    }
    
    func addBadge(number: Int, withOffset offset: CGPoint = CGPoint.zero, andColor color: UIColor = UIColor.red, andFilled filled: Bool = true) {
        guard let view = self.value(forKey: "view") as? UIView else { return }
        
        badgeLayer?.removeFromSuperlayer()
        
        // Initialize Badge
        let badge = CAShapeLayer()
        let radius = CGFloat(7)
        let location = CGPoint(x: view.frame.width - (radius + offset.x), y: (radius + offset.y))
        badge.drawCircleAtLocation(location: location, withRadius: radius, andColor: color, filled: filled)
        view.layer.addSublayer(badge)
        
        // Initialiaze Badge's label
        let label = CATextLayer()
        label.string = "\(number)"
        label.alignmentMode = kCAAlignmentCenter
        label.fontSize = 11
        label.frame = CGRect(origin: CGPoint(x: location.x - 4, y: offset.y), size: CGSize(width: 8, height: 16))
        label.foregroundColor = filled ? UIColor.white.cgColor : color.cgColor
        label.backgroundColor = UIColor.clear.cgColor
        label.contentsScale = UIScreen.main.scale
        badge.addSublayer(label)
        
        // Save Badge as UIBarButtonItem property
        objc_setAssociatedObject(self, &handle, badge, .OBJC_ASSOCIATION_RETAIN_NONATOMIC)
    }
    
    func updateBadge(number: Int) {
        if let text = badgeLayer?.sublayers?.filter({ $0 is CATextLayer }).first as? CATextLayer {
            text.string = "\(number)"
        }
    }
    
    func removeBadge() {
        badgeLayer?.removeFromSuperlayer()
    }
}
