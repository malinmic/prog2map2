package no.ntnu.idatt2001.mmedvard.models;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * This class contains static methods for reading and writing csv files containing a list of patients
 *
 * @author OAHjellj
 */
public class FileManager {
    /**
     * Makes an arraylist of patients from the specified file
     *
     * @param file file that should be read
     * @return an ArrayList containing all patients in the file
     * @throws IOException if file is not found
     */
    public static ArrayList<Patient> importFromFile(File file) throws IOException {
        ArrayList<Patient> patients = new ArrayList<>();
        BufferedReader csvReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));

        String row = csvReader.readLine();
        if(!row.split(";")[0].equals("firstName")) throw new IOException("File is not an importable file");

        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(";");
            if(data.length != 4 && data.length != 5) throw new IndexOutOfBoundsException("Mismatching row found in file");

            if(data.length == 5) {
                if (data[4].trim().length() == 11) {
                    Patient patient = new Patient(data[0].trim(), data[1].trim(), data[2].trim(), data[4].trim());
                    patients.add(patient);

                    if (!data[3].isEmpty()) {
                        patient.setDiagnosis(data[3]);
                    }
                }
            }else {
                if (data[3].trim().length() == 11) {
                    Patient patient = new Patient(data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim());
                    patients.add(patient);
                }
            }
        }
        csvReader.close();

        return patients;
    }

    /**
     * Overwrites the given file with the current list of patients
     *
     * @param file file that should be written
     * @param patients to be saved
     * @throws IOException if file not found
     */
    public static void exportToFile(File file, PatientRegister patients) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), StandardCharsets.UTF_8));

        writer.write("firstName;lastName;generalPractitioner;diagnosis;socialSecurityNumber");

        for(Patient patient : patients.getAllPatients()) {
            writer.append("\n").append(patient.getFirstName()).append(";")
                    .append(patient.getLastName()).append(";")
                    .append(patient.getGeneralPractitioner()).append(";")
                    .append(patient.getDiagnosis()).append(";")
                    .append(patient.getSocialSecurityNumber()).append(";");
        }

        writer.close();
    }
}

