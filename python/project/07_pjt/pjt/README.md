### A. 전체 배우 목록 제공
* [GET] api/v1/actors/

- `@api_view(['GET'])` 이게 있어야 rest_framework의 Response 사용할 수 있다.  
- 객체를 가져올 때, 해당 모델을 사용하여 가져올 수 있지만, `get_list_or_404`. `get_object_or_404`를 이용하여 가져올 수 있다.  
- `serializers`를 이용해서 객체를 JSON으로 변환한다.
### B. 단일 배우 정보 제공
* [GET] api/v1/actor/1/

- pk값을 이용해서 many to many로 연관된 데이터를 가져오기 위해서 serializers 내부에 serializers를 다시 작성
- movie_set: 이름 형식 준수 (movie_list로 하니까 안됨)
  fields는 튜플과 리스트만 인식할 수 있는데, fields = ('title',)의 마지막 콤마를 빼면 그냥 str 타입으로 인식되어 오류가 남.
  ```python
  class ActorSerializer(serializers.ModelSerializer):
    class MovieListSerializer(serializers.ModelSerializer):
        class Meta:
            model = Movie
            fields = ('title',)
    movie_set = MovieListSerializer(read_only=True, many=True)
    class Meta:
        model = Actor
        fields = '__all__'
  ```
### C. 전체 영화 목록 제공
* [GET] api/v1/movies/
* A.와 내용이 같다.
### D. 단일 영화 정보 제공
* [GET] api/v1/movies/1/
* B.와 내용이 같다.
### E. 전체 리뷰 목록 제공 
* [GET] api/v1/reviews/
* A.와 내용이 같다.
### F. 단일 리뷰 조회 & 수정 & 삭제 (1/3)
* [GET] api/v1/reviews/1/
* review 안에 movie가 있어서 (두 정보를 함께 출력) serializer가 중첩되어 2번 들어간다.
### F. 단일 리뷰 조회 & 수정 & 삭제 (2/3)
* [PUT] api/v1/reviews/1/
* put메서드일 때, serializer에 객체와 request.data 값을 넣는다. 
* `is_valid()`로 유효성 검사
* `raise_exception=True` : HTTP 400 Bad Request 응답을 리턴
```python
elif request.method == 'PUT':
        serializer = ReviewSerializer(review, data=request.data)
        if serializer.is_valid(raise_exception=True):
            serializer.save()
            return Response(serializer.data)
```
### F. 단일 리뷰 조회 & 수정 & 삭제 (3/3)
* [DELETE] api/v1/reviews/1/
  
```python
f-string: 문자열 포메팅. value값에 적용하여 review_pk를 출력.
elif request.method == 'DELETE':
        review.delete()
        return Response({'message': f'review {review_pk} is deleted'})
```
### G. 리뷰 생성
* [POST] api/v1/movies/1/reviews/
* serializer에 request 데이터를 넣어준다.
```python
@api_view(['POST'])
def create_review(request, movie_pk):
    movie = get_object_or_404(Movie, pk=movie_pk)
    serializer = ReviewSerializer(data=request.data)
    if serializer.is_valid(raise_exception=True):
        serializer.save(movie=movie)
        return Response(serializer.data)
```