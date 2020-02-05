package edu.bsu.twoWeek;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.InputStream;
import java.util.List;

public class App extends Application {
    public static void main(String[] args) { launch(args);}

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Revision Finder");
        String term = "soup";
        TextField termField = new TextField();
        JTextArea outputField = new JTextArea();
        outputField.setEditable(false);
        Button parseRevisionButton = new Button("Find Revisions");
        Button numberOfEditsButton = new Button("Find Editors");
        numberOfEditsButton.setOnAction(event -> ){
            try{
                WikiConnectionCreator creator = new WikiConnectionCreator();
                EditorParser parser = new EditorParser();
                InputStream stream = creator.createConnection(term);
                List<Editor> List = parser.parseEditors(stream);
            }catch(Exception e){

            }
        };
        parseRevisionButton.setOnAction(event -> {
            try {
                WikiConnectionCreator creator = new WikiConnectionCreator();
                RevisionParser parser = new RevisionParser();
                InputStream stream = creator.createConnection(term);
                List<Revision> list = parser.parseRevisions(stream);

                outputField.setText(String.valueOf(list));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Alert");
                alert.setHeaderText("Cannot open resource");
                alert.setContentText("Term: " + termField.getText());
                alert.setResizable(true);
                alert.showAndWait();
            }
        });
        HBox hbox = new HBox(parseRevisionButton, numberOfEditsButton);
        VBox vbox = new VBox(termField, hbox);
        Scene scene = new Scene(vbox,500,500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
