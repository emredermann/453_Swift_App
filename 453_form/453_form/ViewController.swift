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
    @IBOutlet weak var gender_picker: UIPickerView!
    @IBOutlet weak var city_picker: UIPickerView!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        add_button.isHidden = button_visibility();
    }
    
    func button_visibility() -> Bool{
        print(name_surname_attr.text);
        print(birth_date_attr.date.formatted());
        print(applied_vaccine_attr.text);
        print(side_effect_vaccination_attr.text);
        print(pcr_positive_cases_and_symproms_attr.text);
        
        
        
        if(name_surname_attr.hasText
           && birth_date_attr.isEnabled
           && applied_vaccine_attr.hasText
           && side_effect_vaccination_attr.hasText
           && pcr_positive_cases_and_symproms_attr.hasText
          ){
            return true;
        }else{
            return false;
        }
    }
    
    
    @IBAction func name_surname(_ sender: UITextField) {
        name_surname_attr.text = sender.text!;
    }
    
    @IBAction func birth_date(_ sender: UIDatePicker) {
        birth_date_attr = sender;
    }
    @IBAction func city(_ sender: UIPickerView) {
        city_picker = sender;

    }
    @IBAction func gender(_ sender: UIPickerView) {
        gender_picker = sender;
    }
    @IBAction func applied_vaccine(_ sender: UITextField) {
        applied_vaccine_attr.text = sender.text!;
    }
    @IBAction func side_effect_vaccination(_ sender: UITextField) {
        side_effect_vaccination_attr.text = sender.text!;
    }
    @IBAction func pcr_positive_cases_and_symproms(_ sender: UITextField) {
        pcr_positive_cases_and_symproms_attr.text = sender.text!;
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

