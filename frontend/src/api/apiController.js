
import { isEmptyObject } from "../utils/validator";

const apiController = {
    get: async (url, query = {}) => {
        const queryString = getQueryString(query)

        const response = await fetch(url + queryString);
        return await response.json();
    },
    post: async (url, param = {}, option = {}) => {
        const response = await fetch(url, {
            body: param,
            ...option,
            method: 'POST',
        });
        return await response.json();
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