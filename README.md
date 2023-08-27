# Go On A Trip

<div align="right">

![GitHub language count](https://img.shields.io/github/languages/count/SSAFY9-GOAT/Go_On_A_Trip-server)
![GitHub top language](https://img.shields.io/github/languages/top/SSAFY9-GOAT/Go_On_A_Trip-server)
![GitHub top language](https://img.shields.io/github/commit-activity/w/SSAFY9-GOAT/Go_On_A_Trip-server)
![GitHub top language](https://img.shields.io/github/last-commit/SSAFY9-GOAT/Go_On_A_Trip-server)

</div>

# 팀원

<table align="center">
    <tr align="center">
        <td style="min-width: 150px;"><a href="https://github.com/leeyr0412">
            <img src="https://avatars.githubusercontent.com/u/64480162?v=4?s=100" width="200px;" alt="leeyr0412"/><br />
            <sub><b>이예리</b></sub></a>  
            <br />💻
        </td>
        <td style="min-width: 150px;"><a href="https://github.com/DongHyun22">
            <img src="https://avatars.githubusercontent.com/u/79788971?v=4?s=100" width="200px;" alt="DongHyun22"/><br />
            <sub><b>김동현</b></sub></a>
            <br />💻
        </td>
    </tr>
</table>

## ⚒️ 화면 구성

### 메인 화면

- 홈페이지 메인 화면 입니다.
- 메인페이지 하단에서 실시간 트래픽을 백트래킹 알고리즘을 통해 분석하여 나이, 연령대 별 가장 인기있는 여행지를 보여줍니다.
  ![main.png](display%2Fmain.png)
  ![main2.PNG](display%2Fmain2.PNG)

  // TODO: 2023-08-27 (027) 메인 gif 만들기

### 회원관리

#### 로그인

- 로그인 페이지 입니다.
- 유효하지 않은 값이 입력되면 '아이디 혹은 비밀번호 확인 후 다시 로그인하세요'라는 경고창이 나타납니다.
  ![login.png](display%2Flogin.png)
- 로그인이 완료되면 헤더 부분에 로그인/회원가입 버튼 대신 마이페이지와 로그아웃이 나타납니다.

  // TODO: 2023-08-27 (027) 네이버 로그인

#### 회원가입 화면

- 회원가입 페이지 입니다.
- 조건에 맞지 않는 값을 입력했을 경우 가입이 되지 않습니다.
- 회원가입이 완료되면 로그인 페이지로 이동됩니다.
  ![signup1.png](display%2Fsignup1.png)

#### 아이디/비밀번호 찾기

// TODO: 2023-08-27 (027) 비밀번호 찾기 gif

- 로그인 페이지에서 아이디찾기, 비밀번호 찾기를 누르면 모달창이 나타납니다.
- 아이디는 전화번호와 이메일를 이용해 찾을 수 있습니다.
- 비밀번호는 아이디와 전화번호, 이메일을 이용해 찾을 수 있습니다.
  ![findId.png](display%2FfindId.png)

- 아이디/비밀번호 찾기 결과창입니다.
- 찾은 정보와 로그인 하기로 연결되는 버튼이 있습니다.
  ![findId2.png](display%2FfindId2.png)

#### 마이페이지 조회

- 마이페이지 화면입니다.
- 로그인 한 유저가 본인의 정보를 확인할 수 있습니다.
  ![mypage.png](display%2Fmypage.png)
- 마이페이지에서 게시물 관련 메뉴를 클릭하면 사용자가 작성하거나 좋아요를 누른 게시물만 확인할 수 있습니다.
- 마이페이지에서 내가 등록한 핫플레이스를 확인할 수 있습니다.
- ![mypage_hotplace.png](display%2Fmypage_hotplace.png)

#### 회원정보 수정

- 마이페이지 화면에서 사이드바를 클릭하면 각 정보를 수정할 수 있습니다.
- 비밀번호가 일치하지 않거나 유효한 값이 아니면 경고창을 띄웁니다.
- 비밀번호가 변경되면 로그아웃되고 다시 로그인하라는 경고창을 띄웁니다.
  ![chagePw.png](display%2FchagePw.png)

#### 회원 탈퇴

- 회원 탈퇴 클릭 시 비밀번호를 입력받습니다.
- ![memberout.png](display%2Fmemberout.png)탈퇴하면 메인 세션이 지워지고 메인페이지로 이동합니다.

### 관광지 조회 화면

- 관광지 조회 화면입니다.
- 지도에 관광지 위치에 마크가 찍히고 사진과 함께 설명이 출력됩니다.
  ![article1.png](display%2Farticle1.png)
  ![article2.png](display%2Farticle2.png)
- 결과얻기 버튼을 클릭하면 왼쪽 사이트바가 나타나 목록을 확인할 수 있습니다.

### 게시판 구현

- 자유게시판과 공지사항 게시판 입니다.

#### 자유게시판

  ![board.png](display%2Fboard.png)
- 자유게시판을 클릭하면 자유게시판 목록이 출력됩니다.
- 관리자 권한이 없어도 모든 이용자가 게시글 작성, 읽기 권한이 있습니다.
- 이용자는 자신이 쓴 글에대한 게시글 수정, 삭제 권한이 있습니다.
- 관리자는 모든 글에대한 삭제 권한이 있습니다.

#### 공지사항

- 관리자 계정이 아니면 공지 등록 버튼이 보이지 않고 목록만 출력됩니다.
  ![notion1.png](display%2Fnotion1.png)
- 관리자 계정으로 로그인 하면 버튼이 활성화 됩니다.
  ![notion2.png](display%2Fnotion2.png)
- 관리자가 공지사항 글 봤을 때 수정과 삭제 버튼이 나타납니다.
  ![viewnotion.png](display%2Fviewnotion.png)
- 관리자는 수정과 삭제 등록을 할 수 있지만 일반 사용자는 읽기만 가능합니다.

#### 핫플레이스

- 핫플레이스 목록을 확인할 수 있는 페이지입니다.
- 사용자는 관광지 목록에서 방문했던 핫플레이스 등록을 할 수 있습니다.
  ![hotplace_main.png](display%2Fhotplace_main.png)
  ![hotplace.png](display%2Fhotplace.png)

#### 여행계획

![plan1.png](display%2Fplan1.png)
![tripPlan1.png](display%2FtripPlan1.png)
![tripPlan2.png](display%2FtripPlan2.png)

## ⚒️ 기능 목록

[기능 목록 명세서](docs%2FREADME.md)

## ⚒️ 데이터베이스

[SCHEMA](docs%2Fsql%2Fschema.sql)

[//]: # "[ERD](https://www.erdcloud.com/d/rjTmz95cMnPXerZpW)"
[//]: # "![EnjoyTrip.png](display%2FEnjoyTrip.png)"

## ✅ 커밋 컨벤션

| 제목        | 내용                                                                             |
| ----------- | -------------------------------------------------------------------------------- |
| init        | 작업 세팅 커밋 (패키지 설치 등)                                                  |
| feat        | 새로운 기능을 추가할 경우                                                        |
| style       | 기능에 영향을 주지 않는 커밋, 코드 순서, css등의 포맷에 관한 커밋                |
| fix         | 버그를 고친 경우                                                                 |
| refactor    | 프로덕션 코드 리팩토링                                                           |
| test        | 테스트 코드 작성                                                                 |
| docs        | 문서를 수정한 경우, 파일 삭제, 파일명 수정 등 ex) README.md                      |
| chore       | 빌드 테스트 업데이트, 패키지 매니저를 설정하는 경우, 주석 추가, 자잘한 문서 수정 |
| code review | 코드 리뷰 반영                                                                   |

---

[//]: #
[//]: # "## 📁 폴더 구조"
