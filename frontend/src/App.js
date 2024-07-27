import './styles/App.css';
import Header from './components/Header.js';
import Chat from './pages/Chat.js';

function App() {
  return (
    <section className='container'>
      <Header title='MPS(Missing Person Saver)' />
      <Chat />
    </section>   
  );
}

export default App;
