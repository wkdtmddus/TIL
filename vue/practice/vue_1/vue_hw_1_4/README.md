### Vue3 다양한 데이터 렌더링 Lv4
목표  
- Vue3 CDN을 사용하여 Vue.js 앱을 초기화하고 데이터를 관리할 수 있다.
- Vue 앱에서 데이터를 템플릿에 바인딩하여 화면에 표시할 수 있다.
- 사용자 이벤트에 대응하여 함수를 호출하고 데이터를 업데이트할 수 있다.
---
문제  
CDN을 활용하여 Vue3 기본 문법을 연습하고자 한다.  
주어진 코드를 활용하여 요구사항을 만족하는 코드를 작성하시오.  

요구사항
- Vue3 CDN을 사용한다.
1. 아이디가 app인 container 요소에 새로운 앱 인스턴스를 mount 하시오.
2. 각 변수를 작성한다.
  - title 변수에 '점수표' 문자열을 할당한다.
  - avgScore 변수에 null을 할당한다.
  - scores 변수에, 임의로 작성한 정수를 요소로 하는 배열을 할당한다.
  - is_vacation 변수에 상태정보를 담고 있는 객체를 할당한다.
    - {status: Boolean Type} 형태로 작성한다.
  - 단, ref 함수를 사용하여 반응성을 가지는 참조 변수로 만들어야 한다.
3. 각 데이터는 template을 수정하여 적절하게 표기 될 수 있도록 작성한다.
4. cal_average 함수를 정의하고, 평균 점수 계산 버튼을 클릭하면 함수가 작동하도록 한다.
  - cal_average 함수는 scores 배열에 담긴 정수의 평균 값을 avgScore에 할당한다.
---
`reduce()`를 활용하여 배열의 합을 구할 수 있습니다.  
https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Array/reduce
<div style="text-align: right">20240425</div>