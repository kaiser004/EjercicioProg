
package vistacontrolador;

import javafx.scene.input.KeyEvent;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.CorrectorProyectos;

public class GuiCorrectorProyectos extends Application
{

	private MenuItem itemLeer;
	private MenuItem itemGuardar;
	private MenuItem itemSalir;

	private TextField txtAlumno;
	private Button btnVerProyecto;

	private RadioButton rbtAprobados;
	private RadioButton rbtOrdenados;
	private Button btnMostrar;

	private TextArea areaTexto;

	private Button btnClear;
	private Button btnSalir;

	private CorrectorProyectos corrector; // el modelo

	@Override
	public void start(Stage stage) {

		corrector = new CorrectorProyectos();

		BorderPane root = crearGui();

		Scene scene = new Scene(root, 800, 600);
		stage.setScene(scene);
		stage.setTitle("- Corrector de proyectos -");
		scene.getStylesheets().add(getClass()
		                .getResource("/css/application.css").toExternalForm());
		stage.show();
	}

	private BorderPane crearGui() {

		BorderPane panel = new BorderPane();
		MenuBar barraMenu = crearBarraMenu();
		panel.setTop(barraMenu);

		VBox panelPrincipal = crearPanelPrincipal();
		panel.setCenter(panelPrincipal);

		HBox panelBotones = crearPanelBotones();
		panel.setBottom(panelBotones);

		return panel;
	}

	private MenuBar crearBarraMenu() {

		MenuBar barraMenu = new MenuBar();

		Menu menu = new Menu("Archivo");

		itemLeer = new MenuItem("_Leer de fichero");
		itemLeer.setAccelerator(KeyCombination.keyCombination("CTRL+L"));
		itemLeer.setOnAction(e -> leerDeFichero());
		// a completar
		
		itemGuardar = new MenuItem("_Guardar de fichero");
		itemGuardar.setAccelerator(KeyCombination.keyCombination("CTRL + G"));
		itemGuardar.setDisable(true);
		itemGuardar.setOnAction(e -> salvarEnFichero());
		
		
		itemSalir = new MenuItem("_Salir");
		itemSalir.setAccelerator(KeyCombination.keyCombination("CTRL + S"));
		itemSalir.setOnAction(e -> salir());
		
		menu.getItems().addAll(itemLeer , itemGuardar , itemSalir);
		barraMenu.getMenus().add(menu);

		return barraMenu;
	}

	private VBox crearPanelPrincipal() {

		VBox panel = new VBox();
		panel.setPadding(new Insets(5));
		panel.setSpacing(10);

		Label lblEntrada = new Label("Panel de entrada");
		// a completar
		lblEntrada.setMaxSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
		lblEntrada.getStyleClass().add("titulo-panel");
		panel.getChildren().addAll(lblEntrada , crearPanelEntrada());
		
		Label lblOpciones = new Label("Panel de opciones");
		lblOpciones.setMaxSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
		lblOpciones.getStyleClass().add("titulo-panel");
		panel.getChildren().addAll( lblOpciones , crearPanelOpciones());
		
		
		areaTexto = new TextArea();
		areaTexto.getStyleClass().add("text-area");
		areaTexto.setPrefSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
		panel.getChildren().add(areaTexto);
		return panel;
	}

	private HBox crearPanelEntrada() {

		HBox panel = new HBox();
		panel.setPadding(new Insets(5));
		// a completar
		Label lblAlum = new Label("Alumno");
		txtAlumno = new TextField();
		txtAlumno.setPrefColumnCount(30);
		btnVerProyecto = new Button("Ver proyecto");
		btnVerProyecto.prefWidth(120);
		panel.setSpacing(10);
		btnVerProyecto.setOnAction(e -> verProyecto());
		txtAlumno.setOnKeyPressed(new EventHandler<KeyEvent>() {
			 public void handle(KeyEvent keyEvent) {
			 verProyecto();
			 }}); 

		panel.getChildren().addAll(lblAlum , txtAlumno , btnVerProyecto);
		return panel;
	}

	private HBox crearPanelOpciones() {

		HBox panel = new HBox();
		panel.setPadding(new Insets(5));
		// a completar
		panel.setSpacing(50);
		panel.setAlignment(Pos.CENTER);
		ToggleGroup tg = new ToggleGroup();
		rbtAprobados = new RadioButton("Mostrar Aprobados");
		rbtAprobados.setSelected(true);
		rbtAprobados.setToggleGroup(tg);
		rbtOrdenados = new RadioButton("Mostrar ordenados");
		rbtOrdenados.setToggleGroup(tg);
		btnMostrar = new Button("Mostrar");
		btnMostrar.setOnAction(e -> mostrar());
		
		panel.getChildren().addAll(rbtAprobados , rbtOrdenados , btnMostrar);
		return panel;
	}

	private HBox crearPanelBotones() {

		HBox panel = new HBox();
		panel.setPadding(new Insets(5));
		panel.setSpacing(5);
		// a completar
		btnClear = new Button("Clear");
		btnClear.setPrefWidth(90);
		btnSalir = new Button("Salir");
		btnSalir.setPrefWidth(90);
		panel.setAlignment(Pos.BOTTOM_RIGHT);
		panel.getChildren().addAll(btnClear , btnSalir);
		btnClear.setOnAction(e -> clear());
		btnSalir.setOnAction(e -> salir());

		return panel;
	}

	private void salvarEnFichero() {
		// a completar
		try {
			corrector.guardarOrdenadosPorNota();
		} catch (IOException e) {
			e.getMessage();
		}
	}

	private void leerDeFichero() {
		// a completar
		corrector.leerDatosProyectos();
		itemLeer.setDisable(true);
		itemGuardar.setDisable(false);
	}

	private void verProyecto() {
		// a completar
		if (txtAlumno.getText().isEmpty()) {
			areaTexto.setText("La caja de texto esta vacia");
		}
		if (!itemLeer.isDisable() == true) {
			areaTexto.setText("Aun no se ha leido de fichero");
		}
		String alumno = txtAlumno.getText();
		areaTexto.setText(String.valueOf(corrector.proyectoDe(alumno)));
		cogerFoco();
	}

	private void mostrar() {
		clear();
		// a completar
		if (!itemLeer.isDisable() == true) {
			areaTexto.setText("Aun no se ha leido de fichero");
		}
		if (rbtAprobados.isSelected()) {
			Integer apro = corrector.aprobados();
			areaTexto.appendText("Aprobados: " + apro);
		}
		if (rbtOrdenados.isSelected()) {
			areaTexto.appendText("Ordenados: " + corrector.ordenadosPorNota());
		}
		
	}

	private void cogerFoco() {

		txtAlumno.requestFocus();
		txtAlumno.selectAll();

	}

	private void salir() {

		System.exit(0);
	}

	private void clear() {

		areaTexto.clear();
		cogerFoco();
	}

	public static void main(String[] args) {

		launch(args);
	}
}
