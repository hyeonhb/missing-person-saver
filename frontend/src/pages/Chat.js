import { useState, useRef, useEffect } from 'react';
// Components
import BubbleList from './BubbleList'; 
import Modal from '../components/Modal';
// Utils
import storage from '../utils/storage';
import { isEmptyObject } from '../utils/validator';
import { formatResponseMsg } from '../utils/converter';
// Styles
import '../styles/Bubble.css';
// API
import userApi from '../api/userApi';
import misperApi from '../api/misperApi';
import messageApi from '../api/messageApi';

const Chat = () => {
  const [showModal, setShowModal] = useState(false);
  const [messages, setMessages] = useState([]);
  const [newMessage, setNewMessage] = useState('');
  const [misperInfo, setMisperInfo] = useState({});
  const [answer, setAnswer] = useState({});

  const messagesEndRef = useRef(null);
  const inputRef = useRef(null);

  useEffect(() => {
    const userId = storage.get.userId();
    if (!userId) openModal(); // userId가 아직 없으면 제보자 정보 입력받는 Modal부터 띄워주자.
    else initRoom(); // 있으면 바로 채팅 시작
  } , []);

  useEffect(() => {
    const hasAnswer = !isEmptyObject(answer);
    if (hasAnswer) {
      setMessages([...messages, answer]);

      // 답변 초기화
      setAnswer({});
    }
  }, [answer]);

  useEffect(() => {
    scrollToBottom();
  }, [messages]);

  function openModal() {
    setShowModal(true);
  }

  const initRoom = async () => {
    // 실종자 정보 셋업
    const key = storage.get.misperKey();
    const info = await misperApi.getMisperInfo({uid: key});
    setMisperInfo(info);

    // roomId가 있으면 기존 채팅 내역을 불러와서 셋업해주자.
    const roomId = storage.get.roomId();
    if (!roomId) return;

    const response = await messageApi.getMessageHistory({roomId});
    const histories = response.messages.map(msg => formatResponseMsg(msg));

    setMessages([...histories]);
  };

  const handleInputChange = (e) => {
    setNewMessage(e.target.value);
  };

  const updateNewMsg = () => {
    // user가 보낸 메시지를 BubbleList에 새로운 Bubble로 생성 (user-bubble)
    const updatedMsg = {
      text: newMessage,
      isUser: true,
    };
    setMessages([...messages, updatedMsg]);
    setNewMessage(''); // 입력창 값 초기화

    // 신규 질문에 대한 답변 가져와서 이것도 새로운 Bubble로 생성 (bot-bubble)
    const param = {
      question: updatedMsg.text,
      roomId: storage.get.roomId(),
    };
    messageApi.getAnswer(param).then(response => {
      const formattedAnswer = formatResponseMsg(response);
      setAnswer(formattedAnswer);
    });

    // 버튼을 직접 눌렀을 때를 대비한 auto focusing
    focusInput();
  }

  function updateAnswer() {
    
  }

  const closeModal = () => {
    setShowModal(false);
  };

  const handleModalSubmit = async informerInfo => {
    // Modal 컴포넌트에서 넘어온 제보자 정보를 API에 넘겨서 새로 생성된 userId와 roomId를 얻어오자
    const {userId, roomId} = await userApi.saveUserProfile(informerInfo);

    // 위 API 얻어돈 ID 값들을 storage에 저장
    storage.set.userId(userId);
    storage.set.roomId(roomId);

    // 채팅 시작
    initRoom();
  };

  const scrollToBottom = () => {
    if (messagesEndRef.current) {
      messagesEndRef.current.scrollIntoView({ behavior: 'smooth' });
    }
  };

  const focusInput = () => {
    if (inputRef.current) inputRef.current.focus();
  }

  return (
    <div className="chat-container">
      {showModal && <Modal onClose={closeModal} onSubmit={handleModalSubmit} />}
      <section className="chat-messages">
        <BubbleList messages={messages} />
        <div ref={messagesEndRef} />
      </section>
      <div className="chat-input-container">
          <input
            ref={inputRef}
            type="text"
            className="chat-input"
            placeholder="메시지를 입력하세요."
            value={newMessage}
            onChange={handleInputChange}
            onKeyPress={(e) => {
              if (e.key === 'Enter') updateNewMsg();
            }}
          />
          <button className="send-button" onClick={updateNewMsg}>
            전송
          </button>
        </div>
    </div>
  );
};

export default Chat;