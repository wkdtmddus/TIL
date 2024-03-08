### Bootstrap 문법 실습 예제 Lv4
목표  
- Bootstrap과 Bootstrap component를 통해 문제를 해결한다.
- Bootstrap CDN을 적용할 수 있다.
- Bootstrap class 개념을 이해하고 사용할 수 있다.
---
문제  
부트스트랩을 활용하여 디바이스의 크기에 따라 적절한 마진값을 가질 수 있도록 설정하고자 한다.  
요구사항을 참고하여 올바른 부트스트랩 클래스를 작성하시오.  

요구사항  
- 디바이스의 너비가 768px 이상일 경우에는, 마진이 상하좌우 24px가 되어야 한다.
- 디바이스의 너비가 1200px 이상일 경우에는, 마진이 상하좌우 32px가 되어야 한다.
---
`Bootstrap`의 `component`들을 확인하여 적용합니다.  
부트스트랩에 내장되어있는 공식을 사용합니다.  
```
Where size is one of:

0 - for classes that eliminate the margin or padding by setting it to 0
1 - (by default) for classes that set the margin or padding to $spacer * .25
2 - (by default) for classes that set the margin or padding to $spacer * .5
3 - (by default) for classes that set the margin or padding to $spacer * 1 (16rem)
4 - (by default) for classes that set the margin or padding to $spacer * 1.5
5 - (by default) for classes that set the margin or padding to $spacer * 3
auto - for classes that set the margin to auto
```
<div style="text-align: right">20240308</div>