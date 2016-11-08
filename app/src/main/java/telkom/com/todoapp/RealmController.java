package telkom.com.todoapp;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import io.realm.Realm;
import io.realm.RealmResults;
import telkom.com.todoapp.Data.Todo;

/**
 * Created by sukri on 08/11/16.
 */

public class RealmController {

    private static RealmController instance;
    private final Realm realm;

    public RealmController(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment) {

        if (instance == null) {
            instance = new RealmController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static RealmController with(Activity activity) {

        if (instance == null) {
            instance = new RealmController(activity.getApplication());
        }
        return instance;
    }

    public static RealmController with(Application application) {

        if (instance == null) {
            instance = new RealmController(application);
        }
        return instance;
    }

    public static RealmController getInstance() {

        return instance;
    }

    public Realm getRealm() {

        return realm;
    }

    //Refresh the realm istance
    public void refresh() {

        realm.refresh();
    }

    //clear all objects from Book.class
    public void clearAll() {

        realm.beginTransaction();
        realm.clear(Todo.class);
        realm.commitTransaction();
    }

    //find all objects in the Book.class
    public RealmResults<Todo> getTodo() {

        return realm.where(Todo.class).findAll();
    }

    //query a single item with the given id
    public Todo getTodo(String id) {

        return realm.where(Todo.class).equalTo("id", id).findFirst();
    }

    //check if Book.class is empty
    public boolean hasTodo() {

        return !realm.allObjects(Todo.class).isEmpty();
    }

    //query example
    public RealmResults<Todo> queryTodo(String cari) {

        return realm.where(Todo.class)
                .contains("title", cari)
                .or()
                .contains("description", cari)
                .findAll();

    }
}
