package hu.somaszigeti.mytodolist.screens;

public class Presenter<T extends Screen> {

    protected T screen;

    public void attach(T screen) {
        this.screen = screen;
    }

    public void detach() {
        this.screen = null;
    }
}
