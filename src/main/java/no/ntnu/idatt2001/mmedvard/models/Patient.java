package no.ntnu.idatt2001.mmedvard.models;

public class Patient {

    private String firstName;
    private String lastName;
    private String socialSecurityNumber;
    private String diagnosis;
    private String generalPractitioner;

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

    public void setGeneralPractitioner(String generalPractitioner) {
        if(generalPractitioner == null){
            throw new IllegalArgumentException("Social security number cannot be null");
        }

        this.generalPractitioner = generalPractitioner;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        if(socialSecurityNumber == null){
            throw new IllegalArgumentException("Social security number cannot be null");
        }
        if(socialSecurityNumber.replaceAll("[^\\d.]", "").length() != 11){
            throw new IllegalArgumentException("Social security number must be 11 digits (Numbers only)");
        }

        this.socialSecurityNumber = socialSecurityNumber;
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

    public String getGeneralPractitioner() {
        return generalPractitioner;
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

    public boolean equals(Object otherPatient){
        if(otherPatient instanceof Patient){
            Patient otherDetails = (Patient) otherPatient;
            return socialSecurityNumber.equals(otherDetails.getSocialSecurityNumber());
        }else{
            return false;
        }
    }

    public int hashCode(){
        return socialSecurityNumber.hashCode();
    }

    public String toString(){
        return firstName + lastName + socialSecurityNumber + diagnosis + generalPractitioner;
    }
}
