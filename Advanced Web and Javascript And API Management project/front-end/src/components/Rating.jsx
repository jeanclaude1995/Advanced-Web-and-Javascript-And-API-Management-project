


import React, { useState } from "react";
import axios from "axios";
import { useLocation,useNavigate } from "react-router-dom";
import { data } from "autoprefixer";

function RatingForm({ movieId }) {
  const [rating, setRating] = useState(0);
  const [commentTitle, setCommentTitle] = useState("");
  const [commentContent, setCommentContent] = useState("");
  const navigate = useNavigate();
  // const [movieTitle, setMovieTitle] = useState("");
  const location = useLocation();
  console.log(location.state);
  const handleSubmit = (event) => {
    event.preventDefault();
    axios
      .post("http://localhost:4500/user-rating", {
        rating,
        commentTitle,
        commentContent,
        Movie_title: location.state.movie_title,
      })
      .then((response) => {
        console.log(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const data_collection = (rating, commenttitle, commentContent,movieTitle) =>{ 
    console.log("tittle "+location.state.movie_title);
    var config = {
      method: 'post',
      url: 'http://localhost:4500/movie-rating',
      headers: { 
        'Content-Type': 'application/json'
      },
      data : {rating:rating, commentTitle:commenttitle, commentContent:commentContent,movieTitle:location.state.movie_title,userId:location.state.user_id}
    };
    
    axios(config)
    .then(function (response) {
      console.log(JSON.stringify(response.data));
      alert("Review Added Successfully");
      navigate("/", { replace: true });
    })
    .catch(function (error) {
      alert("Review Not Added");
      console.log(error);
    });
    
    
    
  }

  return (
    <form onSubmit={handleSubmit} style={{ margin: "20px", backgroundColor: "beige", padding: "20px" }}>
      <h2 style={{ margin: "40px 0" }}>Add a Review for {location.state.movie_title}</h2>
      <div style={{ margin: "10px 0" }}>
        <label htmlFor="rating">Rating:</label>
        <input
          type="number"
          id="rating"
          min="1"
          max="10"
          value={rating}
          onChange={(event) => setRating(event.target.value)}
          required
          style={{ marginLeft: "10px" }}
        />
      </div>

      <div style={{ margin: "10px 0" }}>
        <label htmlFor="commentTitle">Comment Title:</label>
        <input
          type="text"
          id="commentTitle"
          value={commentTitle}
          onChange={(event) => setCommentTitle(event.target.value)}
          maxLength="100"
          style={{ marginLeft: "10px" }}
        />
      </div>

      <div style={{ margin: "10px 0" }}>
        <label htmlFor="commentContent">Comment:</label>
        <textarea
          id="commentContent"
          value={commentContent}
          onChange={(event) => setCommentContent(event.target.value)}
          maxLength="1000"
          style={{ marginLeft: "10px" }}
        ></textarea>
      </div>

      <button type="submit" style={{ 
  marginTop: "10px", 
  padding: "10px 20px", 
  backgroundColor: "#007bff", 
  color: "#fff", 
  borderRadius: "5px", 
  border: "none", 
  fontSize: "16px",
  fontWeight: "bold",
  cursor: "pointer",
  boxShadow: "0px 2px 5px rgba(0, 0, 0, 0.3)",
  transition: "all 0.3s ease-in-out",
  } } onClick={() => data_collection(rating,commentTitle,commentContent,location.state.movie_title)}>
  Submit
</button> 
    </form>
  );
}

export default RatingForm;

