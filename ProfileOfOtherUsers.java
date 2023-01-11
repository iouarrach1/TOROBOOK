
//Ibrahim ouarrach
// here is the profile of the club or the any other user that you searched for

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
public class ProfileOfOtherUsers {
    public static int t= 0;
    public static boolean followClicked= false;
    public static void displaywindow4() throws ClassNotFoundException, IOException {

        // here we fill the user array with the elemmts in the file array using the read method that i created
        for(int i = 0; i<FirstWindow.counterForNumberOfUsers; i++)
        {
            FirstWindow.usersOfTheProgram[i] = readObjectFromFile(FirstWindow.fileForUserObjects[i]);
        }
        // this array is used to store all the people following
        String[] thePeopleFollowing =  FirstWindow.usersOfTheProgram[FirstWindow.inderxOfTheUser].getFolowing();
        // this variable is used to store the index of the latest follo stored
        t = FirstWindow.usersOfTheProgram[FirstWindow.inderxOfTheUser].getIndexForTheLastelemtAddedInFollowArray();
    
        Stage window5 = new Stage();

        Button feed = new Button("feed");
        Button exitToLoginPage = new Button("Exit to loggin page");
        Button follow = new Button("follow");

        // this is for the image and showing it
        Image image = new Image(new FileInputStream(FirstWindow.usersOfTheProgram[feedWindow.indexForTheUserSearchingFor].getProfilePicture()));  
        ImageView imageView = new ImageView(image); 
        imageView.setX(50); 
        imageView.setY(25); 
        imageView.setFitHeight(150); 
        imageView.setFitWidth(150); 
        imageView.setPreserveRatio(true);  
        Group root = new Group(imageView);  

        
        Text post = new Text(FirstWindow.usersOfTheProgram[feedWindow.indexForTheUserSearchingFor].getOuputForPost());

        Label UserName = new Label("UserName: "+FirstWindow.usersOfTheProgram[feedWindow.indexForTheUserSearchingFor].getUserName());
        HBox HboxUserName = new HBox(UserName);
        HboxUserName.setAlignment(Pos.CENTER);
        Label  firstAndLastName = new Label("Name: "+FirstWindow.usersOfTheProgram[feedWindow.indexForTheUserSearchingFor].getFirstName()+"  "+FirstWindow.usersOfTheProgram[feedWindow.indexForTheUserSearchingFor].getLastName());
        HBox HboxfirstAndLastName = new HBox(firstAndLastName);
        HboxfirstAndLastName.setAlignment(Pos.CENTER);
        Label age = new Label("Age: "+FirstWindow.usersOfTheProgram[feedWindow.indexForTheUserSearchingFor].getAge());
        HBox Hboxage = new HBox(age);
        Hboxage.setAlignment(Pos.CENTER);
        Label hobby = new Label("Hobby: "+FirstWindow.usersOfTheProgram[feedWindow.indexForTheUserSearchingFor].getHobby());
        HBox Hboxhobby = new HBox(hobby);
        Hboxhobby.setAlignment(Pos.CENTER);
        Label gender = new Label("Gender: "+FirstWindow.usersOfTheProgram[feedWindow.indexForTheUserSearchingFor].getGender());
        HBox Hboxgender = new HBox(gender);
        Hboxgender.setAlignment(Pos.CENTER);

        // here this botton is for when the user follow a person
        follow.setOnAction(value0 -> 
        {   
            boolean correctlogin = false;
            for(int i =0; i<5; i++)
            {
                
                if(thePeopleFollowing[i].equals(FirstWindow.usersOfTheProgram[feedWindow.indexForTheUserSearchingFor].getUserName()))
                {
                    correctlogin = true;
                }
            }
            if( correctlogin == false)
            {
                thePeopleFollowing[t] = FirstWindow.usersOfTheProgram[feedWindow.indexForTheUserSearchingFor].getUserName();
                t++;
                FirstWindow.usersOfTheProgram[FirstWindow.inderxOfTheUser].setFolowing(thePeopleFollowing);
                FirstWindow.usersOfTheProgram[FirstWindow.inderxOfTheUser].setIndexForTheLastelemtAddedInFollowArray(t);
            }

        });
        // this botton for exiting
        exitToLoginPage.setOnAction(value0 -> 
        {  

            window5.close();
            try {
                writeObjectToFile(FirstWindow.usersOfTheProgram[FirstWindow.inderxOfTheUser], FirstWindow.fileForUserObjects[FirstWindow.inderxOfTheUser]);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        // this botton for accessing the feed
        feed.setOnAction(value0 -> 
        {  
            window5.close();
            try {
                writeObjectToFile(FirstWindow.usersOfTheProgram[FirstWindow.inderxOfTheUser], FirstWindow.fileForUserObjects[FirstWindow.inderxOfTheUser]);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                feedWindow.displaywindow4();
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        });

        HBox HboxBotton = new HBox(20, feed, exitToLoginPage);
        HboxBotton.setAlignment(Pos.CENTER);
        HBox HboxFollowBotton = new HBox( follow);
        HboxFollowBotton.setAlignment(Pos.CENTER);
        VBox VboxBot = new VBox(10,root ,HboxUserName, HboxfirstAndLastName,Hboxage,Hboxhobby, Hboxgender,HboxFollowBotton, post, HboxBotton);
        VboxBot.setAlignment(Pos.TOP_CENTER);
    
        Scene scene = new Scene(VboxBot, 800, 600);
        // Create a scene and place it in the stage
        window5.setTitle("FaceBook media demo By Ibrahim Ouarrach");
        window5.setScene(scene); // Place in scene in the stage
        window5.show();
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

    // this method is for writing to a file, i founded it in the internet link: https://mkyong.com/java/how-to-write-an-object-to-file-in-java/
    public static void writeObjectToFile(User obj, File file) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(obj);
            oos.flush();
        }
    }
}
