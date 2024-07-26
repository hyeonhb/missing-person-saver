import apiController from "./apiController";

const userApi = {
    getTestResponse: async () => {
        return await apiController.get('https://jsonplaceholder.typicode.com/posts')
    },
    /**
     * @param {object} request - name: String, contact: String
     * @returns {object} roomId: String, userId: Number
     */
    saveUserProfile: async param => {
        return {
            roomId: 1111,
            userId: 9999,
        };
    },
}

export default userApi;