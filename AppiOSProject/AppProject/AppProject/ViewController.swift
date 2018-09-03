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
        
        conect()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
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
        
        let event = self.menuList[indexPath.row]
        
//        let viewController = self.storyboard!.instantiateViewController(withIdentifier: "evento_detalhes") as! Evento_Detalhes_VC
//        viewController.evento = event
//        self.navigationController?.pushViewController(viewController, animated: true)
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
                        
//                        if(self.eventos_list.count > 0){
//                            self.noEventsView.isHidden = true
//                        } else {
//                            self.noEventsView.isHidden = false
//                        }
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

