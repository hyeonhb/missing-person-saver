import { isEmptyObject } from "../utils/validator";
import axiosInstance from "./axiosConfig";

const apiController = {
    get: async (url, query = {}) => {
        const queryString = getQueryString(query);

        return await axiosApi(url, queryString, null, 'get');
    },
    post: async (url, param = {}, body = {}) => {
        const queryString = getQueryString(param);

        return await axiosApi(url, queryString, body, 'post');
    },
}

// async function fetchApi(url, option) {
//     try {
//         const response = await fetch(url, option);
//         return await response.json();
//     } catch(err) {
//         console.error(`${url}에서 에러 발생`, err);
//         return err;
//     }
// }

async function axiosApi(url, param, body, restApi) {
    try {
        let response;
        if (restApi === 'get') {
            response = await axiosInstance.get(url + param);
        } else if (restApi === 'post') {
            response = await axiosInstance.post(url + param, body);
        }
        console.log(response.data);
        return response.data;
    } catch (error) {
        console.error(`${url}에서 에러 발생`, error);
        return error;
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