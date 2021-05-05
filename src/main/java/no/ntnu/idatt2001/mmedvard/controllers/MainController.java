package no.ntnu.idatt2001.mmedvard.controllers;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import no.ntnu.idatt2001.mmedvard.models.Patient;
import no.ntnu.idatt2001.mmedvard.models.PatientRegister;
import no.ntnu.idatt2001.mmedvard.views.App;
import no.ntnu.idatt2001.mmedvard.views.PatientDialogView;

import java.awt.event.ActionEvent;
import java.util.Optional;

public class MainController {

    public void exit(javafx.event.ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setContentText("Do you want to exit the Patient Register?");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == ButtonType.OK){
            Platform.exit();
        }

    }

    public void showAboutDialog(String version){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Patient Register \nv" + version);
        alert.setContentText("A sufficient application created by \n" + "mmedvard \n" + "2021");

        alert.showAndWait();

    }

    public void addPatient(PatientRegister patientRegister, App parent){

        PatientDialogView patientDialogView = new PatientDialogView();

        Optional<Patient> result = patientDialogView.showAndWait();

        if(result.isPresent()){
            Patient newPatient = result.get();
            patientRegister.addPatient(newPatient);
            parent.updateObservableList();
        }
    }

    public void editPatient(Patient selectedPatient, App parent){
        if(selectedPatient == null){
            showPleaseSelectItemDialog();
        }else{
            /**
            PatientDialogView patientDialogView = new PatientDialogView(selectedPatient, true);
            patientDialogView.showAndWait();

            parent.updateObservableList();
             */

            PatientDialogView editPatientDialog = new PatientDialogView(selectedPatient, true);
            editPatientDialog.showAndWait();
            parent.updateObservableList();

        }

    }

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

    public void showPleaseSelectItemDialog(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Information");
        alert.setHeaderText("No item selected");
        alert.setContentText("Please select an item from the table");

        alert.showAndWait();
    }

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


}
