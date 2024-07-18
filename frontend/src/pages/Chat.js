import React, { useState } from 'react';
import Bubble from '../components/Bubble.js';
import '../components/Bubble.css';

const Chat = () => {
  const [messages, setMessages] = useState([]);
  const [newMessage, setNewMessage] = useState('');

  const handleInputChange = (e) => {
    setNewMessage(e.target.value);
  };

  const handleSendMessage = () => {
    if (newMessage.trim() === '') return;
  
    // 새로운 메시지를 생성하여 상태를 업데이트
    const userMessage = { text: newMessage, isUser: true };
    const newMessages = [...messages, userMessage];
    setMessages(newMessages);
    setNewMessage('');
  
    // 챗봇 응답을 지연시키기 위해 setTimeout 사용
    setTimeout(() => {
      const botResponse = '안녕하세요! 저는 챗봇입니다.';
      const updatedMessages = [...newMessages, { text: botResponse, isUser: false }];
      setMessages(updatedMessages);
  
      // 맨 아래로 스크롤
      scrollToBottom();
    }, 500); // 챗봇 응답 딜레이 설정 예시
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
    </div>
  );
};

export default Chat;