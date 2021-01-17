package ru.home.mysuperbot;

import java.io.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatMember;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultArticle;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultPhoto;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import org.springframework.mail.SimpleMailMessage;

import javax.swing.*;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Math.toIntExact;

public class Bot2 extends TelegramLongPollingBot {

    public static final String TOKEN = "1321885924:AAGr6gSlt4GZOOmCvWNz31uxAV8cr4XM9dk"; // Токен бота Telegram.
    public static final String USERNAME = "GrayLokiBot"; // Username бота Telegram.
    public static final String B = "C:\\Users\\loki\\IdeaProjects\\BotTelergram\\src\\main\\java\\ru\\home\\mysuperbot\\loki.png";
    public static String text;
    public static final String D = "C:\\Users\\loki\\IdeaProjects\\BotTelergram\\src\\main\\java\\ru\\home\\mysuperbot\\Blindside.mp3";
    public String loki;
    public int count;
    public HashSet<String> list = new HashSet<>();
    public int size;
    File file = new File("log.txt");

    // E-mail
    //@Autowired
    //private JavaMailSender emailSender; // Sender.

    public Bot2() {
    }

    public Bot2(DefaultBotOptions options) {
        super(options);
    }

    public String getBotUsername() {
        return USERNAME;
    }

    public String getBotToken() {
        return TOKEN;
    }

