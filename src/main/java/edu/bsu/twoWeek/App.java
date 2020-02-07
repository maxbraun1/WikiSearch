package edu.bsu.twoWeek;

import com.google.gson.JsonArray;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.InputStream;

public class App extends Application {
    public static void main(String[] args) { launch(args);}

    @Override
    public void start(Stage primaryStage){

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20,20,20,20));
        grid.setVgap(10);
        grid.setHgap(10);

        Label logo = new Label("WikiSearch");
        logo.setFont(new Font("Arial Black", 30));
        logo.maxHeight(50);
        logo.minHeight(50);

        TextField termField = new TextField();
        termField.setMaxHeight(30);
        termField.setMinHeight(30);

        TextArea outputField = new TextArea();
        outputField.setMinHeight(300);
        outputField.setMaxHeight(300);
        outputField.setEditable(false);

        Label credits = new Label("By Max Braun & Curtis Clemmons");
        credits.setMaxHeight(30);
        credits.setMinHeight(30);

        Button revisionButton = new Button("Latest Revisions");
        revisionButton.setMinWidth(150);
        revisionButton.setMaxHeight(30);
        revisionButton.setMinHeight(30);

        Button editorsButton = new Button("Recent Top Editors");
        editorsButton.setMinWidth(150);
        editorsButton.setMaxHeight(30);
        editorsButton.setMinHeight(30);

        editorsButton.setOnAction(event -> {
            String term = termField.getText();
            try{

                WikiConnectionCreator creator = new WikiConnectionCreator();
                InputStream stream = creator.createConnection(term);

                DataParser parser = new DataParser();
                JsonArray revisions = parser.parseData(stream);

                if(revisions == null){

                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Alert");
                    alert.setHeaderText("Page Does Not Exist");
                    alert.setContentText("Wiki page for term '" + termField.getText() + "' does not exist.");
                    alert.setResizable(false);
                    alert.showAndWait();
                    outputField.setText("");
                }else{
                    EditorSorter editorSorter = new EditorSorter();
                    String result = editorSorter.sortEditors(revisions);

                    outputField.setText(result);
                }


            }catch(Exception e){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Alert");
                alert.setHeaderText("Internet not connected");
                alert.setContentText("Term: " + termField.getText());
                alert.setResizable(true);
                alert.showAndWait();
            }
        });

        revisionButton.setOnAction(event -> {
            try {
                String term = termField.getText();

                WikiConnectionCreator creator = new WikiConnectionCreator();
                InputStream stream = creator.createConnection(term);

                DataParser parser = new DataParser();
                JsonArray revisions = parser.parseData(stream);
                if(revisions == null){

                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Alert");
                    alert.setHeaderText("Page Does Not Exist");
                    alert.setContentText("Wiki page for term '" + termField.getText() + "' does not exist.");
                    alert.setResizable(false);
                    alert.showAndWait();
                    outputField.setText("");
                }else{

                    RevisionPrinter revisionPrinter = new RevisionPrinter();
                    String result = revisionPrinter.printRevisions(revisions);

                    outputField.setText(result);
                }

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Alert");
                alert.setHeaderText("Internet not connected");
                alert.setContentText("Term: " + termField.getText());
                alert.setResizable(true);
                alert.showAndWait();
            }
        });

        GridPane.setConstraints(logo,0,0,2,1);
        GridPane.setConstraints(credits,0,1,2,1);
        GridPane.setConstraints(revisionButton,0,2);
        GridPane.setConstraints(editorsButton,1,2);
        GridPane.setConstraints(termField,0,3,2,1);
        GridPane.setConstraints(outputField,0,4,2,3);
        grid.getChildren().addAll(logo,credits,revisionButton,editorsButton,termField,outputField);


        Scene scene = new Scene(grid, 350, 520);
        primaryStage.setScene(scene);
        primaryStage.setTitle("WikiSearch");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
