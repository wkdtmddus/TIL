### 다양한 directive 연습하기 - 1 Lv2
목표  
- Vue.js를 이용하여 HTML 요소와 데이터 간의 양방향 바인딩 구현하기
- Vue.js 디렉티브를 사용하여 DOM 요소와 데이터 간의 상호작용 구현하기
---
문제  
vue3이 제공하는 다양한 directive를 연습해 보고자 한다.  
주어진 코드를 활용하여 요구 사항을 만족하는 코드를 작성하시오.  

요구사항
- Vue3 CDN을 사용한다.
1. 새로운 app instance를 생성하여 아이디가 app인 container에 mount 하시오.
2. 사용자가 입력한 값이 colorClass 변수에 할당 될 수 있도록 양방향 바인딩 directive를 사용하시오.
3. colorClass 변수의 값이 첫 번째 p 태그의 class가 될 수 있도록 단방향 바인딩 directive를 사용하시오.
---
`v-on`은 `@`로 대체할 수 있습니다.  
`input` event가 발생하면 현재 target에서 value를 가져옵니다.
<div style="text-align: right">20240429</div>