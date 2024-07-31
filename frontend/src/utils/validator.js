export function isEmptyValue(val) {
    // 빈 값인지 체크하려는 value의 type이 가변적이라 하나로 단정짓지 못하거나 undefined나 null값이 넘어올 수도 있을 경우에 사용하기 위한 함수이기 때문에
    // type이나 undefined, null값 여부를 확신할 수 있다면 사용하는 쪽에서 type을 명시하기 위해 아래의 isEmptyString()나 isEmptyObject()를 사용하길 권장
  
    if (val === null || val === undefined) {
      return true;
    } else if (typeof val === 'string') {
      return isEmptyString(val);
    } else if (typeof val === 'object') {
      return isEmptyObject(val);
    }
  
    return false;
  }

export const isEmptyObject = object => {
    return Object.keys(object).length === 0;
}

export const isEmptyString = string => {
    return string.trim() === '';
}