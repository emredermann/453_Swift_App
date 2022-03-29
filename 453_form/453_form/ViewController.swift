//
//  ViewController.swift
//  458_form
//
//  Created by Emre Derman on 3/16/22.
//

import UIKit

class ViewController: UIViewController {
    
    @IBOutlet weak var name_attr : UITextField!
    @IBOutlet weak var surname_attr : UITextField!
    @IBOutlet weak var birth_date_attr : UITextField!
    @IBOutlet weak var side_effect_vaccination_attr : UITextField!
    @IBOutlet weak var pcr_positive_cases_and_symproms_attr : UITextField!
    @IBOutlet weak var add_button : UIButton!
    @IBOutlet weak var city_attr: UITextField!
    @IBOutlet weak var applied_vaccine_attr : UITextField!
    @IBOutlet weak var gender_attr: UITextField!
    var max_count = 255;
    var characterset_birth = CharacterSet(charactersIn: "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ")
    var characterset = CharacterSet(charactersIn: "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789")
    var cities = ["Adana","Adiyaman","Afyon","Agri","Aksaray","Amasya","Ankara","Antalya","Ardahan","Artvin","Aydin","Balikesir","Bartin","Batman","Bayburt","Bilecik","Bingol","Bitlis","Bolu","Burdur","Bursa","Canakkale","Cankiri","Corum","Denizli","Diyarbakir","Duzce","Edirne","Elazig","Erzincan","Erzurum","Eskisehir","Gaziantep","Giresun","Gumushane","Hakkari","Hatay","Igdir","Isparta","Istanbul","Izmir","Kahramanmaras","Karabuk","Karaman","Kars","Kastamonu","Kayseri","Kilis","Kirikkale","Kirklareli","Kirsehir","Kocaeli","Konya","Kutahya","Malatya","Manisa","Mardin","Mersin","Mugla","Mus","Nevsehir","Nigde","Ordu","Osmaniye","Rize","Sakarya","Samsun","Sanliurfa","Siirt","Sinop","Sirnak","Sivas","Tekirdag","Tokat","Trabzon","Tunceli","Usak","Van","Yalova","Yozgat","Zonguldak"]
    
    override func viewDidLoad() {
        super.viewDidLoad();
        check_visibility()
        add_button.isHidden = true;
    }
    
    @IBAction func check_visibility(){
        print(name_attr.text!)
        print(surname_attr.text!)
        print(city_attr.text!)
        print(side_effect_vaccination_attr.text!)
        print(pcr_positive_cases_and_symproms_attr.text!)
        print(applied_vaccine_attr.text!)
        print(birth_date_attr.text!)
        
        if(name_attr.hasText
           && gender_attr.hasText
           && surname_attr.hasText
           && applied_vaccine_attr.hasText
           && city_attr.hasText
           && birth_date_attr.hasText
           && side_effect_vaccination_attr.hasText
           && pcr_positive_cases_and_symproms_attr.hasText
          ){
            add_button.isHidden = false;
        }else{
            add_button.isHidden = true;
        }
    }
    
    @IBAction func gender_at(_ sender: UITextField) {
        if (sender.text?.rangeOfCharacter(from: characterset.inverted) != nil) {
            check_visibility();
        }else{
            gender_attr = sender;
            check_visibility();
        }
    }
    @IBAction func city_at(_ sender: UITextField) {
        var flag = false
        for x in cities{
            if  x == sender.text{
                flag = true
            }
        }
        if flag == true
        {
            city_attr = sender;
            check_visibility();
        }
        else{
            check_visibility();

        }
        flag = false
    }
    
    @IBAction func name_attr(_ sender: UITextField) {
        if sender.text!.count < 60
            {
                if sender.text?.rangeOfCharacter(from: characterset.inverted) != nil {
                check_visibility();
            }else{
                name_attr = sender;
                check_visibility();
            }
        }
    }
    @IBAction func surname_attr(_ sender: UITextField) {
        if sender.text!.count < 60
            {
                if sender.text?.rangeOfCharacter(from: characterset.inverted) != nil {
                check_visibility();
            }else{
                surname_attr = sender;
                check_visibility();
            }
        }
    }

    @IBAction func applied_vaccine(_ sender: UITextField) {
        if sender.text?.rangeOfCharacter(from: characterset.inverted) != nil {
            check_visibility();
        }else{
            applied_vaccine_attr = sender;
            check_visibility();
        }
    }
    @IBAction  func birth_date(_ sender: UITextField) {
        var flag = true
        for x in sender.text!.unicodeScalars{
            if(characterset.contains(x)){
                flag = false
            }
        }
        if (flag == false) {
            check_visibility();
        }else{
            birth_date_attr = sender;
            check_visibility();
        }
    }
    @IBAction  func side_effect_vaccination(_ sender: UITextField) {
            side_effect_vaccination_attr = sender;
            check_visibility();
    }
    @IBAction  func pcr_positive_cases_and_symproms(_ sender: UITextField) {
            pcr_positive_cases_and_symproms_attr = sender;
            check_visibility();
    }
    @IBAction func add_data(_ sender: UIButton) {
        let message_attr = "Your response has been taken "
        print(name_attr.text!)
        print(surname_attr.text!)
        print(side_effect_vaccination_attr.text!)
        print(pcr_positive_cases_and_symproms_attr.text!)
        print(applied_vaccine_attr.text!)
        print(birth_date_attr.text!)
        print(city_attr.text!)
        let alertController = UIAlertController(
            title: name_attr.text!,
            message: message_attr,
            preferredStyle: UIAlertController.Style.alert)
               alertController.addAction(UIAlertAction(title: "OK", style: UIAlertAction.Style.default,
       handler: nil))
               present(alertController, animated: true, completion: nil)
           }
}
