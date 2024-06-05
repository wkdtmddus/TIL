from django.shortcuts import render
from rest_framework.response import Response
from rest_framework.decorators import api_view
from rest_framework import status
from django.conf import settings
from django.shortcuts import get_object_or_404, get_list_or_404
from django.http import JsonResponse
import requests
from django.contrib.auth.hashers import check_password


@api_view(['POST'])
def user_delete(request):
    user = request.user
    password = request.data.get('password1', None)
    if user.is_authenticated:
        if password and check_password(password, user.password):
            user.delete()
            return Response(status=status.HTTP_204_NO_CONTENT)
        else:
            return Response({'error': 'Invalid password'}, status=status.HTTP_400_BAD_REQUEST)
    else:
        return Response(status=status.HTTP_401_UNAUTHORIZED)