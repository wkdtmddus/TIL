### app mount 연습 Lv2
목표  
- Vue3 CDN을 활용하여 다중 Vue 앱 인스턴스를 생성하고 관리할 수 있다. 
- ref 함수를 사용하여 반응성을 가진 참조 변수를 선언하고 초기화할 수 있다. 
- Vue 템플릿을 수정하여 동적으로 데이터를 표시할 수 있다.
---
문제  
Vue3 기본 동작 원리를 연습하고자 한다.  
요구 사항을 만족하는 코드를 작성하시오.  

요구사항
- Vue3 CDN을 사용한다.
1. Vue 앱 인스턴스를 생성하여 firstApp에 할당한다.
2. 생성한 앱을 아이디가 firstApp인 container에 mount 한다.
3. title 변수에 '첫번째 앱' 문자열을 할당한다.
  - 단, ref 함수를 사용하여 반응성을 가지는 참조 변수로 만들어야 한다.
4. template을 수정하여 적절하게 표기 될 수 있도록 작성한다.
5. 두번째, 세번째 앱도 동일하게 작성한다.
  - 단, title 변수에는 각기 다른 문자열을 할당한다.
---
`mount`를 이용하여 이전 `document.querySelector`처럼 사용할 수 있습니다.
<div style="text-align: right">20240425</div>