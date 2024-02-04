### 문제 4
새롭게 서비스되는 사이트에서 로그인 기능을 담당하게 되었다.  
사용자의 입력 정보는 dictionary 형태로 함수의 매개변수에 전달된다고 한다.  
사용자가 입력한 password는 8자리 이상 32자리 미만이고, password와 password_confirm도 완벽히 일치해야 한다.  
이 두 조건을 모두 만족하면 True, 하나라도 만족하지 않으면 False를 반환하는 compare_pw 함수를 완성하시오.  
Python 내장함수 len 사용 시 감점

---
password의 길이를 알기 위해 `for문`을 사용하여 변수를 1씩 증가시킵니다.  
`if문`을 사용하여 조건을 두 번 적용합니다.
<div style="text-align: right">20240129</div>