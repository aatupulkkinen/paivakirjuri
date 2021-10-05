package otp;


import java.io.IOException;

public interface SceneController {
    void openMainScene() throws IOException;
    void openChangePasswordScene() throws IOException;
    void openForgotPasswordScene() throws IOException;
    void openRegisterAccountScene() throws IOException;
    void openLoginScene() throws IOException;
    void openSettingsScene() throws IOException;
}
