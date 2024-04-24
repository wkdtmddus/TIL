### setInterval - Momentum Lv4
목표  
- 학습 주제
  - 자바스크립트 이벤트
- 학습 목표
  - 브라우저에서 이벤트가 어떻게 작동하는지 이해할 수 있다.
  - 주어진 요구사항에 따라 이벤트 핸들러를 작성하고 활용할 수 있다.
- 사전지식
  - 사전지식
    - `setInterval()`
      - 참고 문서 : https://developer.mozilla.org/ko/docs/Web/API/setInterval
      - 각 호출 사이에 고정된 시간 지연으로 함수를 반복적으로 호출하거나 코드 스니펫을 실행
      ```
      setInterval(code)
      setInterval(code, delay)

      const intervalID = setInterval(myCallback, 500, "Parameter 1", "Parameter 2");

      function myCallback(a, b) {
        console.log(a);
        console.log(b);
      }
      ```
    - `Date`
      ```
      let now = new Date()
      let hours = now.getHours()
      let minutes = now.getMinutes()
      let seconds = now.getSeconds()
      ```
- 문제의도
  - 자바스크립트로 이벤트를 다루는 기본적인 이해와 활용 능력을 평가한다.
  - 이벤트 핸들러를 등록하며 DOM 조작하는 방법에 대한 능력을 검증한다.
---
문제  
JS Web API 중에는 setInterval 이라는 함수가 있다.  
이 함수는 변수로 넘겨 받은 delay 정수 ms(밀리세컨드) 마다 주기적으로 다른 함수를 호출하는 기능을 가지고 있다.  
setInterval 함수를 사용해서, 매초 마다 시간이 흐르는 모습을 만들어 화면에 렌더링 해보고자 한다.  
시간 정보는 Date를 사용해 얻을 수 있다.  
`자세한 사용법은 상단의 사전 지식이나, mdn 문서를 참고하여 작성한다.`  
또한, lodash를 사용해서 제공된 이미지 들 중, 무작위 하나의 이미지가 첫 렌더링시 화면에 나타나도록 하여야 한다.  
요구 사항을 참고하여 코드를 작성하시오.  

요구사항
- setInterval 함수는 1000ms마다 formatTime 함수를 호출한다.
  - 이미 작성되어 있으므로 수정하지 않는다.
- formatTime 함수
  - now 변수에는 함수가 호출된 시점의 시간 정보가 할당되어있다.
  - Date 객체의 각 메서드들은 사전 지식란을 참고한다.
  - 시, 분, 초 정보를 각 변수에 할당하고 #time의 textContent를 채워 넣는다.
    - 단, 1시, 2분, 3초와 같이 정수가 한 자리 수일 때는 다음과 같이 변환하여야 한다.
    - 01 : 02 : 03
  - 오전, 오후를 구분하여 표기한다.
    - 12시가 넘어서면 오후로 바뀌어서 표기되어야 한다.
    - 예를들어,
      - 9시 13분 53초 | 오전 09 : 13 : 53
      - 15시 02분 11초 | 오후 03 : 02 : 11
- body의 backgroundImage 변경
  - 페이지가 최초로 렌더링 될 때, lodash를 사용하여 무작위 이미지로 바뀌어야 한다.
  - _.sample()을 사용하여 1 ~ 6의 숫자 중, 무작위 하나를 반환받는다.
  - 반환받은 값을 이름으로 하는 이미지 파일을 body의 backgroundImage로 설정한다.
  - 이미지는 images 폴더에 있는 파일을 활용한다.
---
요구사항을 천천히 따라갑니다.  
`_.sample()`을 사용하여 무작위 요소를 하나 얻습니다. 해당 요소로 이미지 파일에 접근합니다.  
`Date()`를 사용하여 현재 시간에 대한 전체적인 내용을 얻습니다.
<div style="text-align: right">20240422</div>