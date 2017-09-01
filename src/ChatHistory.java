import java.util.ArrayList;

class ChatHistory {
    private static ArrayList<Message> list = new ArrayList<>();

    static void add(Message message) {
        if (list.size() >= Settings.getSizeHistory()) {
            list.remove(0); // Удаление самого старого сообщения
        }
        list.add(message); // Добавление нового сообщения в историю
        new AddToMySQL(message);
    }

    static ArrayList<Message> getList() {
        return list;
    }
}
