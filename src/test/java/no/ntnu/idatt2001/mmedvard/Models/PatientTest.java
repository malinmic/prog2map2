package no.ntnu.idatt2001.mmedvard.Models;

import no.ntnu.idatt2001.mmedvard.models.Patient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class PatientTest {
    @Test
    public void constructor_nullFirstName_throws() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Patient(null, "Normann","","05059012122"));
    }

    @Test
    public void constructor_nullLastName_throws() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Patient("Ola", null,"","05059012122"));
    }

    @Test
    public void constructor_nullGeneralPractitioner_throws() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Patient("Ola", "Normann",null,"05059012122"));
    }

    @Test
    public void constructor_nullSocialSecurityNumber_throws() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Patient("Ola", "Normann","",null));
    }

    @Test
    public void constructor_illegalSocialSecurityNumber_throws() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Patient("Ola", "Normann","","0fsdagak"));
    }


    @Test
    void setGeneralPractitioner_legalFiled_sets() {
        Patient patient = new Patient("Ola","Normann","Alida","05059012122");

        patient.setGeneralPractitioner("Malin");

        Assertions.assertEquals("Malin",patient.getGeneralPractitioner());
    }

    @Test
    void setGeneralPractitioner_illegalFiled_throws() {
        Patient patient = new Patient("Ola","Normann","Alida","05059012122");

        Assertions.assertThrows(IllegalArgumentException.class, () -> patient.setGeneralPractitioner(null));
    }

    @Test
    void setDiagnosis_legalFiled_sets() {
        Patient patient = new Patient("Ola","Normann","Alida","05059012122");

        patient.setDiagnosis("Kostokondritt");

        Assertions.assertEquals("Kostokondritt",patient.getDiagnosis());
    }

    @Test
    void setDiagnosis_illegalField_throws(){
        Patient patient = new Patient("Ola","Normann","Alida","05059012122");

        Assertions.assertThrows(IllegalArgumentException.class, () -> patient.setDiagnosis(null));
    }

    @Test
    void setSocialSecurityNumber_legalFiled_sets() {
        Patient patient = new Patient("Ola","Normann","Alida","05059012122");

        patient.setSocialSecurityNumber("09099190000");

        Assertions.assertEquals("09099190000",patient.getSocialSecurityNumber());

    }

    @Test
    void setSocialSecurityNumber_not11digits_throws() {
        Patient patient = new Patient("Ola","Normann","Alida","05059012122");

        Assertions.assertThrows(IllegalArgumentException.class, () -> patient.setSocialSecurityNumber("dfgd"));

    }

    @Test
    void setSocialSecurityNumber_null_throws(){
        Patient patient = new Patient("Ola","Normann","Alida","05059012122");

        Assertions.assertThrows(IllegalArgumentException.class, () -> patient.setSocialSecurityNumber(null));
    }



    @Test
    void setLastName_legalFiled_sets() {
        Patient patient = new Patient("Ola","Normann","Alida","05059012122");

        patient.setLastName("Olsen");

        Assertions.assertEquals("Olsen",patient.getLastName());

    }

    @Test
    void setLastName_illegalField_throws(){
        Patient patient = new Patient("Ola","Normann","Alida","05059012122");

        Assertions.assertThrows(IllegalArgumentException.class, () -> patient.setLastName(null));
    }

    @Test
    void setLastName_emptyName_throws(){
        Patient patient = new Patient("Ola","Normann","Alida","05059012122");

        Assertions.assertThrows(IllegalArgumentException.class, () -> patient.setLastName(" "));
    }

    @Test
    void setFirstName_legalFiled_sets() {
        Patient patient = new Patient("Ola","Normann","Alida","05059012122");

        patient.setFirstName("Per");

        Assertions.assertEquals("Per",patient.getFirstName());
    }

    @Test
    void setFirstName_illegalField_throws(){
        Patient patient = new Patient("Ola","Normann","Alida","05059012122");

        Assertions.assertThrows(IllegalArgumentException.class, () -> patient.setFirstName(null));
    }

    @Test
    void setFirstName_emptyName_throws(){
        Patient patient = new Patient("Ola","Normann","Alida","05059012122");

        Assertions.assertThrows(IllegalArgumentException.class, () -> patient.setFirstName(" "));
    }


    @Test
    void getGeneralPractitioner_gets() {
        Patient patient = new Patient("Ola","Normann","Alida","05059012122");

        Assertions.assertEquals("Alida", patient.getGeneralPractitioner());
    }


    @Test
    void getDiagnosis_gets() {
        Patient patient = new Patient("Ola","Normann","Alida","05059012122");

        Assertions.assertEquals("", patient.getDiagnosis());
    }

    @Test
    void getSocialSecurityNumber_gets() {
        Patient patient = new Patient("Ola","Normann","Alida","05059012122");

        Assertions.assertEquals("05059012122", patient.getSocialSecurityNumber());
    }

    @Test
    void getFirstName_gets() {
        Patient patient = new Patient("Ola","Normann","Alida","05059012122");

        Assertions.assertEquals("Ola", patient.getFirstName());
    }

    @Test
    void getLastName() {
        Patient patient = new Patient("Ola","Normann","Alida","05059012122");

        Assertions.assertEquals("Normann", patient.getLastName());
    }

    @Test
    void testEquals_notEqual_returnsFalse() {
        Patient patient1 = new Patient("Ola","Normann","Alida","05059055500");
        Patient patient2 = new Patient("Ola","Normann","Alida","05059012122");

        Assertions.assertFalse(patient1.equals(patient2));
    }

    @Test
    void testEquals_equal_returnsTrue() {
        Patient patient1 = new Patient("Ola","Normann","Alida","05059055500");
        Patient patient2 = new Patient("Ola","Normann","Alida","05059055500");

        Assertions.assertTrue(patient1.equals(patient2));
    }

    @Test
    void testToString() {
        Patient patient = new Patient("Ola","Normann","Alida","05059012122");

        Assertions.assertEquals("Name: Ola Normann\nSocial security number: 05059012122\nGeneral Practitioner: Alida Diagnosis:", patient.toString());
    }
}
