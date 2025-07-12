package project.communityboard.entity.board;

import project.communityboard.entity.article.Article;
import project.communityboard.entity.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Board {
    private ArrayList<Article> articles;
    private String nameOfBoard;
    private Scanner sc;

    public Board(String nameOfBoard) {
        articles = new ArrayList<>();
        this.nameOfBoard = nameOfBoard;
        sc = new Scanner(System.in);
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public String getNameOfBoard() {
        return nameOfBoard;
    }

    public Optional<Article> getPostByArticleNo(int articleNo) {
        return articles.stream()
                .filter(article -> article.getArticleNo() == articleNo)
                .findFirst();
    }

    public void showBoard() {
        System.out.println("게시판 이름 : " + nameOfBoard);
        System.out.println("총 게시글 갯수 : " + articles.size());
        for (Article article : articles) {
            article.showArticle();
        }
    }

    public void postArticle(String title, String content, User user) {
        articles.add(new Article(title, content, user));
    }

    public void readArticle(Article article) {
        article.setViews(article.getViews() + 1);
        System.out.println(article.toString());
    }

    public void editArticle(Article article, String title, String content) {
        article.setUpdateTime(LocalDateTime.now());
        article.setTitle(title);
        article.setContent(content);
    }

    public void deleteArticle(Article article) {
        articles.remove(article);
    }


}
