import React, { useState } from "react";
import Main from '../components/Main'
import Row from '../components/Row'
import Search from '../components/Search'
import axios from 'axios'
import Detail from '../components/Detail'
import Results from "../components/Results"; 
import requests from '../Request'
import {Link} from 'react-router-dom'
import Navbar from "../components/Navbar";

const Home = () => {
  const [state, setState] = useState({
    s: "sherlock",
    results: [],
    selected: {},
  });
  
  const apiurl = "https://www.omdbapi.com/?apikey=a2526df0";
  
  const searchInput = (e) => {
    let s = e.target.value;
    console.log("changed");
  
    setState((prevState) => {
    return { ...prevState, s: s };
    });
  };
  
  const search = (e) => {
    if (e.key === "Enter") {
    axios(apiurl + "&s=" + state.s).then(({ data }) => {
      let results = data.Search;
  
      console.log(results);
  
      setState((prevState) => {
      return { ...prevState, results: results };
      });
    });
    }
  };
  
  const openDetail = (id) => {
    axios(apiurl + "&i=" + id).then(({ data }) => {
    let result = data;
  
    setState((prevState) => {
      return { ...prevState, selected: result };
    });
    });
  };
  
  const closeDetail = () => {
    setState((prevState) => {
    return { ...prevState, selected: {} };
    });
  };
  

  return (
    <div>
        <Navbar/>
        <Main/>
        <Search handleInput={searchInput} search={search} />
        <Results results={state.results} openDetail={openDetail} /> 
        {typeof state.selected.Title != "undefined" ? (
        <Detail selected={state.selected} closeDetail={closeDetail} />):( console.log("no detail"))}
        <Row rowID='1' title='Last Seen' fetchURL={requests.requestUpcoming}/>
        <Row rowID='2' title=' Recomanded Movies' fetchURL={requests.requestPopular}/>
        <Row rowID='3' title='New Movies' fetchURL={requests.requestTopRated}/>
    </div>
    
    
  )
}

export default Home