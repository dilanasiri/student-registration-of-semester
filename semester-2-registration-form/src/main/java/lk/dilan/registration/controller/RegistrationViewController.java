package lk.dilan.registration.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import lk.dilan.registration.model.StudentInfo;
import lk.dilan.registration.util.Gender;

import java.util.ArrayList;

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
    private ListView<String> lstContacts;

    @FXML
    private ListView<String> lstModules;

    @FXML
    private ListView<String> lstSelectedModules;

    @FXML
    private ListView<StudentInfo> lstStudents;

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
    public void initialize() {
        lstModules.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lstSelectedModules.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        txtName.textProperty().addListener((value, previousText, currentText) -> {
            txtName.getStyleClass().remove("invalid");

            for (char c : currentText.toCharArray()) {
                if (!(Character.isLetter(c) || Character.isSpaceChar(c))) {
                    txtName.getStyleClass().add("invalid");
                    return;
                }
            }
        });

        txtContact.textProperty().addListener((value, previousContact, currentContact) -> {
            txtContact.getStyleClass().remove("invalid");
            if (currentContact.isEmpty()) {
                btnAdd.setDisable(true);
                return;
            }

            btnAdd.setDisable(false);
            currentContact = currentContact.strip();

            if (!(currentContact.length() == 11 &&
                    isNumber(currentContact.substring(0, 3)) &&
                    currentContact.charAt(3) == '-' &&
                    isNumber(currentContact.substring(4)))) {
                txtContact.getStyleClass().add("invalid");
                btnAdd.setDisable(true);
            }
        });

        lstContacts.getSelectionModel().selectedItemProperty().addListener((value, previous, current) -> {
            btnRemove.setDisable(current == null);
        });

        lstModules.getSelectionModel().selectedItemProperty().addListener((value, previous, current) -> {
            btnForward.setDisable(current == null);
        });

        lstSelectedModules.getSelectionModel().selectedItemProperty().addListener((value, previous, current) -> {
            btnBack.setDisable(current == null);
        });

        lstStudents.getSelectionModel().selectedItemProperty().addListener((value, previous, current) -> {
            btnDelete.setDisable(current == null);
            if (current == null) return;

            txtId.setText(current.id);
            txtName.setText(current.name);
            if (current.gender == Gender.MALE){
                rdoMale.getToggleGroup().selectToggle(rdoMale);
            }else{
                rdoFemale.getToggleGroup().selectToggle(rdoFemale);
            }
            txtContact.clear();
            lstContacts.getItems().clear();
            lstContacts.getItems().addAll(current.contacts);
            lstSelectedModules.getItems().clear();
            lstSelectedModules.getItems().addAll(current.modules);

            lstModules.getItems().clear();
            lstModules.getItems().addAll("In16-S2-CS2812 - Visual Programming","In16-S2-DE2281 - Nutrition and Health","In16-S2-EL1022 - Language Skill Enhancement II","In16-S2-EN1802 - Basic Electronics",
                    "In16-S2-MA1023 - Method of Mathematics","In16-S2-ME1090 - Engineering Drawing & Computer","In16-S2-ME1100 - Mechanics of Materials I","In16-S2-MT1962 - Engineering Skill Development","In16-S2-MT1952 - Engineering Design");
            lstModules.getItems().removeAll(current.modules);

            lstContacts.getSelectionModel().clearSelection();
            lstModules.getSelectionModel().clearSelection();
            lstSelectedModules.getSelectionModel().clearSelection();
        });
    }
    private boolean isNumber(String input) {
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        for (String contact : lstContacts.getItems()) {
            if (contact.equals(txtContact.getText().strip())) {
                txtContact.getStyleClass().add("invalid");
                return;
            }
        }

        lstContacts.getItems().add(txtContact.getText().strip());
        txtContact.clear();
        txtContact.requestFocus();
        lstContacts.getSelectionModel().clearSelection();
        lstContacts.getStyleClass().remove("invalid");

    }

    @FXML
    void btnBackOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnForwardOnAction(ActionEvent event) {
        ObservableList<String> modules = lstModules.getItems();
        ObservableList<String> selectedModules = lstSelectedModules.getItems();

        selectedModules.addAll(lstModules.getSelectionModel().getSelectedItems());
        modules.removeAll(lstModules.getSelectionModel().getSelectedItems());

        lstModules.getSelectionModel().clearSelection();
        lstSelectedModules.getStyleClass().remove("invalid");

    }

    @FXML
    void btnNewStudentOnAction(ActionEvent event) {
        lstStudents.getSelectionModel().clearSelection();

        txtId.setText(generateNewStudentId());
        lblGender.setTextFill(Color.BLACK);

        txtName.getStyleClass().remove("invalid");
        txtContact.getStyleClass().remove("invalid");
        lstContacts.getStyleClass().remove("invalid");
        lstSelectedModules.getStyleClass().remove("invalid");

        txtId.setDisable(false);
        txtName.setDisable(false);
        txtContact.setDisable(false);
        rdoMale.setDisable(false);
        rdoFemale.setDisable(false);
        lstContacts.setDisable(false);
        lstModules.setDisable(false);
        lstSelectedModules.setDisable(false);
//        btnForward.setDisable(false);
        btnSave.setDisable(false);

        txtName.clear();
        txtContact.clear();
        lstContacts.getItems().clear();
        lstSelectedModules.getItems().clear();

        ObservableList<String> moduleList = lstModules.getItems();
        moduleList.clear();
        moduleList.addAll("In16-S2-CS2812 - Visual Programming","In16-S2-DE2281 - Nutrition and Health","In16-S2-EL1022 - Language Skill Enhancement II","In16-S2-EN1802 - Basic Electronics",
                "In16-S2-MA1023 - Method of Mathematics","In16-S2-ME1090 - Engineering Drawing & Computer","In16-S2-ME1100 - Mechanics of Materials I","In16-S2-MT1962 - Engineering Skill Development","In16-S2-MT1952 - Engineering Design");

        lstModules.getSelectionModel().clearSelection();

        rdoMale.getToggleGroup().selectToggle(null);

        txtName.requestFocus();

    }

    private String generateNewStudentId() {
        ObservableList<StudentInfo> studentList = lstStudents.getItems();
        if (studentList.isEmpty()) return "S160000";

        String lastStudentId = studentList.get(studentList.size() - 1).id;  // S160005 - > 0005 -> 5 + 1 -> 6
        var newId = Integer.parseInt(lastStudentId.substring(1)) + 1;

        return String.format("S16%04d", newId);
    }

    @FXML
    void btnRemoveOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        boolean isDataValid = true;
        lblGender.setTextFill(Color.BLACK);
        lstContacts.getStyleClass().remove("invalid");
        lstSelectedModules.getStyleClass().remove("invalid");

        String name = txtName.getText();

        if (lstSelectedModules.getItems().size() < 2){
            isDataValid = false;
            lstSelectedModules.getStyleClass().add("invalid");
            lstModules.requestFocus();
        }

        if (lstContacts.getItems().isEmpty()){
            isDataValid = false;
            lstContacts.getStyleClass().add("invalid");
            txtContact.selectAll();
            txtContact.requestFocus();
        }

        if (rdoMale.getToggleGroup().getSelectedToggle() == null) {
            isDataValid = false;
            rdoMale.requestFocus();
            lblGender.setTextFill(Color.RED);
        }

        if (name.isBlank() || txtName.getStyleClass().contains("invalid")) {
            isDataValid = false;
            if (!txtName.getStyleClass().contains("invalid")) txtName.getStyleClass().add("invalid");
            txtName.selectAll();
            txtName.requestFocus();
        }

        if (!isDataValid) return;

        ObservableList<StudentInfo> studentList = lstStudents.getItems();

        /* Business Validation */
        StudentInfo selectedStudent = lstStudents.getSelectionModel().getSelectedItem();

        for (String enteredContact : lstContacts.getItems()) {
            for (StudentInfo student : studentList) {
                if (student == selectedStudent) continue;
                if (student.contacts.contains(enteredContact)){
                    new Alert(Alert.AlertType.ERROR,
                            String.format("Contact number: %s already exists", enteredContact)).showAndWait();
                    lstContacts.getStyleClass().add("invalid");
                    lstContacts.requestFocus();
                    return;
                }
            }
        }

        if (selectedStudent == null) {  // Add
            StudentInfo student = new StudentInfo(txtId.getText(),
                    txtName.getText().strip(),
                    rdoMale.isSelected() ? Gender.MALE : Gender.FEMALE,
                    new ArrayList<>(lstContacts.getItems()),
                    new ArrayList<>(lstSelectedModules.getItems()));
            studentList.add(student);
        }
        btnNewStudent.fire();


        }

    @FXML
    void lstContactsOnKeyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.DELETE) btnRemove.fire();
    }

    @FXML
    void lstModulesOnKeyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) btnForward.fire();
    }

    @FXML
    void lstSelectedModulesOnKeyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.DELETE) btnBack.fire();

    }

    @FXML
    void lstStudentsOnKeyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.DELETE) btnDelete.fire();

    }

    @FXML
    void txtContactOnAction(ActionEvent event) {
        btnAdd.fire();
    }

}
