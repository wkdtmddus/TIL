from rest_framework import serializers
from .models import SavingProduct, SavingOption


class SavingProductSerializer(serializers.ModelSerializer):
    class Meta:
        model = SavingProduct
        fields = '__all__'

class SavingOptionSerializer(serializers.ModelSerializer):
    class SavingProductTitleSerializer(serializers.ModelSerializer):
        class Meta:
            model = SavingProduct
            fields = ('kor_co_nm',)

    product = SavingProductTitleSerializer(read_only=True)
    class Meta:
        model = SavingOption
        fields = '__all__'


class SavingOptionListSerializer(serializers.ModelSerializer):
    class SavingProductSerializer(serializers.ModelSerializer):
        class Meta:
            model = SavingProduct
            fields = ('fin_prdt_nm', 'kor_co_nm',)

    product = SavingProductSerializer(read_only=True)

    class Meta:
        model = SavingOption
        fields = '__all__'
        read_only_fields = ('product',)