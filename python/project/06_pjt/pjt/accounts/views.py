from django.shortcuts import render, redirect
from django.contrib.auth import login as auth_login
from django.contrib.auth import logout as auth_logout
from django.contrib.auth.forms import AuthenticationForm
from .forms import CustomUserCreationForm
from django.views.decorators.http import require_http_methods
from django.contrib.auth.decorators import login_required
from django.contrib.auth import get_user_model


@require_http_methods(["GET", "POST"])
def signup(request):
    if request.method == 'POST':
        form = CustomUserCreationForm(request.POST)
        if form.is_valid():
            user = form.save()
            auth_login(request, user)
            return redirect('boards:index')
    else:
        form = CustomUserCreationForm()
    context = {
        'form': form,
    }
    return render(request, 'accounts/signup.html', context)


@require_http_methods(["GET", "POST"])
def login(request):
    if request.method == 'POST':
        form = AuthenticationForm(request, request.POST)
        if form.is_valid():
            auth_login(request, form.get_user())
            return redirect("boards:index")
    else:
        form = AuthenticationForm()
    context = {
        'form': form,
    }
    return render(request, 'accounts/login.html', context)

@login_required
def logout(request):
    auth_logout(request)
    return redirect('boards:index')


@login_required
@require_http_methods(["POST"])
def follow(request, user_pk):
    me = request.user
    you = get_user_model().objects.get(pk=user_pk)

    if me != you:
        if me in you.followers.all():
            you.followers.remove(me)
        else:
            you.followers.add(me)
    return redirect('accounts:profile', you.nickname)


def profile(request, nickname):
    person = get_user_model().objects.get(nickname=nickname)
    date_joined = person.date_joined
    person_pk = person.pk
    username = person.username
    boards = person.board_set.all()
    comments = person.comment_set.all()
    context = {
        'person': person,
        'person_pk': person_pk,
        'nickname': nickname,
        'username': username,
        'date_joined': date_joined,
        'boards': boards,
        'comments': comments
    }
    return render(request, "accounts/profile.html", context)




