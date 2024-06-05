from rest_framework import serializers
from .models import DepositProduct, DepositOption


class DepositProductSerializer(serializers.ModelSerializer):
    class Meta:
        model = DepositProduct
        fields = '__all__'

class DepositOptionSerializer(serializers.ModelSerializer):
    class DepositProductTitleSerializer(serializers.ModelSerializer):
        class Meta:
            model = DepositProduct
            fields = ('fin_prdt_nm', 'kor_co_nm',)

    product = DepositProductTitleSerializer(read_only=True)
    class Meta:
        model = DepositOption
        fields = '__all__'


class DepositOptionListSerializer(serializers.ModelSerializer):
    class DepositProductSerializer(serializers.ModelSerializer):
        class Meta:
            model = DepositProduct
            fields = ('fin_prdt_nm', 'kor_co_nm',)

    product = DepositProductSerializer(read_only=True)

    class Meta:
        model = DepositOption
        fields = '__all__'
        read_only_fields = ('product',)