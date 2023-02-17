
const express = require('express');
const ratingModel = require('../models/rating');
const router = express.Router();

// Allow a user to add a rating and comment to a movie
router.post('/movie-rating', async (req, res) => {
  const { rating, commentTitle, commentContent, userId, movieTitle } = req.body;

  if (rating !== '' && commentTitle !== '' && commentContent !== '' && userId !== '') {
    try {
      const newRating = new ratingModel({
        rating: rating,
        commentTitle: commentTitle,
        commentContent: commentContent,
        userId: userId,
        movieTitle: movieTitle
      });

      const savedRating = await newRating.save();
      return res.status(200).json(savedRating);
    } catch (error) {
      return res.status(500).json({ 'msg': error.message });
    }
  } else {
    return res.status(400).json({ 'msg': 'Please fill in all fields!' });
  }
});

// Allow a user to update their movie rating and comment
router.put('/movie-rating/:ratingId', async (req, res) => {
  const { rating, commentTitle, commentContent } = req.body;
  const { ratingId } = req.params;

  const updatedRating = await ratingModel.findByIdAndUpdate(ratingId,
    {
      rating: rating,
      commentTitle: commentTitle,
      commentContent: commentContent
    },
    { new: true });

  return res.status(200).json({ 'msg': 'Rating updated successfully' });
});

// Allow a user to delete their movie rating and comment
router.delete('/movie-rating/:ratingId', async (req, res) => {
  const { ratingId } = req.params;

  await ratingModel.findByIdAndDelete(ratingId);
  return res.status(200).json({ 'msg': 'Rating and comment deleted successfully' });
});

// Allow a user to retrieve all movie ratings and comments
router.get('/movie-ratings', async (req, res) => {
  const allRatings = await ratingModel.find({});
  return res.status(200).json(allRatings);
});

module.exports = router;
