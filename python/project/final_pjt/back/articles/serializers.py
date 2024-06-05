from rest_framework import serializers
from .models import Article, Comment
from django.contrib.auth import get_user_model

User = get_user_model()


class ArticleListSerializer(serializers.ModelSerializer):
    comment_count = serializers.IntegerField(source='comment_set.count', read_only=True)
    nickname = serializers.SerializerMethodField()
    class Meta:
        model = Article
        fields = ('id', 'title', 'content', 'user', 'comment_count', 'nickname', 'created_at',)

    def get_nickname(self, obj):
        return obj.user.nickname


class ArticleSerializer(serializers.ModelSerializer):
    class CommentDetailSerializer(serializers.ModelSerializer):
        username = serializers.SerializerMethodField()
        nickname = serializers.SerializerMethodField()
        class Meta:
            model = Comment
            fields = '__all__'
        def get_username(self, obj):
            return obj.user.username
        def get_nickname(self, obj):
            return obj.user.nickname
    
    comment_set = CommentDetailSerializer(read_only=True, many=True)
    comment_count = serializers.IntegerField(source='comment_set.count', read_only=True)
    username = serializers.SerializerMethodField()
    nickname = serializers.SerializerMethodField()
    class Meta:
        model = Article
        fields = '__all__'
        read_only_fields = ('user',)
    
    def get_username(self, obj):
        return obj.user.username
    def get_nickname(self, obj):
        return obj.user.nickname


class CommentSerializer(serializers.ModelSerializer):
    class ArticleTitleSerializer(serializers.ModelSerializer):
        class Meta:
            model = Article
            fields = ('id',)

    article = ArticleTitleSerializer(read_only=True)

    class Meta:
        model = Comment
        fields = '__all__'
        read_only_fields = ('user',)