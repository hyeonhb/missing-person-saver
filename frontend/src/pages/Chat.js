import { useState, useRef, useEffect } from 'react';
// Components
import BubbleList from '../components/BubbleList'; 
import Modal from '../components/Modal';
import ReportOptions from '../components/ReportOptions'
import QuestionTypeModal from '../components/QuestionTypeModal';
// Utils
import storage from '../utils/storage';
import { isEmptyObject, isEmptyString } from '../utils/validator';
import { formatResponseMsg } from '../utils/converter';
// Styles
import '../styles/Bubble.css';
// API
import userApi from '../api/userApi';
import misperApi from '../api/misperApi';
import messageApi from '../api/messageApi';

const Chat = () => {
  const [showProfileModal, setShowProfileModal] = useState(false);
  const [showQuestionModal, setShowQuestionModal] = useState(true);
  const [messages, setMessages] = useState([]);
  const [newMessage, setNewMessage] = useState('');
  const hasNewMessage = () => !isEmptyString(newMessage);
  const [misperInfo, setMisperInfo] = useState({});
  const [answer, setAnswer] = useState({});
  const [showOptionsModal, setShowOptionsModal] = useState(false);

  const messagesEndRef = useRef(null);
  const inputRef = useRef(null);

  useEffect(() => {
    const userId = storage.get.userId();
    if (!userId) setShowProfileModal(true); // userId가 아직 없으면 제보자 정보 입력받는 Modal부터 띄워주자.
    else initRoom(); // 있으면 바로 채팅 시작
  } , []);

  
  useEffect(() => {
    // 실종자 정보 셋업
    const hasMisperInfo = !isEmptyObject(misperInfo);
    if (hasMisperInfo) {
      let text = '아래 실종자의 제보 및 QnA 창입니다.\n\n';
      for (const key in misperInfo) {
        if (key === 'image_urls') continue;
        text += `${key}: ${misperInfo[key]}\n`;
      }

      // roomId가 있으면 기존 채팅 내역을 불러와서 셋업
      const roomId = storage.get.roomId();
      if (!roomId) return;

      messageApi.getMessageHistory().then(response => {
        const histories = response.map(msg => formatResponseMsg(msg));

        setMessages([{text, isUser: false}, ...messages, ...histories]);
      });
    }
  }, [misperInfo]);

  // 신규 답변 셋업
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

  const initRoom = async () => {
    // 실종자 정보 셋업
    const key = storage.get.misperKey();
    const info = await misperApi.getMisperInfos();

    setMisperInfo(info);
  };

  const handleInputChange = (e) => {
    setNewMessage(e.target.value);
  };

  const updateNewMsg = () => {
    // 입력된 message가 없는데 호출 됐으면 early return
    if (!hasNewMessage()) return;

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
      type: 1,
    };
    messageApi.saveMessages(param).then(response => {
      const formattedAnswer = formatResponseMsg(response);
      setAnswer(formattedAnswer);
    });

    // 버튼을 직접 눌렀을 때를 대비한 auto focusing
    focusInput();
  }

  const handleModalSubmit = async informerInfo => {
    // Modal 컴포넌트에서 넘어온 제보자 정보를 API에 넘겨서 새로 생성된 userId와 roomId를 얻어오자
    const {userId, roomId} = await userApi.getChatRoom(storage.get.misperKey(),informerInfo);

    // 위 API 얻어돈 ID 값들을 storage에 저장
    storage.set.userId(userId);
    storage.set.roomId(roomId);

    // 채팅 시작
    await initRoom();
  };

  const handleQuestionModalSubmit = (questionType) => {
    setShowQuestionModal(false);
  }

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
      {showProfileModal && <Modal onClose={() => setShowProfileModal(false)} onSubmit={handleModalSubmit} />}
      {showQuestionModal && <QuestionTypeModal onClose={() => setShowQuestionModal(false)} onSubmit={handleQuestionModalSubmit} />}
      <section className="chat-messages">
        <BubbleList messages={messages} />
        <div ref={messagesEndRef} />
      </section>
      {!showProfileModal &&
        <div className="chat-input-container">
          <button className="report-button" onClick={() => setShowOptionsModal(true)}>제보</button>
          <input
            ref={inputRef}
            type="text"
            className="chat-input"
            placeholder="질문을 입력해 주세요."
            value={newMessage}
            onChange={handleInputChange}
            onKeyPress={(e) => {
              if (e.key === 'Enter') updateNewMsg();
            }}
          />
          <button className="send-button" disabled={!hasNewMessage()} onClick={updateNewMsg}>
            전송
          </button>
        </div>
      }
      {showOptionsModal && <ReportOptions onClose={() => setShowOptionsModal(false)} />}
    </div>
  );
};

export default Chat;