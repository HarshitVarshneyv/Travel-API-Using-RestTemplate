import React from 'react';
import Navbar from '../component/navbar';
import { useNavigate } from 'react-router-dom';

const LandingPage = () => {

    const navigate = useNavigate();

    const handleGetStarted = () =>{
        navigate('/create/trip');
    };
  return (
    <div>
        <Navbar/>
    <div className="min-h-screen bg-gray-100 font-sans antialiased flex flex-col items-center justify-center p-4 md:p-8">
      {/* Content for the Landing Page */}
      <h1 className="text-3xl md:text-4xl font-bold text-gray-800">
        Welcome to the Travel API Dashboard
      </h1>
      <p className="text-lg text-center text-gray-600 mt-4 max-w-2xl">
        Explore a world of travel possibilities. Our API provides comprehensive data for destinations, flights, hotels, and bookings, empowering you to build amazing travel applications.
      </p>
      <div className="mt-8 flex flex-col sm:flex-row space-y-4 sm:space-y-0 sm:space-x-4">
        <button onClick={handleGetStarted} className="bg-blue-600 hover:bg-blue-700 text-white font-bold py-3 px-6 rounded-lg shadow-md transition-all duration-300 transform hover:scale-105">
          Get Started
        </button>
        <button className="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-3 px-6 rounded-lg shadow-md transition-all duration-300 transform hover:scale-105">
          Learn More
        </button>
      </div>
    </div>
     </div>
  );
};

export default LandingPage;
