import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useMovieStore = defineStore('movie', () => {
  const movies = ref([])
  const likemovies = ref([])
  const findMovieStore = function (inputText) {
    axios({
      method: 'get',
      url: `https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=25&q=${inputText}&type=video&key=AIzaSyD8zT4zscEYXo0ndCnJ_E0HitP-eM3J9lc`
    }).then((response) => {
      // console.log(response.data.items)
      movies.value = response.data.items
    }).catch((error) => {
      console.log(error)
    })
  }
  const getMovieStore = function (videoId) {
    const video = ref()
    for (const movie of movies.value) {
      if (movie.id.videoId === videoId) {
        video.value = movie
        return movie
      }
    }
  }
  return { movies, likemovies, findMovieStore, getMovieStore }
},{
  persist: true,
},)
