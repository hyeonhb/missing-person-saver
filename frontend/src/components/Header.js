import React from 'react';

function Header(props) {
  return (
    <header className='header'>
      <p>{props.title}</p>
    </header>
  );
}

export default Header;