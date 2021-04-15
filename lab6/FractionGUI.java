// mtreadwa && Max Treadway

package lab6;

import java.math.BigDecimal;
import java.math.RoundingMode;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class FractionGUI extends Application{

	GridPane grid = new GridPane();
	TextField numerator1 = new TextField();
	TextField denominator1 = new TextField();
	TextField numerator2 = new TextField();
	TextField denominator2 = new TextField();
	Label numerator3 = new Label();
	Label denominator3 = new Label();
	Label decimal = new Label();

	@Override
	public void start(Stage primaryStage) throws Exception {
		setScreen();
		Scene scene = new Scene (grid, 350, 250);
		primaryStage.setTitle("Fraction Adder");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void setScreen() {		
		// Moved to member variables
		//TextField numerator1 = new TextField();
		//TextField denominator1 = new TextField();
		//TextField numerator2 = new TextField();
		//TextField denominator2 = new TextField();
		//Label numerator3 = new Label();
		//Label denominator3 = new Label();
		//Label decimal = new Label();

		numerator1.setPrefWidth(50);
		denominator1.setPrefWidth(50);
		numerator2.setPrefWidth(50);
		denominator2.setPrefWidth(50);
		numerator3.setPrefWidth(50);
		denominator3.setPrefWidth(50);

		numerator1.setAlignment(Pos.CENTER);
		denominator1.setAlignment(Pos.CENTER);
		numerator2.setAlignment(Pos.CENTER);
		denominator2.setAlignment(Pos.CENTER);
		numerator3.setAlignment(Pos.CENTER);
		denominator3.setAlignment(Pos.CENTER);

		numerator1.setFont(Font.font(15));
		denominator1.setFont(Font.font(15));
		numerator2.setFont(Font.font(15));
		denominator2.setFont(Font.font(15));
		numerator3.setFont(Font.font(15));
		denominator3.setFont(Font.font(15));
		decimal.setFont(Font.font(15));

		Label plus = new Label("+");
		plus.setPrefWidth(20);
		plus.setFont(Font.font(25));
		Label divide1 = new Label("__________");
		Label divide2 = new Label("__________");
		Label divide3 = new Label("__________");
		Label equals = new Label("=");
		equals.setPrefWidth(20);
		equals.setFont(Font.font(25));

		Button goButton = new Button("Go!");  		//to be attached to an event handler
		Button clearButton = new Button("Clear"); 	//to be attached to an event handler
		goButton.setPrefWidth(75);
		clearButton.setPrefWidth(75);

		grid.add(new Label("Numerator"), 0, 0);
		grid.add(new Label("Denominator"), 0, 2);
		grid.add(numerator1, 1, 0);
		grid.add(divide1, 1, 1);
		grid.add(denominator1, 1, 2);
		grid.add(plus, 2, 1);
		grid.add(numerator2, 3, 0);
		grid.add(divide2, 3, 1);
		grid.add(denominator2, 3,2);
		grid.add(equals, 4, 1);
		grid.add(numerator3, 5, 0);
		grid.add(divide3, 5, 1);
		grid.add(denominator3, 5, 2);
		grid.add(decimal, 5, 4);

		grid.add(goButton, 0, 4, 3, 1);
		grid.add(clearButton, 1, 4, 3, 1);
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		
		 //registers buttons with respective event handler
		goButton.setOnAction(new GoButtonHandler());
		clearButton.setOnAction(new ClearButtonHandler());
	}
	
	private class GoButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			//Fraction fraction = new Fraction();
			Fraction f1 = new Fraction(Integer.parseInt(numerator1.getText()), Integer.parseInt(denominator1.getText()));
			Fraction f2 = new Fraction(Integer.parseInt(numerator2.getText()), Integer.parseInt(denominator2.getText()));
			Fraction f3 = f1.add(f2);
			numerator3.setText(String.valueOf((f3.numerator)));
			denominator3.setText(String.valueOf((f3.denominator)));
			if ((Integer.parseInt(denominator3.getText()) == 0) && (Integer.parseInt(numerator3.getText()) == 0)) {
				decimal.setText("NaN");
			} else if (Integer.parseInt(denominator3.getText()) == 0) {
				decimal.setText("Infinity");
			} else if (Integer.parseInt(numerator3.getText()) == 0){
				decimal.setText("Undefined");
			} else {
				// reduce to 2 decimals (not explicit but appears in JAR)
				BigDecimal roudndeddub = new BigDecimal(f3.toDecimal()).setScale(2, RoundingMode.HALF_UP);
				decimal.setText(String.valueOf((roudndeddub)));
			}
		}
	}

	private class ClearButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			// clear fraction
			numerator1.setText(null);
			numerator2.setText(null);
			denominator1.setText(null);
			denominator2.setText(null);
			numerator3.setText(null);
			denominator3.setText(null);
			decimal.setText(null);
		}
	}
}