import { isEmptyObject } from "../utils/validator";

const apiController = {
    get: async (url, query = {}) => {
        try {
            const queryString = getQueryString(query)

            const response = await fetch(url + queryString);
            return await response.json();
        } catch(err) {
            console.error(`${url}에서 에러 발생`, err);
            return err;
        }
    },
    post: async (url, param = {}, option = {}) => {
        try {
            const response = await fetch(url, {
                body: param,
                ...option,
                method: 'POST',
            });
            return await response.json();
        } catch(err) {
            console.error(`${url}에서 에러 발생`, err);
            return err;
        }
    },
}

function getQueryString(query) {
    if (isEmptyObject(query)) return '';

    let queryString = '?'
    for (const key in query) {
        queryString += key;
        queryString += '=';
        queryString += query[key];
    }

    return queryString;
}

export default apiController;