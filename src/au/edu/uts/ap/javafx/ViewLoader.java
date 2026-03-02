package au.edu.uts.ap.javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewLoader {

    /**
     * Loads the given FXML, injects the model and stage into its controller,
     * attaches our global stylesheet, and shows the stage.
     *
     * @param model           the model to inject into the controller
     * @param fxml            the path (e.g. "/view/LoginView.fxml")
     * @param title           window title
     * @param stage           the JavaFX Stage to show
     * @param onStageClosed   a callback to run when this stage is closed
     */
    public static <T> void showStage(
            T model,
            String fxml,
            String title,
            Stage stage,
            Runnable onStageClosed
    ) throws IOException {
        FXMLLoader loader = new FXMLLoader(
            Controller.class.getResource(fxml),
            null,
            null,
            type -> {
                try {
                    @SuppressWarnings("unchecked")
                    Controller<T> controller = (Controller<T>) type.newInstance();
                    controller.model = model;
                    controller.stage = stage;
                    return controller;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        );

        Parent root = loader.load();

        // 1) Create the scene
        Scene scene = new Scene(root);

        // 2) Attach the one global stylesheet
        scene.getStylesheets().add(
            Controller.class
                      .getResource("/view/style.css")
                      .toExternalForm()
        );

        // 3) Show the stage
        stage.setTitle(title);
        stage.setScene(scene);
        stage.sizeToScene();

        
        stage.setOnHidden(e -> onStageClosed.run());

        stage.show();
    }

    
    public static <T> void showStage(
            T model,
            String fxml,
            String title,
            Stage stage
    ) throws IOException {
        showStage(model, fxml, title, stage, () -> {});
    }
}