    public void onUpdateReceived(Update update) {
        File file2 = new File("listOfIdUsers.txt");
      //  InlineQueryResultPhoto inlineQueryResultArticle=new InlineQueryResultPhoto();
        //inlineQueryResultArticle.setPhotoUrl("https://w-dog.ru/wallpapers/9/19/429925869375672/lodka-priroda-pejzazh-ozero-osen-les-derevya-doma-listya-trava.jpg");
        try (Reader reader = new InputStreamReader(new FileInputStream(file2))) {
            char[] array = new char[128];
            int count = reader.read(array);
            StringBuilder result = new StringBuilder();
            while (count > 0) {
                result.append(new String(array, 0, count));
                count = reader.read(array);
            }
            String[] names = result.toString().split("\n");
            size = names.length;
            System.out.println(size);
            for (String test:names
                 ) {
                list.add(test);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (update.getMessage() != null && update.getMessage().hasText()) {
            String chat_id = update.getMessage().getChatId().toString();
            // update.getMessage().setChat(new Chat());
            // Главные кнопки клавиатуры сначала!
            if (update.getMessage().getText().equals("❎ Получить предсказание") == true) {
                try {
                    execute(new SendMessage(chat_id, "Предсказаний нет."));

                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (update.getMessage().getText().equals("❎ Моя анкета") == true) {
                try {
                    execute(new SendMessage(chat_id, "Анкета не заполнена."));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (update.getMessage().getText().equals("❎ Помощь") == true) {
                try {
                    execute(new SendMessage(chat_id, "Помощи не будет! Хо-хо-хо!"));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            // Потом остальные команды!
            else if (update.getMessage().getText().contains("ы") == true) {
                try {
                    execute(new SendMessage(chat_id, "Тык!!!"));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if (update.getMessage().getText().contains("/send") == true) {
                for (String loki:list
                     ) {
                    try {
                        String change=update.getMessage().getText();
                        change=change.replace("/send","");
                        execute(new SendMessage(loki.toString(), change));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                }
            }else if (update.getMessage().getText().equals("/start") == true) {
                // Отправляем клавиатуру с кнопками пользователю:
                try {
                    final ReplyKeyboardMarkup replyKeyboardMarkup = getMainMenuKeyboard();
                    SendMessage myMess = new SendMessage(chat_id, "Добро пожаловать в мой бот!");
                    myMess.setReplyMarkup(replyKeyboardMarkup);
                    execute(myMess);
                    //execute(sendInlineKeyBoardMessage(update.getMessage().getChatId()));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (update.getMessage().getText().equals("/help") == true) {
                // Отправляем клавиатуру с кнопками пользователю:
                try {
                    execute(sendInlineKeyBoardMessage(update.getMessage().getChatId()));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (update.getMessage().getText().contains("broadcast") == true) {
                // sendEmail("Текст пользователя: " + update.getMessage().getText());
                broadcast(update.getMessage().getText());
            } else if (update.getMessage().getText().contains("loki") == true) {
                try {
                    execute(new SendMessage("393219701", "Сообщение для " + update.getMessage().getText()));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (update.getMessage().getText().contains("group") == true) {
                try {
                    String s = update.getMessage().getText().replace("group", "");
                    execute(new SendMessage("-1001420188773", s));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else {
                try {

                    //  execute(new SendPhoto(chat_id, new InputFile("https://static0.colliderimages.com/wordpress/wp-content/uploads/thor-the-dark-world-loki.jpg")));
                    execute(new SendMessage(update.getMessage().getChatId().toString(), update.getMessage().getText()));
                    // System.out.println(update.getMessage().getText().toString());
                    try {
                        file.createNewFile();
                        file2.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    StringBuilder stringBuilder = new StringBuilder();
                    String name;
                    list.add(update.getMessage().getFrom().getId().toString());

                    System.out.println(list.size());
                    for (String list2 :
                            list) {
                        System.out.println(list2);

                    }
                    if (list.size() != size) {
                        try (OutputStream outputStream2 = new FileOutputStream(file2, false)) {
                            for (String name2 : list
                            ) {
                                name2 += "\n";
                                outputStream2.write(name2.getBytes());
                            }
                            size = list.size();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

                    Date date = new Date();
                    try (OutputStream outputStream = new FileOutputStream(file, true)) {
                        //stringBuilder.append(update.getMessage().getText().toString());
                        name = "-------------------------------------\n"
                                + "Date: "
                                +date.toString()
                                + "\n"
                                + "Message send: "
                                + update.getMessage().getFrom().getFirstName()
                                + " "
                                + update.getMessage().getFrom().getLastName()
                                + "(id: " + update.getMessage().getFrom().getId() + ")"
                                + "\n"
                                + "Group Id: "
                                + update.getMessage().getChatId()
                                + "\n"
                                + "Text: "
                                + update.getMessage().getText().toString() + "\n" +
                                "-------------------------------------\n\n\n\n";

                        outputStream.write(name.getBytes());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //  loki=update.getMessage().getText().toString();
                    //Chat chat=new Chat();

                    final int i;

                    //  System.out.println(update.getMessage().getMessageId());
                    //    System.out.println(update.getMessage().getFrom().getId());

                    //  chat.getMessage().getMessageId();
                    //   System.out.println(update.getMessage().getChat().getAllMembersAreAdministrators());
                    //     System.out.println(update.getMessage().getText()+ count++);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        } else if (update.hasCallbackQuery()) {
            String call_data = update.getCallbackQuery().getData();
            long message_id = update.getCallbackQuery().getMessage().getMessageId();
            long chat_id = update.getCallbackQuery().getMessage().getChatId();

            if (call_data.equals("Row 1 Button 1")) { // Если нажата первая кнопка первого ряда "ТЫК".
                try {
                    execute(new SendMessage(String.valueOf(chat_id), "ТЫК!"));
                    //execute(new SendMessage(String.valueOf(chat_id), "ТЫК!"));
                    try (OutputStream outputStream = new FileOutputStream(file, false)) {
                        //stringBuilder.append(update.getMessage().getText().toString());
                        String name="";
                        outputStream.write(name.getBytes());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    // Убираем клавиатуру:
                    /*
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(String.valueOf(chat_id));
                    sendMessage.setText("Клавиатура:");
                    sendMessage.setReplyMarkup(new ReplyKeyboardRemove());
                    execute(sendMessage);
                    */

                    /*
                    Integer messageId = Integer.parseInt(String.valueOf(chat_id));
                    DeleteMessage deleteMessage = new DeleteMessage(String.valueOf(chat_id), messageId);
                    execute(deleteMessage);
                     */

                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (call_data.equals("Row 2 Button 1")) { // Если нажата первая кнопка первого ряда "ТЫК".
                try {
                    execute(new SendMessage(String.valueOf(chat_id), "ТАК!"));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Основная клавиатура бота!
    private ReplyKeyboardMarkup getMainMenuKeyboard() {
        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();
        row1.add(new KeyboardButton("❎ Получить предсказание"));
        row2.add(new KeyboardButton("❎ Моя анкета"));
        row3.add(new KeyboardButton("❎ Помощь"));
        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }

    // Клавиатуара и кнопки на ней:
    public static SendMessage sendInlineKeyBoardMessage(long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(); // Клавиатура
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton(); // Кнопка №1
        inlineKeyboardButton.setText("Тык"); // Текст кнопки №1
        inlineKeyboardButton.setCallbackData("Row 1 Button 1");
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton(); // Кнопка №2
        inlineKeyboardButton2.setText("Так"); // Текст кнопки №1
        inlineKeyboardButton2.setCallbackData("Row 2 Button 1");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>(); // Ряд кнопок №1
        keyboardButtonsRow1.add(inlineKeyboardButton);
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>(); // Ряд кнопок №2
        keyboardButtonsRow2.add(inlineKeyboardButton2);
        // Разметка клавиатуры (несколько рядов кнопок):
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        // Установим все кнопки в разметку клавиатуры.
        inlineKeyboardMarkup.setKeyboard(rowList);
        // Отправляем эту клавиатуры с кнопками пользователю:
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText("Клавиатура:");
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }

    public static void broadcast(String text) {

        //List<User> users = userService.findAllUsers();
        //users.forEach(user -> new SendMessage(user.getChatId(), text));
    }
}
