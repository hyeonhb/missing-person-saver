import apiController from "./apiController";

const messageApi = {
    getTestResponse: async () => {
        return await apiController.get('https://jsonplaceholder.typicode.com/posts')
    },
    /**
     * @param {object} request - roomId: Number
     * @returns {object} messages: Array[Object] -> {chatId: Number, content: String, createdDate: Datetime, type: Number}
     */
    getMessageHistory: async (roomId) => {
        return await apiController.get('/chat-message/get-messages', {roomId: roomId});
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

    saveMessages: async (roomId, body) => {
        return await apiController.post('/chat-message/save', {roomId: roomId}, {
            content: body.question,
            type: body.type,
            detailType: body.detailType
        } );
    }
}

export default messageApi;