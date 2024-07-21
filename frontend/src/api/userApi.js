import apiController from "./apiController";

const userApi = {
    getTestResponse: async () => {
        return await apiController.get('https://jsonplaceholder.typicode.com/posts')
    }
}

export default userApi;