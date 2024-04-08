from django.shortcuts import render
from .models import Book, Review
from .forms import ReviewForm

# Create your views here.
def index(request):
    books = Book.objects.all()
    context = {
        'books': books
    }
    return render(request, 'libraries/index.html', context)

def detail(request, book_pk):
    book = Book.objects.get(pk=book_pk)
    reviews = book.review_set.all()
    review_form = ReviewForm()
    context = {
        'book': book,
        'reviews': reviews,
        'review_form': review_form,
    }
    return render(request, 'libraries/detail.html', context)
