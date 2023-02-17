import axios from 'axios'
import React, {useEffect, useState} from 'react'
import Movie from './Movie'
import {MdChevronLeft, MdChevronRight} from 'react-icons/md'



const Row = ({title, fetchURL, rowID}) => {

    const [movies, setmovies] = useState([])


    useEffect(() =>{

        axios.get(fetchURL).then((response)=>{

            setmovies(response.data.results)

            // async function fetchData(){
            //                 const request = await axios.get(fetchURL)
            //                 setmovies(request.data.results)
            //                 // console.log(request.data.results)
            //                 return request;
            //             }
            //             fetchData();
        })
       
    },
    [fetchURL])

  

const slideLeft = ()=> {
    var slider = document.getElementById('slide' + rowID);
    slider.scrollLeft = slider.scrollLeft - 500;
}
const slideRight = ()=> {
    var slider = document.getElementById('slide' + rowID);
    slider.scrollLeft = slider.scrollLeft + 500;
}
    


    
    







    return (
    <>
    <h2 className='text-white font-bold md:text-xl p-4'>{title}</h2>
    <div className='relative flex items-center group'>
        <MdChevronLeft 
        onClick={slideLeft}
        className='bg-white rounded-full absolute left-0 hover:opacity-100 cursor-pointer z-10 hidden group-hover:block' size={30}/>
    <div id={'slide' + rowID}className='w-full h-full overflow-x-scroll whitespace-nowrap scroll-smooth scrollbar-hide relative'  >
        {movies.map((item, id) => (
            <><Movie key={id} item={item} />
           
                </>
        ))};
     

    </div>
   
   
    <MdChevronRight 
    onClick={slideRight}
    className='bg-white rounded-full absolute right-0 hover:opacity-100 cursor-pointer z-10 hidden group-hover:block' size={30}/>

    </div>
    </>
  );
}

export default Row

