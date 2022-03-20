//
//  ViewController.swift
//  453_form
//
//  Created by Emre Derman on 3/16/22.
//

import UIKit

class ViewController: UIViewController {
    
    @IBOutlet weak var name_surname_attr : UITextField!
    @IBOutlet weak var birth_date_attr : UIDatePicker!
    @IBOutlet weak var applied_vaccine_attr : UITextField!
    @IBOutlet weak var side_effect_vaccination_attr : UITextField!
    @IBOutlet weak var pcr_positive_cases_and_symproms_attr : UITextField!
    @IBOutlet weak var add_button : UIButton!
    @IBOutlet weak var city_attr: UITextField!
    @IBOutlet weak var gender_attr: UITextField!
    var date_flag = false

    override func viewDidLoad() {
        
        super.viewDidLoad();
        // Do any additional setup after loading the view.
        birth_date_attr.datePickerMode = .date
        add_button.isHidden = true;
    }
    func check_visibility(){
        if(name_surname_attr.hasText
           && applied_vaccine_attr.hasText
           && date_flag
           && side_effect_vaccination_attr.hasText
           && pcr_positive_cases_and_symproms_attr.hasText
           && city_attr.hasText
           && gender_attr.hasText
          ){
            add_button.isHidden = false;
        }else{
            add_button.isHidden = true;
        }
    }
    @IBAction func name_surname(_ sender: UITextField) {
        name_surname_attr = sender;
        
    print(birth_date_attr.isSelected)
        check_visibility();
        print(sender.text)
    }
    @IBAction   func birth_date(_ sender: UIDatePicker) {
        birth_date_attr = sender;
        date_flag = true
        check_visibility();
    }
    @IBAction   func city(_ sender: UITextField) {
            city_attr = sender;
        check_visibility();
    }
    @IBAction    func gender(_ sender: UITextField) {
        gender_attr = sender;
        print(sender.text)
        check_visibility();
    }
    @IBAction  func applied_vaccine(_ sender: UITextField) {
        applied_vaccine_attr = sender;
        print(sender.text)
        check_visibility();
    }
    @IBAction  func side_effect_vaccination(_ sender: UITextField) {
        side_effect_vaccination_attr = sender;
        print(sender.text)
        check_visibility();
    }
    @IBAction  func pcr_positive_cases_and_symproms(_ sender: UITextField) {
        pcr_positive_cases_and_symproms_attr = sender;
        print(sender.text)
        check_visibility();
    }
    @IBAction func add_data(_ sender: UIButton) {
        let alertController = UIAlertController(
            title: "ILK iOS UYGULAMAM",
            message: name_surname_attr.text,
            preferredStyle: UIAlertController.Style.alert)
               alertController.addAction(UIAlertAction(title: "OK", style: UIAlertAction.Style.default,
       handler: nil))
               present(alertController, animated: true, completion: nil)
           }
        
}
