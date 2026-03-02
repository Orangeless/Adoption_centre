package controller;

import java.io.IOException;
import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Application.AdoptionCentre;
import model.Exceptions.InvalidOperationException;
import model.Exceptions.UnauthorizedAccessException;
import model.Users.Customer;
import model.Users.Manager;

public class LoginController extends Controller<AdoptionCentre> {
    @FXML private ImageView  banner;
    @FXML private TextField  usernameField;
    @FXML private TextField  emailField;
    @FXML private TextField  managerIdField;

    @FXML
    private void initialize() {
        banner.setImage(new Image("file:src/view/image/cat_banner.jpg"));
        banner.setPreserveRatio(false);
        banner.setFitWidth(400);
        banner.setFitHeight(200);
        
        stage.setResizable(false);
    }

    @FXML
    private void onLogin() throws IOException {
        try {
            if (!managerIdField.getText().trim().isEmpty()) {
                Manager m = model.getUsers()
                                 .validateManager(managerIdField.getText().trim());
                ViewLoader.showStage(
                  model,
                  "/view/ManagerDashboard.fxml",
                  "Manager Dashboard",
                  new Stage()
                );
            } else {
                Customer c = model.getUsers()
                                  .validateCustomer(
                                      usernameField.getText().trim(),
                                      emailField.getText().trim()
                                  );
                ViewLoader.showStage(
                  model,
                  "/view/CustomerDashboard.fxml",
                  "Customer Dashboard",
                  new Stage()
                );
            }
            stage.close();
        }
        catch (InvalidOperationException | UnauthorizedAccessException ex) {
            ViewLoader.showStage(
              ex,
              "/view/ErrorView.fxml",
              "Error",
              new Stage()
            );
        }
    }

    @FXML
    private void onExit() {
        stage.close();
    }
}
