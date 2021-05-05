package no.ntnu.idatt2001.mmedvard.Models;

import no.ntnu.idatt2001.mmedvard.models.Patient;
import no.ntnu.idatt2001.mmedvard.models.PatientRegister;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class PatientRegisterTest {
    @Test
    void addPatient_legalPatient_addsPatient() {
        PatientRegister patientRegister = new PatientRegister();
        Patient patient = new Patient("Ola","Normann"," ","09099090000");

        Assertions.assertTrue(patientRegister.addPatient(patient));
    }

    @Test
    void removePatient_existingPatient_removes() {
        PatientRegister patientRegister = new PatientRegister();
        Patient patient = new Patient("Ola","Normann"," ","09099090000");
        patientRegister.addPatient(patient);

        Assertions.assertTrue(patientRegister.removePatient(patient));
    }

    @Test
    void removePatient_newPatient_returnsFalse() {
        PatientRegister patientRegister = new PatientRegister();
        Patient patient = new Patient("Ola","Normann"," ","09099090000");

        Assertions.assertFalse(patientRegister.removePatient(patient));
    }

    @Test
    void getAllPatients_() {
        PatientRegister patientRegister = new PatientRegister();

        Assertions.assertEquals(new ArrayList<Patient>(), patientRegister.getAllPatients());
    }
}
