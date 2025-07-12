package project.communityboard.service;

import project.communityboard.entity.article.Article;
import project.communityboard.entity.board.Board;
import project.communityboard.entity.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class BoardService {

    Scanner sc = new Scanner(System.in);

    public void runBoardService(Board board, User activeUser) {
        int choice = 0;
        while (choice != 6) {
            choice = selectChoice(activeUser);
            switch (choice) {
                case 1 -> board.showBoard(); // 전체 게시글 목록 확인

                case 2 -> { // 게시글 보기
                    System.out.println("\n읽을 게시물 번호를 입력해 주세요 : ");
                    int articleNo = sc.nextInt();

                    Article searchArticle = board.getPostByArticleNo(articleNo).orElse(null);
                    if (searchArticle != null) {
                        board.readArticle(searchArticle);
                    } else {
                        System.out.println("없는 게시글 번호입니다!");
                    }
                }

                case 3 -> { // 게시글 작성
                    if (activeUser != null) {
                        System.out.println("\n게시글 작성을 시작합니다.");
                        sc.nextLine();
                        System.out.print("제목을 입력하세요 : ");
                        String title = sc.nextLine();
                        System.out.print("내용을 입력하세요 : ");
                        String content = sc.nextLine();
                        board.postArticle(title, content,activeUser);
                    } else {
                        System.out.println("게시글 작성은 로그인이 필요합니다.");
                    }
                }

                case 4 -> { // 게시물 수정
                    if (activeUser != null) { // 로그인한 상태
                        System.out.println("\n수정할 게시물 번호를 입력해 주세요 : ");
                        int articleNo = sc.nextInt();
                        sc.nextLine();
                        Article editArticle = board.getPostByArticleNo(articleNo).orElse(null); // 수정할 게시물 찾기

                        if (editArticle != null) { // 게시물이 있다면
                            if (!editArticle.getUserID().equals(activeUser.getUserID())) { // 글 작성자가 아니라면
                                System.out.println("글 작성자가 아닙니다. 수정 권한이 없습니다.");
                            } else { // 글 작성자라면
                                String oldTitle = editArticle.getTitle();
                                System.out.println("현재 제목 : " + oldTitle);
                                System.out.print("새로운 제목을 입력해 주세요 ('No' 입력 시 제목 유지) :");
                                String newTitle = sc.nextLine();
                                String title = ((newTitle.equals("No")) ? oldTitle : newTitle);

                                String oldContent = editArticle.getContent();
                                System.out.println("현재 내용 : " + oldContent);
                                System.out.print("새로운 내용을 입력해 주세요 ('No' 입력 시 내용 유지) :");
                                String newContent = sc.nextLine();
                                String content = ((newContent.equals("No")) ? oldContent : newContent);

                                board.editArticle(editArticle, title, content);
                            }
                        } else {
                            System.out.println("없는 게시글 번호입니다!");
                        }

                    } else { // 로그인 되어있지 않는 상태
                        System.out.println("게시글 수정은 로그인이 필요합니다.");
                    }
                }
                case 5 -> {
                    if (activeUser != null) {
                        System.out.println("\n삭제할 게시물 번호를 입력해 주세요 : ");
                        int articleNo = sc.nextInt();
                        sc.nextLine();

                        Article deleteArticle = board.getPostByArticleNo(articleNo).orElse(null);

                        if (deleteArticle != null) {
                            if (!deleteArticle.getUserID().equals(activeUser.getUserID())) {
                                System.out.println("글 작성자가 아닙니다. 삭제 권한이 없습니다.");
                            } else {
                                board.deleteArticle(deleteArticle);
                            }
                        } else {
                            System.out.println("없는 게시글 번호입니다!");
                        }

                    } else {
                        System.out.println("게시글 삭제는 로그인이 필요합니다.");
                    }
                }
            }

        }
    }

    private int selectChoice(User activeUser) {
        System.out.println("\n게시판 서비스에 온 것을 환영합니다! 현재 사용자 : " + ((activeUser != null)? activeUser.getUserName() : "로그인 없음"));
        System.out.println("1. 전체 게시글 보기 \n2. 게시글 읽기 \n3. 게시글 작성 \n4. 게시글 수정 \n5. 게시글 삭제 \n6. 메인 메뉴로 나가기");
        System.out.print("진행하려는 메뉴 번호를 숫자로 입력해 주세요 : ");

        return sc.nextInt();
    }

}
