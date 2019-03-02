package appregime.view;

import appregime.controller.AjouterIngredientController;
import appregime.controller.IngredientController;
import appregime.model.IngredientList;
import appregime.model.Modelngredient;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class AjouterIngredientView extends View {
    public AjouterIngredientView(AjouterIngredientController ajouterIngredientController, IngredientList listIngredients) {
        super(ajouterIngredientController, listIngredients);
        ListView ingredients = ajouterIngredientController.getIngredientsListView();
        ingredients.setItems(listIngredients.getIngredientList());
        //call a cell factory and display each observable item in the ListView
        adaptItems(ingredients);
    }

    /**
     * display each item of the ListView
     * Automatically called if the ModelListCustomers.listOfCustumers is changed
     * @param listView the element to be filled
     */
    private void adaptItems(ListView listView) {
        //Set a new cell factory to use in the ListView.
        listView.setCellFactory(
                //  first parameter specifies the type of the object passed in to the call method
                //  the second parameter specifying the return type of the method.
                new Callback<ListView, ListCell>() {
                    //define what to do when ModelListCustomers.listOfCustumers is changed
                    @Override
                    public ListCell call(ListView listView) {
                        return new ListCell() {
                            @Override
                            protected void updateItem(Object item, boolean empty) {
                                super.updateItem(item, empty);
                                if (item != null) {
                                    Parent listElement=null;
                                    IngredientController ingredientController = new IngredientController();
                                    ingredientController.setNomIngredient((Modelngredient) item);
                                    setGraphic(ingredientController.getFxml());
                                    adaptItems(listView);
                                }
                            }

                        };
                    }
                });
    }
}
