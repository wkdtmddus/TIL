### 09 PJT
### Vue를 사용한 쇼핑몰 장바구니 기능 구현
### 이번 프로젝트를 하기 위해 알아야 할 핵심 지식
- Vue3
- Fake Store API
- 쇼핑몰 구현을 위해 개발용으로 제공되는 Fake API
### 진행 순서
- 함께 개발하는 것
- 쇼핑몰 장바구니 기능을 구현합니다.
- 데이터는 Fake Store API 를 활용하여 가져옵니다.
### 쇼핑몰 장바구니 기능 구현
### 쇼핑몰 데이터
- FakeStore API 란?
  - 가상의 온라인 쇼핑몰 데이터를 무료로 제공하는 API
- FakeStoreAPI
  - Docs: https://fakestoreapi.com/docs
### 우리가 구현할 페이지
- 상품 목록 페이지
- 상품 상세 페이지
- 장바구니 페이지
### 예시
### 상품 목록 페이지
- Card 형태로 상품 정보를 출력합니다.
- 버튼 종류
  - 상세 페이지로 이동
  - 장바구니에 추가
- 장바구니에 담은 상품 목록은 Local Storage 에 저장합니다.
### 상품 상세 페이지
- 상품의 상세 정보를 출력합니다.
- 버튼 종류
  - 장바구니에 추가
### 장바구니 페이지
- 현재까지 장바구니에 담은 상품 목록을 출력합니다.
- 버튼 목록
  - 상세 페이지로 이동
  - 장바구니에서 제거