package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ErrorController extends Controller<Exception> {
    @FXML private ImageView errorBanner;
    @FXML private Label messageLabel;

    @FXML
    private void initialize() {
        errorBanner.setImage(
          new Image("file:src/view/image/error_banner.jpg")
        );
        errorBanner.setPreserveRatio(false);
        errorBanner.setFitWidth(400);
        errorBanner.setFitHeight(200);
        messageLabel.setText(model.getMessage());
    }

    @FXML
    private void onClose() {
        stage.close();
    }
}
