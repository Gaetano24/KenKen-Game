package Model.Command;

public interface InvokerIF {

    void handle(CommandIF cmd);
    void redo();
    void undo();
    void reset();

}//InvokerIF
