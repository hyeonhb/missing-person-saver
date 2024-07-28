import React, { useState, useRef, useEffect } from 'react';
import BubbleList from './BubbleList'; 
import '../components/Bubble.css';
import Modal from '../components/Modal';
import Reportoptions from '../components/Reportoptions';

const Chat = () => {
  const [showModal, setShowModal] = useState(true);
  const [messages, setMessages] = useState([]);
  const [newMessage, setNewMessage] = useState('');
  const [userInfo, setUserInfo] = useState({ name: '', contact: '' });
  const [showReportOptions, setShowReportOptions] = useState(false);

  const messagesEndRef = useRef(null);

  useEffect(() => {
    scrollToBottom();
  }, [messages]);

  const handleInputChange = (e) => {
    setNewMessage(e.target.value);
  };

  const handleSendMessage = () => {
    if (newMessage.trim() === '') return;

    const userMessage = { text: newMessage, isUser: true };
    const updatedMessages = [...messages, userMessage];
    setMessages(updatedMessages);
    setNewMessage('');

    setTimeout(() => {
      const botResponse = '정보 확인 중입니다. 잠시만 기다려주세요.';
      const botMessage = { text: botResponse, isUser: false, profileImg: '/chatbot_icon.png' };
      setMessages(prevMessages => [...prevMessages, botMessage]);
    }, 500);
  };

  const closeModal = () => {
    setShowModal(false);
  };

  const handleModalSubmit = (name, contact) => {
    setUserInfo({ name, contact });

    const initialBotMessage = {
      text: '7월 21일 13:53에 실종된 ㅇㅇㅇ에 대한 제보 서비스입니다. 무엇을 확인하시겠습니까?',
      isUser: false,
      profileImg: '/chatbot_icon.png',
      options: ['옵션1', '옵션2', '즉시제보']
    };
    setMessages([initialBotMessage]);
  };

  const toggleReportOptions = () => {
    setShowReportOptions(!showReportOptions);
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
        <BubbleList messages={messages} />
        <div ref={messagesEndRef}></div>
      </section>
      <div className="chat-input-container">
        <button className="report-button" onClick={toggleReportOptions}>제보</button>
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
        <button className="send-button" onClick={handleSendMessage}>전송</button>
      </div>
      {showReportOptions && <Reportoptions onClose={toggleReportOptions} />}
    </div>
  );
};

export default Chat;