//
//  ViewController.swift
//  453_form
//
//  Created by Emre Derman on 3/16/22.
//

import UIKit

class ViewController: UIViewController {
    
    
    
    @IBOutlet weak var name_surname_attr : UITextField!
    @IBOutlet weak var birth_date_attr : UITextField!
    @IBOutlet weak var side_effect_vaccination_attr : UITextField!
    @IBOutlet weak var pcr_positive_cases_and_symproms_attr : UITextField!
    @IBOutlet weak var add_button : UITextField!
    @IBOutlet weak var city_attr: UITextField!
    @IBOutlet weak var applied_vaccine_attr : UITextField!
    @IBOutlet weak var gender_attr: UITextField!
    var date_flag = false
    var vaccine_flag = false
    var gender_flag = false
    var city_flag = false

    
    /*
    var cities = ["Adana","Adiyaman","Afyon","Agri","Aksaray","Amasya","Ankara","Antalya","Ardahan","Artvin","Aydin","Balikesir","Bartin","Batman","Bayburt","Bilecik","Bingol","Bitlis","Bolu","Burdur","Bursa","Canakkale","Cankiri","Corum","Denizli","Diyarbakir","Duzce","Edirne","Elazig","Erzincan","Erzurum","Eskisehir","Gaziantep","Giresun","Gumushane","Hakkari","Hatay","Igdir","Isparta","Istanbul","Izmir","Kahramanmaras","Karabuk","Karaman","Kars","Kastamonu","Kayseri","Kilis","Kirikkale","Kirklareli","Kirsehir","Kocaeli","Konya","Kutahya","Malatya","Manisa","Mardin","Mersin","Mugla","Mus","Nevsehir","Nigde","Ordu","Osmaniye","Rize","Sakarya","Samsun","Sanliurfa","Siirt","Sinop","Sirnak","Sivas","Tekirdag","Tokat","Trabzon","Tunceli","Usak","Van","Yalova","Yozgat","Zonguldak"]
    var vaccine_types = ["Sinovac","Biontech","Turkovac"]
    var gender_options = ["Female","Male"]
     */
    
    override func viewDidLoad() {
        super.viewDidLoad();
        // Do any additional setup after loading the view.
        check_visibility()
        add_button.isHidden = true;
    }
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
            return 1
        }
    func check_visibility(){

        print(name_surname_attr.text!)
        print(side_effect_vaccination_attr.text!)
        print(pcr_positive_cases_and_symproms_attr.text!)
        
        if(name_surname_attr.hasText
           && gender_attr.hasText
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
    @IBAction func name_surname(_ sender: UITextField) {
        name_surname_attr = sender;
        check_visibility();
    }
    @IBAction   func birth_date(_ sender: UITextField) {
        date_flag = true
        check_visibility();
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
        let alertController = UIAlertController(
            title: name_surname_attr.text!,
            message: message_attr,
            preferredStyle: UIAlertController.Style.alert)
               alertController.addAction(UIAlertAction(title: "OK", style: UIAlertAction.Style.default,
       handler: nil))
               present(alertController, animated: true, completion: nil)
           }
}
