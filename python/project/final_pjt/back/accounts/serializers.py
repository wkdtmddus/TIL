from rest_framework import serializers
from dj_rest_auth.registration.serializers import RegisterSerializer
from .models import User
from django.contrib.auth import get_user_model
from dj_rest_auth.serializers import UserDetailsSerializer
from fins.serializers import DepositOptionSerializer
from savs.serializers import SavingOptionSerializer
UserModel = get_user_model()



class CustomRegisterSerializer(RegisterSerializer):
    nickname = serializers.CharField(
    required=False,
    allow_blank=True,
    max_length=255
    )

    def get_cleaned_data(self):
        return {
        'username': self.validated_data.get('username', ''),
        'password1': self.validated_data.get('password1', ''),
        'email': self.validated_data.get('email', ''),
        'nickname': self.validated_data.get('nickname', ''),
        }

class CustomUserDetailsSerializer(UserDetailsSerializer):
    fins = serializers.SerializerMethodField()
    savs = serializers.SerializerMethodField()

    def get_fins(self, user):
        # 사용자의 모든 fins 가져오기
        fins = user.fins.all()
        # 가져온 fins를 시리얼라이즈
        serializer = DepositOptionSerializer(fins, many=True)
        
        return serializer.data
    
    def get_savs(self, user):
        savs = user.savs.all()
        serializer = SavingOptionSerializer(savs, many=True)
        
        return serializer.data
    
    class Meta:
        extra_fields = []
        if hasattr(UserModel, 'USERNAME_FIELD'):
            extra_fields.append(UserModel.USERNAME_FIELD)
        if hasattr(UserModel, 'EMAIL_FIELD'):
            extra_fields.append(UserModel.EMAIL_FIELD)
        if hasattr(UserModel, 'nickname'):
            extra_fields.append('nickname')
        if hasattr(UserModel, 'fins'):
            extra_fields.append('fins')
        if hasattr(UserModel, 'savs'):
            extra_fields.append('savs')
        model = UserModel
        fields = ('pk', *extra_fields)