import apiController from "./apiController";

const messageApi = {
    getTestResponse: async () => {
        return await apiController.get('https://jsonplaceholder.typicode.com/posts')
    },
    getMessageHistory: async param => {
        // param = {roomId: Number}
        return {
            messages: [
                {chatId: 1},
                {},
                {},
            ],
        }
    },
}

export default messageApi;