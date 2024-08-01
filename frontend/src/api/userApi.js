import apiController from "./apiController";

const userApi = {
    getTestResponse: async () => {
        return await apiController.get('https://jsonplaceholder.typicode.com/posts')
    },

    /**
     * @param {object} request - mpId: String, body: {name: String, contact: String}
     * @returns {object} roomId: String, userId: Number
     */
    getChatRoom: async (mpId, body) => {
        return await apiController.post('/users/login', {mpId: mpId}, {
            name: body.name,
            telno: body.contact
        } );
    },
}

export default userApi;