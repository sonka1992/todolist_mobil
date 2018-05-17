package hu.somaszigeti.mytodolist.screens;

public interface BasePresenter<T extends Screen> {

    void attach(T screen);

    void detach();
}
