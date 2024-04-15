from rest_framework import serializers
from .models import Book, Review


class BookListSerializer(serializers.ModelSerializer):

    class Meta:
        model = Book
        fields = ('title', )

class BookSerializer(serializers.ModelSerializer):
    class ReviewSerializer(serializers.ModelSerializer):
        class Meta:
            model = Review
            fields = ('content', 'score',)
    review_set = ReviewSerializer(read_only=True, many=True)
    class Meta:
        model = Book
        fields = '__all__'

class ReviewListSerializer(serializers.ModelSerializer):
    class BookSerializer(serializers.ModelSerializer):
        class Meta:
            model = Book
            fields = ('isbn',)
    book = BookSerializer(read_only=True)
    class Meta:
        model = Review
        fields = ('content', 'score', 'book',)

class ReviewSerializer(serializers.ModelSerializer):

    class Meta:
        model = Review
        fields = '__all__'
        read_only_fields = ('book',)