package no.ntnu.idatt2001.mmedvard;

import javafx.scene.Parent;
import javafx.scene.Scene;
import java.util.Objects;

/**
 * SceneFactory, to fulfill assignment requirements
 *
 */
public class SceneFactory {
    public static Scene create(Parent parent) {
        Scene scene = new Scene(parent);

        scene.getStylesheets().add(Objects.requireNonNull(App.class.getResource("style.css")).toExternalForm());
        return scene;
    }
}
