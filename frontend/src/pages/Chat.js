import React, { useState } from 'react';
import Bubble from '../components/Bubble.js';
import '../components/Bubble.css';
import Modal from '../components/Modal';

const Chat = () => {
  const [showModal, setShowModal] = useState(true);
  const [messages, setMessages] = useState([]);
  const [newMessage, setNewMessage] = useState('');
  const [userInfo, setUserInfo] = useState({ name: '', contact: '' });

  const handleInputChange = (e) => {
    setNewMessage(e.target.value);
  };

  const handleSendMessage = () => {
    if (newMessage.trim() === '') return;
  
    // 새로운 메시지를 생성하여 상태를 업데이트
    const userMessage = { text: newMessage, isUser: true };
    const updatedMessages = [...messages, userMessage];
    setMessages(updatedMessages);
    setNewMessage('');


    // 챗봇 응답을 지연시키기 위해 setTimeout 사용
    setTimeout(() => {
      const botResponse = '안녕하세요! 저는 챗봇입니다.';
      const botMessage = { text: botResponse, isUser: false };
      setMessages((prevMessages) => [...prevMessages, botMessage]);

      // 맨 아래로 스크롤
      scrollToBottom();
    }, 500); // 챗봇 응답 딜레이 설정 예시
  };

  // 모달을 닫는 함수
  const closeModal = () => {
    setShowModal(false);
  };

  const handleModalSubmit = (name, contact) => {
    setUserInfo({ name, contact });
  };

  // 채팅을 재시작하는 함수
  const restartChat = () => {
    setMessages([]);
    setNewMessage('');
    setShowModal(true);
  };

  // 맨 아래로 스크롤하는 함수
  const scrollToBottom = () => {
    const chatContainer = document.getElementsByClassName('chat-messages')[0];
    if (chatContainer) {
      chatContainer.scrollTop = chatContainer.scrollHeight;
    }
  };

  return (
    <div className="chat-container">
      {showModal && <Modal onClose={closeModal} onSubmit={handleModalSubmit} />}
      <section className="chat-messages">
        {messages.map((message, index) => (
          <Bubble key={index} text={message.text} isUser={message.isUser} />
        ))}
      </section>
      <div className="chat-input-container">
        <input
          type="text"
          className="chat-input"
          placeholder="메시지를 입력하세요..."
          value={newMessage}
          onChange={handleInputChange}
          onKeyPress={(e) => {
            if (e.key === 'Enter') handleSendMessage();
          }}
        />
        <button className="send-button" onClick={handleSendMessage}>
          전송
        </button>
      </div>
      <button onClick={restartChat}>채팅 재시작</button>
    </div>
  );
};

export default Chat;
