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
    const key = urlParams.get('misper') || 'TEST_KEY';

    storage.set.misperKey(key);
  }

  return (
    <section className='container'>
      <Header title='MPS(Missing Person Saver)' />
      <Chat />
    </section>   
  );
}

export default App;
