import React from 'react';

function Header(props) {
  return (
    <header style={{ height: '40px', backgroundColor: 'pink', display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
      <h1>{props.title}</h1>
    </header>
  );
}

export default Header;