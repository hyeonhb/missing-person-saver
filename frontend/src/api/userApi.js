import apiController from "./apiController";

const userApi = {
    getTestResponse: async () => {
        return await apiController.get('https://jsonplaceholder.typicode.com/posts')
    },
    saveUserProfile: async param => {
        // param = {name: String, contact: String}
        return {
            roomId: 1111,
            userId: 9999,
        };
    },
}

export default userApi;