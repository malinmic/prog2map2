package no.ntnu.idatt2001.mmedvard.models;

import java.util.ArrayList;
import java.util.Collection;

public class PatientRegister{

    private ArrayList<Patient> patientArrayList;

    public PatientRegister(){
        patientArrayList = new ArrayList<>();
    }

    public void addPatient(Patient newPatient){
        if(newPatient != null){
            patientArrayList.add(newPatient);
        }
    }

    public void removePatient(Patient patientToBeRemoved){
        this.patientArrayList.remove(patientToBeRemoved);
    }

    public Collection<Patient> getAllPatients(){
        return this.patientArrayList;
    }


}