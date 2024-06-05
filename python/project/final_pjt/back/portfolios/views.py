from rest_framework.response import Response
from django.core.exceptions import ObjectDoesNotExist
from rest_framework.decorators import api_view
from rest_framework import status
from rest_framework.decorators import permission_classes
from rest_framework.permissions import IsAuthenticated
from django.shortcuts import get_object_or_404, get_list_or_404
from .serializers import PortfolioSerializer
from .models import Portfolio
import random
from fins.models import DepositProduct, DepositOption
from fins.serializers import DepositOptionSerializer
from savs.models import SavingProduct, SavingOption
from savs.serializers import SavingOptionSerializer


def recommend_products(bank_name, tendency):
    if tendency == "예금":
        matching_products = DepositProduct.objects.filter(kor_co_nm__icontains=bank_name)
        options = list(DepositOption.objects.filter(product__in=matching_products))
    elif tendency == "적금":
        matching_products = SavingProduct.objects.filter(kor_co_nm__icontains=bank_name)
        options = list(SavingOption.objects.filter(product__in=matching_products))
    else:
        options = []
    
    if len(options) >= 5:
        recommended_options = random.sample(options, 5)
    else:
        recommended_options = options

    return recommended_options

@api_view(['GET', 'PUT'])
def portfolio(request):
    if request.method == 'GET':
        try:
            portfolio = Portfolio.objects.get(user=request.user)
        except ObjectDoesNotExist:
            # 포트폴리오가 없는 경우
            portfolio = Portfolio.objects.create(user=request.user, bank="", tendency="")
        serializer = PortfolioSerializer(portfolio)
        recommended_products = recommend_products(portfolio.bank, portfolio.tendency)

        serializer_data = serializer.data
        if portfolio.tendency == "적금":
            serializer_data["recommended_products"] = [SavingOptionSerializer(product).data for product in recommended_products]
        else:
            serializer_data["recommended_products"] = [DepositOptionSerializer(product).data for product in recommended_products]
        
        return Response(serializer_data)
 
    elif request.method == 'PUT':
        try:
            portfolio = Portfolio.objects.get(user=request.user)
        except ObjectDoesNotExist:
            # 포트폴리오가 없는 경우
            portfolio = Portfolio.objects.create(user=request.user, bank="", tendency="")
            serializer = PortfolioSerializer(portfolio)
            return Response(serializer.data)
        serializer = PortfolioSerializer(portfolio, data=request.data, partial=True)
        if request.user == portfolio.user:
            if serializer.is_valid(raise_exception=True):
                serializer.save()
                recommended_products = recommend_products(portfolio.bank, portfolio.tendency)
                serializer_data = serializer.data
                if portfolio.tendency == "적금":
                    serializer_data["recommended_products"] = [SavingOptionSerializer(product).data for product in recommended_products]
                else:
                    serializer_data["recommended_products"] = [DepositOptionSerializer(product).data for product in recommended_products]
                return Response(serializer_data)
        else:
            return Response(status=status.HTTP_400_BAD_REQUEST)
