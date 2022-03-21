//
//  ViewController.swift
//  453_form
//
//  Created by Emre Derman on 3/16/22.
//

import UIKit

class ViewController: UIViewController, UIPickerViewDataSource, UIPickerViewDelegate, UITextFieldDelegate {
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        if(pickerView == city_attr){
            let tempArr = cities
            return tempArr.count
        }
        
        if(pickerView == applied_vaccine_attr){
            let tempArr = vaccine_types
            return tempArr.count
        }
        if(pickerView == gender_attr){
            let tempArr = gender_options
            return tempArr.count
        }
        return -1
        }

        func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
            if(pickerView == city_attr){
                let tempArr = cities
                return tempArr[row]
            }
            
            if(pickerView == applied_vaccine_attr){
                let tempArr = vaccine_types
                return tempArr[row]
            }
            if(pickerView == gender_attr){
                let tempArr = gender_options
                return tempArr[row]
            }
            return ""
        }

        func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
            if(pickerView == city_attr){
                let tempArr = cities
                self.city_attr_text = tempArr[row]
                city_flag = true
            }
            
            if(pickerView == applied_vaccine_attr){
                let tempArr = vaccine_types
                self.applied_vaccine_attr_text = tempArr[row]
                vaccine_flag = true
            }
            if(pickerView == gender_attr){
                let tempArr = gender_options
                self.gender_attr_text = tempArr[row]
                gender_flag = true
            }
            pickerView.reloadAllComponents()
            check_visibility();
        }
    
    
    @IBOutlet weak var name_surname_attr : UITextField!
    @IBOutlet weak var birth_date_attr : UIDatePicker!
    @IBOutlet weak var side_effect_vaccination_attr : UITextField!
    @IBOutlet weak var pcr_positive_cases_and_symproms_attr : UITextField!
    @IBOutlet weak var add_button : UIButton!
    @IBOutlet weak var city_attr: UIPickerView!
    @IBOutlet weak var applied_vaccine_attr : UIPickerView!
    @IBOutlet weak var gender_attr: UIPickerView!
    var date_flag = false
    var vaccine_flag = false
    var gender_flag = false
    var city_flag = false
    var gender_attr_text = ""
    var applied_vaccine_attr_text = ""
    var city_attr_text = ""
    var cities = ["Adana","Adiyaman","Afyon","Agri","Aksaray","Amasya","Ankara","Antalya","Ardahan","Artvin","Aydin","Balikesir","Bartin","Batman","Bayburt","Bilecik","Bingol","Bitlis","Bolu","Burdur","Bursa","Canakkale","Cankiri","Corum","Denizli","Diyarbakir","Duzce","Edirne","Elazig","Erzincan","Erzurum","Eskisehir","Gaziantep","Giresun","Gumushane","Hakkari","Hatay","Igdir","Isparta","Istanbul","Izmir","Kahramanmaras","Karabuk","Karaman","Kars","Kastamonu","Kayseri","Kilis","Kirikkale","Kirklareli","Kirsehir","Kocaeli","Konya","Kutahya","Malatya","Manisa","Mardin","Mersin","Mugla","Mus","Nevsehir","Nigde","Ordu","Osmaniye","Rize","Sakarya","Samsun","Sanliurfa","Siirt","Sinop","Sirnak","Sivas","Tekirdag","Tokat","Trabzon","Tunceli","Usak","Van","Yalova","Yozgat","Zonguldak"]
    
    var vaccine_types = ["Sinovac","Biontech","Turkovac"]
    
    var gender_options = ["Female","Male"]
    
    override func viewDidLoad() {
        
        super.viewDidLoad();
        // Do any additional setup after loading the view.
        birth_date_attr.datePickerMode = .date
        
        gender_attr.dataSource = self
        gender_attr.delegate = self
        
        applied_vaccine_attr.dataSource = self
        applied_vaccine_attr.delegate = self
        
        city_attr.dataSource = self
        city_attr.delegate = self
        
        add_button.isHidden = true;
    }
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
            return 1
        }
    func check_visibility(){

        print(name_surname_attr.text!)
        print(gender_attr_text)
        print(applied_vaccine_attr_text)
        print(city_attr_text)
        print(side_effect_vaccination_attr.text!)
        print(pcr_positive_cases_and_symproms_attr.text!)

        
        if(name_surname_attr.hasText
           && gender_flag
           && vaccine_flag
           && city_flag
           && date_flag
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
        //print(sender.text)
    }
    @IBAction   func birth_date(_ sender: UIDatePicker) {
        birth_date_attr = sender;
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
