<img src="docs/images/logo.png" alt="따다" height="200" width="150" />

## 📅 **프로젝트 기간**

- 2024.10.14 ~ 2024.11.19

## 개발 관련 기술

### 📋 git 이슈/브랜치 관리

### issue

- 프론트엔드와 백엔드, 데이터는 `label`로 분류한다.
- `assignees`는 이슈 생성자가 스스로 할당한다.
- 이슈 타입

  ```markdown
  FEAT : 새로운 기능 추가
  FIX : 버그 수정
  HOTFIX : 치명적인 버그 급하게 수정
  CHORE : (코드 수정 없는) 설정 변경
  DOCS : 문서 생성 및 수정
  DESIGN : 레이아웃 구현 및 디자인 수정
  REFACTOR : 리팩토링
  REMOVE : 파일/코드 삭제
  MERGE : 브랜치 병합
  ```

- 작성 예시
  **[타입] 이슈 명**
  - [FEAT] PWA 구현
  - [DESIGN] 랜딩 페이지 레이아웃 디자인

### branch

- 프론트엔드, 백엔드, 데이터는 접두사로 **`FE/ BE/ DATA/`** 를 붙인다.
- 브랜치 생성 시, 영문은 모두 **소문자**를 사용한다.
- git flow 방식을 채용하여 dev branch로 protect한다.
- 완료 된 작업에 대하여 PR 완료 이후 해당 작업 브랜치는 삭제한다.
- 생성 예시

  **분야/타입/#이슈번호\_이슈명**

### commit

- 영문은 모두 **소문자**를 사용한다.
- 한글도 가능하다.
- 생성 예시
  - docs: TIL 생성
  - feat: PWA setting

### merge

- 영문은 모두 **소문자**를 사용한다.
- 내용은 템플릿을 사용한다.

### 👩🏻‍🔧 **기술 스택**

<div align="left">

### FE

---

#### 🚀 프레임워크 및 라이브러리

  <img src="https://img.shields.io/badge/react-61DAFB?style=for-the-badge&amp;logo=react&amp;logoColor=black" height="35">
  <img src="https://img.shields.io/badge/next.js-000000?style=for-the-badge&amp;logo=nextdotjs&amp;logoColor=white" height="35">
  <img src="https://img.shields.io/badge/TypeScript-3178C6?style=for-the-badge&amp;logo=typeScript&amp;logoColor=white" height="35"> 
  
  #### 📊 상태 관리
  <img src="https://img.shields.io/badge/zustand-FFFFFF?style=for-the-badge&amp;logo=zustand&amp;" height="35"> 
  <img src="https://img.shields.io/badge/reactquery-FF4154?style=for-the-badge&amp;logo=reactquery&amp;logoColor=white" height="35">
  
  #### 📡 데이터 요청
  <img src="https://img.shields.io/badge/Axios-5A29E4?style=for-the-badge&amp;logo=axios&amp;logoColor=white" height="35">
  
  #### 💄 스타일링
  <img src="https://img.shields.io/badge/tailwind-06B6D4?style=for-the-badge&amp;logo=tailwindcss&amp;logoColor=white" height="35"> 
  <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&amp;logo=html5&amp;logoColor=white" height="35">
  
  #### 🔧 코드 품질 관리
  <img src="https://img.shields.io/badge/eslint-4B32C3?style=for-the-badge&amp;logo=eslint&amp;logoColor=white" height="35">
  <img src="https://img.shields.io/badge/prettier-F7B93E?style=for-the-badge&amp;logo=prettier&amp;logoColor=white" height="35">

### BE

---

#### 🚀 프레임워크 및 라이브러리

<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&amp;logo=springboot&amp;logoColor=white" height="35"> 
<img src="https://img.shields.io/badge/spring_security-6DB33F?style=for-the-badge&amp;logo=springsecurity&amp;logoColor=white" height="35"> 
<img src="https://img.shields.io/badge/swagger-85EA2D?style=for-the-badge&amp;logo=swagger&amp;logoColor=white" height="35">

#### 📂 DB 및 스토리지

<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&amp;logo=mysql&amp;logoColor=white" height="35"> 
<img src="https://img.shields.io/badge/redis-FF4438?style=for-the-badge&amp;logo=redis&amp;logoColor=white" height="35"> 
<img src="https://img.shields.io/badge/aws_s3-569A31?style=for-the-badge&amp;logo=amazons3&amp;logoColor=white" height="35">

### AI

---

<img src="https://img.shields.io/badge/openai-412991?style=for-the-badge&amp;logo=openai&amp;logoColor=white" height="35">

### INFRA

---

#### 🔗 CI/CD

<img src="https://img.shields.io/badge/jenkins-D24939?style=for-the-badge&amp;logo=jenkins&amp;logoColor=white" height="35"> 
<img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&amp;logo=docker&amp;logoColor=white" height="35"> 
<img src="https://img.shields.io/badge/nginx-009639?style=for-the-badge&amp;logo=nginx&amp;logoColor=white" height="35">
<br>

## 📖 **ERD**

<img src="docs/images/ERD_1.png" alt="erd_1" height="300" width="400" />
<img src="docs/images/ERD_2.png" alt="erd_2" height="400" width="300" />

<br>

## 📖 **Architecture**

<img src="docs/images/architecture.png" alt="architecture" height="300" width="400" />

<br>

## 📖 **페이지별 기능**

### 1. 로그인

<img src="docs/images/login.PNG" alt="login" height="400" width="300" />

### 2. 프로젝트

<img src="docs/images/project_list.PNG" alt="project_list" height="300" width="300" />

### 2-1 프로젝트 정보

<img src="docs/images/project_info.PNG" alt="project_list" height="400" width="700" />

### 2-2 프로젝트 채팅 회고록

<img src="docs/images/chatting_summary.PNG" alt="chatting_summary" height="400" width="600" />

매일 자정마다 그날의 채팅을 요약및 정리해주는 회고록

### 2-3 프로젝트 채팅

<img src="docs/images/chatting.PNG" alt="chatting" height="300" width="300" />

프로젝트 별 채팅

### 2-3 챗봇

<img src="docs/images/chatbot.PNG" alt="chatting" height="300" width="300" />

jira에 대해 물어볼 수 있는 챗봇

### 2-4 전체 업무 로그

<img src="docs/images/work-log.PNG" alt="chatting" height="300" width="350" />

본인에게 할당된 이슈들을 편집 및 수정, 드래그 앤드롭 기능

### 2-4 전체 업무 로그 수정

<img src="docs/images/work-log-edit.PNG" alt="chatting" height="200" width="150" />

이슈 편집 및 수정
