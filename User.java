
//Ibrahim Ouarrach
// this class is for user that we will store all the information in
import java.io.Serializable;

public class User  implements Serializable {
    private static final long serialVersionUID = 1L;
    private String age;
    private String firstName;
    private String lastName;
    private String gender;
    private String hobby;
    private String pasword;
    private String userName;
    private String profilePicture;
    private String[] folowing = new String[5];
    private int indexForTheLastelemtAddedInFollowArray = 0;
    private String[] posts = new String[5];
    private int indexForPosting = 0;
    private String OuputForPost = "";

    public User()
    {
        for(int i =0; i<5; i++)
        {
            folowing[i] ="-";
        }
        for(int i =0; i<5; i++)
        {
            posts[i] ="";
        }
    }

    public String getAge() {
        return age;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getGender() {
        return gender;
    }
    public String getHobby() {
        return hobby;
    }
    public String getLastName() {
        return lastName;
    }
    public String getUserName() {
        return userName;
    }
    public String getPasword() {
        return pasword;
    }
    public String getProfilePicture() {
        return profilePicture;
    }
    public String[] getFolowing() {
        return folowing;
    }
    public int getIndexForTheLastelemtAddedInFollowArray() {
        return indexForTheLastelemtAddedInFollowArray;
    }
    public String[] getPosts() {
        return posts;
    }
    public int getIndexForPosting() {
        return indexForPosting;
    }
    public void setOuputForPost(String ouputForPost) {
        OuputForPost = ouputForPost;
    }

    public void setAge(String age) {
        this.age = age;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPasword(String pasword) {
        this.pasword = pasword;
    }
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
    public void setFolowing(String[] folowing) {
        this.folowing = folowing;
    }
    public void setIndexForTheLastelemtAddedInFollowArray(int indexForTheLastelemtAddedInFollowArray) {
        this.indexForTheLastelemtAddedInFollowArray = indexForTheLastelemtAddedInFollowArray;
    }
    public void setPosts(String[] posts) {
        this.posts = posts;
    }
    public void setIndexForPosting(int indexForPosting) {
        this.indexForPosting = indexForPosting;
    }
    public String getOuputForPost() {
        return OuputForPost;
    }

}
