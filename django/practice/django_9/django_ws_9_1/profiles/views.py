from django.shortcuts import render
from accounts.models import User

# Create your views here.
def index(request):
    people = User.objects.all()
    context = {
        'people': people
    }
    return render(request, 'profiles/index.html', context)