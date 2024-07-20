export const handleOption1 = (setMessages, setWaitingForOption) => {
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
  
  export const handleOption1_1 = (setMessages, setWaitingForOption) => {
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

// 하위 옵션 추가시 해당 코드 수정

  export const handleOption1_1_1 = (setMessages, setWaitingForOption) => {
    const userMessage = { text: '옵션1-1-1이 선택되었습니다.', isUser: true };
    setMessages(prevMessages => [...prevMessages, userMessage]);
  
    setTimeout(() => {
      const botResponse = '옵션1-1-1에 대한 응답을 처리합니다.';
      const botMessage = { text: botResponse, isUser: false, profileImg: '/chatbot_icon.png' };
      setMessages(prevMessages => [...prevMessages, botMessage]);
      setWaitingForOption(false);
    }, 500);
  };