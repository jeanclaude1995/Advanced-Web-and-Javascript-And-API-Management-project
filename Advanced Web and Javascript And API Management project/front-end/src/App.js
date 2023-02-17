import {Route, Routes } from "react-router-dom";
import Navbar from "./components/Navbar"
import Account from "./pages/Account";
import Home from "./pages/Home"
import Login from "./pages/Login"
import Signup from "./pages/Signup"
import Rating from "./components/Rating"
import "./App.css"



function App() {


  
  return (
    <>
      <Navbar/>
      <Routes>
        <Route path='/home' element={<Home />} />
        <Route path='/' element={<Login/>} />
        <Route path='/signup' element={<Signup />} />
        <Route path='/account' element={<Account/>}/>
        <Route path='/rating' element={<Rating/>}/>
      </Routes>

    </>
  );
}

export default App;
