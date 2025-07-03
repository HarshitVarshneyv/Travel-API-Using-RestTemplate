import React, { useState } from 'react';

// Main App component for the Travel Booking Page
const App = () => {
    // State for OS type, initialized as an empty string
    const [osType, setOsType] = useState('');
    // State for passenger details, initialized with one empty passenger object
    const [paxDetails, setPaxDetails] = useState([
        { name: '', email: '', isPrimaryPax: false }
    ]);
    // State for the requestor's email
    const [requestorEmail, setRequestorEmail] = useState('');
    // State for managing the custom message box visibility and content
    const [messageBox, setMessageBox] = useState({
        visible: false,
        type: '', // 'success' or 'error'
        message: ''
    });

    /**
     * Handles changes to individual passenger details.
     * @param {number} index - The index of the passenger in the paxDetails array.
     * @param {string} field - The field to update (e.g., 'name', 'email', 'isPrimaryPax').
     * @param {any} value - The new value for the field.
     */
    const handlePaxChange = (index, field, value) => {
        setPaxDetails(prev => {
            const updated = [...prev];
            // Update the specific field for the passenger at the given index
            updated[index][field] = value;
            return updated;
        });
    };

    /**
     * Adds a new empty traveller object to the paxDetails array.
     */
    const addTraveller = () => {
        setPaxDetails(prev => [...prev, { name: '', email: '', isPrimaryPax: false }]);
    };

    /**
     * Removes a traveller from the paxDetails array based on their index.
     * @param {number} index - The index of the traveller to remove.
     */
    const removeTraveller = (index) => {
        setPaxDetails(prev => prev.filter((_, i) => i !== index));
    };

    /**
     * Displays a custom message box with a given type and message.
     * @param {string} type - The type of message ('success' or 'error').
     * @param {string} message - The message to display.
     */
    const showMessageBox = (type, message) => {
        setMessageBox({ visible: true, type, message });
        // Automatically hide the message box after 3 seconds
        setTimeout(() => {
            setMessageBox({ visible: false, type: '', message: '' });
        }, 3000);
    };

    /**
     * Handles the form submission.
     * Prevents default form submission and sends data to the backend.
     * @param {Event} e - The form submission event.
     */
    const handleSubmit = async (e) => {
        e.preventDefault(); // Prevent default form submission

        // Construct the payload based on current state
        const payload = {
            deviceDetails: { osType },
            // Ensure paxDetails is directly under travellerDetails, not nested in another object
            travellerDetails: { paxDetails },
            requestorInfo: { email: requestorEmail }
            // tripId will auto-generate in backend as per original comment
        };

        console.log("Payload ready to send to MyBiz:", payload);

        try {
            // Simulate API call using a placeholder URL
            // In a real application, replace this with your actual backend URL
            const response = await fetch("http://localhost:8080/api/travel/create-itinerary", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(payload)
            });

            // Check if the response was successful
            if (!response.ok) {
                // If response is not ok, throw an error with status text
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const data = await response.json(); // Parse JSON response
            console.log("Success response from MyBiz:", data);
            showMessageBox('success', 'Travel request submitted successfully!');

            if(data.redirectUrl){
                alert("Redirecting to the Mybiz to book your details...");  
                window.open(data.redirectUrl, "_blank");
            } else{
                alert("No redirect URL provided by MyBiz.");
            }
        } catch (error) {
            console.error("Error submitting travel request:", error);
            showMessageBox('error', `Error submitting travel request: ${error.message}`);
        }
    };

    return (
        // Main container with responsive padding and max-width, centered
        <div className="min-h-screen bg-gray-100 flex items-center justify-center p-4 sm:p-6 lg:p-8 font-inter">
            <div className="bg-white p-6 sm:p-8 rounded-xl shadow-lg w-full max-w-md">
                <h2 className="text-2xl sm:text-3xl font-bold text-gray-800 mb-6 text-center">
                    Travel Booking Form
                </h2>
                <form onSubmit={handleSubmit} className="space-y-6">
                    {/* Device OS Type Section */}
                    <div className="flex flex-col">
                        <label htmlFor="osType" className="text-gray-700 font-medium mb-2">
                            Device OS Type:
                        </label>
                        <input
                            id="osType"
                            type="text"
                            value={osType}
                            onChange={(e) => setOsType(e.target.value)}
                            placeholder="e.g., desktop, iOS, Android"
                            required
                            className="p-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent transition duration-200"
                        />
                    </div>

                    {/* Traveller Details Section */}
                    <h3 className="text-xl font-semibold text-gray-800 mt-8 mb-4">Traveller Details</h3>
                    {paxDetails.map((pax, index) => (
                        <div
                            key={index}
                            className="border border-gray-200 p-4 rounded-lg shadow-sm bg-gray-50 relative"
                        >
                            <p className="text-lg font-semibold text-gray-700 mb-3">Traveller {index + 1}</p>
                            <div className="grid grid-cols-1 gap-4">
                                {/* Traveller Name Input */}
                                <div className="flex flex-col">
                                    <label htmlFor={`name-${index}`} className="text-gray-700 font-medium mb-1">Name:</label>
                                    <input
                                        id={`name-${index}`}
                                        type="text"
                                        value={pax.name}
                                        onChange={(e) => handlePaxChange(index, 'name', e.target.value)}
                                        required
                                        className="p-2 border border-gray-300 rounded-md focus:ring-1 focus:ring-blue-400"
                                    />
                                </div>
                                {/* Traveller Email Input */}
                                <div className="flex flex-col">
                                    <label htmlFor={`email-${index}`} className="text-gray-700 font-medium mb-1">Email:</label>
                                    <input
                                        id={`email-${index}`}
                                        type="email"
                                        value={pax.email}
                                        onChange={(e) => handlePaxChange(index, 'email', e.target.value)}
                                        required
                                        className="p-2 border border-gray-300 rounded-md focus:ring-1 focus:ring-blue-400"
                                    />
                                </div>
                                {/* Primary Pax Checkbox */}
                                <div className="flex items-center mt-2">
                                    <input
                                        id={`primary-pax-${index}`}
                                        type="checkbox"
                                        checked={pax.isPrimaryPax}
                                        onChange={(e) => handlePaxChange(index, 'isPrimaryPax', e.target.checked)}
                                        className="h-4 w-4 text-blue-600 border-gray-300 rounded focus:ring-blue-500"
                                    />
                                    <label htmlFor={`primary-pax-${index}`} className="ml-2 text-gray-700">
                                        Is Primary Pax
                                    </label>
                                </div>
                            </div>
                            {/* Remove Traveller Button (only if more than one traveller) */}
                            {paxDetails.length > 1 && (
                                <button
                                    type="button"
                                    onClick={() => removeTraveller(index)}
                                    className="absolute top-3 right-3 p-2 bg-red-100 text-red-600 rounded-full hover:bg-red-200 focus:outline-none focus:ring-2 focus:ring-red-500 transition duration-200"
                                    title="Remove Traveller"
                                >
                                    {/* Using a simple X icon for removal */}
                                    <svg xmlns="http://www.w3.org/2000/svg" className="h-4 w-4" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                        <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M6 18L18 6M6 6l12 12" />
                                    </svg>
                                </button>
                            )}
                        </div>
                    ))}
                    {/* Add Traveller Button */}
                    <button
                        type="button"
                        onClick={addTraveller}
                        className="w-full bg-blue-500 text-white p-3 rounded-lg hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-opacity-50 transition duration-200 flex items-center justify-center space-x-2"
                    >
                        <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M12 6v6m0 0v6m0-6h6m-6 0H6" />
                        </svg>
                        <span>Add Traveller</span>
                    </button>

                    {/* Requestor Info Section */}
                    <div className="flex flex-col mt-6">
                        <label htmlFor="requestorEmail" className="text-gray-700 font-medium mb-2">
                            Requestor Email:
                        </label>
                        <input
                            id="requestorEmail"
                            type="email"
                            value={requestorEmail}
                            onChange={(e) => setRequestorEmail(e.target.value)}
                            required
                            className="p-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent transition duration-200"
                        />
                    </div>

                    {/* Submit Button */}
                    <button
                        type="submit"
                        className="w-full bg-green-600 text-white p-3 rounded-lg font-semibold text-lg hover:bg-green-700 focus:outline-none focus:ring-2 focus:ring-green-500 focus:ring-opacity-50 transition duration-200 shadow-md"
                    >
                        Submit Travel Request
                    </button>
                </form>

                {/* Custom Message Box */}
                {messageBox.visible && (
                    <div
                        className={`fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50`}
                    >
                        <div
                            className={`p-6 rounded-lg shadow-xl text-center max-w-sm mx-auto
                                ${messageBox.type === 'success' ? 'bg-green-100 text-green-800 border border-green-300' : 'bg-red-100 text-red-800 border border-red-300'}`
                            }
                        >
                            <p className="text-lg font-medium">{messageBox.message}</p>
                            <button
                                onClick={() => setMessageBox({ ...messageBox, visible: false })}
                                className="mt-4 px-4 py-2 bg-gray-200 text-gray-800 rounded-md hover:bg-gray-300 focus:outline-none focus:ring-2 focus:ring-gray-400"
                            >
                                Close
                            </button>
                        </div>
                    </div>
                )}
            </div>
        </div>
    );
};

export default App; // Export the App component as default
