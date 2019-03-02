package appregime.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SplitMenuButton;

public class PageAjouterUnObjectifController extends Controller {

    @FXML
    private Button boutonMonProfil;

    @FXML
    private SplitMenuButton boutonFiltres;

    @FXML
    private Button boutonNote;

    @FXML
    private Button boutonDate;

    @FXML
    private Button boutonRechercher;

    @FXML
    private Button boutonAjouterAMesObjectifs;

    @FXML
    private Button detailObjectif;

    public PageAjouterUnObjectifController() {
        super("/appregime/view/PageAjouterUnObjectif.fxml");
        boutonMonProfil.setOnAction(event -> retourProfil());
        boutonAjouterAMesObjectifs.setOnAction(event -> ajouterObjectif());
        detailObjectif.setOnAction(event -> voirDetailObjectif());
    }

    private void retourProfil(){
        ProfilController controller = new ProfilController();
        controller.showWithMenu();
    }

    //A modifier, il faut ajouter l'objectif a la liste
    private void ajouterObjectif(){
        ProfilController controller = new ProfilController();
        controller.showWithMenu();
    }

    private void voirDetailObjectif(){
        InformationSurUnObjectifControler controller = new InformationSurUnObjectifControler();
        controller.showWithMenu();
    }
}
