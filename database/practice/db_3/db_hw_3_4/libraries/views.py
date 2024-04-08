from django.shortcuts import render, redirect
from .models import Author, Book
from .forms import BookForm

# Create your views here.
def index(request):
    authors = Author.objects.all()
    context = {
        'authors': authors,
    }
    return render(request, 'libraries/index.html', context)

def detail(request, author_pk):
    author = Author.objects.get(pk=author_pk)
    books = author.book_set.all()
    book_form = BookForm()
    context = {
        'author': author,
        'books': books,
        'book_form': book_form,
    }
    return render(request, 'libraries/detail.html', context)

def book_create(request, author_pk):
    author = Author.objects.get(pk=author_pk)
    book_form = BookForm(request.POST)
    books = author.book_set.all()
    if book_form.is_valid():
        book = book_form.save(commit=False)
        book.author = author
        book.save()
        return redirect('libraries:detail', author_pk)
    context = {
        'book_form': book_form,
        'author': author,
        'books': books,
    }
    return render(request, 'libraries/detail.html', context)