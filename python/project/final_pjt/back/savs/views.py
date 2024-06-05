from django.shortcuts import render
from rest_framework.response import Response
from rest_framework.decorators import api_view
from rest_framework import status
from django.conf import settings
from django.shortcuts import get_object_or_404, get_list_or_404
from django.http import JsonResponse
import requests
from .serializers import SavingProductSerializer, SavingOptionSerializer, SavingOptionListSerializer
from .models import SavingProduct, SavingOption


# Create your views here.
BASE_URL = 'http://finlife.fss.or.kr/finlifeapi/'


def save_saving_products(i):
    URL = BASE_URL + 'savingProductsSearch.json'
    params = {
        'auth': '',
        'topFinGrpNo': '020000',
        'pageNo': i
    }
    response = requests.get(URL, params=params).json()
    saving_products_data = response.get('result', {}).get('baseList', [])
    option_data_list = response.get('result', {}).get('optionList', [])
    for data in saving_products_data:
        serializer = SavingProductSerializer(data=data)
        if serializer.is_valid():
            serializer.save()
    for option_data in option_data_list:
        option_serializer = SavingOptionSerializer(data=option_data)
        if option_serializer.is_valid():
            product = get_object_or_404(SavingProduct, fin_prdt_cd=option_data['fin_prdt_cd'])
            option_serializer.save(product=product)
    return

@api_view(['GET'])
def saving_products(request):
    if request.method == 'GET':
        options = SavingOption.objects.all()
        if not options:
            save_saving_products(1)
            save_saving_products(2)
            save_saving_products(3)
            options = SavingOption.objects.all()
            serializer = SavingOptionListSerializer(options, many=True)
            return Response(serializer.data)
        serializer = SavingOptionListSerializer(options, many=True)
        return Response(serializer.data)
    
@api_view(['GET'])
def saving_product_options(request, fin_prdt_cd):
    if request.method == 'GET':
        product = get_object_or_404(SavingProduct, fin_prdt_cd=fin_prdt_cd)
        options = SavingOption.objects.filter(product=product)
        serializer = SavingOptionSerializer(options, many=True)
        return Response(serializer.data)

@api_view(['GET', 'POST'])
def option_detail(request, option_pk):
    if request.method == 'GET':
        option = get_object_or_404(SavingOption, pk=option_pk)
        serializer = SavingOptionSerializer(option)
        return Response(serializer.data)
    elif request.method == 'POST':
        option = get_object_or_404(SavingOption, pk=option_pk)
        user = request.user
        if option in user.savs.all():
            user.savs.remove(option)
            return Response({ 'message': '삭제 완료' }, status=status.HTTP_200_OK)
        else:
            user.savs.add(option)
            return Response({ 'message': '추가 완료' }, status=status.HTTP_200_OK)