import com.jfoenix.controls.JFXDecorator;
import controllers.MainController;
import db.DBConnection;
import de.codecentric.centerdevice.javafxsvg.SvgImageLoaderFactory;
import de.codecentric.centerdevice.javafxsvg.dimension.PrimitiveDimensionProvider;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.container.DefaultFlowContainer;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static final String MAIN_FXML = "view/main.fxml";
    private static final String CSS_URL = "css/";
    private static final String IMG_URL = "images/";
    private static final String DB_NAME = "callisto.db";


    @FXMLViewFlowContext
    private ViewFlowContext flowContext;

    public static void main(String[] args) {
        DBConnection dbConnection = new DBConnection(DB_NAME);
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        SvgImageLoaderFactory.install(new PrimitiveDimensionProvider());

        Flow flow = new Flow(MainController.class);
        DefaultFlowContainer container = new DefaultFlowContainer();
        flowContext = new ViewFlowContext();
        flowContext.register("primaryStage", stage);

        try {
            flow.createHandler(flowContext).start(container);
        } catch (FlowException e) {
            e.printStackTrace();
        }

        JFXDecorator decorator = new JFXDecorator(stage, container.getView());
        decorator.setCustomMaximize(true);
//        decorator.setGraphic();
        decorator.setTitle("Callisto");
        decorator.setId("title_decorator");
        double width = 1024;
        double height = 576;
//        try {
//            Rectangle2D bounds = Screen.getScreens().get(0).getBounds();
//            width = bounds.getWidth() / 2.5;
//            height = bounds.getHeight() / 1.35;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        Scene scene = new Scene(decorator, width, height);
        final ObservableList<String> stylesheets = scene.getStylesheets();
        stylesheets.addAll(
                getClass().getResource("/css/font.css").toExternalForm(),   // font.css must always be loaded first.
                getClass().getResource("/css/title_bar.css").toExternalForm(),
                getClass().getResource("/css/body.css").toExternalForm()
        );
        stage.setScene(scene);
        stage.show();
    }
}
