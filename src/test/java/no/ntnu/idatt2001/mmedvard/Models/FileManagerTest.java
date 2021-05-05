package no.ntnu.idatt2001.mmedvard.Models;

import no.ntnu.idatt2001.mmedvard.models.FileManager;
import no.ntnu.idatt2001.mmedvard.models.Patient;
import no.ntnu.idatt2001.mmedvard.models.PatientRegister;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileManagerTest {
    @Test
    public void importFromFile_imports(){
        try {
            Assertions.assertEquals(List.of(new Patient("Ola","Norman","Alida","09090909090")), FileManager.importFromFile(new File("src/test/java/no/ntnu/idatt2001/mmedvard/Resources/TestPatient.csv")));
        } catch (IOException e) {
            Assertions.fail();
        }
    }

    @Test
    public void exportFromFile_exports(){
        File file = new File("patients.csv");
        PatientRegister register = new PatientRegister();
        register.addPatient(new Patient("Ola","Normann","","12345678910"));

        try {
            FileManager.exportToFile(file, register);
        } catch (IOException e) {
            Assertions.fail();
        }

        try {
            Assertions.assertEquals(register.getAllPatients(), FileManager.importFromFile(file));
        } catch (IOException e) {
            Assertions.fail();
        }
    }
}
