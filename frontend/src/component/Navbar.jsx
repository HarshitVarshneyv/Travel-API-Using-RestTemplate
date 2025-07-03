import React, { useState } from 'react';

// Navbar component
const Navbar = () => {
  const [isOpen, setIsOpen] = useState(false); // State to control mobile menu visibility

  // Function to toggle the mobile menu
  const toggleMenu = () => {
    setIsOpen(!isOpen);
  };

  // Navigation links data
  const navLinks = [
    { name: 'Home', href: '#home' },
    { name: 'Destinations', href: '#destinations' },
    { name: 'Flights', href: '#flights' },
    { name: 'Hotels', href: '#hotels' },
    { name: 'Bookings', href: '#bookings' },
    { name: 'Contact', href: '#contact' },
  ];

  return (
    <nav className="bg-gradient-to-r from-blue-600 to-blue-800 p-4 shadow-lg rounded-b-lg">
      <div className="container mx-auto flex justify-between items-center">
        {/* Brand/Logo */}
        <a href="#top" className="text-white text-2xl md:text-3xl font-extrabold tracking-wide rounded-md p-2 hover:bg-blue-700 transition-colors duration-300">
          TravelAPI
        </a>

        {/* Mobile menu button */}
        <div className="md:hidden">
          <button
            onClick={toggleMenu}
            className="text-white focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-offset-blue-800 focus:ring-white rounded-md p-2 transition-transform duration-300 transform hover:scale-105"
            aria-label="Toggle navigation"
          >
            {isOpen ? (
              // Close icon (X)
              <svg
                className="h-8 w-8"
                fill="none"
                stroke="currentColor"
                viewBox="0 0 24 24"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  strokeWidth="2"
                  d="M6 18L18 6M6 6l12 12"
                ></path>
              </svg>
            ) : (
              // Hamburger icon
              <svg
                className="h-8 w-8"
                fill="none"
                stroke="currentColor"
                viewBox="0 0 24 24"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  strokeWidth="2"
                  d="M4 6h16M4 12h16m-7 6h7"
                ></path>
              </svg>
            )}
          </button>
        </div>

        {/* Desktop navigation links */}
        <div className="hidden md:flex space-x-6">
          {navLinks.map((link) => (
            <a
              key={link.name}
              href={link.href}
              // Removed setActivePage since it's not passed as a prop in the user's provided structure
              // You would need to pass setActivePage as a prop to Navbar if you want to use it here
              className="text-white hover:text-blue-200 transition-colors duration-300 text-lg font-medium px-3 py-2 rounded-md hover:bg-blue-700"
            >
              {link.name}
            </a>
          ))}
        </div>
      </div>

      {/* Mobile navigation menu (conditionally rendered) */}
      {isOpen && (
        <div className="md:hidden mt-4 bg-blue-700 rounded-lg shadow-inner p-4">
          <div className="flex flex-col space-y-3">
            {navLinks.map((link) => (
              <a
                key={link.name}
                href={link.href}
                onClick={() => {
                  // Removed setActivePage here as well
                  setIsOpen(false); // Close menu after clicking a link
                }}
                className="text-white hover:text-blue-200 transition-colors duration-300 text-lg font-medium px-4 py-2 rounded-md hover:bg-blue-600 block text-center"
              >
                {link.name}
              </a>
            ))}
          </div>
        </div>
      )}
    </nav>
  );
};

export default Navbar; // Export the Navbar component
