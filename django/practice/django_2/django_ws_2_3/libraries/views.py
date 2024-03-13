from django.shortcuts import render
import requests
from pprint import pprint

# Create your views here.
def index(request):
    return render(request, 'index.html')

def recommend(request):

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
    
    new_books = []
    for i in books:
        lst = [
            i.get('제목'),
            i.get('저자')
        ]
        new_books.append(lst)

    context = {
        'books': new_books
    }
    return render(request, 'recommend.html', context)