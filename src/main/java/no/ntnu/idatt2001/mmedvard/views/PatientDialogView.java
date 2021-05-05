package no.ntnu.idatt2001.mmedvard.views;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import no.ntnu.idatt2001.mmedvard.models.Patient;
import java.awt.*;
import java.util.Optional;

import no.ntnu.idatt2001.mmedvard.models.PatientRegister;


public class PatientDialogView extends Dialog<Patient>{


   public enum Mode {
       NEW, EDIT, INFO
   }

   private final Mode mode;
   private Patient existingPatient = null;


   public PatientDialogView(){
       super();
       this.mode = Mode.NEW;
       createPatient();
   }


   public PatientDialogView(Patient patient, boolean editable){
       super();
       if(editable){
           this.mode = Mode.EDIT;
       }else{
           this.mode = Mode.INFO;
       }

       this.existingPatient = patient;
       createPatient();
   }

   private void createPatient(){
       switch (this.mode){
           case EDIT:
               setTitle("Patient details - edit");
               break;

           case NEW:
               setTitle("Patient details - add");
               break;

           case INFO:
               setTitle("Patient details");
               break;

           default:
               setTitle("Unknown mode...");
               break;
       }


       getDialogPane().getButtonTypes().addAll(ButtonType.OK,ButtonType.CANCEL);



       GridPane grid = new GridPane();
       grid.setHgap(10);
       grid.setVgap(10);
       grid.setPadding(new Insets(20,150,10,10));



       TextField firstNameTxt = new TextField();
       firstNameTxt.setPromptText("First name: ");

       TextField lastNameTxt = new TextField();
       lastNameTxt.setPromptText("Last name: ");

       TextField socialNumbrTxt = new TextField();
       socialNumbrTxt.setPromptText("Social security number: ");

       TextField diagnosisTxt = new TextField();
       diagnosisTxt.setPromptText("Diagnosis: ");



       if((mode == Mode.EDIT) || (mode == Mode.INFO)) {
           firstNameTxt.setText(existingPatient.getFirstName());
           lastNameTxt.setText((existingPatient.getLastName()));
           socialNumbrTxt.setText(existingPatient.getSocialSecurityNumber());
           diagnosisTxt.setText(existingPatient.getDiagnosis());

           if(mode == Mode.INFO) {
               firstNameTxt.setEditable(false);
               lastNameTxt.setEditable(false);
               socialNumbrTxt.setEditable(false);
               diagnosisTxt.setEditable(false);
           }
       }


       grid.add(new Label("First name: "),0,0);
       grid.add(firstNameTxt,1,0);

       grid.add(new Label("Last name"),0,1);
       grid.add(lastNameTxt,1,1);

       grid.add(new Label("Social security number"),0,2);
       grid.add(socialNumbrTxt,1,2);

       grid.add(new Label("Diagnosis"),0,3);
       grid.add(diagnosisTxt,1,3);

       getDialogPane().setContent(grid);

       setResultConverter((ButtonType button) -> {
           Patient result = null;
           if(button == ButtonType.OK) {

               if(mode == Mode.NEW) {
                   result = new Patient(firstNameTxt.getText(),lastNameTxt.getText(),socialNumbrTxt.getText(),diagnosisTxt.getText());
               }else if(mode == Mode.EDIT){
                   existingPatient.setFirstName(firstNameTxt.getText());
                   existingPatient.setLastName(lastNameTxt.getText());
                   existingPatient.setSocialSecurityNumber(socialNumbrTxt.getText());
                   existingPatient.setDiagnosis(diagnosisTxt.getText());
                   result = existingPatient;
               }
           }
           return result;
       });




   }



}







