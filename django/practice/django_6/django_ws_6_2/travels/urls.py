from django.urls import path
from . import views

app_name = 'travels'
urlpatterns = [
    path('', views.index, name='index'),
    path('create/', views.create, name='create'),
    path('<int:travel_pk>/', views.detail, name='detail'),
]