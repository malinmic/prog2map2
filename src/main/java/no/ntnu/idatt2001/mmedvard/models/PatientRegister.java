package no.ntnu.idatt2001.mmedvard.models;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Class containing a list of patients
 */
public class PatientRegister{

    private ArrayList<Patient> patientArrayList;

    public PatientRegister(){
        patientArrayList = new ArrayList<>();
    }

    /**
     * adds patient to the registry
     * @param newPatient patient
     * @return true if patient was added
     */
    public boolean addPatient(Patient newPatient){
        if(patientArrayList.add(newPatient)) {
            return true;
        }
        return false;
    }

    /**
     * removes patient from the registry
     * @param patientToBeRemoved patient
     * @return true if patient was removed
     */
    public boolean removePatient(Patient patientToBeRemoved){
        return this.patientArrayList.remove(patientToBeRemoved);
    }

    /**
     * gets the list of patients
     * @return the list of patients
     */
    public Collection<Patient> getAllPatients(){
        return this.patientArrayList;
    }


}