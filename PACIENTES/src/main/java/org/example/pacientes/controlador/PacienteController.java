package org.example.pacientes.controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.pacientes.dao.PacienteDAO;
import org.example.pacientes.dao.PacienteDAOImpl;
import org.example.pacientes.modelo.Paciente;

import java.util.List;

public class PacienteController {

    @FXML private TextField txtCedula;
    @FXML private TextField txtNombres;
    @FXML private TextField txtApellidos;
    @FXML private DatePicker dpFecha;
    @FXML private RadioButton rbM;
    @FXML private RadioButton rbF;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtEmail;
    @FXML private ComboBox<String> cbSangre;
    @FXML private TextArea txtAlergias;
    @FXML private CheckBox chkActivo;

    @FXML private TableView<Paciente> tabla;
    @FXML private TableColumn<Paciente, String> colCedula;
    @FXML private TableColumn<Paciente, String> colNombres;
    @FXML private TableColumn<Paciente, String> colApellidos;

    private final PacienteDAO dao = new PacienteDAOImpl();
    private final ToggleGroup grupoGenero = new ToggleGroup();

    @FXML
    public void initialize() {

        rbM.setToggleGroup(grupoGenero);
        rbF.setToggleGroup(grupoGenero);

        cbSangre.getItems().addAll("A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-");

        colCedula.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getCedula()));
        colNombres.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getNombres()));
        colApellidos.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getApellidos()));

        cargarDatos();
    }

    // ===================== GUARDAR =====================
    @FXML
    public void guardar() {

        if (txtCedula.getText().isEmpty() || txtNombres.getText().isEmpty()) {
            mostrar("Error", "Cédula y nombres obligatorios");
            return;
        }

        String genero = "";
        if (rbM.isSelected()) genero = "M";
        else if (rbF.isSelected()) genero = "F";

        Paciente p = new Paciente(
                txtCedula.getText(),
                txtNombres.getText(),
                txtApellidos.getText(),
                dpFecha.getValue(),
                genero,
                txtTelefono.getText(),
                txtEmail.getText(),
                cbSangre.getValue(),
                txtAlergias.getText(),
                chkActivo.isSelected()
        );

        dao.insertar(p);
        cargarDatos();
        limpiar();
        mostrar("Éxito", "Paciente guardado correctamente");
    }

    // ===================== ELIMINAR =====================
    @FXML
    public void eliminar() {

        Paciente p = tabla.getSelectionModel().getSelectedItem();

        if (p == null) {
            mostrar("Error", "Selecciona un paciente");
            return;
        }

        dao.eliminar(p.getCedula());
        cargarDatos();
        limpiar();
        mostrar("Éxito", "Paciente eliminado");
    }

    // ===================== SELECCION =====================
    @FXML
    public void cargarSeleccion() {

        Paciente p = tabla.getSelectionModel().getSelectedItem();

        if (p != null) {
            txtCedula.setText(p.getCedula());
            txtNombres.setText(p.getNombres());
            txtApellidos.setText(p.getApellidos());
            dpFecha.setValue(p.getFechaNac());
            txtTelefono.setText(p.getTelefono());
            txtEmail.setText(p.getEmail());
            cbSangre.setValue(p.getTipoSangre());
            txtAlergias.setText(p.getAlergias());
            chkActivo.setSelected(p.isActivo());

            if ("M".equals(p.getGenero())) rbM.setSelected(true);
            else rbF.setSelected(true);
        }
    }

    // ===================== LIMPIAR =====================
    @FXML
    public void limpiar() {

        txtCedula.clear();
        txtNombres.clear();
        txtApellidos.clear();
        dpFecha.setValue(null);
        txtTelefono.clear();
        txtEmail.clear();
        cbSangre.setValue(null);
        txtAlergias.clear();
        chkActivo.setSelected(false);
        rbM.setSelected(false);
        rbF.setSelected(false);
    }

    // ===================== BUSCAR =====================
    @FXML
    public void buscar() {

        String cedula = txtCedula.getText();
        String nombre = txtNombres.getText();

        List<Paciente> lista = dao.listar();

        ObservableList<Paciente> filtrados = FXCollections.observableArrayList();

        for (Paciente p : lista) {

            if (!cedula.isEmpty() && p.getCedula().contains(cedula)) {
                filtrados.add(p);
            }
            else if (!nombre.isEmpty() && p.getNombres().toLowerCase().contains(nombre.toLowerCase())) {
                filtrados.add(p);
            }
        }

        if (filtrados.isEmpty()) {
            mostrar("Sin resultados", "No se encontraron pacientes");
        }

        tabla.setItems(filtrados);
    }

    // ===================== CARGAR TABLA =====================
    private void cargarDatos() {
        List<Paciente> lista = dao.listar();
        tabla.setItems(FXCollections.observableArrayList(lista));
    }

    // ===================== ALERTAS =====================
    private void mostrar(String titulo, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(msg);
        alert.show();
    }
}