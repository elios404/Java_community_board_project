package project.communityboard.service;

import project.communityboard.entity.user.Member;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class UserService {

    Scanner sc = new Scanner(System.in);

    public Member registerUser() {
        System.out.println("\n회원 가입 공간입니다!");

        System.out.print("아이디를 입력해 주세요 : ");
        String userID = sc.nextLine();

        System.out.print("유저 닉네임을 입력해 주세요 : ");
        String userName = sc.nextLine();

        System.out.print("유저 비밀번호를 입력해 주세요 : ");
        String userPassword = sc.nextLine();

        return new Member.Builder(userID, userPassword).userName(userName).build();
    }

    public Member loginUser(ArrayList<Member> users) {
        System.out.println("\n로그인 공간입니다!");

        System.out.print("\n회원 아이디를 입력해 주세요 : ");
        String userIdInput = sc.nextLine();

        Member user = getUserByUserID(users, userIdInput).orElse(null);
        if (user != null) {
            System.out.print("비밀번호를 입력해 주세요 : ");
            String userPasswordInput = sc.nextLine();
            if (userPasswordInput.equals(user.getUserPassword())) {
                return user;
            } else {
                System.out.println("비밀번호가 잘못되었습니다! 다시 시도해 주세요!");
                return null;
            }
        } else {
            System.out.println("존재하지 않는 사용자 입니다!");
            return null;
        }
    }

    private Optional<Member> getUserByUserID(ArrayList<Member> users, String userID) {
        return users.stream()
                .filter(user -> user.getUserID().equals(userID))
                .findFirst();
    }
}
