from django.shortcuts import render
from rest_framework.response import Response
from rest_framework.decorators import api_view
from rest_framework import status
from django.conf import settings
from django.shortcuts import get_object_or_404, get_list_or_404
from django.http import JsonResponse
import requests
from .serializers import DepositProductSerializer, DepositOptionSerializer, DepositOptionListSerializer
from .models import DepositProduct, DepositOption

# DEPOSIT_API_KEY = settings.DEPOSIT_API_KEY

# Create your views here.
BASE_URL = 'http://finlife.fss.or.kr/finlifeapi/'


def save_deposit_products(i):
    URL = BASE_URL + 'depositProductsSearch.json'
    params = {
        'auth': '',
        'topFinGrpNo': '020000',
        'pageNo': i
    }
    response = requests.get(URL, params=params).json()
    deposit_products_data = response.get('result', {}).get('baseList', [])
    option_data_list = response.get('result', {}).get('optionList', [])
    for data in deposit_products_data:
        serializer = DepositProductSerializer(data=data)
        if serializer.is_valid():
            serializer.save()
    for option_data in option_data_list:
        option_serializer = DepositOptionSerializer(data=option_data)
        if option_serializer.is_valid():
            product = get_object_or_404(DepositProduct, fin_prdt_cd=option_data['fin_prdt_cd'])
            option_serializer.save(product=product)
    return

@api_view(['GET'])
def deposit_products(request):
    if request.method == 'GET':
        # 모든 DepositProduct 객체 가져오기
        options = DepositOption.objects.all()
        if not options:
            save_deposit_products(1)
            save_deposit_products(2)
            options = DepositOption.objects.all()
            serializer = DepositOptionListSerializer(options, many=True)
            return Response(serializer.data)
        serializer = DepositOptionListSerializer(options, many=True)
        return Response(serializer.data)
    
@api_view(['GET'])
def deposit_product_options(request, fin_prdt_cd):
    if request.method == 'GET':
        product = get_object_or_404(DepositProduct, fin_prdt_cd=fin_prdt_cd)
        options = DepositOption.objects.filter(product=product)
        serializer = DepositOptionSerializer(options, many=True)
        return Response(serializer.data)

@api_view(['GET', 'POST'])
def option_detail(request, option_pk):
    if request.method == 'GET':
        option = get_object_or_404(DepositOption, pk=option_pk)
        serializer = DepositOptionSerializer(option)
        return Response(serializer.data)
    elif request.method == 'POST':
        option = get_object_or_404(DepositOption, pk=option_pk)
        user = request.user
        if option in user.fins.all():
            user.fins.remove(option)
            return Response({ 'message': '삭제 완료' }, status=status.HTTP_200_OK)
        else:
            user.fins.add(option)
            return Response({ 'message': '추가 완료' }, status=status.HTTP_200_OK)

