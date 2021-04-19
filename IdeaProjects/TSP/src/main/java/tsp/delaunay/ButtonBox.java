package tsp.delaunay;

import javafx.scene.control.*;

class ButtonBox extends MenuBar implements ButtonBoxInterface {


    private final ButtonBoxController controller;
    private final Menu algo;
    private final Menu view;
    private Label tourLengthLabel;
    Menu file;

    ButtonBox() {
        controller = new ButtonBoxController();
        controller.setView(this);

        file = new Menu("File");
        getMenus().add(file);

        algo = new Menu("Algorithm");
        getMenus().add(algo);

        view = new Menu("View");
        getMenus().add(view);


        createBrowseButton();
        createInitialisationMenuButton();
        createTwoOptButton();
        createTourTriangualtionSyncButton();
        createKOptButton();


        //createTriangulationButton();
        createTourCheckbox();
        createTriangulationCheckbox();
        createMSTCheckbox();

        createDelaunayTriangulationMenuButton();


        createTourLengthLabel();

        createResetButton();


        autosize();
        //setAlignment(Pos.BASELINE_RIGHT);
        //setSpacing(10);

        updateTourLengthLabel();

    }

    private void createBrowseButton() {

        MenuItem browseButton = new MenuItem("0. Choose file");
        browseButton.setOnAction(actionEvent -> controller.pushBrowseButton());

        file.getItems().add(browseButton);
    }

    private void createInitialisationMenuButton() {
        Menu menuButton = new Menu("1. Choose Intial Tour");

        menuButton.getItems().add(createRandomTourInitializationButton());
        menuButton.getItems().add(createMstTourInitializationButton());
        menuButton.getItems().add(createChristophidesTourInitializationButton());

        algo.getItems().add(menuButton);
    }

    private void createTwoOptButton() {
        MenuItem twoOptButton = new MenuItem("2. Two Optimisation Tour/ Eliminate Crossing");
        twoOptButton.setOnAction(actionEvent -> controller.pushTwoOpt());

        algo.getItems().add(twoOptButton);
    }

    private void createTourTriangualtionSyncButton() {
        MenuItem tourTriangulationSyncButton = new MenuItem("3. Sync Tour and Triangulation");
        tourTriangulationSyncButton.setOnAction(actionEvent -> controller.pushSyncTourAndTriangualtion());

        algo.getItems().add(tourTriangulationSyncButton);
    }

    private void createKOptButton() {
        MenuItem kOptButton = new MenuItem("4. K Optimisation Tour and Triangulation");
        kOptButton.setOnAction(actionEvent -> controller.pushKOpt());

        algo.getItems().add(kOptButton);
    }

    private void createTourCheckbox() {
        CheckMenuItem tourCheckbox = new CheckMenuItem("Show Tour");
        tourCheckbox.setOnAction(actionEvent -> controller.pushTourCheckBox());

        view.getItems().add(tourCheckbox);
    }

    private void createMSTCheckbox() {

        CheckMenuItem mstCheckbox = new CheckMenuItem("Show MST");
        mstCheckbox.setOnAction(actionEvent -> controller.pushMSTButton());

        view.getItems().add(mstCheckbox);

    }

    //TODO eindeutigere Funktionsbezeichner und Labels
    private void createTriangulationCheckbox() {
        CheckMenuItem triangulationCheckbox = new CheckMenuItem("Show Triangulation");
        triangulationCheckbox.setOnAction(actionEvent -> controller.pushTriangulationCheckbox());

        view.getItems().add(triangulationCheckbox);
    }

    void createDelaunayTriangulationMenuButton() {
        Menu delaunayTriangulationMenuButton = new Menu("Delaunay Edges Higher Order");
        //delaunayTriangulationMenuButton.setSelected(false);
        //delaunayTriangulationMenuButton.setOnAction(actionEvent -> controller.pushTriang0());

        MenuItem hideMenuItem = new MenuItem("Hide");
        hideMenuItem.setOnAction(e -> {
            controller.mainController.hideDelaunayEdgesWithSpecificOrder();
        });

        delaunayTriangulationMenuButton.getItems().add(hideMenuItem);


        for (int i = 0; i <= 4; i++) {
            int k = i;
            MenuItem menuItem = new MenuItem("Delaunay Order " + i);
            menuItem.setOnAction(e -> {
                controller.mainController.showDelaunayEdgesWithSpecificOrder(k);
            });

            delaunayTriangulationMenuButton.getItems().add(menuItem);

        }

        view.getItems().add(delaunayTriangulationMenuButton);
    }

    private void createTourLengthLabel() {
        tourLengthLabel = new Label();

        getChildren().add(tourLengthLabel);

    }

    private void createResetButton() {
        Menu resetButton = new Menu("0. Reset");
        resetButton.setOnAction(actionEvent -> controller.mainController.resetInstance());

        algo.getItems().add(resetButton);
    }

    private MenuItem createRandomTourInitializationButton() {
        MenuItem randomTourInitializationButton = new MenuItem("Random Tour");
        randomTourInitializationButton.setOnAction(actionEvent -> controller.mainController.setRandomTour());

        return randomTourInitializationButton;
    }

    private MenuItem createMstTourInitializationButton() {
        MenuItem mstTourInitializationButton = new MenuItem("Mst Tour");
        mstTourInitializationButton.setOnAction(actionEvent -> controller.mainController.setMstTour());

        //getChildren().add(mstTourInitializationButton);
        return mstTourInitializationButton;
    }

    private MenuItem createChristophidesTourInitializationButton() {
        MenuItem christophidesTourInitializationButton = new MenuItem("Christophides Tour");
        christophidesTourInitializationButton.setOnAction(actionEvent -> controller.mainController.setChristophidesTour());

        //getChildren().add(christophidesTourInitializationButton);
        return christophidesTourInitializationButton;
    }

    //TODO eindeutigere Funktionsbezeichner und Labels

    @Override
    public void updateTourLengthLabel() {
        try {

            tourLengthLabel.setText(String.valueOf(controller.mainController.getInstance().getTourLength()));
        } catch (NullPointerException e) {
            tourLengthLabel.setText("Noch keine Tour berechnet");
        }
    }

    ButtonBoxControllerInterface getController() {
        return controller;
    }
}
