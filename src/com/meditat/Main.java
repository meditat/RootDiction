package com.meditat;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;


public class Main {

    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        JFrame frame = new JFrame("Swing and JavaFX");
        int iWant =(int)(width/3.8);//width of the frame
        frame.setSize(iWant, (int)height);
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setExtendedState(JFrame.MAXIMIZED_VERT);
        frame.setLocation( (int)width-iWant, 0 );//coordinates of the action center
        JPanel panel = new JPanel(new BorderLayout(0, 0));

        frame.setOpacity(0.8f);
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        final JFXPanel jfxPanel = new JFXPanel();
        panel.add(jfxPanel);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFx(jfxPanel);
            }
        });
    }

    private static void initFx(JFXPanel fxPanel) {
        Stage stage = new Stage();
        stage.setTitle("Web View");
        Scene scene = new Scene(new Browser(), 1000, 1000, Color.web("#666970"));
        stage.setScene(scene);
        fxPanel.setScene(scene);
    }

}

class Browser extends Region {

    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();


    public Browser() {
        // load the home page
        webEngine.load("http://www.google.com/");

        //add components
        getChildren().add(browser);
    }

    @Override
    protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser,0,0,w,h,0, HPos.CENTER,VPos.CENTER);
    }

}