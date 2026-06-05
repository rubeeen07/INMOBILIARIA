package ies.dam.inmobiliaria.controller;

import ies.dam.inmobiliaria.dao.ViviendaDAO;
import ies.dam.inmobiliaria.model.Vivienda;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class InmobiliariaController {

    @FXML
    private ComboBox<String> cmbTipoOperacion;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnLimpiar;

    @FXML
    private Label lblResultado;

    @FXML
    private TableView<Vivienda> tablaViviendas;

    @FXML
    private TableColumn<Vivienda, Integer> colId;

    @FXML
    private TableColumn<Vivienda, String> colTipo;

    @FXML
    private TableColumn<Vivienda, String> colDireccion;

    @FXML
    private TableColumn<Vivienda, String> colCiudad;

    @FXML
    private TableColumn<Vivienda, Integer> colHabitaciones;

    @FXML
    private TableColumn<Vivienda, Double> colMetros;

    @FXML
    private TableColumn<Vivienda, String> colPrecio;

    @FXML
    private TableColumn<Vivienda, String> colDisponibilidad;

    private final ViviendaDAO viviendaDAO = new ViviendaDAO();

    @FXML
    private void initialize() {
        configurarComboBox();
        configurarTabla();
        configurarBindingBotones();
        cargarTodasLasViviendas();
    }

    private void configurarComboBox() {
        cmbTipoOperacion.setItems(FXCollections.observableArrayList("Compra", "Alquiler"));
        cmbTipoOperacion.setPromptText("Selecciona compra o alquiler");
    }

    private void configurarTabla() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoOperacion"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        colHabitaciones.setCellValueFactory(new PropertyValueFactory<>("habitaciones"));
        colMetros.setCellValueFactory(new PropertyValueFactory<>("metros"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precioTexto"));
        colDisponibilidad.setCellValueFactory(new PropertyValueFactory<>("disponibilidadTexto"));
    }

    private void configurarBindingBotones() {
        btnBuscar.disableProperty().bind(cmbTipoOperacion.valueProperty().isNull());
    }

    private void cargarTodasLasViviendas() {
        List<Vivienda> viviendas = viviendaDAO.listarTodas();
        tablaViviendas.setItems(FXCollections.observableArrayList(viviendas));

        lblResultado.setText("Selecciona compra o alquiler para filtrar las viviendas disponibles.");
        lblResultado.getStyleClass().removeAll("mensaje-ok", "mensaje-error", "mensaje-info");
        lblResultado.getStyleClass().add("mensaje-info");
    }

    @FXML
    private void buscarViviendas() {
        String tipoSeleccionado = cmbTipoOperacion.getValue();

        if (tipoSeleccionado == null || tipoSeleccionado.isBlank()) {
            mostrarMensaje("Debes seleccionar si quieres comprar o alquilar.", false);
            return;
        }

        String tipoOperacionBD = convertirTipoOperacion(tipoSeleccionado);

        List<Vivienda> viviendasDisponibles = viviendaDAO.buscarPorTipoOperacion(tipoOperacionBD);
        tablaViviendas.setItems(FXCollections.observableArrayList(viviendasDisponibles));

        if (viviendasDisponibles.isEmpty()) {
            mostrarMensaje("No hay viviendas disponibles para " + tipoSeleccionado.toLowerCase() + ".", false);
        } else {
            mostrarMensaje("Se han encontrado " + viviendasDisponibles.size()
                    + " viviendas disponibles para " + tipoSeleccionado.toLowerCase() + ".", true);
        }
    }

    @FXML
    private void limpiarBusqueda() {
        cmbTipoOperacion.getSelectionModel().clearSelection();
        cargarTodasLasViviendas();
    }

    private String convertirTipoOperacion(String tipoSeleccionado) {
        if ("Compra".equalsIgnoreCase(tipoSeleccionado)) {
            return "COMPRA";
        }
        return "ALQUILER";
    }

    private void mostrarMensaje(String mensaje, boolean correcto) {
        lblResultado.setText(mensaje);
        lblResultado.getStyleClass().removeAll("mensaje-ok", "mensaje-error", "mensaje-info");

        if (correcto) {
            lblResultado.getStyleClass().add("mensaje-ok");
        } else {
            lblResultado.getStyleClass().add("mensaje-error");
        }
    }
}
