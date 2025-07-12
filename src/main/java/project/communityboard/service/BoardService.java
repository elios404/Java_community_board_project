package project.communityboard.service;

import project.communityboard.entity.board.Board;
import project.communityboard.entity.user.User;

import java.util.ArrayList;
import java.util.Scanner;

public class BoardService {

    Scanner sc = new Scanner(System.in);

    public void runBoardService(Board board, User activeUser) {
        int choice = 0;
        while (choice != 6) {
            choice = selectChoice(activeUser);
            switch (choice) {
                case 1 -> board.showBoard();
                case 2 -> {
                    System.out.println("\n읽을 게시물 번호를 입력해 주세요 : ");
                    int articleNo = sc.nextInt();
                    board.readArticle(articleNo);
                }
                case 3 -> {
                    if (activeUser != null) {
                        System.out.println("\n게시글 작성을 시작합니다.");
                        board.postArticle(activeUser);
                    } else {
                        System.out.println("게시글 작성은 로그인이 필요합니다.");
                    }
                }
                case 4 -> {
                    if (activeUser != null) {
                        System.out.println("\n수정할 게시물 번호를 입력해 주세요 : ");
                        int articleNo = sc.nextInt();
                        board.editArticle(articleNo, activeUser);
                    } else {
                        System.out.println("게시글 수정은 로그인이 필요합니다.");
                    }
                }
                case 5 -> {
                    if (activeUser != null) {
                        System.out.println("\n삭제할 게시물 번호를 입력해 주세요 : ");
                        int articleNo = sc.nextInt();
                        board.deleteArticle(articleNo, activeUser);
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
