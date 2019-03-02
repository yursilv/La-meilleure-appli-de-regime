package appregime.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class AccueilController extends Controller {

    @FXML
    private Label nom;

    @FXML
    private Label poidsActuel;

    @FXML
    private Button actualiser;

    @FXML
    private Label jour;

    @FXML
    private Label DateJour;

    @FXML
    private DatePicker calendrier;

    @FXML
    private Label PoidsDepart;

    @FXML
    private Label PoidsVisé;



    public AccueilController() {
        super("/appregime/view/accueil.fxml");
        actualiser.setOnAction(event -> creerRegime());
    }

    private void creerRegime() {
        PoidsMajController poidsMajController = new PoidsMajController();
        poidsMajController.showInMyStage("Modifie le poids actuel");
    }
}