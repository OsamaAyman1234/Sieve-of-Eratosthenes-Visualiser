package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
public class Main   extends Application   {
    BorderPane bp = new BorderPane();
    @Override
    public void start(Stage primaryStage) throws Exception{
        intro();
        primaryStage.setTitle("Sieve of Eratosthenes Visualizer");
        primaryStage.setScene(new Scene(bp,800,600));
        primaryStage.show();

    }
    void intro()  throws Exception{
        VBox vbox = new VBox();
        Label l = new Label("Enter The Value of N");
        l.setStyle("-fx-font-size: 16pt;");
        TextField tf = new TextField();
        HBox hBoxTF = new HBox(tf);
        hBoxTF.setAlignment(Pos.CENTER);
        Button enter = new Button("Enter");
        enter.setOnAction(e -> enterOnAction(tf)) ;
        HBox hBoxButton = new HBox(enter);
        hBoxButton.setAlignment(Pos.CENTER_RIGHT);
        enter.setStyle("-fx-font-size: 12pt");
        vbox.getChildren().addAll(l,hBoxTF,hBoxButton);
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10,10,10,10));
        bp.setCenter(vbox);

    }
    void enterOnAction(TextField tf)  {
        int n = Integer.parseInt(tf.getText());
        GridPane gp = new GridPane();
        Pane[] arr = new Pane[n + 1];
        boolean[] notPrime = new boolean[n + 1];
        int count = 2;
        int size = (int) Math.ceil(Math.sqrt(n));
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (count == n + 1)
                    break;
                Label l = new Label(String.valueOf(count));
                arr[count] = new Pane(l);
                l.setPadding(new Insets(10, 10, 10, 10));
                l.setStyle(" -fx-font-size: 16; -fx-font-weight: bold");
                arr[count].setStyle("-fx-background-color : greenyellow ; -fx-border-color : black");
                gp.add(arr[count], j, i);
                count++;
            }
        }
        gp.setAlignment(Pos.CENTER);
        int end = (int) Math.ceil(Math.sqrt(n + 1));
        new Thread(()->{
            for (int i = 2; i <= end; i++) {
                if (!notPrime[i]) {
                    arr[i].setStyle("-fx-background-color : greenyellow; -fx-border-color : deepskyblue; -fx-border-width: 5");
                    for (int j = i * i; j <= n; j += i) {
                        notPrime[j] = true;
                        arr[j].setStyle("-fx-background-color : orangered; -fx-border-color : black");
                        try {Thread.sleep(120);}
                        catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    arr[i].setStyle("-fx-background-color : greenyellow; -fx-border-color : black");
                }
            }
        }).start();
        bp.setCenter(gp);
    }


    public static void main  (String[] args)  {
        launch(args);
    }
}