export function formatResponseMsg(msg) {
  msg.text = msg.content;
  msg.isUser = msg.type !== 2;

  return msg;
}