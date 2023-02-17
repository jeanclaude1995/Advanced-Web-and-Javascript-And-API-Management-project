const key ='7b8ed17f3221d225a3e80378dcf4359b'

const requests = {
    requestTopRated: `https://api.themoviedb.org/3/movie/top_rated?api_key=${key}&language=en-US&page=1`,
    requestPopular: `https://api.themoviedb.org/3/movie/popular?api_key=${key}&language=en-US&page=1`,
    requestUpcoming: `https://api.themoviedb.org/3/movie/upcoming?api_key=${key}&language=en-US&page=1`,
    requestImages: `https://api.themoviedb.org/3/movie/{movie_id}/images?api_key=<<api_key>>&language=en-US`


    
};

export default requests