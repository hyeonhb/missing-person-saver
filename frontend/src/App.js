import { useEffect } from 'react';
// Styles
import './styles/App.css';
// Components
import Header from './components/Header.js';
import Chat from './pages/Chat.js';
// Utils
import storage from './utils/storage.js';

function App() {
  useEffect(() => {
    setMisperKey();
  }, []);

  function setMisperKey() {
    const urlParams = new URLSearchParams(window.location.search);
    const key = urlParams.get('misper') || '5768021';

    storage.set.misperKey(key);
  }

  return (
    <section className='container'>
      <Header title='MPS(실종자 제보 서비스)' />
      <Chat />
    </section>   
  );
}

export default App;
