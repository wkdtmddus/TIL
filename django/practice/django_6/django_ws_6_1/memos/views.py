from django.shortcuts import render, redirect
from .models import Memo
from .forms import MemoForm

# Create your views here.
def index(request):
    memos = Memo.objects.all()
    context = {
        'memos': memos
    }
    return render(request, 'memos/index.html', context)

def create(request):
    if request.method == 'POST':
        form = MemoForm(request.POST)
        if form.is_valid():
            memo = form.save()
            return redirect('memos:detail', memo.pk)
    else:
        form = MemoForm()
    context = {
        'form': form
    }
    return render(request, 'memos/create.html', context)

def detail(request, memo_pk):
    memo = Memo.objects.get(pk=memo_pk)
    context = {
        'memo': memo
    }
    return render(request, 'memos/detail.html', context)

def delete(request, memo_pk):
    memo = Memo.objects.get(pk=memo_pk)
    memo.delete()
    return redirect('memos:index')