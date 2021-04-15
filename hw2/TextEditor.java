// mtreadwa && Max Treadway

package hw2;

import java.io.File;
import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.*;

/** TextEditor is a simple application to demonstrate how to develop an 
 * end-to-end application with text-file as 'Model', 
 * GUI as 'View', and various event-handlers as 'Controllers'   */

public class TextEditor extends Application {

	Stage stage;
	BorderPane root = new BorderPane(); 	//holds all GUI components
	TextArea fileTextArea = new TextArea(); //displays the file content
	Label statusLabel = new Label();		//shows the status of various actions
	String searchString;					//used in Search function
	StringBuilder fileContent;				//holds the file text

	FileUtilities fileUtilities = new FileUtilities();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		this.stage = stage;
		setScreen();
		Scene scene = new Scene(root, 500, 600);
		stage.setTitle("Text Reader");
		stage.setScene(scene);
		stage.show();
	}

	public void setScreen() {
		//create menus
		Menu fileMenu = new Menu("File");
		Menu toolsMenu = new Menu("Tools");
		Menu helpMenu = new Menu("Help");

		//attach File menu items and their event handlers
		MenuItem openFileMenuItem = new MenuItem("Open");
		openFileMenuItem.setOnAction(new OpenFileHandler());
		MenuItem closeFileMenuItem = new MenuItem("Close");
		closeFileMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				fileTextArea.clear();
				statusLabel.setText("");
				root.setCenter(null);
			}
		});
		MenuItem exitFileMenuItem = new MenuItem("Exit");
		exitFileMenuItem.setOnAction(actionEvent->Platform.exit());

		//attach Tools menu items and their event handlers
		MenuItem searchToolsMenuItem = new MenuItem("Search");
		searchToolsMenuItem.setOnAction(new SearchToolHandler());
		MenuItem replaceToolsMenuItem = new MenuItem("Replace");
		replaceToolsMenuItem.setOnAction(new ReplaceToolHandler());
		MenuItem wordCountToolsMenuItem = new MenuItem("Word Count");
		wordCountToolsMenuItem.setOnAction(new WordCountToolHandler());

		//set Help menu
		MenuItem aboutHelpMenuItem = new MenuItem("About");
		aboutHelpMenuItem.setOnAction(new AboutHandler());

		//set menubar
		MenuBar menuBar = new MenuBar();

		fileMenu.getItems().addAll(openFileMenuItem, closeFileMenuItem, new SeparatorMenuItem(), exitFileMenuItem);
		toolsMenu.getItems().addAll(searchToolsMenuItem, replaceToolsMenuItem, new SeparatorMenuItem(),wordCountToolsMenuItem);
		helpMenu.getItems().addAll(aboutHelpMenuItem);
		menuBar.getMenus().addAll(fileMenu, toolsMenu, helpMenu);	

		//set status bar
		statusLabel.setPrefWidth(this.stage.getMaxWidth());
		statusLabel.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

		//set layout 
		root.setTop(menuBar);
		root.setBottom(statusLabel);
	}

	/**OpenHandler provides the functionality for opening a file when the File-Open menu option 
	 * is selected by the user. */
	private class OpenFileHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			//Set OpenFile to bring up directory containing sample.txt
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Select File");
			//Open requested file & push to fileTextArea
			File file = null;
			if((file = fileChooser.showOpenDialog(stage)) != null) {
				fileContent = fileUtilities.readFile(file.getAbsolutePath());
				fileTextArea.clear();
				fileTextArea.appendText(fileContent.toString());
				fileTextArea.setWrapText(true);
				fileTextArea.positionCaret(0);
				root.setCenter(fileTextArea);
				statusLabel.setText(file.getPath());
			}
		}
	}

	/** getString() is a helper method that opens a dialog box to take user input for a 'purpose'. 
	 * This 'purpose' is meant to be either 'Search' or 'Replace' which
	 * appears as the title and label in the dialog box.
	 * The method returns the string entered by the user.
	 * When user presses Cancel, it returns null.
	 * Use this method in Search and Replace handlers as needed.
	 */
	private String getString(String purpose) {   
		String inputString = null;
		TextInputDialog searchTextInputDialog = new TextInputDialog();
		searchTextInputDialog.setTitle(purpose);
		searchTextInputDialog.setHeaderText(String.format("%s string", purpose));
		searchTextInputDialog.setContentText(String.format("Enter %s string ", purpose));
		Optional <String> searchStringOptional = searchTextInputDialog.showAndWait();
		if (searchStringOptional.isPresent()) {
			statusLabel.setText("");
			inputString = searchStringOptional.get();
		}
		if (inputString ==  null || inputString.isEmpty()) return null; //to handle empty string on Cancel
		else return inputString;
	}

	/** SearchToolHandler provides the functionality for searching a string when the Tool-Search menu option
	 * is selected by the user. It uses the getString() method as a helper method to capture the user input. */
	private class SearchToolHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			//Get user input
			String inputString = getString("Search");
			searchString = inputString;
			//EMPTY DIALOG INPUT
			//Either OK or Cancel button selected
			if (searchString == null || searchString.isEmpty()) {
				statusLabel.setText("Search Cancelled");		
			} else {
				//instantiate searchAll method
				int[] positions = fileUtilities.searchAll(fileContent, searchString);
				//HAS DIALOG INPUT
				//if found
				if (searchString != null && positions != null) {
					statusLabel.setText(searchString + " found " + positions.length + " times");
					int caretPosition = fileTextArea.getCaretPosition();
					for(int i = 0; i < positions.length; i++) {
						if (caretPosition <= positions[i]) {
							fileTextArea.positionCaret(positions[i] + searchString.length());
							caretPosition = fileTextArea.getCaretPosition() - searchString.length();
							fileTextArea.selectPositionCaret(caretPosition);
							break;
						}
					}
				}
				//not found
				if (searchString != null && positions == null) {
					statusLabel.setText(searchString + " not found");
				}
				//cancelled
				if (searchString == null && positions == null) {
					statusLabel.setText("Search Cancelled");
				}
			}
		}
	}


	/**ReplaceToolHandler provides the functionality for searching and replacing a string when the Tool-Replace menu option
	 * is selected by the user. It uses the getString() method as a helper method to capture the user input. 
	 */
	private class ReplaceToolHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			//SEARCH
			String inputString = getString("Search");
			searchString = inputString;
			//EMPTY DIALOG INPUT
			//either OK or Cancel button selected
			if (searchString == null || searchString.isEmpty()) {
				statusLabel.setText("Search Cancelled");		
			} else {
				//instantiate search method
				int[] positions = fileUtilities.searchAll(fileContent, searchString);
				//HAS DIALOG INPUT
				//If found
				if (searchString != null && positions != null) {
					statusLabel.setText(searchString + " found " + positions.length + " times");
					int caretPosition = fileTextArea.getCaretPosition();
					for(int i = 0; i < positions.length; i++) {
						if (caretPosition <= positions[i]) {
							fileTextArea.positionCaret(positions[i] + searchString.length());
							caretPosition = fileTextArea.getCaretPosition() - searchString.length();
							fileTextArea.selectPositionCaret(caretPosition);
							break;
						}
					}
					//REPLACE
					inputString = getString("Replace");
					String replaceTerm = inputString;
					//EMPTY DIALOG INPUT
					//Either OK or Cancel button selected
					if (replaceTerm == null || replaceTerm.isEmpty()) {
						statusLabel.setText("Replace Cancelled");	
					} else {
						int replacements = fileUtilities.replace(fileContent, searchString, replaceTerm);
						statusLabel.setText(searchString + " found and replaced with " + replaceTerm + " at " + replacements + " places");
						fileTextArea.setText(fileContent.toString());
					}
				}
				//If not found
				if (searchString != null && positions == null) {
					statusLabel.setText(searchString + " not found");
				}
				//If cancelled
				if (searchString == null && positions == null) {
					statusLabel.setText("Search Cancelled");
				}
			}
		}
	}

	/**WordCountToolHandler provides the functionality for counting the total number of words 
	 * in the text file that is currently open. */
	private class WordCountToolHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			int wordCount = fileUtilities.countWords(fileContent);
			statusLabel.setText(wordCount + " words");
		}
	}

	private class AboutHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("About");
			alert.setHeaderText("mtreadwa Text Editor");
			alert.setContentText("Version 1.0 \nRelease 1.0\nCopyleft Universe\nDeveloped by a minion!");
			Image image = new Image(this.getClass().getResource("image.png").toString());
			ImageView imageView = new ImageView();
			imageView.setImage(image);
			imageView.setFitWidth(150);
			imageView.setPreserveRatio(true);
			imageView.setSmooth(true);
			alert.setGraphic(imageView);
			alert.showAndWait();
		}
	}
}
