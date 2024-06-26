from django.shortcuts import render, redirect
from django.contrib.auth import get_user_model
from django.contrib.auth import login as auth_login
from django.contrib.auth import logout as auth_logout
from django.contrib.auth.forms import AuthenticationForm
from .models import User
from django.contrib.auth.decorators import login_required

User = get_user_model()
# Create your views here.
def index(request):
    persons = User.objects.all().order_by('-score')
    context = {
        'persons': persons
    }
    return render(request, 'accounts/index.html', context)

def login(request):
    if request.method == 'POST':
        form = AuthenticationForm(request, request.POST)
        if form.is_valid():
            auth_login(request, form.get_user())
            return redirect('accounts:index')
    else:
        form = AuthenticationForm()
    context = {
        'form': form
    }
    return render(request, 'accounts/login.html', context)

def logout(request):
    auth_logout(request)
    return redirect('accounts:index')

def increase_score(request, user_pk):
    if request.method == 'POST':
        request_user = request.user
        user = User.objects.get(pk=user_pk)
        if request_user.pk == user.pk:
            user.score += 100
            user.save()
            return redirect('accounts:index')
    return redirect('accounts:index')

@login_required
def my_score(request):
    user = request.user
    user.score += 100
    user.save()
    return redirect('accounts:index')