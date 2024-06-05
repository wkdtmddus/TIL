from django.urls import path
from . import views


urlpatterns = [
    path('', views.cards), # 전체 카드 목록
]