import React, { useState, useRef, useEffect } from 'react';
import BubbleList from './BubbleList'; 
import '../components/Bubble.css';
import Modal from '../components/Modal';

// 옵션 처리 로직 분리
const handleOption1 = (setMessages, setWaitingForOption) => {
  const userMessage = { text: '옵션1이 선택되었습니다.', isUser: true };
  setMessages(prevMessages => [...prevMessages, userMessage]);

  setTimeout(() => {
    const botResponse = '옵션1에 대한 하위 옵션을 선택하세요.';
    const botMessage = {
      text: botResponse,
      isUser: false,
      profileImg: '/chatbot_icon.png',
      options: ['옵션1-1', '즉시제보']
    };
    setMessages(prevMessages => [...prevMessages, botMessage]);
    setWaitingForOption(true);
  }, 500);
};

const handleOption1_1 = (setMessages, setWaitingForOption) => {
  const userMessage = { text: '옵션1-1이 선택되었습니다.', isUser: true };
  setMessages(prevMessages => [...prevMessages, userMessage]);

  setTimeout(() => {
    const botResponse = '옵션1-1에 대한 하위 옵션을 선택하세요.';
    const botMessage = {
      text: botResponse,
      isUser: false,
      profileImg: '/chatbot_icon.png',
      options: ['옵션1-1-1', '즉시제보']
    };
    setMessages(prevMessages => [...prevMessages, botMessage]);
    setWaitingForOption(true);
  }, 500);
};

// '즉시제보' 클릭 시 처리 로직
const handleImmediateReport = (newMessage, setMessages, setShowInput) => {
  const userMessage = { text: newMessage, isUser: true };
  setMessages(prevMessages => [...prevMessages, userMessage]);

  setTimeout(() => {
    const botResponse = '제보내용이 정상적으로 접수되었습니다. 정보제공에 감사드리며 앞으로도 많은 협조바랍니다.';
    const botMessage = {
      text: botResponse,
      isUser: false,
      profileImg: '/chatbot_icon.png',
      options: ['채팅종료', '추가제보']
    };
    setMessages(prevMessages => [...prevMessages, botMessage]);
    setShowInput(false); // 메시지 입력창 비활성화
  }, 500);
};

// '추가제보' 클릭 시 처리 로직
const handleAdditionalReport = (setMessages, setShowInput) => {
  setShowInput(true); // 메시지 입력창 활성화

  setTimeout(() => {
    const botResponse = '추가 제보를 입력하세요.';
    const botMessage = {
      text: botResponse,
      isUser: false,
      profileImg: '/chatbot_icon.png'
    };
    setMessages(prevMessages => [...prevMessages, botMessage]);
  }, 500);
};

const Chat = () => {
  const [showModal, setShowModal] = useState(true);
  const [messages, setMessages] = useState([]);
  const [newMessage, setNewMessage] = useState('');
  const [userInfo, setUserInfo] = useState({ name: '', contact: '' });
  const [waitingForOption, setWaitingForOption] = useState(false);
  const [showInput, setShowInput] = useState(false);

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
    setWaitingForOption(true);
  };

  const handleOptionSelect = (option) => {
    if (option === '즉시제보') {
      setWaitingForOption(false);
      setShowInput(true); // '즉시제보' 선택 시 메시지 입력창 활성화
      return;
    }
    if (option === '추가제보') {
      handleAdditionalReport(setMessages, setShowInput);
      return;
    }
    
    setWaitingForOption(false);
    setShowInput(false); // 하위 옵션 선택 시 입력창 비활성화
    const userMessage = { text: `사용자 선택: ${option}`, isUser: true };
    setMessages(prevMessages => [...prevMessages, userMessage]);

    if (option === '옵션1') {
      handleOption1(setMessages, setWaitingForOption);
    } else if (option === '옵션1-1') {
      handleOption1_1(setMessages, setWaitingForOption);
    } else if (option === '옵션2') {
      // Handle option 2 similarly
    } else {
      setTimeout(() => {
        const botResponse = `선택된 옵션은 '${option}'입니다. 추가로 도움이 필요하시면 말씀해 주세요.`;
        const botMessage = { text: botResponse, isUser: false, profileImg: '/chatbot_icon.png' };
        setMessages(prevMessages => [...prevMessages, botMessage]);
      }, 500);
    }
  };

  const handleImmediateReportSubmit = () => {
    handleImmediateReport(newMessage, setMessages, setShowInput);
    setNewMessage(''); // 메시지 입력 후 입력창 비우기
  };

  const handleAdditionalReportSubmit = () => {
    handleImmediateReport(newMessage, setMessages, setShowInput);
    setNewMessage(''); // 메시지 입력 후 입력창 비우기
  };

  const restartChat = () => {
    setMessages([]);
    setNewMessage('');
    setWaitingForOption(false);
    setShowInput(false);

    const initialBotMessage = {
      text: '7월 21일 13:53에 실종된 ㅇㅇㅇ에 대한 제보 서비스입니다. 무엇을 확인하시겠습니까?',
      isUser: false,
      profileImg: '/chatbot_icon.png',
      options: ['옵션1', '옵션2', '즉시제보']
    };
    setMessages([initialBotMessage]); // 초기 봇 메시지로 돌아가기
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
        <BubbleList messages={messages} onOptionSelect={handleOptionSelect} />
        <div ref={messagesEndRef}></div>
      </section>
      {showInput && (
        <div className="chat-input-container">
          <input
            type="text"
            className="chat-input"
            placeholder="메시지를 입력하세요."
            value={newMessage}
            onChange={handleInputChange}
            onKeyPress={(e) => {
              if (e.key === 'Enter') handleImmediateReportSubmit();
            }}
          />
          <button className="send-button" onClick={handleImmediateReportSubmit}>
            전송
          </button>
        </div>
      )}
      <button onClick={restartChat}>채팅 재시작</button>
    </div>
  );
};

export default Chat;