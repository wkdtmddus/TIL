import requests
from pprint import pprint

API_URL = 'http://www.aladin.co.kr/ttb/api/ItemList.aspx'
API_KEY = 'ttb'
params = {
    'ttbkey': API_KEY,
    'QueryType': 'ItemNewSpecial',
    'MaxResults': 50,
    'start': 1,
    'SearchTarget': 'Book',
    'output': 'JS',
    'Version': 20131101
}

res = requests.get(API_URL, params).json()
items = res.get('item')
books = []
for i in items:
    dic = {
        '제목': i.get('title'),
        '저자': i.get('author'),
        '출간일': i.get('pubDate'),
        '국제 표준 도서 번호': i.get('isbn')
    }
    books.append(dic)
pprint(books)
