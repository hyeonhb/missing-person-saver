import apiController from "./apiController";
import storage from "../utils/storage";
storage.get.roomId()

const messageApi = {

    getTestResponse: async () => {
        return await apiController.get('https://jsonplaceholder.typicode.com/posts')
    },
    /**
     * @param {object} request - roomId: Number
     * @returns {object} messages: Array[Object] -> {chatId: Number, content: String, createdDate: Datetime, type: Number}
     */
    getMessageHistory: async () => {
        return await apiController.get('/chat-message/get-messages', {roomId: storage.get.roomId()});
    },

    /**
     * @param {object} request - roomId: Number, question: String
     * @returns {object} chatId: Number, content: String, createdDate: Datetime, type: Number
     */
    saveMessages: async (body) => {
        return await apiController.post('/chat-message/save', {roomId: storage.get.roomId()}, {
            content: body.question,
            type: body.type,
            detailType: body.detailType
        } );
    }
}

export default messageApi;