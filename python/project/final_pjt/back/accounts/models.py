from django.db import models
from django.contrib.auth.models import AbstractUser
from allauth.account.adapter import DefaultAccountAdapter
from fins.models import DepositOption
from savs.models import SavingOption

# Create your models here.
class User(AbstractUser):
    nickname = models.CharField(max_length=100, unique=True)
    fins = models.ManyToManyField(DepositOption, blank=True, related_name='like_fin')
    savs = models.ManyToManyField(SavingOption, blank=True, related_name='like_sav')

class CustomAccountAdapter(DefaultAccountAdapter):
    def save_user(self, request, user, form, commit=True):
        from allauth.account.utils import user_email, user_field, user_username
        data = form.cleaned_data
        email = data.get("email")
        nickname = data.get("nickname")
        username = data.get("username")
        if nickname:
            user_field(user, "nickname", nickname)
        if username:
            user_field(user, "username", username)
        if email:
            user_field(user, "email", email)
        if "password1" in data:
            user.set_password(data["password1"])
        else:
            user.set_unusable_password()
        if commit:
            user.save()
        return user