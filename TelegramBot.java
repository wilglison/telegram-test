import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TelegramBot {
    private String botToken;
    private String chatId;

    public TelegramBot(String botToken, String chatId) {
        this.botToken = botToken;
        this.chatId = chatId;
    }

    public void sendMessage(String message) throws MalformedURLException, IOException {
        String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
        urlString = String.format(urlString, botToken, chatId, URLEncoder.encode(message, "UTF-8"));

        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();
        conn.getInputStream();
    }

    public static void main(String[] args) throws MalformedURLException, IOException {
        String telegramBotToken = "";
        String telegramChatId = "";
        try {
            File arquivo = new File("telegram.conf");
            Scanner scanner = new Scanner(arquivo);
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                if (linha.contains("TelegramBotToken:"))
                    telegramBotToken = linha.substring("TelegramBotToken:".length());
                if (linha.contains("TelegramChatId:"))
                    telegramChatId = linha.substring("TelegramChatId:".length());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.");
            e.printStackTrace();
        }
        if (args.length == 0) {
            System.err.println("Erro: falta argumento\nTente \'java TelegramBot \"msg\"\'");
            System.exit(1);
        } else {
            TelegramBot telegramBot = new TelegramBot(telegramBotToken, telegramChatId);
            telegramBot.sendMessage(args[0]);
            System.exit(0);
        }

    }
}
