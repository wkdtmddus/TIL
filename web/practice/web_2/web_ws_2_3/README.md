### 도서 관리 서비스-position Lv3
목표  
- CSS Box-model(content, padding, border, margin)을 이해한다.
- CSS Display를 활용해 Layout Positioning을 이해하고 연습한다.
---
문제  
도서 관리 서비스의 베스트 셀러와 신간 목록을 구분짓고자 한다.  
position을 활용하여 개선된 레이아웃을 구성하시오.  
요구 사항과 제공된 스켈레톤 코드, mdn 문서를 참고하여 코드를 작성하시오.  

요구사항  
- mdn 문서 참고
  - https://developer.mozilla.org/ko/docs/Web/CSS/position
- h1태그 제목 아래 베스트 셀러 목록 섹션을 생성한다.
- 2번째 도서 카드 아래 신간 도서 목록 섹션을 생성한다.
- 생성된 두 섹션은 일반적인 상황에서는 제자리를 지키고 있으나, 화면의 상단 끝에 다다르게 되면, 다음 섹션을 만나기 전까지 스크롤을 따라 이동하도록 위치를 설정한다.
  - 섹션의 배경 색, 글씨 크기 등은 자유롭게 구성한다.
- 주어진 CSS 속성을 수정하여 화면 좌측 하단에 고정된 버튼이 만들어지도록 한다.
  - 버튼은 TOP글자로 된 a태그를 자식으로 가지고 있다.
  - a태그의 href 속성은 #으로 작성한다.
---
`position`으로 해당 요소의 위치 성격을 조정할 수 있다.  
헤더에 `<link rel="stylesheet" href="style.css">`를 통해서 css 파일을 불러올 수 있다.
<div style="text-align: right">20240307</div>