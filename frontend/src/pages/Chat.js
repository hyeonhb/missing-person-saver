import React, { useState, useRef, useEffect } from 'react';
import Bubble from '../components/Bubble.js';
import '../components/Bubble.css';
import Modal from '../components/Modal';

const Chat = () => {
  const [showModal, setShowModal] = useState(true);
  const [messages, setMessages] = useState([]);
  const [newMessage, setNewMessage] = useState('');
  const [userInfo, setUserInfo] = useState({ name: '', contact: '', profileImg: '/user-profile-img.png' });

  const messagesEndRef = useRef(null);

  useEffect(() => {
    scrollToBottom();
  }, [messages]);

  const handleInputChange = (e) => {
    setNewMessage(e.target.value);
  };

  const handleSendMessage = () => {
    if (newMessage.trim() === '') return;

    const userMessage = { text: newMessage, isUser: true, profileImg: userInfo.profileImg };
    const updatedMessages = [...messages, userMessage];
    setMessages(updatedMessages);
    setNewMessage('');

    setTimeout(() => {
      const botResponse = '정보 확인 중입니다. 잠시만 기다려주세요.';
      const botMessage = { text: botResponse, isUser: false, profileImg: '/bot-profile-img.png' };
      setMessages((prevMessages) => [...prevMessages, botMessage]);
    }, 500);
  };

  const closeModal = () => {
    setShowModal(false);
  };

  const handleModalSubmit = (name, contact) => {
    setUserInfo({ name, contact, profileImg: '/user-profile-img.png' });

    // 모달에서 정보 입력이 완료되면 바로 챗봇의 초기 메시지를 추가합니다.
    const initialBotMessage = '7월 21일 13:53에 실종된 ㅇㅇㅇ에 대한 제보 서비스입니다. 무엇을 확인하시겠습니까?';
    const initialBotMessageObj = { text: initialBotMessage, isUser: false, profileImg: '/bot-profile-img.png' };
    setMessages([initialBotMessageObj]);
  };

  const restartChat = () => {
    setMessages([]);
    setNewMessage('');
    setShowModal(true);
  };

  const scrollToBottom = () => {
    if (messagesEndRef.current) {
      messagesEndRef.current.scrollIntoView({ behavior: 'smooth' });
    }
  };

  return (
    <div className="chat-container">
      {showModal && <Modal onClose={closeModal} onSubmit={handleModalSubmit} />}
      <section className="chat-messages">
        {messages.map((message, index) => (
          <div key={index} className={`message-container ${message.isUser ? 'user' : 'bot'}`}>
            <div className={`bubble-container ${message.isUser ? 'user-message' : 'bot-message'}`}>
              {!message.isUser && (
                <div className="profile">
                  <img src={message.profileImg} alt="Profile" className="profile-img" />
                </div>
              )}
              <Bubble text={message.text} isUser={message.isUser} />
              {message.isUser && (
                <div className="profile">
                  <img src={message.profileImg} alt="Profile" className="profile-img" />
                </div>
              )}
            </div>
          </div>
        ))}
        <div ref={messagesEndRef}></div>
      </section>
      <div className="chat-input-container">
        <input
          type="text"
          className="chat-input"
          placeholder="메시지를 입력하세요."
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
