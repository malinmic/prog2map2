package no.ntnu.idatt2001.mmedvard.models;


/**
 * Class representing a pasient
 */

public class Patient {

    private String firstName;
    private String lastName;
    private String socialSecurityNumber;
    private String diagnosis;
    private String generalPractitioner;


    /**
     * Constructs new patient
     * @param firstName String
     * @param lastName String
     * @param generalPractitioner String
     * @param socialSecurityNumber String of only numbers, 11 digits
     */
    public Patient(String firstName, String lastName, String generalPractitioner, String socialSecurityNumber){
        if(firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be empty");
        }
        if(lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be empty");
        }
        if(generalPractitioner == null){
            throw new IllegalArgumentException("generalPractitioner cannot be null");
        }
        if(socialSecurityNumber == null){
            throw new IllegalArgumentException("Social security number cannot be null");
        }
        if(socialSecurityNumber.replaceAll("[^\\d.]", "").length() != 11){
            throw new IllegalArgumentException("Social security number must be 11 digits (Numbers only)");
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
        this.diagnosis = "";
        this.generalPractitioner = generalPractitioner;
    }

    /**
     * set general practitioner
     * @param generalPractitioner String
     */
    public void setGeneralPractitioner(String generalPractitioner) {
        if(generalPractitioner == null){
            throw new IllegalArgumentException("Social security number cannot be null");
        }

        this.generalPractitioner = generalPractitioner;
    }

    /**
     * sets diagnosis
     * @param diagnosis String
     */
    public void setDiagnosis(String diagnosis) {
        if(diagnosis == null) {
            throw new IllegalArgumentException("diagnosis cannot be null");
        }
        this.diagnosis = diagnosis;
    }

    /**
     * sets social security number
     * @param socialSecurityNumber String, only 11 digits
     */
    public void setSocialSecurityNumber(String socialSecurityNumber) {
        if(socialSecurityNumber == null){
            throw new IllegalArgumentException("Social security number cannot be null");
        }
        if(socialSecurityNumber.replaceAll("[^\\d.]", "").length() != 11){
            throw new IllegalArgumentException("Social security number must be 11 digits (Numbers only)");
        }

        this.socialSecurityNumber = socialSecurityNumber;
    }

    /**
     * sets last name
     * @param lastName String
     */
    public void setLastName(String lastName) {
        if(lastName == null || lastName.trim().length() == 0){
            throw new IllegalArgumentException("Last name cannot be empty");
        }

        this.lastName = lastName;

    }

    /**
     * sets first name
     * @param firstName String
     */
    public void setFirstName(String firstName) {
        if(firstName == null || firstName.trim().length() == 0){
            throw new IllegalArgumentException("First name cannot be empty");
        }

        this.firstName = firstName;

    }


    /**
     * gets general practitioner
     * @return String
     */
    public String getGeneralPractitioner() {
        return generalPractitioner;
    }

    /**
     * gets diagnosis
     * @return String
     */
    public String getDiagnosis() {
        return diagnosis;
    }

    /**
     * gets social security number
     * @return String, 11 digits
     */
    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }


    /**
     * gets first name
     * @return String
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * gets last name
     * @return String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * equals method to check if patients are the same
     * @param otherPatient object
     * @return true if equal
     */
    public boolean equals(Object otherPatient){
        if(otherPatient instanceof Patient){
            Patient otherDetails = (Patient) otherPatient;
            return socialSecurityNumber.equals(otherDetails.getSocialSecurityNumber());
        }else{
            return false;
        }
    }

    /**
     *
     * @return hashCode from socialSecurityNumber
     */
    public int hashCode(){
        return socialSecurityNumber.hashCode();
    }

    /**
     * toString
     * @return String of all patient fields
     */
    public String toString(){
        return "Name: " + firstName + " " + lastName + "\nSocial security number: " + socialSecurityNumber+ "\nGeneral Practitioner: " + generalPractitioner + " Diagnosis:" + diagnosis ;
    }
}
