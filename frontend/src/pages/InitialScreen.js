import React, { useEffect } from 'react';
import '../styles/InitialScreen.css';

const InitialScreen = ({ onAnimationComplete }) => {
  useEffect(() => {
    const timer = setTimeout(() => {
      onAnimationComplete();
    }, 2000); // 2초 후에 onAnimationComplete 호출

    return () => clearTimeout(timer); // 컴포넌트 언마운트 시 타이머 정리
  }, [onAnimationComplete]);

  return (
    <div className="initial-screen">
      <img src="/InitialScreen.png" alt="Initial Screen" className="initial-image" />
    </div>
  );
};

export default InitialScreen;