package Views;
import Controllers.*;
import Models.*;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.collections.*;

public class CategoryView extends BorderPane{
    private CategoryController catController;
    private TextField txfStatus = new TextField();
    private TextField txfCreateCategory = new TextField();
    private TextField txfDeleteCategory = new TextField();
    //private ListView cats = new ListView();

    public CategoryView(){
        catController = CategoryController.getInstance();

        showCurrentCategories();
        //setLeft(showCurrentCategories());
        setRight(manageCategories());

    }

    public void showCurrentCategories(){
        ListView cats = new ListView();

        cats.setItems(FXCollections.observableArrayList(catController.getAllCategories()));
        
        setLeft(cats);
    }

    public VBox manageCategories(){
        VBox manCats = new VBox();
        HBox hbCreateCategory = new HBox();
        Label lblCreateCategory = new Label("Create Category: ");
        hbCreateCategory.getChildren().addAll(lblCreateCategory, txfCreateCategory);

        HBox hbDeleteCategory = new HBox();
        Label lblDeleteCategory = new Label("Delete Category: ");
        hbDeleteCategory.getChildren().addAll(lblDeleteCategory, txfDeleteCategory);

        Button btnCreate = new Button("Create");
        btnCreate.setOnAction(e -> createCat(txfCreateCategory.getText()));

        Button btnDelete = new Button("Delete");
        btnDelete.setOnAction(e -> deleteCat(txfDeleteCategory.getText()));

        manCats.getChildren().addAll(hbCreateCategory, btnCreate, hbDeleteCategory, btnDelete, txfStatus);

        return manCats;
    }

    public void createCat(String catName){
        if(catController.createCategory(catName)){
            txfStatus.setText("Category " + catName + " created successfully.");
        }else{
            txfStatus.setText("Category " + catName + " already exists.");
        }
        
        txfCreateCategory.setText("");

        showCurrentCategories();
    }

    public void deleteCat(String catName){
        if(catController.deleteCategory(catName)){
            txfStatus.setText("Category " + catName + " deleted successfully.");
        }else{
            txfStatus.setText("Category " + catName + " doesn't exist.");
        }

        txfDeleteCategory.setText("");
        showCurrentCategories();
    }
}
