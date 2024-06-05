from django.urls import path
from . import views


urlpatterns = [
    path('deposit_products/', views.deposit_products), # 전체 정기예금 상품 목록 출력 & 삽입
    path('deposit_product_options/<str:fin_prdt_cd>/', views.deposit_product_options), # 특정 상품 옵션 리스트
    path('deposit_product_option/<int:option_pk>/', views.option_detail),
]