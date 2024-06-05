from django.shortcuts import render
from rest_framework.response import Response
from rest_framework.decorators import api_view
from rest_framework import status
from django.conf import settings
from django.shortcuts import get_object_or_404, get_list_or_404
from django.http import JsonResponse
import requests
import datetime


# Create your views here.
BASE_URL = 'https://www.koreaexim.go.kr/site/program/financial/exchangeJSON'

@api_view(['GET'])
def rates(request):
    current_date = datetime.datetime.now().strftime('%Y%m%d')
    URL = BASE_URL
    params = {
        'authkey': '',
        'searchdate': current_date,
        'data': "AP01"
    }
    response = requests.get(URL, params=params).json()
    return JsonResponse({ 'response': response })