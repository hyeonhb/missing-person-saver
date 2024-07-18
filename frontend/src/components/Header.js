function Header(props) {
    return (
     <header style={{height: '30px',backgroundColor:"pink",display:"flex",alignItems:"center",justifyContent:"center"}}>
        {props.title}
     </header>
    );
  }
  
  export default Header;