import React, { useEffect, useState } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

// Styles
import './styles/App.css';
import './styles/InitialScreen.css';
import './styles/ReportList.css';

// Components
import Header from './components/Header.js';
import InitialScreen from './pages/InitialScreen.js';
import Chat from './pages/Chat.js';
import Auth from './pages/Auth.js'; // Auth 컴포넌트 가져오기
import ReportDetail from './pages/ReportDetail.js'; // ReportDetail 컴포넌트 가져오기

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
    <Router>
      <section className='container'>
        {!showInitialScreen && <Header title='MPS(실종자 제보 서비스)' />}
        <Routes>
          <Route
            path="/"
            element={showInitialScreen ? (
              <InitialScreen onAnimationComplete={handleAnimationComplete} />
            ) : (
              <Chat />
            )}
          />
          <Route path="/auth" element={<Auth />} />
          <Route path="/report/:id" element={<ReportDetail />} />
        </Routes>
      </section>
    </Router>
  );
}

export default App;