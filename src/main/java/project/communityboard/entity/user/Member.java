package project.communityboard.entity.user;

public class Member {

    private static int globalUserNo = 1;

    private int userNo;
    private String userID;
    private String userName;
    private String userPassword;

    public Member(Builder builder) {
        this.userID = builder.userID;
        this.userName = builder.userName;
        this.userPassword = builder.userPassword;

        this.userNo = globalUserNo++;
    }

    public static class Builder {
        private String userID;
        private String userName;
        private String userPassword;

        public Builder(String userID, String userPassword) {
            this.userID = userID;
            this.userPassword = userPassword;
        }

        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public Member build() {
            return new Member(this);
        }
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
