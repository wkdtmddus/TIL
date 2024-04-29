### 다양한 directive 연습하기 - 2 Lv4
목표  
- Vue.js를 활용하여 HTML 요소와 양방향 데이터 바인딩 구현하기
- Vue.js 디렉티브를 활용하여 이벤트 핸들링 및 스타일 업데이트 기능 구현하기
---
문제  
vue3이 제공하는 다양한 directive를 연습해 보고자 한다.  
주어진 코드를 활용하여 요구 사항을 만족하는 코드를 작성하시오.  

요구사항
- Vue3 CDN을 사용한다.
1. 새로운 app instance를 생성하여 아이디가 app인 container에 mount 하시오.
2. 사용자가 입력한 색상 명이 colorType 변수에 할당 될 수 있도록 양방향 바인딩 directive를 사용하시오.
3. 사용자가 입력한 글꼴 명이 fontType 변수에 할당 될 수 있도록 양방향 바인딩 directive를 사용하시오.
4. 첫 번째 p 태그의 style에 적용할 stlyeObject 변수를 만들고, 단방향 바인딩 directive를 사용하여 연결하시오.
5. 값을 모두 입력 한 후, enter를 입력하거나, 변경! 버튼을 클릭하면, changeStyle 함수가 실행되도록 이벤트 핸들러 directive를 사용하시오.
  - 단, enter를 입력 할 때도, 버튼을 클릭 할 때도, form 태그의 기본 이벤트 동작은 중단 시켜야 한다.
  - changeStyle 함수 내부에서 사용자가 입력한 colorType, fontType 값을 적절한 각각 'color', 'font-family' 키에 할당한 객체를 만든다.
  - 생성된 객체를 styleObject에 할당한다.
  - 할당이 모두 완료되면 colorType과 fontType의 값을 빈 문자열로 초기화 한다.
---
`@click.prevent`를 사용하여 이벤트 핸들링을 합니다.  
`버블링`을 막지 않았다면, `event.target.form`을 사용하여 자식 요소에 접근할 수 있습니다.
<div style="text-align: right">20240429</div>