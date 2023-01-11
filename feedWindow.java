

//ibrahim ouarrach
// this class is for the feed window
import java.io.*;
import java.util.Arrays;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class feedWindow {
    public static String postOutput = "";
    static int indexForTheUserSearchingFor = 0;
    public static void displaywindow4() throws ClassNotFoundException, IOException {
        for(int i = 0; i<FirstWindow.counterForNumberOfUsers; i++)
        {
            FirstWindow.usersOfTheProgram[i] = readObjectFromFile(FirstWindow.fileForUserObjects[i]);
        }
        Stage window4 = new Stage();

        Button profile = new Button("go back to your profile");
        Button search = new Button("Search");

        TextField UserNameTextField = new TextField();
        HBox UserNameHBox = new HBox(10, UserNameTextField, search);
        UserNameHBox.setAlignment(Pos.TOP_CENTER);

        Label infoDisplayer = new Label("");

        Label post = new Label("The latest posts:");
        Text latestPosts = new Text(FirstWindow.usersOfTheProgram[FirstWindow.inderxOfTheUser].getOuputForPost());
        postOutput = "";

        // here is where we show the posts of everyone.
        for(int i =0; i<FirstWindow.counterForNumberOfUsers; i++)
        {
            String[] tempArray = FirstWindow.usersOfTheProgram[i].getPosts();
            String temp =FirstWindow.usersOfTheProgram[i].getUserName() +" : "+ FirstWindow.usersOfTheProgram[i].getOuputForPost() +"\n";
            postOutput = postOutput + String.format("%s", temp);
        }
        latestPosts.setText(postOutput);

        //here is the user can go back to the landing page
        profile.setOnAction(value0 -> 
        {  
            window4.close();
            try {
                LoginWindow.displaywindow3();
            } catch (ClassNotFoundException | IOException e) {
            
                e.printStackTrace();
            }
        });
        // here where the user can search for the username of an other person
        search.setOnAction(value0 -> 
        {  
            String usernameString = UserNameTextField.getText();
            if(UsernameSearch( usernameString))
            {
                window4.close();
                try {
                    ProfileOfOtherUsers.displaywindow4();
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }
            }
            else 
            {
                infoDisplayer.setText("The user that you are looking for is not found");
            }
        });

        HBox HboxSearch = new HBox(20, UserNameHBox, profile);
        HboxSearch.setAlignment(Pos.CENTER);
        VBox VboxBot = new VBox(20, HboxSearch, infoDisplayer,post,latestPosts, profile);
        VboxBot.setAlignment(Pos.TOP_CENTER);
    
        Scene scene = new Scene(VboxBot, 600, 400);
        // Create a scene and place it in the stage
        window4.setTitle("FaceBook media demo By Ibrahim Ouarrach");
        window4.setScene(scene); // Place in scene in the stage
        window4.show();
        
    }   

    public static User readObjectFromFile(File file) throws IOException, ClassNotFoundException {
        User result = null;
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            result = (User) ois.readObject();
        }
        return result;
    }
    // this method is used to search for some person in the program by just his user name
    public static boolean UsernameSearch(String userName)
    {
        boolean correctlogin = false;
        for(int i =0; i<FirstWindow.counterForNumberOfUsers; i++)
        {
            if(FirstWindow.usersOfTheProgram[i].getUserName().equals(userName) && !(FirstWindow.usersOfTheProgram[i].getUserName().equals(FirstWindow.usersOfTheProgram[FirstWindow.inderxOfTheUser].getUserName())))
            {
                correctlogin = true;
                indexForTheUserSearchingFor = i;
            }
        }
       return correctlogin;
    }
}
