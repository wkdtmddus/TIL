### 도서관 사용자 관리 서비스 - 사전 준비 Lv1
목표  
- 제어문을 작성하는 방법을 이해하고 명세에 맞춰 코드를 작성한다.
- 작성한 코드에서 직접 디버깅을 수행하여 발생하는 문제를 해결한다.
---
문제  
도서관 사용자 관리 서비스를 구축하기 위한 임시 데이터를 수집하는 코드를 작성하고자 한다.  
주어진 코드를 실행한 결과를 확인하고, 요구 사항에 맞춰 코드를 수정하시오.   

요구사항  
코드를 실행하기 위해 requests를 설치한다.  
설치 방법 및 사용 방법은 https://pypi.org/project/request/ 문서 참고  
무작위 유저 정보를 얻어오기 위한 경로 openAPI https://jsonplaceholder.typicode.com/guide/ 문서 참고  
응답 받아온 데이터에서 username과 company의 name을 출력하시오.
```
<Response [200]>
{'address': {'city': 'Gwenborough',
             'geo': {'lat': '-37.3159', 'lng': '81.1496'},
             'street': 'Kulas Light',
             'suite': 'Apt. 556',
             'zipcode': '92998-3874'},
 'company': {'bs': 'harness real-time e-markets',
             'catchPhrase': 'Multi-layered client-server neural-net', 
             'name': 'Romaguera-Crona'},
 'email': 'Sincere@april.biz',
 'id': 1,
 'name': 'Leanne Graham',
 'phone': '1-770-736-8031 x56442',
 'username': 'Bret',
 'website': 'hildegard.org'}
<class 'dict'>
'Leanne Graham'
'Bret'
'Romaguera-Crona'
```
---
`requests` 라이브러리를 사용하여 `http`요청을 보낼 수 있습니다.  
`json`으로 데이터를 받으면 `dict`로 변환합니다.  
`pprint`를 사용하여 데이터를 보기 좋게 출력합니다.  
특정 데이터 출력을 위해 `key`로 데이터를 얻습니다.
<div style="text-align: right">20240118</div>