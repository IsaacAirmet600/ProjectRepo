public class MenuOption {
    String command;
    String description;
    MenuOption(String command, String description){
        this.command = command;
        this.description = description;
    }
    public String getCommand(){
        return command;
    }
    public String getDescription(){
        return description;
    }
}
