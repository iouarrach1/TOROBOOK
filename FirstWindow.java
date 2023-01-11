

// ibrhim ouarrach
// this class is the mainwindow where tht euser login
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FirstWindow extends Application  {

    // here we created these arrays of object for users so that we can acces each user depending on data he gave us, we use counterForNumberOfUsers to sknow how many objects we have, ofcourse no more then 10
    public static User[] usersOfTheProgram= new User[10];
    public static int counterForNumberOfUsers = 0; 
    //here we use the array to save all the files that has an object and we use inderxOfTheUser to save the index of the user that with the same username and password
    public static File[] fileForUserObjects= new File[10];
    public static int inderxOfTheUser = 0;
    
    public void start(Stage primaryStage) throws ClassNotFoundException, IOException{

        counterForNumberOfUsers = 0;
        // here we just fiil the file array with the files that we alredy have
        for(int i =0; i<fileForUserObjects.length;i++)
        {
            if(Files.exists(Paths.get("user"+i+".bin")))
            { 
                fileForUserObjects[i] = new File("user"+i+".bin");
                counterForNumberOfUsers++;
            }
        }
        // here we fill the user array with the elemmts in the file array using the read method that i created
        for(int i = 0; i<FirstWindow.counterForNumberOfUsers; i++)
        {
            FirstWindow.usersOfTheProgram[i] = readObjectFromFile(FirstWindow.fileForUserObjects[i]);
        }

        // here are just abels and TextField and botton that i used to get data
        Label message = new Label("");

        Label UserName = new Label("UserName:");
        TextField UserNameTextField = new TextField();
        HBox UserNameHBox = new HBox(10, UserName, UserNameTextField);
        UserNameHBox.setAlignment(Pos.CENTER);

        Label Password = new Label("Password:");
        TextField PasswordTextField = new TextField();
        HBox PasswordHBox = new HBox(10, Password, PasswordTextField);
        PasswordHBox.setAlignment(Pos.CENTER);

        Button loginBotton = new Button("login");
        Button registerBotton = new Button("register");
        loginBotton.setOnAction(value0 -> 
        {  
            String usernameString = UserNameTextField.getText();
            String passwordString = PasswordTextField.getText();
            // he we use the method that i created to check if the login info is correct or not
            if(correctloginInfo(usernameString, passwordString))
            {
                // here we call the next window using a method from an other class, to login to the user profile
                try {
                    LoginWindow.displaywindow3();
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }
            }
            message.setText("The info that you putted is incorrect, try again");
        });
        registerBotton.setOnAction(value1 -> 
        {  
            if(counterForNumberOfUsers<9)
            {
                // here we call the next window using a method from an other class, sending so that the user can register
               RegisterWindow.displaywindow2();
            }
        });

        HBox HboxBotton = new HBox(20, registerBotton, loginBotton);
        HboxBotton.setAlignment(Pos.CENTER);
        VBox VboxPane = new VBox(20,UserNameHBox ,PasswordHBox, message, HboxBotton);
        VboxPane.setAlignment(Pos.CENTER);
    
        Scene scene = new Scene(VboxPane, 600, 400);
        primaryStage.setTitle("FaceBook media demo By Ibrahim Ouarrach");
        primaryStage.setScene(scene); 
        primaryStage.show();
      }
    

    public static void main(String[] args) {
        launch();
    }
    // i created this method to check if the user name and the password are the same for the aboject that the userwant to access
    // i achieved that by first searching for the username and then checking if the passwoord of the same object is the same
    public boolean correctloginInfo(String userName, String password)
    {
        boolean correctlogin = false;
        for(int i =0; i<counterForNumberOfUsers; i++)
        {
            if(usersOfTheProgram[i].getUserName().equals(userName) && usersOfTheProgram[i].getPasword().equals(password))
            {
                correctlogin = true;
                inderxOfTheUser = i;
            }
        }
       return correctlogin;
    }

    // this method is for reading from a file, i founded it in the internet link: https://mkyong.com/java/how-to-write-an-object-to-file-in-java/
    public static User readObjectFromFile(File file) throws IOException, ClassNotFoundException {
        User result = null;
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            result = (User) ois.readObject();
        }
        return result;
    }
}