import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lk.vivoxalabs.customstage.CustomStage;
import lk.vivoxalabs.customstage.CustomStageBuilder;
import lk.vivoxalabs.customstage.tools.HorizontalPos;

import java.io.IOException;

public class Main extends Application {

    private static final String mainFXML = "view/main.fxml";
    private static final String cssURL = "css/";
    private static final String imgURL = "images/";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Parent root = null;
        CustomStage stage = null;
        try {
            root = FXMLLoader.load(getClass().getResource(mainFXML));

            stage = new CustomStageBuilder()
                    .setWindowTitle("Callisto", HorizontalPos.RIGHT, HorizontalPos.CENTER)
                    .setActionIcons(
                            new Image(imgURL + "window-close.svg"),    //Close
                            new Image(imgURL + "window-minimize.svg"), //Minimize
                            new Image(imgURL + "window-maximize.svg"), // Maximize
                            new Image(imgURL + "window-restore.svg"))  // Restore
                    .setTitleColor("black")
//                    .setStyleSheet(getClass().getResource(cssURL + "title_bar.css"))
                    .setIcon(imgURL + "logo.png")
                    .setButtonHoverColor("FFAB40", "FFAB40", "d32f2f")
                    .setDimensions(450, 450, 1920, 1080) //min,max values for window resizing
                    .setWindowColor("FF6D00")
                    .build();

        } catch (IOException e) {
            e.getMessage();
            e.printStackTrace();
        }

        if (root != null && stage != null) {
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
            stage.show();
        }
    }
}
