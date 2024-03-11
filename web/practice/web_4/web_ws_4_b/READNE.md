### 여행 블로그 만들기-목록 화면 구성 Lv2
목표  
  - 학습 주제
    - CSS Flexible Box Layout
    - Bootstrap Component
    - Bootstrap Grip System
  - 학습 목표
    - Bootstrap Flexbox 사용법을 이해한다.
    - Bootstrap Flexbox, Grid System을 이해하고 명세에 맞춰 코드를 작성할 수 있다.
  - 학습 개념
    - CDN
      - CDN(Content Delivery Network)은 많은 장소에 걸쳐 분산된 서버들의 그룹입니다.
    - bootstrap
      - 부트스트랩은 웹사이트를 쉽게 만들 수 있게 도와주는 CSS, JS 프레임워크입니다.
    - breakpoints
      - Breakpoints는 Bootstrap의 반응형 레이아웃이 뷰포트 크기 또는 기기에서 어떻게 작동할지 결정하는 사용자가 정의 가능한 넓이입니다.
  - 학습 방향
    - Bootstrap 공식 문서를 읽어보며 다양한 component를 확인한다.
    - HTML문서에 CDN을 적용시키고, CDN을 통해 얻게 되는 CSS파일의 내용을 확인한다.
---
문제  
여행 블로그의 화면을 구성하고자 한다.  
먼저, Bootstrap을 사용하여 Navbar부터 만들어봅시다.  
아래 요구사항을 충족하는 코드를 작성하시오.  

요구사항  
- 여행지 목록은 그리드 컬럼으로 구성되어야 한다.
  - Bootstrap grid-card를 참고한다.
- 여행지 목록의 각 항목은 이미지, 목적지 제목, 세부정보 그리고 더보기 버튼을 포함하고, 그리드 컬럼을 적절히 활용하여 배치해야 한다.
- 여행지 목록은 여러 줄에 걸쳐 표시될 수 있어야 한다.
- 디바이스의 크기에 따라 서로 다른 레이아웃이 구성되도록 한다.
- 레이아웃 구성은 자유롭게 작성한다.
---
`그리드`를 이용해서 카드의 배치를 정할 수 있습니다.
<div style="text-align: right">20240311</div>