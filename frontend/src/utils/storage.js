const ITEM_MAP = {
  USER_ID: 'userId',
  ROOM_ID: 'roomId',
  MISPER_KEY: 'misperKey',
  QUESTION_TYPE: 'questionType',
};

const storage = {
  get: {
    misperKey: () => window.localStorage.getItem(ITEM_MAP.MISPER_KEY),
    userId: () => window.localStorage.getItem(ITEM_MAP.USER_ID),
    roomId: () => window.localStorage.getItem(ITEM_MAP.ROOM_ID),
    questionType: () => window.localStorage.getItem(ITEM_MAP.QUESTION_TYPE),
  },
  set: {
    misperKey: value => window.localStorage.setItem(ITEM_MAP.MISPER_KEY, value),
    userId: value => window.localStorage.setItem(ITEM_MAP.USER_ID, value),
    roomId: value => window.localStorage.setItem(ITEM_MAP.ROOM_ID, value),
    questionType: value => window.localStorage.setItem(ITEM_MAP.QUESTION_TYPE, value),
  },
};

export default storage;