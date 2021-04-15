// mtreadwa && Max Treadway

package lab5;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**DocAnalyst uses a GUI to take file names of two types of files: CSV and regular text.
 * It displays word Count when the Regular button is pressed
 * and displays row and column count when CSV button is pressed.
 */
public class DocAnalyst extends Application{
	
	TextField inputText = new TextField();		//captures filename input by user
	Label resultLabel = new Label(); 			//displays result
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(setupScreen(), 400, 225);
		primaryStage.setTitle("Doc Analyst");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	BorderPane setupScreen() {
		BorderPane root = new BorderPane();
		Button csvButton = new Button("CSV");
		Button regularButton = new Button("Regular");

		GridPane docTypeGrid = new GridPane();	//holds buttons
		GridPane inputGrid = new GridPane();	//holds inputText and inputLabel
		root.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		Label inputLabel = new Label("Enter file name:");
		inputLabel.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
		inputLabel.setFont(Font.font(20));
		inputLabel.setPrefSize(200, 75);
		inputText.setFont(Font.font(20));
		inputText.setAlignment(Pos.CENTER);
		inputText.setPrefSize(200, 75);
		inputGrid.add(inputLabel, 0, 0);
		inputGrid.add(inputText, 1, 0);
		inputGrid.setStyle("-fx-border-color: grey");

		resultLabel.setFont(Font.font(20));
		resultLabel.setPrefSize(600,75);
		resultLabel.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
		resultLabel.setAlignment(Pos.CENTER);
		BorderPane.setAlignment(resultLabel, Pos.CENTER);

		csvButton.setPrefSize(200, 75);
		csvButton.setFont(Font.font(30));
		docTypeGrid.add(csvButton,  0, 0);
		regularButton.setPrefSize(200, 75);
		regularButton.setFont(Font.font(30));
		docTypeGrid.add(regularButton,  1, 0);

		root.setTop(docTypeGrid);	
		root.setCenter(inputGrid);
		root.setBottom(resultLabel);
		
		//registers buttons with respective event handler
		csvButton.setOnAction(new CSVButtonHandler());
		regularButton.setOnAction(new RegularButtonHandler());
		return root;
	}

	/** CSVButtonHandler takes the file name entered in inputText and creates 
	 * CSVDoc instance using this file name. It then invokes readFile() method and then
	 * collecDocInfo() method to get CSVDoc's rowCount and columnCount. 
	 * Finally it updates the resultLabel accordingly. */
	
	private class CSVButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			//create doc object using input text name
			CSVDoc csvDoc = new CSVDoc(inputText.getText());
			//invoke doc read method
			csvDoc.readFile();
			//invoke doc collectDocInfo method
			csvDoc.collectDocInfo();
			//update resultLabel with doc information
			resultLabel.setText("Rows: " + csvDoc.rowCount + ", Columns: " + csvDoc.columnCount);
		}
	}

	/** RegularButtonHandler takes the file name entered in inputText and creates 
	 * RegularDoc instance using this file name. It then invokes readFile() method and then
	 * collecDocInfo() method to get RegularDoc's wordCount. 
	 * Finally it updates the resultLabel accordingly.   
	 */
	private class RegularButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			//create doc object using input text name
			RegularDoc regularDoc = new RegularDoc(inputText.getText()); 
			//invoke doc read method
			regularDoc.readFile();
			//invoke doc collectDocInfo method
			regularDoc.collectDocInfo();
			//update resultLabel with doc information
			resultLabel.setText("Words: " + regularDoc.wordCount);
		}
	}
}
