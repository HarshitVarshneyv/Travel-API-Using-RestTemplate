import React from 'react'
import {Routes, Route} from 'react-router-dom'
import LandingPage from './pages/LandingPage'
import TravelBookPage from './pages/TravelBookPage'

const App = () => { 
  return (
    <>
    <Routes>
      <Route path='/' element={<LandingPage/>} />
      <Route path='/create/trip' element={<TravelBookPage/>} />
    </Routes>
    </>
  )
}

export default App