package ru.home.mysuperbot;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import org.springframework.mail.SimpleMailMessage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;

import static java.lang.Math.toIntExact;


public class MySuperBot extends TelegramLongPollingBot {
    public static final String TOKEN = "1321885924:AAGr6gSlt4GZOOmCvWNz31uxAV8cr4XM9dk"; // Токен бота Telegram.
    public static final String USERNAME = "GrayLokiBot"; // Username бота Telegram.
    public static final String B = "C:\\Users\\loki\\IdeaProjects\\BotTelergram\\src\\main\\java\\ru\\home\\mysuperbot\\loki.png";
    public static String text;
    public static final String D = "C:\\Users\\loki\\IdeaProjects\\BotTelergram\\src\\main\\java\\ru\\home\\mysuperbot\\Blindside.mp3";

    public MySuperBot() {
    }

    public MySuperBot(DefaultBotOptions options) {
        super(options);
    }

    public String getBotUsername() {
        return USERNAME;
    }

    public String getBotToken() {
        return TOKEN;
    }

    public String getText() {
        return text;
    }

    /* public void onUpdateReceived(Update update) {
         if(update.getMessage()!=null && update.getMessage().hasText()) {
             String chat_id = update.getMessage().getChatId().toString();
             try {
                 //System.out.println( update.getMessage().getForwardFrom());
                 //System.out.println(chat_id);
               //  System.out.println(  update.getMessage().getContact());
               //  execute(new SendMessage("-1001420188773", update.getMessage().getText()));
                 execute(new SendMessage("-1001420188773", update.getMessage().getFrom().getFirstName()));
                 //update.getMessage().setChat(new Chat(-1001420188773));
               //  execute(new SendMessage(chat_id, "Hi " + update.getMessage().toString()));
                // execute(new SendMessage(chat_id, "Алекс Дурачек? " + update.getMessage().getText()));
                 update.getMessage().getFrom().getId();
                 text=update.getMessage().getText();
            //     System.out.println(update.getMessage().getChat().getFirstName());
                // execute(new SendMessage())
                // sendEmail sendEmail=new sendEmail();
                // sendEmail.sendEmail(text);
                 //  execute(new SendPhoto(chat_id, new InputFile(new File(B))));

               //  execute(new SendAudio(1,chat_id,new InputFile(new File(D))));


             } catch (TelegramApiException e) {
                 e.printStackTrace();
             }
         }                }*/
    public void onUpdateReceived(Update update) {
        if (update.getMessage() != null && update.getMessage().hasText()) {
            String chat_id = update.getMessage().getChatId().toString();
            // Главные кнопки клавиатуры сначала!
            if (update.getMessage().getText().equals("Получить предсказание") == true) {
                try {
                    execute(new SendMessage(chat_id, "Предсказаний нет."));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (update.getMessage().getText().equals("Моя анкета") == true) {
                try {
                    execute(new SendMessage(chat_id, "Анкета не заполнена."));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (update.getMessage().getText().equals("Помощь") == true) {
                try {
                    execute(new SendMessage(chat_id, "Помощи не будет! Хо-хо-хо!"));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (update.getMessage().getText().equals("/start") == true) {
                // Отправляем клавиатуру с кнопками пользователю:
                try {
                    final ReplyKeyboardMarkup replyKeyboardMarkup = getMainMenuKeyboard();
                    SendMessage myMess = new SendMessage(chat_id, "Добро пожаловать в мой бот!");
                    myMess.setReplyMarkup(replyKeyboardMarkup);
                    execute(myMess);

                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            if (update.getMessage().getText().equals("/help") == true) {
                // Отправляем клавиатуру с кнопками пользователю:
                try {
                    execute(sendInlineKeyBoardMessage(update.getMessage().getChatId()));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } }else if (update.hasCallbackQuery()) {
                String call_data = update.getCallbackQuery().getData();
                long message_id = update.getCallbackQuery().getMessage().getMessageId();
                long chat_id2 = update.getCallbackQuery().getMessage().getChatId();

                /*if (call_data.equals("Row 1 Button 1")) { // Если нажата первая кнопка первого ряда "ТЫК".
                    try {
                        execute(new SendMessage(String.valueOf(chat_id2), "ТЫК!"));
                        System.out.println("ggg");
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }*/
                if (call_data.equals("Row 1 Button 1"))
                    { // Если нажата первая кнопка первого ряда "ТЫК".
                        System.out.println("fff");
                    }

                else if (call_data.equals("Row 2 Button 1")) { // Если нажата первая кнопка второго ряда "ТАК".
                    try {
                        execute(new SendMessage(String.valueOf(chat_id2), "ТАК!"));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                // Потом остальные команды!
            }
        }
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
        private ReplyKeyboardMarkup getMainMenuKeyboard () {
            final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            replyKeyboardMarkup.setSelective(true);
            replyKeyboardMarkup.setResizeKeyboard(true);
            replyKeyboardMarkup.setOneTimeKeyboard(false);
            List<KeyboardRow> keyboard = new ArrayList<>();
            KeyboardRow row1 = new KeyboardRow();
            KeyboardRow row2 = new KeyboardRow();
            KeyboardRow row3 = new KeyboardRow();
            row1.add(new KeyboardButton("Получить предсказание"));
            row2.add(new KeyboardButton("Моя анкета"));
            row3.add(new KeyboardButton("Помощь"));
            keyboard.add(row1);
            keyboard.add(row2);
            keyboard.add(row3);
            replyKeyboardMarkup.setKeyboard(keyboard);
            return replyKeyboardMarkup;
        }
    }

