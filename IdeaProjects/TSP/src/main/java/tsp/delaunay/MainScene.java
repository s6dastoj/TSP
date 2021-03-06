package tsp.delaunay;

import javafx.scene.Parent;
import javafx.scene.Scene;

public class MainScene extends Scene implements MainSceneInterface {

    MainSceneController mainSceneController;
    MainGroup mainGroup;

    public MainScene(Parent root, double width, double height, MainSceneController mainSceneController) {
        super(root, width, height);
        this.mainSceneController = mainSceneController;
    }


    MainScene(MainGroup root, double width, double height) {
        super(root, width, height);

        mainGroup = root;

        mainSceneController = new MainSceneController();

        mainSceneController.setScene(this);

        mainSceneController.setMainGroupController(mainGroup.getController());

    }

}
