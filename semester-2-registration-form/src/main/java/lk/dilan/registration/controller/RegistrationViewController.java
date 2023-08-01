package lk.dilan.registration.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class RegistrationViewController {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnForward;

    @FXML
    private Button btnNewStudent;

    @FXML
    private Button btnRemove;

    @FXML
    private Button btnSave;

    @FXML
    private ToggleGroup gender;

    @FXML
    private Label lblGender;

    @FXML
    private ListView<?> lstContacts;

    @FXML
    private ListView<?> lstModules;

    @FXML
    private ListView<?> lstSelectedModules;

    @FXML
    private ListView<?> lstStudents;

    @FXML
    private RadioButton rdoFemale;

    @FXML
    private RadioButton rdoMale;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnBackOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnForwardOnAction(ActionEvent event) {

    }

    @FXML
    void btnNewStudentOnAction(ActionEvent event) {

        lstStudents.getSelectionModel().clearSelection();
        lblGender.setTextFill(Color.BLACK);

        txtId.setDisable(false);
        txtName.setDisable(false);
        txtContact.setDisable(false);
        rdoMale.setDisable(false);
        rdoFemale.setDisable(false);
        lstContacts.setDisable(false);
        lstModules.setDisable(false);
        lstSelectedModules.setDisable(false);
        btnSave.setDisable(false);

        txtName.clear();
        txtContact.clear();
        lstContacts.getItems().clear();
        lstSelectedModules.getItems().clear();
        rdoMale.getToggleGroup().selectToggle(null);
        rdoFemale.getToggleGroup().selectToggle(null);

        ObservableList<String> moduleList = (ObservableList<String>) lstModules.getItems();
        moduleList.clear();
        moduleList.addAll("In16-S2-CS2812 - Visual Programming","In16-S2-DE2281 - Nutrition and Health","In16-S2-EL1022 - Language Skill Enhancement II","In16-S2-EN1802 - Basic Electronics",
                "In16-S2-MA1023 - Method of Mathematics","In16-S2-ME1090 - Engineering Drawing & Computer","In16-S2-ME1100 - Mechanics of Materials I","In16-S2-MT1962 - Engineering Skill Development","In16-S2-MT1952 - Engineering Design");

        lstModules.getSelectionModel().clearSelection();
        txtName.requestFocus();


    }

    @FXML
    void btnRemoveOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void lstContactsOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void lstModulesOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void lstSelectedModulesOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void lstStudentsOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtContactOnAction(ActionEvent event) {

    }

}
