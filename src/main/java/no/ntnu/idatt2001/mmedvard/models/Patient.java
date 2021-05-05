package no.ntnu.idatt2001.mmedvard.models;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;


//public class Patient implements Comparable<Patient>{
public class Patient {

    private String firstName;
    private String lastName;
    private String socialSecurityNumber;
    private String diagnosis;

    public Patient(String firstName, String lastName, String socialSecurityNumber, String diagnosis){
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
        this.diagnosis = diagnosis;

        /**
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.socialSecurityNumber = new SimpleStringProperty(socialSecurityNumber);
        this.diagnosis = new SimpleStringProperty(diagnosis);
         */
    }



    //public Patient(String text) {
    //}

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        if(socialSecurityNumber == null || socialSecurityNumber.trim().length() == 0){
            throw new IllegalArgumentException("Social security number cannot be empty");
        }else {
            this.socialSecurityNumber = socialSecurityNumber;
        }
    }

    public void setLastName(String lastName) {
        if(lastName == null || lastName.trim().length() == 0){
            throw new IllegalArgumentException("Last name cannot be empty");
        }else {
            this.lastName = lastName;
        }
    }

    public void setFirstName(String firstName) {
        if(firstName == null || firstName.trim().length() == 0){
            throw new IllegalArgumentException("First name cannot be empty");
        }else {
            this.firstName = firstName;
        }
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    /**
    public SimpleStringProperty getFirstName() {
        return firstName;
    }

    public SimpleStringProperty getLastName() {
        return lastName;
    }


    public SimpleStringProperty getSocialSecurityNumber() {
        return socialSecurityNumber;
    }


    public SimpleStringProperty getDiagnosis() {
        return diagnosis;
    }


    public void setFirstName(SimpleStringProperty firstName) {
        this.firstName = firstName;
    }

    public void setLastName(SimpleStringProperty lastName) {
        this.lastName = lastName;
    }

    public void setSocialSecurityNumber(SimpleStringProperty socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public void setDiagnosis(SimpleStringProperty diagnosis) {
        this.diagnosis = diagnosis;
    }
     */

    public boolean equals(Object otherPatient){
        if(otherPatient instanceof Patient){
            Patient otherDetails = (Patient) otherPatient;
            return socialSecurityNumber.equals(otherDetails.getSocialSecurityNumber());
        }else{
            return false;
        }
    }


    //Implements Comparable<Patient>
    /**
    public int compareTo(Patient otherDetails) {
        int comparison = socialSecurityNumber.compareTo(otherDetails.getSocialSecurityNumber());
        return comparison;
    }
     */


    public int hashCode(){
        int hash = 17;
        hash = 37 * hash + socialSecurityNumber.hashCode();

        return hash;
    }

    public String toString(){
        return firstName + lastName + socialSecurityNumber + diagnosis;
    }
}
