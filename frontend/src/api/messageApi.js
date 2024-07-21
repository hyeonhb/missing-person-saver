import apiController from "./apiController";

const messageApi = {
    getTestResponse: async () => {
        return await apiController.get('https://jsonplaceholder.typicode.com/posts')
    }
}

export default messageApi;