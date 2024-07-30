import apiController from "./apiController";

const messageApi = {
    getTestResponse: async () => {
        return await apiController.get('https://jsonplaceholder.typicode.com/posts')
    },
    /**
     * @param {object} request - roomId: Number
     * @returns {object} messages: Array[Object] -> {chatId: Number, content: String, createdDate: Datetime, type: Number}
     */
    getMessageHistory: async param => {
        return new Promise(res => {
            res({
                messages: [
                    {chatId: 0, content: '실종자가 모자를 썼나요 ??', createdDate: '2024-01-02T09:12:46', type: 1},
                    {chatId: 1, content: '아니요. 모자를 쓰지 않았습니다.', createdDate: '2024-01-02T09:13:46', type: 2},
                    {chatId: 2, content: '포스코 본사 1층에서 실종자를 봤습니다.', createdDate: '2024-01-02T09:14:46', type: 3},
                    {chatId: 3, content: '소중한 제보 감사합니다! 신속하게 조치하도록 하겠습니다.', createdDate: '2024-01-02T09:15:46', type: 2},
                ],
            })
        });
    },
    /**
     * @param {object} request - roomId: Number, question: String
     * @returns {object} chatId: Number, content: String, createdDate: Datetime, type: Number
     */
    getAnswer: async param => {
        return new Promise(res => {
            setTimeout(() => {
                res({
                    chatId: 0,
                    content: `아래 채팅에 대한 답변입니다.\n\n질문: ${param.question}`,
                    createdDate: '2024-01-02T09:12:46',
                    type: 2,
                });
            }, 1000);
        });
    },
}

export default messageApi;