package project.communityboard.entity.user;

public class Member {

    private static int globalUserNo = 1;

    private int userNo;
    private String userID;
    private String userName;
    private String userPassword;

    public Member(String userID, String userName, String userPassword) {
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;

        this.userNo = globalUserNo++;
    }

    public int getUserNo() {
        return userNo;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "userNo=" + userNo +
                ", userID='" + userID + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}
