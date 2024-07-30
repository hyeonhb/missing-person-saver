import { isEmptyObject } from "../utils/validator";

const apiController = {
    get: async (url, query = {}) => {
        const queryString = getQueryString(query)

        return await fetchApi(url + queryString);
    },
    post: async (url, param = {}, option = {}) => {
        return await fetchApi(url, {
            body: param,
            ...option,
            method: 'POST',
        });
    },
}

async function fetchApi(url, option) {
    try {
        const response = await fetch(url, option);
        return await response.json();
    } catch(err) {
        console.error(`${url}에서 에러 발생`, err);
        return err;
    }
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