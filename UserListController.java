package controller;

import java.io.IOException;
import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Users.User;

public class UserListController 
        extends Controller<model.Application.AdoptionCentre> {

    @FXML private ImageView banner;
    @FXML private ListView<User> userListView;

    @FXML
    private void initialize() {
        
        banner.setImage(
          new Image("file:src/view/image/cat_banner.jpg")
  
        );
        banner.setPreserveRatio(false);
        banner.setFitWidth(400);
        banner.setFitHeight(200);

        
        userListView.setItems(
          model.getUsers().getUsers()
        );
    }

    @FXML
    private void onClose() {
        stage.close();
    }
}
