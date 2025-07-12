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

    private Optional<Article> getPostByArticleNo(int articleNo) {
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

    public void postArticle(User user) {
        System.out.print("제목을 입력하세요 : ");
        String title = this.sc.nextLine();
        System.out.print("내용을 입력하세요 : ");
        String content = this.sc.nextLine();

        articles.add(new Article(title, content, user));
    }

    public void readArticle(int articleNo) {
        Article searchArticle = getPostByArticleNo(articleNo).orElse(null);
        if (searchArticle != null) {
            searchArticle.setViews(searchArticle.getViews() + 1);
            System.out.println(searchArticle.toString());
        } else {
            System.out.println("없는 게시글 번호입니다!");
        }
    }

    public void editArticle(int articleNo, User user) {
        Article editArticle = getPostByArticleNo(articleNo).orElse(null);
        if (editArticle != null) {
            if (!editArticle.getUserID().equals(user.getUserID())) {
                System.out.println("글 작성자가 아닙니다. 수정 권한이 없습니다.");
            } else {
                String oldTitle = editArticle.getTitle();
                System.out.println("현재 제목 : " + oldTitle);
                System.out.print("새로운 제목을 입력해 주세요 ('No' 입력 시 제목 유지) :");
                String newTitle = sc.nextLine();
                editArticle.setTitle((newTitle.equals("No")) ? oldTitle : newTitle);

                String oldContent = editArticle.getContent();
                System.out.println("현재 내용 : " + oldContent);
                System.out.print("새로운 내용을 입력해 주세요 ('No' 입력 시 내용 유지) :");
                String newContent = sc.nextLine();
                editArticle.setContent((newContent.equals("No")) ? oldContent : newContent);

                editArticle.setUpdateTime(LocalDateTime.now());
            }
        } else {
            System.out.println("없는 게시글 번호입니다!");
        }
    }

    public void deleteArticle(int articleNo, User user) {
        Article deleteArticle = getPostByArticleNo(articleNo).orElse(null);
        if (deleteArticle != null) {
            if (!deleteArticle.getUserID().equals(user.getUserID())) {
                System.out.println("글 작성자가 아닙니다. 삭제 권한이 없습니다.");
            } else {
                articles.remove(deleteArticle);
            }
        } else {
            System.out.println("없는 게시글 번호입니다!");
        }
    }


}
