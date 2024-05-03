from django.shortcuts import render, redirect
from .forms import KeywordForm
from .models import Keyword

# Create your views here.
def keyword(request):
    keywords = Keyword.objects.all()
    if request.method == "POST":
        form = KeywordForm(request.POST)
        if form.is_valid():
            form.save()
            return redirect('trends:keyword')
    else:
        form = KeywordForm()
    context = {
        'form': form,
        'keywords': keywords
    }
    return render(request, 'trends/keyword.html', context)

def keyword_detail(request, keyword_pk):
    keyword = Keyword.objects.get(pk=keyword_pk)
    keyword.delete()
    return redirect('trends:keyword')

def crawling(request):
    pass

def crawling_histogram(request):
    pass

def crawling_advanced(request):
    pass