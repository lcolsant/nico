/**
 * Basic plain-text editor with some custom CSS design.
 * @author: Lee Colsant
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DevProj1 extends Application implements EventHandler<ActionEvent>
{


    Button btnExport;
    Button btnClear;
    Button btnExit;
    TextArea txtArea;

    public static void main(String[] args)
    {
        launch(args);
    }
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("nico");


        //primaryStage.setOpacity(0.5);
      //  primaryStage.initStyle(StageStyle.TRANSPARENT);

        Button buttonLoad = new Button("Open");
        buttonLoad.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);
                File file = fileChooser.showOpenDialog(primaryStage);
                if(file != null){
                    txtArea.setText(readFile(file));
                }

            }
        });



        Button buttonSave = new Button("Save");
        buttonSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);
                File file2 = fileChooser.showSaveDialog(primaryStage);
                if(file2 != null){
                    SaveFile(txtArea.getText(),file2);
                }

            }
        });


        BorderPane layout = new BorderPane();
        layout.getStyleClass().add("pane");
       // layout.setOpacity(0.9);
        HBox hBox = new HBox();
        hBox.getStyleClass().add("hbox");
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);



        btnClear = new Button("Clear");
        btnClear.setPrefSize(60,6);
        btnExit = new Button("Exit");
        btnExit.setPrefSize(60,6);

        btnClear.setOnAction(this);
        btnExit.setOnAction(this);


        hBox.getChildren().addAll(buttonLoad,buttonSave,btnClear,btnExit);

        layout.setTop(hBox);

        VBox vBox = new VBox();
        vBox.getStyleClass().add("vbox");
        //vBox.setOpacity(0.9);
        vBox.setPadding(new Insets(10,10,10,10));
        vBox.setSpacing(10);
        //vBox.setBorder("-fx-border-style: none");
        vBox.setCursor(Cursor.DEFAULT);


        txtArea = new TextArea();
        txtArea.getStyleClass().add("text-area");
      //  txtArea.getStyleClass().add("text-area .content");
        txtArea.setPrefSize(600,570);
        txtArea.setCursor(Cursor.DEFAULT);
        // txtArea.setStyle("-fx-background-color: #383838;");


        txtArea.setWrapText(true);

        vBox.getChildren().add(txtArea);

        layout.setCenter(vBox);


        Scene scene = new Scene(layout,600,600);
        scene.getStylesheets().add("Style.css");


        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String readFile(File file)
    {
        StringBuilder stringBuffer = new StringBuilder();
        BufferedReader bufferedReader = null;

        try
        {
            bufferedReader = new BufferedReader(new FileReader(file));

            String text;
            while((text = bufferedReader.readLine()) != null)
            {
                stringBuffer.append(text + "\n");
            }
        }catch (FileNotFoundException ex){
            Logger.getLogger(DevProj1.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex) {
            Logger.getLogger(DevProj1.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try{
                bufferedReader.close();
            }catch (IOException ex){
                Logger.getLogger(DevProj1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return stringBuffer.toString();

    }

    public void SaveFile(String content, File file)
    {
        try{
            FileWriter fileWriter;

            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        }catch (IOException ex){
            Logger.getLogger(DevProj1.class.getName()).log(Level.SEVERE,null,ex);
        }

    }

    public void handle(ActionEvent event)
    {
        if(event.getSource()==btnExit)
        {
            System.exit(0);
        }
        else if(event.getSource()==btnClear)
        {
            txtArea.clear();
        }

    }


}
