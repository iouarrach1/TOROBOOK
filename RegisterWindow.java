
// IBRAHIM OUARRACH
// this is the registrartion class 
import java.io.*;
import java.util.Random;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
public class RegisterWindow {


    public static void displaywindow2(){

        // Here are just some labels and textFeilds to get data
        FirstWindow.usersOfTheProgram[FirstWindow.counterForNumberOfUsers] = new User();
        Stage window2 = new Stage();
        Button backFirstWindowBotton = new Button("Register");

        Label info = new Label("");
        Label infoForImage = new Label("Enter the path of the imgae. It must be of type: PNG OR JPEG OR GIF OR BMP");

        Label firstName = new Label("First Name:");
        TextField firstNameTextField = new TextField();
        HBox firstNameHBox = new HBox(10, firstName, firstNameTextField);
        firstNameHBox.setAlignment(Pos.CENTER);

        Label lastName = new Label("Last Name:");
        TextField lastNameTextField = new TextField();
        HBox lastNameHBox = new HBox(10, lastName, lastNameTextField);
        lastNameHBox.setAlignment(Pos.CENTER);

        Label age = new Label("Age:");
        TextField ageTextField = new TextField();
        HBox ageHBox = new HBox(10, age, ageTextField);
        ageHBox.setAlignment(Pos.CENTER);

        Label gender = new Label("Gender:");
        TextField genderTextField = new TextField();
        HBox genderHBox = new HBox(10, gender, genderTextField);
        genderHBox.setAlignment(Pos.CENTER);

        Label hobby = new Label("Hobby:");
        TextField hobbyTextField = new TextField();
        HBox hobbyHBox = new HBox(10, hobby, hobbyTextField);
        hobbyHBox.setAlignment(Pos.CENTER);

        Label UserName = new Label("UserName:");
        TextField UserNameTextField = new TextField();
        HBox UserNameHBox = new HBox(10, UserName, UserNameTextField);
        UserNameHBox.setAlignment(Pos.CENTER);

        Label Password = new Label("Password:");
        TextField PasswordTextField = new TextField();
        HBox PasswordHBox = new HBox(10, Password, PasswordTextField);
        PasswordHBox.setAlignment(Pos.CENTER);

        Label ProfileImage = new Label("Profile Image:");
        TextField ProfileImageTextField = new TextField();
        HBox ProfileImageHBox = new HBox(10, ProfileImage, ProfileImageTextField);
        ProfileImageHBox.setAlignment(Pos.CENTER);

        // here is the botton to go back to the login window
        backFirstWindowBotton.setOnAction(value0 -> 
        {  
            // we check if the username is the same as the first name or the last name or if its alredy taken, then we give you a unic username randomly genrated.
            if(!UserNameTextField.getText().equals(firstNameTextField.getText()) && !UserNameTextField.getText().equals(lastNameTextField.getText()) && checkingIfTheNameIsTaken(UserNameTextField.getText(), FirstWindow.usersOfTheProgram) ==0)
            {
                FirstWindow.usersOfTheProgram[FirstWindow.counterForNumberOfUsers].setFirstName(firstNameTextField.getText());
                FirstWindow.usersOfTheProgram[FirstWindow.counterForNumberOfUsers].setLastName(lastNameTextField.getText());
                FirstWindow.usersOfTheProgram[FirstWindow.counterForNumberOfUsers].setAge(ageTextField.getText());
                FirstWindow.usersOfTheProgram[FirstWindow.counterForNumberOfUsers].setGender(genderTextField.getText());
                FirstWindow.usersOfTheProgram[FirstWindow.counterForNumberOfUsers].setHobby(hobbyTextField.getText());
                FirstWindow.usersOfTheProgram[FirstWindow.counterForNumberOfUsers].setUserName(UserNameTextField.getText());
                FirstWindow.usersOfTheProgram[FirstWindow.counterForNumberOfUsers].setPasword(PasswordTextField.getText());
                FirstWindow.usersOfTheProgram[FirstWindow.counterForNumberOfUsers].setProfilePicture(ProfileImageTextField.getText());

                User tempUser = FirstWindow.usersOfTheProgram[FirstWindow.counterForNumberOfUsers];

                FirstWindow.fileForUserObjects[FirstWindow.counterForNumberOfUsers] = new File("user"+FirstWindow.counterForNumberOfUsers+".bin");

                try {
                    writeObjectToFile(tempUser, FirstWindow.fileForUserObjects[FirstWindow.counterForNumberOfUsers]);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                FirstWindow.counterForNumberOfUsers = FirstWindow.counterForNumberOfUsers+1;
                // here we close this winodw and call the next window using a method from an other class, sending a boolean value wich determines the which AI you will play against
                window2.close();
            }
            else 
            {
                String temp = randomUsername(firstNameTextField.getText());
                while(checkingIfTheNameIsTaken(temp, FirstWindow.usersOfTheProgram) !=0)
                {
                    temp = randomUsername(firstNameTextField.getText());
                }
                info.setText(temp);
            }
        });

        VBox VboxBot = new VBox(20, firstNameHBox, lastNameHBox, ageHBox, genderHBox, hobbyHBox,ProfileImageHBox,infoForImage , UserNameHBox,info, PasswordHBox, backFirstWindowBotton);
        VboxBot.setAlignment(Pos.CENTER);
        Scene scene = new Scene(VboxBot, 800, 600);
        // Create a scene and place it in the stage
        window2.setTitle("FaceBook media demo By Ibrahim Ouarrach");
        window2.setScene(scene); // Place in scene in the stage
        window2.show();
    }
    // this method is for writing to a file, i founded it in the internet link: https://mkyong.com/java/how-to-write-an-object-to-file-in-java/
    public static void writeObjectToFile(User obj, File file) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(obj);
            oos.flush();
        }
    }
    // this method is for checking if the username is alredy taken
    public static int checkingIfTheNameIsTaken(String useraName, User[] array)
    {
        int flag =0;
        for(int p =0; p<FirstWindow.counterForNumberOfUsers; p++)
        {
            if(useraName.equals(array[p].getUserName()))
            {
                flag = flag +1;
            }
        }
        return flag;
    }
    // this method is for genrating a random name
    public static String randomUsername(String name)
    {
        Random randy = new Random();
        String username = name+randy.nextInt(100);
        return username;
    }
}

