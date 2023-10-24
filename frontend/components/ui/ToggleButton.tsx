// components/ToggleButton.js
'use client'
import React, { useState } from 'react';

const ToggleButton = ({status}: {status: boolean}) => {
  const [isActive, setIsActive] = useState(status);

  const handleToggle = () => {
    setIsActive(!isActive);
  };

  return (
    <button
      onClick={handleToggle}
      className={`${
        isActive ? 'bg-blue-500' : 'bg-gray-300'
      } w-8 h-4 rounded-full  transition-all duration-300 ease-in-out`}
    >
      <div
        className={`${
          isActive ? 'translate-x-5' : 'translate-x-0'
        } transform h-4 w-4 bg-white rounded-full transition-transform duration-300 ease-in-out`}
      ></div>
    </button>
  );
};

export default ToggleButton;