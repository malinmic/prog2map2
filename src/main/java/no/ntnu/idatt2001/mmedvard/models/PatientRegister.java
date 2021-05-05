package no.ntnu.idatt2001.mmedvard.models;

import java.util.ArrayList;
import java.util.Collection;

public class PatientRegister{

    private ArrayList<Patient> patientArrayList;

    public PatientRegister(){
        patientArrayList = new ArrayList<>();
    }

    public boolean addPatient(Patient newPatient){
        if(patientArrayList.add(newPatient)) {
            return true;
        }
        return false;
    }

    public boolean removePatient(Patient patientToBeRemoved){
        return this.patientArrayList.remove(patientToBeRemoved);
    }

    public Collection<Patient> getAllPatients(){
        return this.patientArrayList;
    }


}