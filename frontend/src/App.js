import React, { useEffect, useState } from 'react';
// Styles
import './styles/App.css';
import './styles/InitialScreen.css';

// Components
import Header from './components/Header.js';
import InitialScreen from './pages/InitialScreen.js';
import Chat from './pages/Chat.js';
// Utils
import storage from './utils/storage.js';

function App() {
  const [showInitialScreen, setShowInitialScreen] = useState(true);

  useEffect(() => {
    setMisperKey();
  }, []);

  function setMisperKey() {
    const urlParams = new URLSearchParams(window.location.search);
    const key = urlParams.get('misper') || 'TEST_KEY';
    storage.set.misperKey(key);
  }

  const handleAnimationComplete = () => {
    setShowInitialScreen(false);
  };

  return (
    <section className='container'>
      {!showInitialScreen && <Header title='MPS(실종자 제보 서비스)' />}
      {showInitialScreen ? (
        <InitialScreen onAnimationComplete={handleAnimationComplete} />
      ) : (
        <Chat />
      )}
    </section>
  );
}

export default App;