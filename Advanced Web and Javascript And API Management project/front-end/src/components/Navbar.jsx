import React from 'react'
import { Link } from 'react-router-dom';

const Navbar = () => {
  return (
    <div className=' flex items-center justify-between p-4 z-[100] w-full absolute'>
      <Link to='/'>

        <h1 className=' text-blue-600 text-4xl font-bold cursor-pointer'> sun video</h1>
       </Link>
         <div>
           <Link to= '/'>
           <button
           className='bg-blue-600 px-6 py-2 rounded cursor-pointer text-white'>
             Logout
           </button>
           </Link>
           </div>
</div>
);
};

export default Navbar;