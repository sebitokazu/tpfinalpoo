package game.frontend;

import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;

public class AppMenu extends MenuBar {

    public AppMenu() {
        Menu file = new Menu("Archivo");
        MenuItem exitMenuItem = new MenuItem("Salir");
        exitMenuItem.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Salir");
            alert.setHeaderText("Salir de la aplicación");
            alert.setContentText("¿Está seguro que desea salir de la aplicación?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent()) {
                if (result.get() == ButtonType.OK) {
                    Platform.exit();
                }
            }
        });
        MenuItem backToMainMenuItem = new MenuItem("Menu Principal");
        backToMainMenuItem.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Salir");
            alert.setHeaderText("Salir al menu principal");
            alert.setContentText("¿Está seguro que desea salir de la aplicación? Se perdera el juego en progreso.");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent()){
                if(result.get() == ButtonType.OK){
                    Stage currentStage = (Stage) getScene().getWindow();
                    GameApp gameApp = new GameApp();
                    gameApp.start(new Stage());
                    currentStage.close();
                }
            }
        });
        file.getItems().add(exitMenuItem);
        file.getItems().add(backToMainMenuItem);
        Menu help = new Menu("Ayuda");
        MenuItem aboutMenuItem = new MenuItem("Acerca De");
        aboutMenuItem.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Acerca De");
            alert.setHeaderText("Candy TPE");
            alert.setContentText("Cátedra POO 2018.\n" +
                    "Implementación Original: Laura Zabaleta (POO 2013).");
            alert.showAndWait();
        });
        help.getItems().add(aboutMenuItem);
        getMenus().addAll(file, help);
    }

}
