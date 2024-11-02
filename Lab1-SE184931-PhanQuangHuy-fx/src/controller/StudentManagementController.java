package controller;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import fall.hsf301.slot02.pojo.Student;
import fall.hsf301.slot02.service.IStudentService;
import fall.hsf301.slot02.service.StudentService;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;


public class StudentManagementController implements Initializable {
	private IStudentService studentService;
	@FXML
	private TextField txtStudentID;
	@FXML
	private TextField txtFirstName;
	@FXML
	private TextField txtLastName;
	@FXML
	private TextField txtFindByName;
	@FXML
	private TextField txtMark;
	@FXML
	private Button btnAdd;
	@FXML
	private Button btnUpdate;
	@FXML
	private Button btnDelete;
	
	private String role;
	
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
		switch (this.role) {
		case "ADMIN":
			this.btnAdd.setDisable(false);
			this.btnUpdate.setDisable(false);
			this.btnDelete.setDisable(false);
			break;
		case "STUDENT":
			this.btnAdd.setDisable(true);
			this.btnUpdate.setDisable(true);
			this.btnDelete.setDisable(true);
			break;
		}
	}
	@FXML
	private TableView<Student> tblStudents;
	@FXML
	private TableColumn<Student, Long> studentId;
	@FXML
	private TableColumn<Student, String> firstName;
	@FXML
	private TableColumn<Student, String> lastName;
	@FXML
	private TableColumn<Student, Integer> mark;
	
	private ObservableList<Student>tableModel;
	
	public StudentManagementController()
	{
		studentService = new StudentService("JPAs");
		tableModel = FXCollections.observableArrayList(studentService.getStudents());
		
	}
	@FXML public void CancelOnaction() throws IOException {
		Platform.exit();
	}
	@FXML
	public void AddOnaction() throws IOException {
	    try {
	        Student st = new Student(txtFirstName.getText(), txtLastName.getText(), Integer.parseInt(txtMark.getText()));
	        studentService.save(st);
	        refreshTable(); 
	    } catch (NumberFormatException e) {
	        showAlert("Input Error", "Please enter a valid number for the mark.");
	    }
	}

	@FXML
	public void updateOnaction() throws IOException {
	    try {
	        int studentId = Integer.parseInt(txtStudentID.getText());
	        Student st = studentService.findByID(studentId);
	        st.setFirstName(txtFirstName.getText());
	        st.setLastName(txtLastName.getText());
	        st.setMarks(Integer.parseInt(txtMark.getText()));
	        studentService.update(st);
	        refreshTable();
	    } catch (NumberFormatException e) {
	        showAlert("Input Error", "Please enter a valid number for the mark.");
	    }
	}
	@FXML
	public void deleteOnaction() throws IOException {
	    try {
	        int studentId = Integer.parseInt(txtStudentID.getText());
	    	studentService.delete(studentId);
	        refreshTable(); 
	    } catch (NumberFormatException e) {
	        showAlert("Input Error", "Please enter a valid Student ID.");
	    }
	}
	private void refreshTable() {
	    tableModel.clear();
	    tableModel.addAll(studentService.getStudents());
	    tblStudents.setItems(tableModel);
	}
	@FXML
	public void SearchOnaction() throws IOException {
	    String searchKeyword = txtFindByName.getText();
	    tableModel.clear();

	    if (!searchKeyword.isEmpty()) {
	        List<Student> searchResults = studentService.findByName(searchKeyword);

	        if (!searchResults.isEmpty()) {
	            tableModel.addAll(searchResults); 
	        } else {
	            showAlert("Không tìm thấy", "Không có sinh viên nào phù hợp với từ khóa tìm kiếm.");
	            tableModel.addAll(studentService.getStudents());
	        }
	    } else {
	        tableModel.addAll(studentService.getStudents());
	    }

	    tblStudents.setItems(tableModel);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		studentId.setCellValueFactory(new PropertyValueFactory<>("Id"));
		firstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
		lastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
		mark.setCellValueFactory(new PropertyValueFactory<>("Mark"));
		tblStudents.setItems(tableModel);
		
		tblStudents.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observableValue, Object oldValue, Object index) {
				if (tblStudents.getSelectionModel().getSelectedItem() != null) {
					TableViewSelectionModel selectionModel = tblStudents.getSelectionModel();
					ObservableList selectedCells = selectionModel.getSelectedCells();
					TablePosition tablePosition = (TablePosition) selectedCells.get(0);
					Object studentID = tablePosition.getTableColumn().getCellData(index);
					try {
						Student student = studentService.findByID(Integer.valueOf(studentID.toString())); 
						showStudent(student);
					} catch (Exception ex) { 
						showAlert("Infomation Board!", "Please choose the First Cell !");
					}
				}
				
			}
	});
	}
	protected void showAlert(String title, String message) {
	    Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    alert.setTitle(title);
	    alert.setHeaderText(null);
	    alert.setContentText(message);
	    alert.showAndWait();
	}
	protected void showStudent(Student student) {
		this.txtStudentID.setText(String.valueOf(student.getId()));
		this.txtFirstName.setText(student.getFirstName());
		this.txtLastName.setText(student.getLastName());
		this.txtMark.setText(String.valueOf(student.getMarks()));
		
	}
	
	
}
