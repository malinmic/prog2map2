package no.ntnu.idatt2001.mmedvard;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatt2001.mmedvard.controllers.MainController;
import no.ntnu.idatt2001.mmedvard.models.Patient;
import no.ntnu.idatt2001.mmedvard.models.PatientRegister;
import no.ntnu.idatt2001.mmedvard.controllers.PatientDialog;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class App extends Application {

    private static final String VERSION = "0.0.1";

    private TableView<Patient> patientTableView = new TableView<Patient>();
    private MainController mainController;
    private PatientDialog patientDialog;
    private PatientRegister patientRegister;
    private ObservableList<Patient> patientRegisterListWrapper;



    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void init() throws Exception{
        super.init();

        this.mainController = new MainController();
        this.patientRegister = new PatientRegister();
        this.patientRegisterListWrapper = getPatientRegisterListWrapper();
    }



    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Patient Register");
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(800);
        createTable();
        MenuBar mainMenu = createMenus();
        ToolBar toolBar = createToolBar();


        VBox topContainer = new VBox();
        topContainer.getChildren().add(mainMenu);
        topContainer.getChildren().add(toolBar);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(this.patientTableView);
        borderPane.setTop(topContainer);
        borderPane.setBottom(createStatusBar());


        Scene scene = new Scene(borderPane,600,800);
        primaryStage.setScene(scene);

        primaryStage.show();

    }


    public void updateObservableList(){
        this.patientTableView.setItems(getPatientRegisterListWrapper());
        this.patientTableView.refresh();
    }

    public ObservableList<Patient> getPatientRegisterListWrapper(){

        if(this.patientRegister == null){
            patientRegisterListWrapper = null;
        }else{
            patientRegisterListWrapper = FXCollections.observableArrayList(this.patientRegister.getAllPatients());

        }
        return patientRegisterListWrapper;

    }

    private Node createStatusBar(){
        HBox statusBar = new HBox();
        statusBar.setStyle("-fx-background-color: #999999;");
        statusBar.getChildren().add(new Text("Status: OK"));

        return statusBar;
    }



    private MenuBar createMenus(){

        //initialize mainController
        this.mainController = new MainController();

        //------------ FILE MENU------------
        Menu fileMenu = new Menu("File");
        MenuItem fileImport = new MenuItem("Import from CSV...");
        fileImport.setOnAction(event -> mainController.importFromFile(event, this.patientRegister,this));
        MenuItem fileExport = new MenuItem("Export from CSV...");
        fileExport.setOnAction(event -> mainController.ExportFromFile(event, this.patientRegister));
        MenuItem fileExit = new MenuItem("Exit");
        fileExit.setOnAction(event -> mainController.exit(event));

        fileMenu.getItems().add(fileImport);
        fileMenu.getItems().add(fileExport);
        //separator line before exit
        fileMenu.getItems().add(new SeparatorMenuItem());
        fileMenu.getItems().add(fileExit);

        //------------ EDIT MENU ------------
        Menu editMenu = new Menu("Edit");

        //add patient
        MenuItem editAdd = new MenuItem("Add new patient...");
        editAdd.setAccelerator(new KeyCodeCombination(KeyCode.A));
        editAdd.setOnAction(event -> mainController.addPatient(this.patientRegister, this));

        //edit patient
        MenuItem editEdit = new MenuItem("Edit selected patient...");
        editEdit.setAccelerator(new KeyCodeCombination(KeyCode.E));
        editEdit.setOnAction(
                event -> mainController.editPatient(this.patientTableView.getSelectionModel().getSelectedItem(),this));


        //remove patient
        MenuItem editRemove = new MenuItem("Remove selected patient");
        editRemove.setAccelerator(new KeyCodeCombination(KeyCode.DELETE));
        editRemove.setOnAction(
                event -> mainController.removePatient(this.patientTableView.getSelectionModel().getSelectedItem(),this.patientRegister,this));



        editMenu.getItems().addAll(editAdd,editEdit,editRemove);

        //------------ HELP MENU ------------
        Menu helpMenu = new Menu("Help");
        MenuItem helpAbout = new MenuItem("About...");
        helpAbout.setOnAction(event -> mainController.showAboutDialog(VERSION));
        helpMenu.getItems().add(helpAbout);

        //------------ MAIN MENU BAR ------------
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu,editMenu,helpMenu);

        return menuBar;
    }

    private ToolBar createToolBar() throws FileNotFoundException {

        //TOOL BAR
        ToolBar toolBar = new ToolBar();

        //ADD BUTTON
        Image addImg = new Image(new FileInputStream(getClass().getClassLoader().getResource("ADDBUTTON.png").getFile()),16,16,true,true);
        Button addButton = new Button("Add", new ImageView(addImg));
        addButton.setOnAction(event -> mainController.addPatient(this.patientRegister, this));


        //EDIT BUTTON
        Image editImg = new Image(new FileInputStream(getClass().getClassLoader().getResource("EDITBUTTON.png").getFile()),16,16,true,true);
        Button editButton = new Button("Edit", new ImageView(editImg));
        editButton.setOnAction(
                event -> mainController.editPatient(this.patientTableView.getSelectionModel().getSelectedItem(),this));

        //REMOVE BUTTON
        Image removeImg = new Image(new FileInputStream(getClass().getClassLoader().getResource("REMOVEBUTTON.png").getFile()),16,16,true,true);
        Button removeButton = new Button("Remove", new ImageView(removeImg));
        removeButton.setOnAction(
                event -> mainController.removePatient(this.patientTableView.getSelectionModel().getSelectedItem(),this.patientRegister,this));

        toolBar.getItems().addAll(addButton, editButton, removeButton);
        return toolBar;

    }


    private void createTable(){
        //COLUMNS FOR TABLE VIEW
        //FIRST NAME
        TableColumn<Patient,String> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setMinWidth(200);
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        //LAST NAME
        TableColumn<Patient,String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setMinWidth(200);
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        //SOCIAL SECURITY NUMBER
        TableColumn<Patient,String> socialSecurityNumberColumn = new TableColumn<>("Social Security Number");
        socialSecurityNumberColumn.setMinWidth(200);
        socialSecurityNumberColumn.setCellValueFactory(new PropertyValueFactory<>("socialSecurityNumber"));

        //DIAGNOSIS
        TableColumn<Patient,String> diagnosisColumn = new TableColumn<>("Diagnosis");
        diagnosisColumn.setMinWidth(200);
        diagnosisColumn.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));

        patientTableView = new TableView<>();
        ObservableList<Patient> observableListOfPatients = this.patientRegisterListWrapper;
        patientTableView.setItems(observableListOfPatients);

        patientTableView.getColumns().addAll(firstNameColumn,lastNameColumn,socialSecurityNumberColumn,diagnosisColumn);

        patientTableView.setItems(FXCollections.observableArrayList(this.patientRegister.getAllPatients()));

        //patientTableView.getItems().add(new Patient("John", "Doe","09129190999","none"));

    }


    @Override
    public void stop() throws Exception{
        super.stop();
    }

}
