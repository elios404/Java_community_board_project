package project.communityboard.service;

import project.communityboard.entity.board.Board;
import project.communityboard.entity.user.Member;

import java.util.ArrayList;
import java.util.Scanner;

public class Service {

    private Member activeUser = null;
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Service service = new Service();
        BoardService boardService = new BoardService();
        UserService userService = new UserService();
        ArrayList<Member> users = new ArrayList<>();

        ArrayList<Board> boards = new ArrayList<>();
        boards.add(new Board("Car"));
        boards.add(new Board("Computer"));

        int menu = 0;

        while(menu!= 4){
            menu = service.selectMenu(boards);
            switch (menu) {
                case 1 -> {
                    System.out.print("원하는 게시판 번호를 입력해주세요 : ");
                    int boardIndex = service.sc.nextInt() - 1;
                    boardService.runBoardService(boards.get(boardIndex),service.activeUser);
                }
                case 2 -> users.add(userService.registerUser());
                case 3 -> service.activeUser = userService.loginUser(users);
            }
        }
    }

    private int selectMenu(ArrayList<Board> boards) {
        System.out.println("\n게시판 서비스에 온 것을 환영합니다! 현재 로그인 : " + ((activeUser != null)? activeUser.getUserName() : "로그인 없음"));
        System.out.println("---- 게시판 목록 ----");
        for (int i = 0; i < boards.size(); i++) {
            Board board = boards.get(i);
            System.out.println(i+1 + " . " + board.getNameOfBoard());
        }
        System.out.println("---- 메뉴 목록 ----");
        System.out.println("1. 게시판 선택 \n2. 사용자 회원가입\n3. 사용자 로그인\n4. 게시판 종료");
        System.out.print("진행하려는 메뉴 번호를 숫자로 입력해 주세요 : ");

        return sc.nextInt();
    }
}
