from django.shortcuts import render, redirect
from .models import Todo

# Create your views here.
def index(request):
    # work = request.GET.get('work')
    todo_list = Todo.objects.all()
    context = {
        # 'work': work
        'todo_list': todo_list
    }
    return render(request, 'todos/index.html', context)

def create_todo(request):
    return render(request, 'todos/create_todo.html')

def detail(request, todo_pk):
    todo = Todo.objects.get(pk=todo_pk)
    context = {
        'todo': todo
    }
    return render(request, 'todos/detail.html', context)

def new_todo(request):
    work = request.POST.get('work')
    content = request.POST.get('content')
    todo = Todo(work=work, content=content, is_completed=False)
    todo.save()
    # context = {
    #     'todo': todo
    # }
    return redirect('todos:detail', todo.pk)

def delete_todo(request, todo_pk):
    todo = Todo.objects.get(pk=todo_pk)
    todo.delete()
    return redirect('todos:index')