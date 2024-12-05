package Views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class LoginView extends VBox {
    protected TextField usernameField;
    protected PasswordField passwordField;
    protected Button loginButton;
    protected Button registerButton;
    protected Text messageText;

    public LoginView() {
        setupUI();
    }

    private void setupUI() {
        setSpacing(10);
        setPadding(new Insets(15));
        setAlignment(Pos.CENTER);
        setMaxWidth(300);

        messageText = new Text();
        usernameField = new TextField();
        passwordField = new PasswordField();
        loginButton = new Button("Login");
        registerButton = new Button("Register");

        usernameField.setPromptText("Username");
        passwordField.setPromptText("Password");

        getChildren().addAll(
            messageText,
            usernameField,
            passwordField,
            loginButton,
            registerButton
        );
    }
}