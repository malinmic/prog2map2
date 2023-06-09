package no.ntnu.idatt2001.mmedvard.controllers;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import no.ntnu.idatt2001.mmedvard.models.FileManager;
import no.ntnu.idatt2001.mmedvard.models.Patient;
import no.ntnu.idatt2001.mmedvard.models.PatientRegister;
import no.ntnu.idatt2001.mmedvard.App;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Class containing methods for all buttons
 * @author mmedvard
 */

public class MainController {

    /**
     *
     * @param event
     */
    public void exit(javafx.event.ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setContentText("Do you want to exit the Patient Register?");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == ButtonType.OK) {
            Platform.exit();
        }
    }


    /**
     * Opens about dialog
     * @param version verison of the application
     */
    public void showAboutDialog(String version){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Patient Register \nv" + version);
        alert.setContentText("A sufficient application created by \n" + "mmedvard \n" + "2021");

        alert.showAndWait();

    }

    /**
     * Opens dialog for adding a new patient
     * @param patientRegister PatientRegister to be added to
     * @param parent parent class App
     */
    public void addPatient(PatientRegister patientRegister, App parent){

        PatientDialog patientDialog = new PatientDialog();

        Optional<Patient> result = patientDialog.showAndWait();

        if(result.isPresent()){
            Patient newPatient = result.get();
            patientRegister.addPatient(newPatient);
            parent.updateObservableList();
        }
    }

    /**
     * Dialog for editing patient details
     * @param selectedPatient the patient selected to be edited
     * @param parent parent class App
     */
    public void editPatient(Patient selectedPatient, App parent){
        if(selectedPatient == null){
            showPleaseSelectItemDialog();
        }else{
            PatientDialog editPatientDialog = new PatientDialog(selectedPatient, true);
            editPatientDialog.showAndWait();
            parent.updateObservableList();

        }

    }

    /**
     * Dialog for deleting patient
     * @param selectedPatient patient to be removed
     * @param patientRegister
     * @param parent parent class App
     */
    public void removePatient(Patient selectedPatient, PatientRegister patientRegister, App parent){
        if(selectedPatient == null){
            showPleaseSelectItemDialog();
        }else{
            if(showDeleteConfirmationDialog()){
                patientRegister.removePatient(selectedPatient);
                parent.updateObservableList();
            }
        }
    }

    /**
     * Alert for no patient selected
     */
    public void showPleaseSelectItemDialog(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Information");
        alert.setHeaderText("No item selected");
        alert.setContentText("Please select an item from the table");

        alert.showAndWait();
    }

    /**
     * Confirmation dialog
     * @return
     */
    public boolean showDeleteConfirmationDialog(){
        boolean deleteOrNotToDelete = false;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Delete");
        alert.setContentText("Are you sure you want to remove this patient from the register?");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent()){
            deleteOrNotToDelete = (result.get() == ButtonType.OK);
        }
        return deleteOrNotToDelete;
    }

    /**
     * OnActionEvent for import from file
     * @param event
     * @param patientRegister
     * @param parent
     */
    public void importFromFile(ActionEvent event, PatientRegister patientRegister, App parent) {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(new Stage());

        if (file == null) {
            return;
        }
        if (!isCSV(file.getName())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wrong file type!");
            alert.setHeaderText("File is not csv type");
            alert.showAndWait();
            return;
        }
        ArrayList<Patient> patientList;
        try {
            patientList = FileManager.importFromFile(file);
        }catch (Exception e) {
            return;
        }

        patientList.forEach(patientRegister::addPatient);
        parent.updateObservableList();
    }

    /**
     * checks if file is correct format
     * @param name
     * @return
     */
    private boolean isCSV(String name) {
        String[] nameArray = name.split("\\.");
        return nameArray[nameArray.length-1].equals("csv");
    }

    /**
     * onActionEvent for export
     * @param event
     * @param patientRegister
     */
    public void ExportFromFile(ActionEvent event, PatientRegister patientRegister){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Name Export File");
        dialog.setHeaderText("Give a name to the export file, if the name is taken a new name can be chosen or the existing file can be overwritten.");
        dialog.setContentText("Please enter file name:");
        Optional<String> resultString = dialog.showAndWait();

        if (resultString.isEmpty() || resultString.get().trim().isEmpty()){
            return;
        }

        File file = new File(resultString.get().trim() + ".csv");

        try {
            if (!file.createNewFile()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Overwrite File?");
                alert.setHeaderText("This file already exists.");
                alert.setContentText("Is it ok to overwrite it?");

                Optional<ButtonType> resultBoolean = alert.showAndWait();
                if (resultBoolean.isPresent() && resultBoolean.get() != ButtonType.OK) {
                    return;
                }
            }

            FileManager.exportToFile(file, patientRegister);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
