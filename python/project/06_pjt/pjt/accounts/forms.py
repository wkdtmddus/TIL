from django.contrib.auth.forms import UserCreationForm
from django.contrib.auth import get_user_model
from django.core.exceptions import ValidationError

class CustomUserCreationForm(UserCreationForm):
    class Meta:
        # id, password, password2 만 입력받음
        model = get_user_model()
        fields = UserCreationForm.Meta.fields + ('nickname',)  # nickname 필드 추가

    def clean_nickname(self):
        nickname = self.cleaned_data.get('nickname')
        # 사용자 모델에서 nickname 필드를 사용하여 중복 검사
        if get_user_model().objects.filter(nickname=nickname).exists():
            raise ValidationError("이미 사용 중인 닉네임입니다.")
        return nickname