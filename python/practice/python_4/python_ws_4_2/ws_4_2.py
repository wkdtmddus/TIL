import requests
from pprint import pprint as print


dummy_data = []

for num in range(1, 11):
    # 무작위 유저 정보 요청 경로
    API_URL = 'https://jsonplaceholder.typicode.com/users/'
    NEW_API_URL = API_URL + str(num)
    # API 요청
    response = requests.get(NEW_API_URL)
    # JSON -> dict 데이터 변환
    parsed_data = response.json()

    # # 응답 데이터 출력
    # print(response)

    # # 변환 데이터 출력
    # print(parsed_data)
    # # 변환 데이터의 타입
    # print(type(parsed_data))

    # 특정 데이터 출력
    # print(parsed_data['name'])
    name = parsed_data['name']

    dummy_data.append(name)

print(dummy_data)