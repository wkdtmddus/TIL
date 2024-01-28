import requests
from pprint import pprint as print


dummy_data = []
dummy = {}

for num in range(1, 11):
    # 무작위 유저 정보 요청 경로
    API_URL = 'https://jsonplaceholder.typicode.com/users/'
    NEW_API_URL = API_URL + str(num)
    # API 요청
    response = requests.get(NEW_API_URL)
    # JSON -> dict 데이터 변환
    parsed_data = response.json()

    lat = float(parsed_data['address']['geo']['lat'])
    lng = float(parsed_data['address']['geo']['lng'])

    if -80 < lat < 80 and -80 < lng < 80:
        dummy = {
            'company': parsed_data['company']['name'],
            'lat': lat,
            'lng': lng,
            'name': parsed_data['name']
        }

        dummy_data.append(dummy)
    
print(dummy_data)