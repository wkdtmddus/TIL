### Vite 활용하기 2 Lv4
목표  
- Vite를 이용하여 Vue.js 프로젝트를 초기화하고 필요한 구조를 설정할 수 있다.
- Vue 컴포넌트를 생성하고 양방향 데이터 바인딩과 단방향 데이터 바인딩을 구현할 수 있다.
---
문제  
Vite를 활용하여 프로젝트를 생성하고자 한다.  
제시된 스크린샷을 참고하여 동일한 환경의 프로젝트를 생성하고, 요구 사항을 만족하도록 코드를 작성하시오.  

요구사항
- 사용하지 않을 컴포넌트와 assets들을 모두 삭제한다.
  - components/ 폴더의 모든 컴포넌트
  - assets/ 폴더의 모든 파일
- 관련된 모든 코드를 삭제한다.
  - App.vue에 작성된 삭제된 component들
  - main.js에 작성된 main.css import 문
- ColorChanger.vue 컴포넌트를 생성한다.
  - 사용자가 값을 입력할 수 있도록 input 태그를 작성한다.
  - '입력창에 올바른 색상 명을 입력하면 글자색이 바껴요.' 문구를 포함한 p 태그를 작성한다.
  - 각각 글씨 색이 red, blue, green이 되도록 설정된 클래스 .red, .blue, .green을 style에 작성한다.
  - 사용자가 입력한 값이 colorClass 변수에 할당 될 수 있도록 양방향 바인딩 directive를 사용한다.
  - colorClass 변수의 값이 첫 번째 p 태그의 class가 될 수 있도록 단방향 바인딩 directive를 사용한다.
-  작성한 component가 화면에 렌더링 될 수 있도록 App.vue에 등록한다.
---
`import { ref } from 'vue'` 를 작성하여 변수 선언을 합니다.  
App.vue로 component를 가져옵니다.
```
<script setup>
import ColorChanger from '@/components/ColorChanger.vue'
</script>

<template>
  <div>
    <ColorChanger />
  </div>
</template>
```
<div style="text-align: right">20240502</div>