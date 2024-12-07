package Views;

import Controllers.UserController;
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
        userController = UserController.getInstance();
        setupUI();
        setupActions();
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
    private void setupActions() {
        loginButton.setOnAction(e -> handleLogin());
        registerButton.setOnAction(e -> handleRegister());
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            messageText.setText("Please fill all fields");
            return;
        }

        if (userController.login(username, password)) {
            messageText.setText("correct");
            //switch view to main
            getScene().setRoot(new MainView());
        } else {
            messageText.setText("wrong username/password or unregistered");
        }
    }

    private void handleRegister() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            messageText.setText("fill out both fields");
            return;
        }

        if (userController.registerUser(username, password)) {
            messageText.setText("registered");
        } else {
            messageText.setText("try a different username");
        }
    }
}