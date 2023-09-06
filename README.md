What can do:

- SendMessage  
  org.telegram.telegrambots.meta.api.methods.send  
  for sending messages

```
SendMessage echoMessage = new SendMessage();
echoMessage.setChatId(message.getChatId());
echoMessage.setText("Your message:\n" + message.getText());
execute(echoMessage);
```

- SendChatAction  
  org.telegram.telegrambots.meta.api.methods.send.SendChatAction  
  imitation of what are you doing in telegram: typing, uploading, etc.

```
SendChatAction sendChatAction = new SendChatAction();
sendChatAction.setChatId(message.getChatId());
sendChatAction.setAction(ActionType.UPLOADPHOTO);
execute(sendChatAction);
```