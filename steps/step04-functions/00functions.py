def costs(update, context):
    string = update.message.text
    print(string)
    chat = update.effective_chat
    context.bot.send_message(chat_id=chat.id, text="Your cost is ")

class cht:
    id=100

class msg:
    text = "sometext"

class upd:
    message = msg()
    effective_chat = cht()
    
class bt:
    def send_message(this,chat_id,text):
        print(chat_id)
        print(text)

class ctxt:
    bot = bt()

el = upd()
print(el.message.text)
ct = ctxt()


costs(el,ct)