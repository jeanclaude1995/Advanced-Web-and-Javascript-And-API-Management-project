import React, {useState } from 'react'
import axios from 'axios';

import { Link, useNavigate } from 'react-router-dom';

const Login = () => {

  const initialValues = {  username: "", password: ""};
  const [formValues, setFormValues] = useState(initialValues);
  const [formErrors, setFormError] = useState({});
  const navigate = useNavigate()
  

  const onChangeHandler = event => {
    const { name, value } = event.target
    setFormValues({ ...formValues, [name]: value })
  }

  const onSubmitHandler = async event => {
    event.preventDefault()
    setFormError(validate(formValues));
    
    
    
    var config = {
      method: 'post',
      url: 'http://localhost:8989/api/public/login',
      headers: { 
        'Content-Type': 'application/json'
      },
      data : formValues
    };
    
    axios(config)
    .then(function (response) {
      console.log(JSON.stringify(response.data));
      console.log(JSON.stringify(response.data.id));
      navigate("/Home", { state: { user_id:response.data.id } },{ replace: true });

    })
    .catch(function (error) {
      alert(error);
    });



  }


  

  const validate = (values) => {
    const errors = {};
    if (!values.username) {
      errors.username = "Username is required!";
    } else if (!values.username === values.username ){

      errors.username = "False Username";
    }
   
    if (!values.password) {
      errors.password = "Password is required";
    } else if (values.password.length < 6) {
      errors.password = "Password must be more than 6 characters";
    } else if (values.password.length > 10) {
      errors.password = "Password cannot exceed more than 10 characters";
    }else if (!values.password === values.password ){

      errors.password = "False Password";
    }


    return errors;
  };
  


  return (
  <div className='w-full h-screen'>
    <img
      className='hidden sm:block absolute w-full h-full object-cover'
      src='https://img.freepik.com/free-photo/movie-background-collage_23-2149876028.jpg?w=996&t=st=1676448004~exp=1676448604~hmac=0b4c7e293d934e06348ddc49c293279187b393d5a771262b0cf2f533e18a5593'
      alt='/'
    />
    <div className='bg-black/60 fixed top-0 left-0 w-full h-screen'></div>
    <div className='fixed w-full px-4 py-24 z-50'>
      <div className='max-w-[450px] h-[600px] mx-auto bg-black/75 text-white'>
        <div className='max-w-[320px] mx-auto py-16'>
          <h1 className='text-3xl font-bold'>Sign In</h1>
          <form  onSubmit={onSubmitHandler} className='w-full flex flex-col py-4'>
            <input
              className='p-3 my-2 bg-gray-700 rouded'
              type='username'
              placeholder='username'
              name='username'
              value={formValues.username}
              onChange={onChangeHandler}
            />
             <p>{formErrors.username}</p>
            <input
              className='p-3 my-2 bg-gray-700 rouded'
              type='password'
              placeholder='Password'
              name='password'
              value={formValues.password}
              onChange={onChangeHandler}
            />
             <p>{formErrors.password}</p>
            <button   className='bg-blue-600 py-3 my-6 rounded font-bold'>
              Sign In
            </button>
            <div className='flex justify-between items-center text-sm text-gray-600'>
              <p>
                <input className='mr-2' type='checkbox' />
                Remember me
              </p>
              <p>Need Help?</p>
            </div>
            <p className='py-8'>
              <span className='text-gray-600'>New to sun?</span>{' '}
              <Link to='/signup'>Sign Up</Link>
            </p>
          </form>
        </div>
      </div>
    </div>
  </div>
);
};
    

export default Login