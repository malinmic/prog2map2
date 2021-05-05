package no.ntnu.idatt2001.mmedvard.controllers;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import no.ntnu.idatt2001.mmedvard.models.Patient;


public class PatientDialog extends Dialog<Patient>{


   public enum Mode {
       NEW, EDIT, INFO
   }

   private final Mode mode;
   private Patient existingPatient = null;


   public PatientDialog(){
       super();
       this.mode = Mode.NEW;
       createPatient();
   }


   public PatientDialog(Patient patient, boolean editable){
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

       TextField generalPractitionerTxt = new TextField();
       generalPractitionerTxt.setPromptText("General Practitioner: ");


       if((mode == Mode.EDIT) || (mode == Mode.INFO)) {
           firstNameTxt.setText(existingPatient.getFirstName());
           lastNameTxt.setText((existingPatient.getLastName()));
           socialNumbrTxt.setText(existingPatient.getSocialSecurityNumber());
           generalPractitionerTxt.setText(existingPatient.getDiagnosis());

           if(mode == Mode.INFO) {
               firstNameTxt.setEditable(false);
               lastNameTxt.setEditable(false);
               generalPractitionerTxt.setEditable(false);
               socialNumbrTxt.setEditable(false);
           }
       }

       grid.add(new Label("First name: "),0,0);
       grid.add(firstNameTxt,1,0);

       grid.add(new Label("Last name"),0,1);
       grid.add(lastNameTxt,1,1);

       grid.add(new Label("General Practitioner"),0,2);
       grid.add(generalPractitionerTxt,1,2);

       grid.add(new Label("Social security number"),0,3);
       grid.add(socialNumbrTxt,1,3);


       getDialogPane().setContent(grid);

       setResultConverter((ButtonType button) -> {
           Patient result = null;
           if(button == ButtonType.OK) {

               if(mode == Mode.NEW) {
                   result = new Patient(firstNameTxt.getText(),lastNameTxt.getText(),generalPractitionerTxt.getText(),socialNumbrTxt.getText());
               }else if(mode == Mode.EDIT){
                   existingPatient.setFirstName(firstNameTxt.getText());
                   existingPatient.setLastName(lastNameTxt.getText());
                   existingPatient.setGeneralPractitioner(generalPractitionerTxt.getText());
                   existingPatient.setSocialSecurityNumber(socialNumbrTxt.getText());

                   result = existingPatient;
               }
           }
           return result;
       });
   }



}







