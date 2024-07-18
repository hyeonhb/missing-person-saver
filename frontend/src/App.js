import './App.css';
import Header from './components/Header.js';
import Chat from './pages/Chat.js';

function App() {
  return (
    <section style={{display:'flex',flexDirection: 'column',width: '100%', height:'100%'}}>
      <Header title='Header' />
      <Chat />
    </section>
  );
}

export default App;
