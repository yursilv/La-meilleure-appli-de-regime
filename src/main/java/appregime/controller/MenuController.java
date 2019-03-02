package appregime.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class MenuController extends Controller {
    @FXML
    private Label accueil;
    @FXML
    private Label monProfil;
    @FXML
    private Label mesRegimes;
    @FXML
    private Label monEvolution;
    @FXML
    private Label deconnexion;

    public MenuController() {
        super("/appregime/view/menu_layout.fxml");
        fxml.getStylesheets().add("/appregime/css/menu_layout.css");

        mesRegimes.setOnMouseClicked(event -> setBelowMenuElement(new MesRegimesController().getFxml()));
        accueil.setOnMouseClicked(event -> setBelowMenuElement(new AccueilController().getFxml()));
        monProfil.setOnMouseClicked(event -> setBelowMenuElement(new ProfilController().getFxml()));
        monEvolution.setOnMouseClicked(event -> setBelowMenuElement(new EvolutionController().getFxml()));
        deconnexion.setOnMouseClicked(event -> connexion());
    }

    /**
     * affiche la partie de l'écran (représentée ici par la variable node) qui se trouve sous la barre de menu
     */
    public void setBelowMenuElement(Node node) {
        fxml.getChildren().set(1, node); // insère à l'indice 1 de la VBox principale (élément 0 étant la barre de menu)
    }

    public void connexion() {
        ConnexionController connexionController = new ConnexionController();
        connexionController.showInPrimaryStage();
    }
}
