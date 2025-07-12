package project.communityboard.entity.article;
import project.communityboard.entity.user.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Article {

    private static int globalArticleNo = 1;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss");

    private int articleNo;
    private String title;
    private String content;
    private String userID;
    private String userName;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private int views = 0;

    public Article(String title, String content, User user) {
        this.title = title;
        this.content = content;

        this.articleNo = globalArticleNo++;
        this.userID = user.getUserID();
        this.userName = user.getUserName();
        this.createTime = LocalDateTime.now();
        this.updateTime = null;
    }

    public int getArticleNo() {
        return articleNo;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public int getViews() {
        return views;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public void showArticle() {
        System.out.println("글 번호 : " + articleNo + " |" +
                        " 글 제목 : " + title + " |" +
                        " 글 작성자 : " + userName + " |" +
                        " 작성 일자 : " + createTime.format(formatter));
    }

    @Override
    public String toString() {
        return "제목 : " + title + "\n" +
                "글 작성자 : " + userName + "\n" +
                "작성일자 : " + createTime.format(formatter) + "\n" +
                "수정일자 : " + ((updateTime != null) ? updateTime.format(formatter) : " ") + "\n" +
                "조회수 : " + views + "\n\n" +
                "내용 : " + content + "\n" ;
    }
}
