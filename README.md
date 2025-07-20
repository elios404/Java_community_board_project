# Java Community Board Project

이 프로젝트는 Java와 Gradle을 사용하여 개발된 간단한 콘솔 기반 커뮤니티 게시판 애플리케이션입니다. 사용자는 게시글을 작성, 조회, 수정, 삭제할 수 있습니다.

## 🌟 주요 기능
- 회원 관리: 사용자 등록 및 정보 관리

- 게시판 관리: 여러 종류의 게시판 생성 및 관리

- 게시글 CRUD: 게시글 작성, 조회, 수정, 삭제 기능

- 콘솔 기반 인터페이스: 터미널 환경에서 모든 기능을 제어

## 🛠️ 기술 스택
- 언어: Java

- 빌드 도구: Gradle

- 테스트: JUnit (테스트 코드 구조 기반)

- 의존성 관리: build.gradle 파일 참조

📁 프로젝트 구조
```
.
├── src
│   ├── main
│   │   └── java
│   │       └── project
│   │           └── communityboard
│   │               ├── entity       # 데이터 모델 (도메인) 클래스
│   │               │   ├── article
│   │               │   │   └── Article.java
│   │               │   ├── board
│   │               │   │   └── Board.java
│   │               │   └── user
│   │               │       └── Member.java
│   │               └── service      # 비즈니스 로직
│   │                   ├── BoardService.java
│   │                   ├── Service.java       # main 메서드 있는 서비스 자바 파일
│   │                   └── UserService.java
│   └── test
│       └── java                     # 단위 테스트 코드
└── build.gradle                     # 프로젝트 의존성 및 빌드 설정
```
## 📊 다이어그램 (Diagrams)
다이어그램은 프로젝트의 구조와 흐름을 시각적으로 이해하는 데 도움을 줍니다.

### 1. 유스케이스 다이어그램 (Use Case Diagram)

사용자(Member)가 시스템과 상호작용하는 시나리오를 보여줍니다.

```
graph TD
    actor Member
    rectangle "커뮤니티 게시판 시스템" {
    usecase "회원가입" as UC1
    usecase "로그인" as UC2
    usecase "게시글 작성" as UC3
    usecase "게시글 조회" as UC4
    usecase "게시글 수정" as UC5
    usecase "게시글 삭제" as UC6
    }
    
    Member --|> UC1
    Member --|> UC2
    Member --|> UC3
    Member --|> UC4
    Member --|> UC5
    Member --|> UC6
```

### 2. 클래스 다이어그램 (Class Diagram)
프로젝트의 주요 클래스와 그들 간의 관계를 나타냅니다.

```
classDiagram
    class Member {
    -Long id
    -String username
    -String password
    +List~Article~ articles
    }
    
    class Board {
        -Long id
        -String name
        +List~Article~ articles
    }
    
    class Article {
        -Long id
        -String title
        -String content
        -LocalDateTime createdAt
        +Member member
        +Board board
    }
    
    class UserService {
        +void register(Member member)
        +Member findMember(Long id)
    }
    
    class BoardService {
        +void writeArticle(Article article)
        +Article findArticle(Long id)
        +void updateArticle(Long id, String title, String content)
        +void deleteArticle(Long id)
    }
    
    Member "1" -- "0..*" Article : "작성"
    Board "1" -- "0..*" Article : "포함"
    UserService ..> Member : "사용"
    BoardService ..> Article : "사용"
    BoardService ..> Board : "사용"
```

### 3. 시퀀스 다이어그램 (Sequence Diagram) - 게시글 작성
여러 사용 흐름 중 사용자가 새로운 게시글을 작성할 때의 객체 간 상호작용 흐름을 보여줍니다.

```
sequenceDiagram
    actor User
    participant MainApp as "애플리케이션"
    participant BoardService as "BoardService"
    participant Article as "Article 객체"
    participant Member as "Member 객체"
    
    User->>MainApp: 게시글 작성 요청 (제목, 내용)
    MainApp->>BoardService: writeArticle(articleData)
    BoardService->>Article: new Article(title, content)
    BoardService->>Member: findAuthenticatedMember()
    Member-->>BoardService: member
    BoardService->>Article: setMember(member)
    Article-->>BoardService:
    BoardService->>BoardService: saveArticle(article)
    BoardService-->>MainApp: 작성 완료 응답
    MainApp-->>User: "게시글이 성공적으로 작성되었습니다."
```
## 🚀 시작하기 (Getting Started)
사전 요구사항
- Java Development Kit (JDK) 11 이상

- Gradle

### 설치 및 실행
1. 프로젝트 클론:

```
git clone git@github.com:elios404/Java_community_board_project.git
cd [Project Directory]
```

2. 애플리케이션 실행:

아래 파일을 IDE로 실행하기
```
src/main/java/project/communityboard/service/Service.java 
```

📝 사용 방법 (How to Use)
애플리케이션을 실행하면 콘솔 메뉴가 나타납니다. 메뉴의 지시에 따라 회원가입, 로그인, 게시글 작성 등의 기능을 사용할 수 있습니다.

1. 프로그램 시작

2. 회원가입 또는 로그인 선택

3. 게시판 선택

4. 게시글 목록 보기, 글 작성, 글 읽기 등 원하는 작업 선택
