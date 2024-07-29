const ITEM_MAP = {
  USER_ID: 'userId',
  ROOM_ID: 'roomId',
  MISPER_KEY: 'misperKey'
};

const storage = {
  get: {
    misperKey: () => window.localStorage.getItem(ITEM_MAP.MISPER_KEY),
    userId: () => window.localStorage.getItem(ITEM_MAP.USER_ID),
    roomId: () => window.localStorage.getItem(ITEM_MAP.ROOM_ID),
  },
  set: {
    misperKey: value => window.localStorage.getItem(ITEM_MAP.MISPER_KEY, value),
    userId: value => window.localStorage.setItem(ITEM_MAP.USER_ID, value),
    roomId: value => window.localStorage.setItem(ITEM_MAP.ROOM_ID, value),
  },
};

export default storage;