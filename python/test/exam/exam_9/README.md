### 문제 9
이진수는 0, 1로 모든 수를 표현하는 방식이다.  
일상생활에서 사용하는 십진수 숫자를 이진수로 변경하기 위해서는 2로 나눈 몫을 2로 나누는 과정을 반복하며 나오는 나머지들을 사용한다.  
십진수 숫자(n)를 인자로 받아 이진수로 변환하여 문자열로 반환하는 함수 dec_to_bin을 완성하시오.  
단, 재귀 함수를 이용하여 구현한다.(재귀 함수를 사용하지 않을 시 감점)

---
최대한 나누었을 때, 항상 나머지는 1로 반환되므로 `if문`을 사용하여 1을 반환합니다.  
재귀 함수를 사용하여 n을 나눈 몫을 값으로 다시 사용하고, 나머지를 `str()`으로 붙여줍니다.
<div style="text-align: right">20240129</div>