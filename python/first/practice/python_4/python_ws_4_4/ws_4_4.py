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
    
# print(dummy_data)



black_list = [
    'Hoeger LLC',
    'Keebler LLC',
    'Yost and Sons',
    'Johns Group',
    'Romaguera-Crona',
]



def create_user(dummy_data):
    censored_user_list = {}
    for dummy in dummy_data:
        if censorship(dummy):
            if censored_user_list.get(dummy['company']):
                censored_user_list.get(dummy['company']).append(dummy['name'])
            else:
                censored_user_list[dummy['company']] = [dummy['name']]
    return censored_user_list


def censorship(dummy):
    if dummy['company'] in black_list:
        print(f'{dummy["company"]} 소속의 {dummy["name"]} 은/는 등록할 수 없습니다.')
        return False
    else:
        print('이상 없습니다.')
        return True


print(create_user(dummy_data))