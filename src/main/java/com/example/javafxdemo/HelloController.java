package com.example.javafxdemo;

import com.example.javafxdemo.metiers.Article;
import com.example.javafxdemo.metiers.DaoArticle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private TextField txtcode ;
    @FXML
    private TextField txtdesignation ;
    @FXML
    private TextField txtprix ;
    @FXML
    protected void onHelloButtonClick() {

        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    protected void AddArticle()
    {
        DaoArticle daoArticle = new DaoArticle();
        Article article = new Article(txtcode.getText(),
                txtdesignation.getText(),Float.parseFloat(txtprix.getText()));
        daoArticle.Create(article);

    }
}