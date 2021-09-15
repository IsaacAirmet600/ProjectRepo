import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    String header;
    int optionCount;
    ArrayList<MenuOption> options = new ArrayList<>();

    public Menu(String header){
        this.header = header;
        optionCount = 0;
    }
    public void addOption(String command, String description){
        if(command != null && !command.isEmpty()){
            options.add(new MenuOption(command, description));
            optionCount += 1;
        }
    }
    private boolean isValidCommand(String command){
        boolean isValid = false;
        if(command.equals("X")){
            isValid = true;
        }
        else{
            for(int i = 0; i < optionCount; i++){
                if(command.equals(getOption(i).getCommand())){
                    isValid = true;
                    break;
                }
            }
        }
        return isValid;
    }
    public MenuOption getOption(int optionIndex){
        MenuOption option = null;
        if (optionIndex >= 0 && optionIndex < optionCount){
            option = options.get(optionIndex);
        }
        return option;
    }
    public String getHeader(){
        return header;
    }
    public int getOptionCount(){
        return optionCount;
    }
    public String show(){
        String command = "";
        boolean keepGoing = true;
        while(keepGoing){
            String optionList = "";
            System.out.println();
            System.out.println(getHeader() + " menu: ");

            for(int i = 0; i < getOptionCount(); i++){
                MenuOption option = getOption(i);
                if(option != null){
                    System.out.println(option.getCommand() + " - " + option.getDescription());
                    optionList += option.getCommand() + ",";
                }
            }
            System.out.println("X - Exit");
            optionList += "X";
            System.out.println("\nEnter a " + getHeader() + " command " + "(" + optionList + ")");
            Scanner newInput = new Scanner(System.in);
            command = newInput.next();
            keepGoing = !isValidCommand(command);
        }
        return command;
    }
}
