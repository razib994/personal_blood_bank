package com.example.mediavision01.personal_blood_info;

import java.util.ArrayList;
import java.util.List;

public class Blood_Person_Info {
    private int id;
    private String bloodpersonName;
    private String bloodpersonEmail;
    private String bloodpersonPhone;
    private String bloodpersonAddress;
    private String bloodpersondateofbirth;
    private String bloodpersongender;
    private String personBloodGroup;

    public Blood_Person_Info(int id, String bloodpersonName, String bloodpersonEmail, String bloodpersonPhone, String bloodpersonAddress, String bloodpersondateofbirth, String bloodpersongender, String personBloodGroup) {
        this.id = id;
        this.bloodpersonName = bloodpersonName;
        this.bloodpersonEmail = bloodpersonEmail;
        this.bloodpersonPhone = bloodpersonPhone;
        this.bloodpersonAddress = bloodpersonAddress;
        this.bloodpersondateofbirth = bloodpersondateofbirth;
        this.bloodpersongender = bloodpersongender;
        this.personBloodGroup = personBloodGroup;
    }

    public Blood_Person_Info(String bloodpersonName, String bloodpersonEmail, String bloodpersonPhone, String bloodpersonAddress, String bloodpersondateofbirth, String bloodpersongender, String personBloodGroup) {
        this.bloodpersonName = bloodpersonName;
        this.bloodpersonEmail = bloodpersonEmail;
        this.bloodpersonPhone = bloodpersonPhone;
        this.bloodpersonAddress = bloodpersonAddress;
        this.bloodpersondateofbirth = bloodpersondateofbirth;
        this.bloodpersongender = bloodpersongender;
        this.personBloodGroup = personBloodGroup;
    }
    private static List<Blood_Person_Info> blood_person_infos = new ArrayList<>();

    public Blood_Person_Info() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBloodpersonName() {
        return bloodpersonName;
    }

    public void setBloodpersonName(String bloodpersonName) {
        this.bloodpersonName = bloodpersonName;
    }

    public String getBloodpersonEmail() {
        return bloodpersonEmail;
    }

    public void setBloodpersonEmail(String bloodpersonEmail) {
        this.bloodpersonEmail = bloodpersonEmail;
    }

    public String getBloodpersonPhone() {
        return bloodpersonPhone;
    }

    public void setBloodpersonPhone(String bloodpersonPhone) {
        this.bloodpersonPhone = bloodpersonPhone;
    }

    public String getBloodpersonAddress() {
        return bloodpersonAddress;
    }

    public void setBloodpersonAddress(String bloodpersonAddress) {
        this.bloodpersonAddress = bloodpersonAddress;
    }

    public String getBloodpersondateofbirth() {
        return bloodpersondateofbirth;
    }

    public void setBloodpersondateofbirth(String bloodpersondateofbirth) {
        this.bloodpersondateofbirth = bloodpersondateofbirth;
    }

    public String getBloodpersongender() {
        return bloodpersongender;
    }

    public void setBloodpersongender(String bloodpersongender) {
        this.bloodpersongender = bloodpersongender;
    }

    public String getPersonBloodGroup() {
        return personBloodGroup;
    }

    public void setPersonBloodGroup(String personBloodGroup) {
        this.personBloodGroup = personBloodGroup;
    }
    public static List<Blood_Person_Info> getBloodPerson() {
        return blood_person_infos;
    }
    public static void addEmploeeList(Blood_Person_Info blood_person_info){
        blood_person_infos.add(blood_person_info);

    }
}
