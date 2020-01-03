package main.java.com.mkudryavtsev.crud2.view.commands.accountCommands;

import main.java.com.mkudryavtsev.crud2.controller.AccountController;
import main.java.com.mkudryavtsev.crud2.dto.AccountDto;
import main.java.com.mkudryavtsev.crud2.view.commands.Command;
import main.java.com.mkudryavtsev.crud2.view.console.ConsoleUtils;

public class ChangeUsernameCommand implements Command {
    private AccountController accountController = AccountController.getAccountController();
    private ConsoleUtils consoleUtils = ConsoleUtils.getConsoleUtils();
    @Override
    public void execute() {
        System.out.println("Введите id аккаунта:");
        String idString = consoleUtils.getStringFromConsole();
        if (idString.equalsIgnoreCase("cancel")) {
            System.out.println("Операция отменена");
            return;
        }
        try {
            Long.parseLong(idString);
        }
        catch (NumberFormatException e) {
            System.out.println("id должен быть числом");
            return;
        }
        if (!accountController.isExist(Long.parseLong(idString))) {
            System.out.println("Аккаунта с таким id не существует");
            return;
        }
        System.out.println("Введите новое имя пользователя:");
        String newUserName = consoleUtils.getStringFromConsole();
        if (newUserName.equalsIgnoreCase("cancel")) {
            System.out.println("Операция отменена");
            return;
        }
        AccountDto accountDto = accountController.changeUserName(idString, newUserName);
        if(accountDto.getErrorMessage() != null){
            System.out.println(accountDto.getErrorMessage());
        }

    }
}
