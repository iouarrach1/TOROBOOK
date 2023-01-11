
//ibrahim Ouarrach
// here is the profile of the user 
import java.io.*;
import java.util.Arrays;
import javafx.scene.text.Text;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class LoginWindow {
    public static int q=0;
    public static String postOutput = "";
    public static void displaywindow3() throws ClassNotFoundException, IOException {
        for(int c = 0; c<FirstWindow.counterForNumberOfUsers; c++)
        {
            FirstWindow.usersOfTheProgram[c] = readObjectFromFile(FirstWindow.fileForUserObjects[c]);
        }
        Stage window3 = new Stage();

        Button feed = new Button("feed");
        Button exitToLoginPage = new Button("Exit to loggin page");

        Button changingTheimage = new Button("change");
        TextField imageChangeTextField = new TextField();
        HBox imageChangeHBox = new HBox(10, imageChangeTextField, changingTheimage);
        imageChangeHBox.setAlignment(Pos.TOP_CENTER);

        Button posting = new Button("post");
        TextField postingTextField = new TextField();
        HBox postingHBox = new HBox(10, postingTextField, posting);
        postingHBox.setAlignment(Pos.TOP_CENTER);

        String[] postsString = FirstWindow.usersOfTheProgram[FirstWindow.inderxOfTheUser].getPosts();
        q = FirstWindow.usersOfTheProgram[FirstWindow.inderxOfTheUser].getIndexForPosting();

        Image image = new Image(new FileInputStream(FirstWindow.usersOfTheProgram[FirstWindow.inderxOfTheUser].getProfilePicture()));  
        ImageView imageView = new ImageView(image); 
        imageView.setX(50); 
        imageView.setY(25); 
        imageView.setFitHeight(150); 
        imageView.setFitWidth(150); 
        imageView.setPreserveRatio(true);  
        Group root = new Group(imageView);  

        Text post = new Text(FirstWindow.usersOfTheProgram[FirstWindow.inderxOfTheUser].getOuputForPost());
        Text info = new Text(" YourPosts");
        
        Label infoForImage = new Label("Enter the path of the imgae. It must be of type: PNG OR JPEG OR GIF OR BMP");

        Label UserName = new Label("UserName: "+FirstWindow.usersOfTheProgram[FirstWindow.inderxOfTheUser].getUserName());
        HBox HboxUserName = new HBox(UserName);
        HboxUserName.setAlignment(Pos.CENTER);
        Label  firstAndLastName = new Label("Name: "+FirstWindow.usersOfTheProgram[FirstWindow.inderxOfTheUser].getFirstName()+"  "+FirstWindow.usersOfTheProgram[FirstWindow.inderxOfTheUser].getLastName());
        HBox HboxfirstAndLastName = new HBox(firstAndLastName);
        HboxfirstAndLastName.setAlignment(Pos.CENTER);
        Label age = new Label("Age: "+FirstWindow.usersOfTheProgram[FirstWindow.inderxOfTheUser].getAge());
        HBox Hboxage = new HBox(age);
        Hboxage.setAlignment(Pos.CENTER);
        Label hobby = new Label("Hobby: "+FirstWindow.usersOfTheProgram[FirstWindow.inderxOfTheUser].getHobby());
        HBox Hboxhobby = new HBox(hobby);
        Hboxhobby.setAlignment(Pos.CENTER);
        Label gender = new Label("Gender: "+FirstWindow.usersOfTheProgram[FirstWindow.inderxOfTheUser].getGender());
        HBox Hboxgender = new HBox(gender);
        Hboxgender.setAlignment(Pos.CENTER);
        Label password = new Label("Password: "+FirstWindow.usersOfTheProgram[FirstWindow.inderxOfTheUser].getPasword());
        HBox Hboxpassword = new HBox(password);
        Hboxpassword.setAlignment(Pos.CENTER);
        Label folowingLabel = new Label("following: "+Arrays.toString(FirstWindow.usersOfTheProgram[FirstWindow.inderxOfTheUser].getFolowing()));
        HBox HboxfolowingLabel = new HBox(folowingLabel);
        HboxfolowingLabel.setAlignment(Pos.CENTER);

        // this botton is to post somthing
        posting.setOnAction(value0 -> 
        {
            if(q<5)
            {
                String postStringFromTextField = postingTextField.getText();
                postsString[q] ="\npost "+(q+1)+ ": "+postStringFromTextField ;

                    q++; 
                
                if(q==5)
                {
                    q--;
                }
                
                FirstWindow.usersOfTheProgram[FirstWindow.inderxOfTheUser].setPosts(postsString);
                FirstWindow.usersOfTheProgram[FirstWindow.inderxOfTheUser].setIndexForPosting(q);
            }
            postOutput = "";
            for(int b = 0; b<5 ; b++)
            {
                postOutput = postOutput + String.format("%s", FirstWindow.usersOfTheProgram[FirstWindow.inderxOfTheUser].getPosts()[b]);
            }
            post.setText(postOutput);
            FirstWindow.usersOfTheProgram[FirstWindow.inderxOfTheUser].setOuputForPost(postOutput);
        });

        // this is for changing a picture 
        changingTheimage.setOnAction(value0 -> 
        {  
            String imagePathString = imageChangeTextField.getText();
            FirstWindow.usersOfTheProgram[FirstWindow.inderxOfTheUser].setProfilePicture(imagePathString);
            try {
                imageView.setImage(new Image(new FileInputStream(imagePathString)));
            } catch (FileNotFoundException e) {
                
                e.printStackTrace();
            }
        });
        //this one is for exiting
        exitToLoginPage.setOnAction(value0 -> 
        {  
            window3.close();

            try {
                writeObjectToFile(FirstWindow.usersOfTheProgram[FirstWindow.inderxOfTheUser], FirstWindow.fileForUserObjects[FirstWindow.inderxOfTheUser]);
            } catch (IOException e) {
                
                e.printStackTrace();
            }
        });
        // this one is for checking the feed
        feed.setOnAction(value0 -> 
        {  
            window3.close();

            try {
                writeObjectToFile(FirstWindow.usersOfTheProgram[FirstWindow.inderxOfTheUser], FirstWindow.fileForUserObjects[FirstWindow.inderxOfTheUser]);
            } catch (IOException e) {
                
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
        VBox VboxBot = new VBox(10,root,imageChangeHBox, infoForImage ,HboxUserName, HboxfirstAndLastName,Hboxage,Hboxhobby, Hboxgender, Hboxpassword, HboxfolowingLabel,postingHBox, info , post, HboxBotton);
        VboxBot.setAlignment(Pos.TOP_CENTER);
    
        Scene scene = new Scene(VboxBot, 1000, 800);
        // Create a scene and place it in the stage
        window3.setTitle("FaceBook media demo By Ibrahim Ouarrach");
        window3.setScene(scene); // Place in scene in the stage
        window3.show();
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
