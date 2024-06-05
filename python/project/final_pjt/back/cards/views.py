import requests
import xml.etree.ElementTree as ET
from rest_framework.decorators import api_view
from rest_framework.response import Response
import json
import datetime

@api_view(['GET'])
def cards(request):
    BASE_URL = 'http://apis.data.go.kr/B190030/GetCardProductInfoService/getCardProductList'
    current_date = datetime.datetime.now().strftime('%Y%m%d')
    URL = BASE_URL
    params = {
        'serviceKey': '',
        'pageNo': 1,
        'numOfRows': "7",
        'sBseDt': '20210730',
        'eBseDt': '20240522'
    }
    
    response = requests.get(URL, params=params)

    # XML 파싱
    root = ET.fromstring(response.text)
    # XML을 딕셔너리로 변환
    response_data = {
        'resultCode': root.findtext('.//resultCode'),
        'resultMsg': root.findtext('.//resultMsg'),
        'numOfRows': root.findtext('.//numOfRows'),
        'pageNo': root.findtext('.//pageNo'),
        'totalCount': root.findtext('.//totalCount'),
    }

    items = []
    for item in root.findall('.//item'):
        item_data = {
            'bseDt': item.findtext('bseDt'),
            'anmfOtl': item.findtext('anmfOtl'),
            'cadTpTcNm': item.findtext('cadTpTcNm'),
            'jinTgtCone': item.findtext('jinTgtCone'),
            'prdNm': item.findtext('prdNm'),
            'prdOtl': item.findtext('prdOtl')
        }
        items.append(item_data)

    response_data['items'] = items

    return Response({ 'response': response_data })
