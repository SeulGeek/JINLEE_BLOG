# JINLEE_BLOG
프로그래밍 관련 글과 개인 포트폴리오, 일상 글을 올려두기 위해 '최범균의 JSP 2.3 웹 프로그래밍'을 예시를 보며 만들어본 개인 블로그입니다. 협업에 필요한 요구사항정의서, 화면정의서 등 총 8개의 문서 작업 후, 이를 토대로 만들었습니다. 참고로, 문서는 개인 기술 블로그(http://13.125.248.64)에서 볼 수 있습니다. 

## 사용 기술
* 언어 : JSP, JAVA
* 운영체제 : Linux(Ubuntu)
* 데이터베이스 : MySQL
* 웹 어플리케이션 서버 : Tomcat8
* API/라이브러리 :  disqus 댓글, 네이버 에디터
* MVC 패턴 적용

## 주요 기능
### 관리자
<img src="https://user-images.githubusercontent.com/49344118/61848646-c4919900-aee9-11e9-9e40-3477ec7290e8.png" height="400">

* 관리자 로그인
* 게시글 등록, 수정, 삭제
* 개인정보수정

### 일반 사용자
<img src="https://user-images.githubusercontent.com/49344118/61849098-2b638200-aeeb-11e9-8891-2b2ef2ddf437.png" height="400">

* 관리자가 등록한 게시글 보기
* 검색어를 통한 게시글 제목 또는 본문 검색
* 태그 검색

### MVC 패턴
### Model
* admin : DB에서 관리자 정보를 수정하거나 가져올 시 사용
<img src="https://user-images.githubusercontent.com/49344118/62322716-a2e37380-b4e0-11e9-98e9-fca451ebb446.png" width="150">

* board.cate : DB에서 게시판, 카테고리 정보를 가져올 시 사용
<img src="https://user-images.githubusercontent.com/49344118/62324180-dffd3500-b4e3-11e9-8569-9e3741f02692.png" width="150">

* post : DB에 게시글 등록, 수정, 삭제, 보기 시 사용
<img src="https://user-images.githubusercontent.com/49344118/62323882-3d44b680-b4e3-11e9-80e6-ab3b6c48799f.png" width="150">

* tag : DB에 태그 등록, 삭제, 보기 시 사용
<img src="https://user-images.githubusercontent.com/49344118/62323896-49c90f00-b4e3-11e9-96de-c674bc0527a9.png" width="150">

### View
<img src="https://user-images.githubusercontent.com/49344118/62321080-c6a4ba80-b4dc-11e9-841b-8ef5795befad.png" width="200">

### Controller
* auth : 로그인, 로그아웃 시 사용
<img src="https://user-images.githubusercontent.com/49344118/62322783-cb6b6d80-b4e0-11e9-964c-40ca1262cda9.png" width="200">

* admin : 개인정보수정 시 사용
<img src="https://user-images.githubusercontent.com/49344118/62323548-80eaf080-b4e2-11e9-8db6-89296da26aa8.png" width="200">

* post : 게시글 등록, 수정, 삭제, 보기 기능이 필요할 때 사용
<img src="https://user-images.githubusercontent.com/49344118/62322957-2e5d0480-b4e1-11e9-889f-0036ac529744.png" width="200">

* mvc
<img src="https://user-images.githubusercontent.com/49344118/62323044-5e0c0c80-b4e1-11e9-9ef4-d9ceeaefaeae.png" width="200">

